package RotLA.treasures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    @Test
    void treasureEffectOnCombatDiceRolls() {
        Treasure armour = new Armor();
        int advRoll = 10, creRoll = 11;
        int[] diceRollsExpected = new int[2];
        diceRollsExpected[0] = advRoll;
        diceRollsExpected[1] = creRoll-1;
        assertArrayEquals(diceRollsExpected,armour.treasureEffectOnCombatDiceRolls(advRoll,creRoll));
    }
}