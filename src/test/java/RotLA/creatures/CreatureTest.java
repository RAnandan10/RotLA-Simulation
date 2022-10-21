package RotLA.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @Test
    void updateFightOutcome() {
        Creature testCreature = new Creature();
        testCreature.updateFightOutcome();
        assertFalse(testCreature.isAlive);
    }


    @Test // To check if dice roll sum is valid, i.e between 2 and 12
    void rollDice() {
        Creature testCreature = new Creature();
        int diceValue = testCreature.rollDice();
        assertTrue(diceValue>=2);
        assertTrue(diceValue<=12);
    }
}