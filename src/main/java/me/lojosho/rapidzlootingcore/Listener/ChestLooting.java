package me.lojosho.rapidzlootingcore.Listener;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import me.lojosho.rapidzlootingcore.RapidzLootingCore;
import me.lojosho.rapidzlootingcore.lootables.LootablesAmmo;
import me.lojosho.rapidzlootingcore.lootables.LootablesArmor;
import me.lojosho.rapidzlootingcore.lootables.LootablesPistols;
import me.lojosho.rapidzlootingcore.lootables.LootablesRifle;
import me.zombie_striker.customitemmanager.CustomBaseObject;
import me.zombie_striker.customitemmanager.CustomItemManager;
import me.zombie_striker.qg.ammo.Ammo;
import me.zombie_striker.qg.api.QualityArmory;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;

public class ChestLooting implements Listener {
    int cooldownSeconds = 300;

    private final RapidzLootingCore plugin;
    LootablesPistols PistolLoot = new LootablesPistols();
    LootablesAmmo AmmoLoot = new LootablesAmmo();
    LootablesArmor ArmorLoot = new LootablesArmor();
    LootablesRifle RifleLoot = new LootablesRifle();

    public ChestLooting(RapidzLootingCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChestOpen(PlayerInteractEvent event) {

        String player = event.getPlayer().getName();
        Player player1 = event.getPlayer();
        int intcooldown = 300000;
        double GivenMoney = RandomMoney();
        Long defaultLong = 0L;
        Inventory Inv = event.getPlayer().getInventory();
        int RandomInt = RandomInt();
        String AmmoGotten = AmmoLoot.AmmoLootable();
        String PistolGotten = PistolLoot.PistolLootable();
        String RifleGotten = RifleLoot.RifleLootable();
        Material ArmorGotten = ArmorLoot.ArmorLootable();


        // tried condensing this... kept shooting errors all over the place.
        if (event.hasBlock()) {
            if (event.getClickedBlock().getType() == Material.CHEST) {
                if (System.currentTimeMillis() - plugin.cooldowns.getOrDefault(event.getClickedBlock().getLocation(), defaultLong) > intcooldown) {
                    plugin.cooldowns.put(event.getClickedBlock().getLocation(), System.currentTimeMillis());
                    EconomyResponse r = plugin.getEconomy().bankDeposit(player, GivenMoney);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten &a&l$" + GivenMoney));
                    event.setCancelled(!event.getPlayer().isOp() || !event.getPlayer().isSneaking());
                    // Holograms
                    Location hololoc = event.getClickedBlock().getLocation();
                    hololoc.setY(hololoc.getY() + 2);
                    hololoc.setZ(hololoc.getZ() + 0.5);
                    hololoc.setX(hololoc.getX() + 0.5);
                    if (CMI.getInstance().getHologramManager().getByLoc(hololoc) == null) {
                        int seconds = (int) (System.currentTimeMillis() - plugin.cooldowns.get(event.getClickedBlock().getLocation())) / 1000; // Coverts to seconds
                        int SecondsLeft = cooldownSeconds - seconds; // Makes it so it countsdown rather than 1/10,2/10,3/10 etc.
                        CMIHologram holo = new CMIHologram("Chest - " + hololoc, hololoc);
                        String SecondsString = secondsToString(SecondsLeft);
                        holo.setLines(Arrays.asList("&7This chest has been looted!", "&7Time left: &6" + SecondsString));
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        holo.update();
                        Location Chestloc = event.getClickedBlock().getLocation();
                        HologramRepeater(hololoc, Chestloc);
                    }
                    //
                    ItemStack temp;
                    CustomBaseObject g = QualityArmory.getCustomItemByName(AmmoGotten);
                    int amount = AmmoLoot.RandomAmmoInteger();
                    QualityArmory.addAmmoToInventory(event.getPlayer(), (Ammo) g, amount);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten &6" + amount + " &7of &a" + AmmoGotten + " &7ammo"));
                    // Suggested: 8% Pistol, 2% Rifle,
                    plugin.getLogger().info(player + " has rolled a " + RandomInt);
                    if (RandomInt >= 1 && RandomInt <= 8) {
                        CustomBaseObject gp = QualityArmory.getCustomItemByName(PistolGotten);
                        temp = CustomItemManager.getItemType("gun").getItem(gp.getItemData().getMat(), gp.getItemData().getData(), gp.getItemData().getVariant());
                        temp.setAmount(1);
                        Inv.addItem(temp);
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten a &6&l" + PistolGotten + " &7pistol"));
                        plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', player + " have gotten a " + PistolGotten + " pistol"));
                    }
                    // rifles
                    if (RandomInt >= 9 && RandomInt <= 10) {
                        CustomBaseObject gr = QualityArmory.getCustomItemByName(RifleGotten);
                        temp = CustomItemManager.getItemType("gun").getItem(gr.getItemData().getMat(), gr.getItemData().getData(), gr.getItemData().getVariant());
                        temp.setAmount(1);
                        Inv.addItem(temp);
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten a &6&l" + RifleGotten + " &7Rifles"));
                        plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', player + " have gotten a " + RifleGotten + " rifle"));
                    }
                    //if (RandomInt >= 11 && RandomInt <= 12) {
                    //    event.getPlayer().getInventory().addItem(new ItemStack(ArmorGotten)); }
                } else if (System.currentTimeMillis() - plugin.cooldowns.get(event.getClickedBlock().getLocation()) < intcooldown) {
                    int seconds = (int) (System.currentTimeMillis() - plugin.cooldowns.get(event.getClickedBlock().getLocation())) / 1000; // Coverts to seconds
                    int SecondsLeft = cooldownSeconds - seconds; // Makes it so it countsdown rather than 1/10,2/10,3/10 etc.
                    String SecondsString = secondsToString(SecondsLeft);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9That chest still on cooldown! " + SecondsString + " left"));
                    event.setCancelled(true);
                }
            }
        }
    }

    private double RandomMoney() {
        Random rand = new Random();
        int minRange = 5, maxRange = 20;
        return rand.nextInt(maxRange - minRange) + minRange;
    }

    private int RandomInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 100;
        return rand.nextInt(maxRange - minRange) + minRange;
    }

    public void HologramRepeater(Location hololoc, Location Chestloc) {
        new BukkitRunnable() {
            @Override
            public void run() {
                int seconds2 = (int) (System.currentTimeMillis() - plugin.cooldowns.get(Chestloc)) / 1000; // Coverts to seconds
                int SecondsLeft2 = cooldownSeconds - seconds2; // Makes it so it countsdown rather than 1/10,2/10,3/10 etc.
                String SecondsString = secondsToString(SecondsLeft2);
                if (CMI.getInstance().getHologramManager().getByLoc(hololoc) != null) {
                    CMIHologram holo = CMI.getInstance().getHologramManager().getByLoc(hololoc);
                    if (SecondsLeft2 >= 1) {
                        holo.setLines(Arrays.asList("&7This chest has been looted!", "&7Time left: &6" + SecondsString));
                        CMI.getInstance().getHologramManager().addHologram(holo);
                        holo.update();
                    } else {
                        holo.remove();
                        this.cancel();
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 10);
    }

    private String secondsToString(int SecondsLeft2) {
        return String.format("%02d:%02d", SecondsLeft2 / 60, SecondsLeft2 % 60);
    }
}
