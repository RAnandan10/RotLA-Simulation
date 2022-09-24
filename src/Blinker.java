import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Blinker extends Creature{
    Blinker(ArrayList<Room> facility){

        super();
        Room r;
        this.type="B";
        List<String> dir = Arrays.asList("4-0-0", "4-0-1", "4-0-2","4-1-2", "4-2-2", "4-2-1", "4-2-0", "4-1-0");
        Random random = new Random();
        this.currentLocation = dir.get(random.nextInt(8));

        //set the blinker in the room
        r = getRoomObjectFromRoomId(this.currentLocation,facility);
        r.addCreatureToList(this.type); // check systax
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
