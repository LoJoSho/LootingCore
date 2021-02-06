package me.lojosho.rapidzlootingcore.lootables;

import org.bukkit.Material;

import java.util.Random;

public class LootablesArmor {

    public Material ArmorLootable() {
        int RandomNumber = RandomInt();
        if (RandomNumber >= 1 && RandomNumber <= 50) {
            if (RandomNumber >= 1 && RandomNumber <= 25) { return Material.LEATHER_HELMET; }
            if (RandomNumber >= 26 && RandomNumber <= 50) { return Material.LEATHER_CHESTPLATE; }
            if (RandomNumber >= 51 && RandomNumber <= 75) { return Material.LEATHER_LEGGINGS; }
            if (RandomNumber >= 76 && RandomNumber <= 90) { return Material.LEATHER_BOOTS; }
        }
        if (RandomNumber >= 51 && RandomNumber <= 70) {
            if (RandomNumber >= 1 && RandomNumber <= 25) { return Material.CHAINMAIL_HELMET; }
            if (RandomNumber >= 26 && RandomNumber <= 50) { return Material.CHAINMAIL_CHESTPLATE; }
            if (RandomNumber >= 51 && RandomNumber <= 75) { return Material.CHAINMAIL_LEGGINGS; }
            if (RandomNumber >= 76 && RandomNumber <= 90) { return Material.CHAINMAIL_BOOTS; }
        }
        if (RandomNumber >= 51 && RandomNumber <= 75) {
            if (RandomNumber >= 1 && RandomNumber <= 25) { return Material.IRON_HELMET; }
            if (RandomNumber >= 26 && RandomNumber <= 50) { return Material.IRON_CHESTPLATE; }
            if (RandomNumber >= 51 && RandomNumber <= 75) { return Material.IRON_LEGGINGS; }
            if (RandomNumber >= 76 && RandomNumber <= 90) { return Material.IRON_BOOTS; }
        }
        if (RandomNumber >= 76 && RandomNumber <= 100) {
            return Material.LEATHER_BOOTS;
        }
        else return null;
    }

    private int RandomInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 50;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
    public int RandomArmorInteger() {
        Random rand = new Random();
        int minRange = 1, maxRange = 10;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
}

