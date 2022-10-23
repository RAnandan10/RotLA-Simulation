package RotLA.treasures;

import RotLA.adventurers.Adventurer;

public class Potion extends Treasure {

    public Potion() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public void treasureEffect(Adventurer adv) {
        adv.maxDamage++;
    }
}
