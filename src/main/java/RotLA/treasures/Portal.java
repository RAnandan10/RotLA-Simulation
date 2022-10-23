package RotLA.treasures;

import RotLA.adventurers.Adventurer;

public class Portal extends Treasure {

    public Portal() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public void treasureEffect(Adventurer adv) {
        //extra move for adventurer
    }
}
