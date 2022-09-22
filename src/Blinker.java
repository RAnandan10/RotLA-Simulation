import java.util.ArrayList;
import java.util.Random;


public class Blinker extends Creature{
    Blinker(){

        super();
        this.creatureId="B";
        //this.currentLocation = 
    }

    // Generate a random room number 
    private String randomRoom(){
        Random random = new Random();
        return (random.nextInt(4)+1) +"-"+ random.nextInt(3) +"-"+ random.nextInt(3);
    }

    public void move(ArrayList<Room> facility){
        Room currentRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        Room room;
        String nextLocation = null;
        ArrayList<Room> adjacentRooms;


        if (currentRoom.isAdventurerPresent()){
            return;
        }
        /* jump to a random room */
        nextLocation = randomRoom();
        room = getRoomObjectFromRoomId(nextLocation,facility);
            this.currentLocation = room.id; // updating the current location in the creature object.
            currentRoom.removeCreatureFromList(this.type);  // removing the creture from the current room
            room.addCreatureToList(this.type); // adding the creture to next room
    }
}
