package RotLA.creatures;

import RotLA.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Blinker class inherits Creature class. This is an example of Inheritance
public class Blinker extends Creature {

    //Constructor for Blinker class
    public Blinker(ArrayList<Room> facility, int i){
        super();
        Room r;
        this.type="Blinker" + i;                      // Blinkers are identified through type 'B'. This is an example of Identity OO concept
        List<String> dir = Arrays.asList("4-0-0", "4-0-1", "4-0-2","4-1-2", "4-2-2", "4-2-1", "4-2-0", "4-1-0", "4-1-1");    //Blinker's starting location is in level 4. These are possible location in level 4
        Random random = new Random();
        this.currentLocation = dir.get(random.nextInt(9));          //Starting location is any one of thr above room numbers

        //Room is updated with blinker presence
        r = getRoomObjectFromRoomId(this.currentLocation,facility);
        r.addCreatureToList(this.type);
    }

    // Generate a random room number for Blinker to move next
    // Method type is private. This is an example for Abstraction
    private String randomRoom(){
        Random random = new Random();
        return (random.nextInt(4)+1) +"-"+ random.nextInt(3) +"-"+ random.nextInt(3);
    }

    // This method overrides the Creature move() method. This is an example for Polymorphism
    public void move(ArrayList<Room> facility){
        Room currentRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        Room nextRoom;
        String nextLocation;

        if (currentRoom.isAdventurerPresent()){
            return;                                                 // Don't move if adventurer is in current room
        }

        nextLocation = randomRoom();
        nextRoom = getRoomObjectFromRoomId(nextLocation,facility);
        this.currentLocation = nextRoom.id;                         // updating the current location in the creature object.
        currentRoom.removeCreatureFromList(this.type);              // removing the creature from the current room
        nextRoom.addCreatureToList(this.type);                      // adding the creature to next room
    }
}
