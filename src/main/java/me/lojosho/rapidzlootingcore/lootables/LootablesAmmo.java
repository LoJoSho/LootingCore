package me.lojosho.rapidzlootingcore.lootables;

import java.util.Random;

public class LootablesAmmo {

    public String AmmoLootable() {
        int RandomNumber = RandomInt();
        if (RandomNumber >= 1 && RandomNumber <= 25) { return "9mm"; }
        if (RandomNumber >= 26 && RandomNumber <= 50) { return "40mm"; }
        if (RandomNumber >= 51 && RandomNumber <= 75) { return "50bgm"; }
        if (RandomNumber >= 76 && RandomNumber <= 90) { return "flamerfuel"; }
        else return null;
    }

    private int RandomInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 50;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
    public int RandomAmmoInteger() {
        Random rand = new Random();
        int minRange = 1, maxRange = 10;
        return rand.nextInt(maxRange - minRange) + minRange;
    }

}
