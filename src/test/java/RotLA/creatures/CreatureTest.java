package RotLA.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @Test
    void updateFightOutcome() {
        System.out.println("Test to check if creature updateFightOutcome() method updates creature's isAlive status");
        Creature testCreature = new Creature();
        testCreature.updateFightOutcome();
        assertFalse(testCreature.isAlive);
    }


    @Test // To check if dice roll sum is valid, i.e between 2 and 12
    void rollDice() {
        System.out.println("Test to check if creature rollDice() method returns a valid 2 dice roll value");
        Creature testCreature = new Creature();
        int diceValue = testCreature.rollDice();
        assertTrue(diceValue>=2);
        assertTrue(diceValue<=12);
    }
}