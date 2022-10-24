package RotLA;

import RotLA.adventurers.Adventurer;
import RotLA.creatures.Creature;
import RotLA.creatures.Orbiter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void addAdventurerToList() {
        System.out.println("Test to check if Room's addAdventurerToList() method works as expected");
        Room testRoom = new Room("1-1-1");
        assertFalse(testRoom.isAdventurerPresent(),"isAdventurerPresent() does not work as expected");
        Adventurer testAdventurer = new Adventurer(new Expert(), new Careless(), "Brawler",12);
        testRoom.addAdventurerToList(testAdventurer.type);
        assertTrue(testRoom.isAdventurerPresent(),"addAdventurerToList() does not work as expected");
    }

    @Test
    void addCreatureToList() {
        System.out.println("Test to check if Room's addCreatureToList() method works as expected");
        Room testRoom = new Room("1-1-1");
        assertFalse(testRoom.isCreaturePresent(),"isCreaturePresent() does not work as expected");
        ArrayList<Room> facility = new ArrayList();
        facility.add(testRoom);
        Creature testCreature = new Orbiter(facility,1);
        testRoom.addCreatureToList(testCreature.type);
        assertTrue(testRoom.isCreaturePresent(),"addCreatureToList() does not work as expected");
    }

    @Test
    void removeAdventurerFromList() {
        System.out.println("Test to check if Room's removeAdventurerFromList() method works as expected");
        Room testRoom = new Room("1-1-1");
        assertFalse(testRoom.isAdventurerPresent(),"isAdventurerPresent() does not work as expected");
        Adventurer testAdventurer = new Adventurer(new Expert(), new Careless(), "Brawler",12);
        testRoom.addAdventurerToList(testAdventurer.type);
        assertTrue(testRoom.isAdventurerPresent(),"addAdventurerToList() does not work as expected");
        testRoom.removeAdventurerFromList(testAdventurer.type);
        assertFalse(testRoom.isAdventurerPresent(),"removeAdventurerFromList() does not work as expected");
    }

    @Test
    void removeCreatureFromList() {
        System.out.println("Test to check if Room's removeCreatureFromList() method works as expected");
        Room testRoom = new Room("1-1-1");ArrayList<Room> facility = new ArrayList();
        facility.add(testRoom);
        Creature testCreature = new Orbiter(facility,1);
        testRoom.addCreatureToList(testCreature.type);
        assertTrue(testRoom.isCreaturePresent(),"isCreaturePresent() does not work as expected");
        testRoom.removeCreatureFromList(testCreature.type);
        assertFalse(testRoom.isCreaturePresent(),"removeCreatureFromList() does not work as expected");
    }

    @Test
    void isAdventurerPresent() {
        System.out.println("Test to check if Room's isAdventurerPresent() method works as expected");
        Room testRoom = new Room("1-1-1");
        assertFalse(testRoom.isAdventurerPresent(),"isAdventurerPresent() does not work as expected");
        Adventurer testAdventurer = new Adventurer(new Expert(), new Careless(), "Brawler",12);
        testRoom.addAdventurerToList(testAdventurer.type);
        assertTrue(testRoom.isAdventurerPresent(),"isAdventurerPresent() does not work as expected");
    }

    @Test
    void isCreaturePresent() {
        System.out.println("Test to check if Room's isCreaturePresent() method works as expected");
        Room testRoom = new Room("1-1-1");
        assertFalse(testRoom.isCreaturePresent(),"isCreaturePresent() does not work as expected");
        ArrayList<Room> facility = new ArrayList();
        facility.add(testRoom);
        Creature testCreature = new Orbiter(facility,1);
        testRoom.addCreatureToList(testCreature.type);
        assertTrue(testRoom.isCreaturePresent(),"isCreaturePresent() does not work as expected");
    }
}