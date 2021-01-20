package me.lojosho.rapidzlootingcore.lootables;

import java.util.Random;

public class LootablesPistols {

    public String PistolLootable() {
        int RandomNumber = RandomPistolInt();
        if (RandomNumber >= 1 && RandomNumber <= 25) { return "10mmpistol"; }
        if (RandomNumber >= 26 && RandomNumber <= 50) { return "deagle"; }
        else return null;
    }

    private int RandomPistolInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 50;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
}
