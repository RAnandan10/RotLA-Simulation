package RotLA.treasures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

    @Test
    void treasureEffectOnCombatDiceRolls() {
        System.out.println("Test to check if Sword treasure increases a adventurer dice roll value by 1");
        Treasure sword = new Sword();
        int advRoll = 10, creRoll = 11;
        int[] diceRollsExpected = new int[2];
        diceRollsExpected[0] = advRoll+1;
        diceRollsExpected[1] = creRoll;
        assertArrayEquals(diceRollsExpected,sword.treasureEffectOnCombatDiceRolls(advRoll,creRoll));
    }
}