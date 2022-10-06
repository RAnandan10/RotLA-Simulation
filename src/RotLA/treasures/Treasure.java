package RotLA.treasures;

import RotLA.adventurers.Adventurer;

public class Treasure {
    public String treasureType;
    public int[] treasureEffectOnCombatDiceRolls(int advRoll, int creRoll){
        int[] diceRolls = new int[2];
        diceRolls[0] = advRoll;
        diceRolls[1] = creRoll;
        return diceRolls;
    }

    public void treasureEffect(Adventurer adv){
    }
}



