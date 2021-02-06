package me.lojosho.rapidzlootingcore.lootables;

import java.util.Random;

public class LootablesPistols {

    public String PistolLootable() {
        int RandomNumber = RandomPistolInt();
        if (RandomNumber >= 1 && RandomNumber <= 25) { return "10mmpistol"; }
        if (RandomNumber >= 26 && RandomNumber <= 50) { return "deagle"; }
        if (RandomNumber >= 51 && RandomNumber <= 75) { return "mac10"; }
        if (RandomNumber >= 76 && RandomNumber <= 100) { return "magnum"; }
        // tried putting this as null, caused it to null and not give the item ~ LoJo
        else return "magnum";
    }

    private int RandomPistolInt() {
        Random rand = new Random();
        int minRange = 1, maxRange = 100;
        return rand.nextInt(maxRange - minRange) + minRange;
    }
}
