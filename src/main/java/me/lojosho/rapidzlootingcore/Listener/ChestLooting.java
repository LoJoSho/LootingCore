package me.lojosho.rapidzlootingcore.Listener;

import me.lojosho.rapidzlootingcore.RapidzLootingCore;
import me.lojosho.rapidzlootingcore.lootables.LootablesAmmo;
import me.lojosho.rapidzlootingcore.lootables.LootablesPistols;
import me.zombie_striker.customitemmanager.CustomBaseObject;
import me.zombie_striker.customitemmanager.CustomItemManager;
import me.zombie_striker.qg.ammo.Ammo;
import me.zombie_striker.qg.api.QualityArmory;
import me.zombie_striker.qg.guns.Gun;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ChestLooting implements Listener {

    private final RapidzLootingCore plugin;
    LootablesPistols PistolLoot = new LootablesPistols();
    LootablesAmmo AmmoLoot = new LootablesAmmo();

    public ChestLooting(RapidzLootingCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChestOpen(PlayerInteractEvent event) {

        String player = event.getPlayer().getName();
        int intcooldown = 10000;
        double GivenMoney = RandomMoney();
        Long defaultLong = 0L;
        Inventory Inv = event.getPlayer().getInventory();
        int randompistol = RandomInt();
        String AmmoGotten = AmmoLoot.AmmoLootable();
        String PistolGotten = PistolLoot.PistolLootable();

        // tried condensing this... kept shooting errors all over the place.
        if (event.hasBlock()) {
            if (event.getClickedBlock().getType() == Material.CHEST)
                if (System.currentTimeMillis() - plugin.cooldowns.getOrDefault(event.getClickedBlock().getLocation(), defaultLong) > 10000) {
                    plugin.cooldowns.put(event.getClickedBlock().getLocation(), System.currentTimeMillis());
                    EconomyResponse r = plugin.getEconomy().bankDeposit(player, GivenMoney);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten &a&l$" + GivenMoney));
                    event.setCancelled(true);
                    ItemStack temp;
                    CustomBaseObject g = QualityArmory.getCustomItemByName(AmmoGotten);
                    int amount = AmmoLoot.RandomAmmoInteger();
                    QualityArmory.addAmmoToInventory(event.getPlayer(), (Ammo) g, amount);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten &6" + amount + " &7of &a" + AmmoGotten));
                    if (randompistol >= 1 && randompistol <= 25) {
                        CustomBaseObject gp = QualityArmory.getCustomItemByName(PistolGotten);
                        temp = CustomItemManager.getItemType("gun").getItem(gp.getItemData().getMat(), gp.getItemData().getData(), gp.getItemData().getVariant());
                        temp.setAmount(gp.getCraftingReturn());
                        Inv.addItem(temp);
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten &6&l" + PistolGotten));
                    }
                } else if (System.currentTimeMillis() - plugin.cooldowns.get(event.getClickedBlock().getLocation()) < intcooldown) {
                    int seconds = (int) (System.currentTimeMillis() - plugin.cooldowns.get(event.getClickedBlock().getLocation())) / 1000; // Coverts to seconds
                    int SecondsLeft = 10 - seconds; // Makes it so it countsdown rather than 1/10,2/10,3/10 etc.
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9That chest still on cooldown! (" + SecondsLeft + " Seconds left)"));
                    event.setCancelled(true);
                }
        }
    }


    private double RandomMoney() {
        Random rand = new Random();
        int minRange = 5, maxRange= 45;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
    private int RandomInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 100;
        return rand.nextInt(maxRange - minRange) + minRange;
    }

}
