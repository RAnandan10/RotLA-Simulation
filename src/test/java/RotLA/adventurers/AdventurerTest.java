package RotLA.adventurers;

import RotLA.Careless;
import RotLA.Expert;
import RotLA.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    @Test
    void isAlive() {
        System.out.println("Test to check if adventurer isAlive() method works as expected");
        Adventurer testAdventurer = new Adventurer(new Expert(), new Careless(), "Brawler",1);
        assertTrue(testAdventurer.isAlive(),"Adventurer should be alive");
        testAdventurer.damage++;
        assertFalse(testAdventurer.isAlive(),"Adventurer should be dead");
    }

    @Test
    void move() {
        System.out.println("Test to check if adventurer move() method works as expected");
        Adventurer testAdventurer = new Adventurer(new Expert(), new Careless(), "Brawler",12);
        Room currentRoom = new Room(testAdventurer.currentLocation);
        Room newRoom = new Room("1-1-1");
        testAdventurer.move(currentRoom,newRoom);
        assertEquals(newRoom.id,testAdventurer.currentLocation);
        assertNotEquals(currentRoom.id,testAdventurer.currentLocation);
    }

    @Test
    void updateFightOutcome() {
        System.out.println("Test to check if adventurer damage is increased when updateFightOutcome() is called");
        Adventurer testAdventurer = new Adventurer(new Expert(), new Careless(), "Brawler",12);
        int advDamage = testAdventurer.damage;
        testAdventurer.updateFightOutcome();
        assertEquals(advDamage+1,testAdventurer.damage, "updateFightOutcome() failed to decrease damage");
    }

    @Test
    void rollDice() {
        System.out.println("Test to check if adventurer rollDice() method returns a valid 2 dice roll value");
        Adventurer testAdventurer = new Adventurer(new Expert(), new Careless(), "Brawler",12);
        int diceValue = testAdventurer.rollDice();
        assertTrue(diceValue>=2);
        assertTrue(diceValue<=12);
    }
}