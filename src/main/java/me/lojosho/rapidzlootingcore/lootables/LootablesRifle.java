package me.lojosho.rapidzlootingcore.lootables;

import java.util.Random;

public class LootablesRifle {

    public String RifleLootable() {
        int RandomNumber = RandomInt();
        if (RandomNumber >= 1 && RandomNumber <= 25) { return "ak47"; }
        if (RandomNumber >= 26 && RandomNumber <= 50) { return "vz58"; }
        if (RandomNumber >= 51 && RandomNumber <= 75) { return "musket"; }
        if (RandomNumber >= 76 && RandomNumber <= 90) { return "pkp"; }
        if (RandomNumber >= 91 && RandomNumber <= 92) { return "awp"; }
        if (RandomNumber >= 93 && RandomNumber <= 95) { return "m1garand"; }
        if (RandomNumber >= 96 && RandomNumber <= 100) { return "sks"; }
        // tried putting this as null, caused it to null and not give the item ~ LoJo
        else return "ak47";
    }

    private int RandomInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 100;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
    public int RandomRifleInteger() {
        Random rand = new Random();
        int minRange = 1, maxRange = 10;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
}
