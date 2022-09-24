import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Seeker extends Creature{

    /**
     * @param facility
     */
    Seeker(ArrayList<Room> facility){
        this.type = "S";

        List<String> dir = Arrays.asList("-0-0", "-0-1", "-0-2","-1-2", "-2-2", "-2-1", "-2-0", "-1-0", "-1-1");
        Random random = new Random();
        final int floor = random.nextInt(5)+1;
        this.currentLocation = floor + dir.get(random.nextInt(8));// check syntax
        //this.currentLocation = "1-2-2"; // make it dynamic

        //set the seeker in the room
        Room newRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        newRoom.addCreatureToList(this.type); // check systax

    }

    /* (non-Javadoc)
     * @see Creature#move(java.util.ArrayList)
     */
    public void move(ArrayList<Room> facility){
        System.out.println("child");
        Room currentRoom = new Room(null);
        for (Room room : facility) {
            if (room.id.equals(this.currentLocation)){
                currentRoom = room;
                break;
            }    
        }
        /* check if adventurer is present in current room --> return  */

        for (Room room : facility) {
            if( room.id.substring(0,1) == this.currentLocation.substring(0,1)){
                if (room.isAdventurerPresent()){
                    this.currentLocation = room.id; // updating the current location in the creature object.
                    currentRoom.removeCreatureFromList(this.type);  // removing the creture from the current room
                    room.addCreatureToList(this.type); // adding the creture to next room
                    break;
                }
            }
        }
        
    }
}
