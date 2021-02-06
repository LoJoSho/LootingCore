package me.lojosho.rapidzlootingcore.lootables;

import java.util.Random;

public class LootablesAmmo {

    public String AmmoLootable() {
        int RandomNumber = RandomInt();
        if (RandomNumber >= 1 && RandomNumber <= 20) { return "9mm"; }
        if (RandomNumber >= 21 && RandomNumber <= 40) { return "40mm"; }
        if (RandomNumber >= 41 && RandomNumber <= 60) { return "50bmg"; }
        if (RandomNumber >= 61 && RandomNumber <= 70) { return "556"; }
        if (RandomNumber >= 71 && RandomNumber <= 89) { return "762"; }
        if (RandomNumber >= 90 && RandomNumber <= 100) { return "musketball"; }
        // tried putting this as null, caused it to null and not give the item ~ LoJo
        else return "9mm";
    }

    private int RandomInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 100;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
    public int RandomAmmoInteger() {
        Random rand = new Random();
        int minRange = 1, maxRange = 10;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
}
