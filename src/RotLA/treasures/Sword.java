package RotLA.treasures;

public class Sword extends Treasure {

    public Sword(){
        this.treasureType = this.getClass().getSimpleName();
    }

    @Override
    public int[] treasureEffectOnCombatDiceRolls(int advRoll, int creRoll){
        int[] diceRolls = new int[2];
        diceRolls[0] = advRoll + 1;
        diceRolls[1] = creRoll;
        return diceRolls;
    }
}
