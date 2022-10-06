package RotLA.treasures;

public class Gem extends Treasure {

    public Gem() {
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public int[] treasureEffectOnCombatDiceRolls(int advRoll, int creRoll) {
        int[] diceRolls = new int[2];
        diceRolls[0] = advRoll;
        diceRolls[1] = creRoll + 1;
        return diceRolls;
    }
}
