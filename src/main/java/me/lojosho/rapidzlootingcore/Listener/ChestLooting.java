package me.lojosho.rapidzlootingcore.Listener;

import me.lojosho.rapidzlootingcore.RapidzLootingCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;
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
        Location loc = event.getClickedBlock().getLocation();

        double GivenMoney = RandomMoney();
        if (event.hasBlock() && !loc.equals(null)) {
            if (event.getClickedBlock().getType().equals(Material.CHEST) && System.currentTimeMillis() - plugin.cooldowns.get(loc) > 1000) {
                plugin.cooldowns.put(loc, System.currentTimeMillis());
                EconomyResponse r = plugin.getEconomy().depositPlayer(player, GivenMoney);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have gotten &a&l$" + GivenMoney));
                event.setCancelled(true);
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
}
