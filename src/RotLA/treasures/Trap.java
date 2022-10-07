package RotLA.treasures;

import RotLA.adventurers.Adventurer;

public class Trap extends Treasure {

    public Trap() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public void treasureEffect(Adventurer adv) {
        adv.updateFightOutcome();
    }
}
