package RotLA.creatures;

import RotLA.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Seeker class extends Creature class. This is an example of Inheritance
public class Seeker extends Creature {

    public Seeker(ArrayList<Room> facility, int i){
        this.type = "Seeker" + i;                            // Seekers are identified through type 'S'. This is an example of Identity OO concept

        List<String> dir = Arrays.asList("-0-0", "-0-1", "-0-2","-1-2", "-2-2", "-2-1", "-2-0", "-1-0", "-1-1");  //Seekers can move only in their level. These are the possible 9 rooms in a level
        Random random = new Random();
        int floor = random.nextInt(4)+1;                                    // Select a random level
        this.currentLocation = floor + dir.get(random.nextInt(9));          // Select any random room in level 'floor'

        //Room is updated with seekers presence
        Room newRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        newRoom.addCreatureToList(this.type);

    }

    // This method overrides the Creature move() method. This is an example for Polymorphism
    public void move(ArrayList<Room> facility){
        Room currentRoom = new Room(null);

        // This is used to get the Room object of current location
        for (Room room : facility) {
            if (room.id.equals(this.currentLocation)){
                currentRoom = room;
                break;
            }    
        }

        if (currentRoom.isAdventurerPresent()){
            return;                                                 // Don't move if adventurer is in current room
        }

        //To move Seeker to an adjacent room where a adventurer is present
        for (Room room : facility) {
            if(room.id.substring(0, 1).equals(this.currentLocation.substring(0, 1))){
                if (room.isAdventurerPresent()){
                    this.currentLocation = room.id;                         // updating the current location in the creature object.
                    currentRoom.removeCreatureFromList(this.type);          // removing the creature from the current room
                    room.addCreatureToList(this.type);                      // adding the creature to next room
                    this.notifySubscribers(this.type + " enters " + this.currentLocation);
                    break;
                }
            }
        }
    }
}
