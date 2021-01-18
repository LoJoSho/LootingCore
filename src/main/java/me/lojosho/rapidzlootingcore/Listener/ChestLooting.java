package me.lojosho.rapidzlootingcore.Listener;

import me.lojosho.rapidzlootingcore.RapidzLootingCore;
import me.zombie_striker.qg.api.QualityArmory;
import me.zombie_striker.qg.guns.Gun;
import me.zombie_striker.qg.guns.reloaders.M1GarandReloader;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ChestLooting implements Listener {

    private final RapidzLootingCore plugin;

    public ChestLooting(RapidzLootingCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChestOpen(PlayerInteractEvent event) {

        String player = event.getPlayer().getName();
        int intcooldown = 10000;
        double GivenMoney = RandomMoney();
        Location loc = event.getClickedBlock().getLocation();
        Long defaultLong = 0L;
        // tried condensing this... kept shooting errors all over the place.
        if (event.hasBlock()) {
            if (event.getClickedBlock().getType() == Material.CHEST)
                if (System.currentTimeMillis() - plugin.cooldowns.getOrDefault(loc, defaultLong) > 10000) {
                    plugin.cooldowns.put(loc, System.currentTimeMillis());
                    EconomyResponse r = plugin.getEconomy().bankDeposit(player, GivenMoney);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten &a&l$" + GivenMoney));
                    event.setCancelled(true);
                    if (RandomInt() == 1) {
                        event.getPlayer().getInventory().addItem(new ItemStack(me.zombie_striker.qg.api.QualityArmory.getCustomItemAsItemStack("10mmpistol")));
                    }
                    if (RandomInt() == 2) {
                        event.getPlayer().getInventory().addItem(new ItemStack(me.zombie_striker.qg.api.QualityArmory.getCustomItemAsItemStack("deagle")));
                    }
                } else if (System.currentTimeMillis() - plugin.cooldowns.get(loc) < intcooldown) {
                    int seconds = (int) (System.currentTimeMillis() - plugin.cooldowns.get(loc)) / 1000; // Coverts to seconds
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
    private double RandomInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 5;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
}
