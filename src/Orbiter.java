import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Orbiter extends Creature{
    String direction;
    Boolean clockWise;

    Orbiter(){
        super();
        this.creatureId = "O" ;
        
        Random random = new Random(); 
        if (random.nextInt(2) == 1){
            this.clockWise = Boolean.TRUE;
        }
        
        this.clockWise = Boolean.FALSE;
    }

    // Generate a  room number on the same floor
    private String adjacentRoom(){
        List<String> dir = Arrays.asList("0-0", "0-1", "0-2","1-2", "2-2", "2-1", "2-0", "1-0");
        int index = 0;
        String roomNumber = this.currentLocation.substring(2,3);

        /*get the current index of dir string from the room id */
        for (int i= 0; i <8; i++){
            if (roomNumber.equals(dir.get(i))){
                index = i;
                break;
            }
        }
        /**if clockwise then get the next entry from dir */
        if (clockWise){
            return this.currentLocation.substring(0,2) + dir.get((index+1)%8);
        }

        /**if conterclockwise then get the prev entry from dir */
        else{
            return this.currentLocation.substring(0,2) + dir.get((index-1)%8);
        }
    }

    public void move(ArrayList<Room> facility){
        System.out.println("child");
        Room currentRoom = new Room(null);
        String nextLocation = null;
        ArrayList<Room> adjacentRooms;
        for (Room room : facility) {
            if (room.id.equals(this.currentLocation)){
                currentRoom = room;
                break;
            }    
        }
        /* check if adventurer is present in current room --> return  */

        if (currentRoom.isAdventurerPresent()){
            return;
        }

       /* jump to a random room */
        nextLocation = adjacentRoom();
        Room room = getRoomObjectFromRoomId(nextLocation,facility);
        this.currentLocation = room.id; // updating the current location in the creature object.
        currentRoom.removeCreatureFromList(this.type);  // removing the creture from the current room
        room.addCreatureToList(this.type); // adding the creture to next room
    }
}
