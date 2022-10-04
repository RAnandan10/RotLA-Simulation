import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Orbiter class extends Creature class. This is an example of Inheritance
public class Orbiter extends Creature{
    private final Boolean clockWise;                  // Tells us if Orbiter moves in clockwise direction or not. Also a private attribute. Example of abstraction

    Orbiter(ArrayList<Room> facility, int i){
        super();
        this.type = "O" + i;
        List<String> dir = Arrays.asList("-0-0", "-0-1", "-0-2","-1-2", "-2-2", "-2-1", "-2-0", "-1-0");    //Room list in a level while moving clockwise direction
        Random random = new Random(); 
        int floor = random.nextInt(3)+1;        // Select random level to initialize the Orbiter

        this.currentLocation = floor + dir.get(random.nextInt(8));      // Gives the random room in the level 'floor'
        if (random.nextInt(2) == 1){                 // Sets direction randomly
            this.clockWise = Boolean.TRUE;
        }
        else {
            this.clockWise = Boolean.FALSE;
        }

        //Room is updated with orbiters presence
        Room newRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        newRoom.addCreatureToList(this.type);
    }

    // Generate a  room number on the same floor based on direction
    // Method type is private. This is an example for Abstraction
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

        /**if counterclockwise then get the prev entry from dir */
        else{
            if (index == 0)
                return this.currentLocation.substring(0,2) + dir.get(7);
            return this.currentLocation.substring(0,2) + dir.get((index-1)%8);
        }
    }

    // This method overrides the Creature move() method. This is an example for Polymorphism
    public void move(ArrayList<Room> facility){
        Room currentRoom = new Room(null);
        String nextLocation = null;
        for (Room room : facility) {
            if (room.id.equals(this.currentLocation)){
                currentRoom = room;
                break;
            }    
        }

        if (currentRoom.isAdventurerPresent()){
            return;                                         // Don't move if adventurer is in current room
        }

       //Move to adjacent room based on direction of orbiters movement
        nextLocation = adjacentRoom();
        Room room = getRoomObjectFromRoomId(nextLocation,facility);
        this.currentLocation = room.id;                         // updating the current location in the creature object.
        currentRoom.removeCreatureFromList(this.type);          // removing the creature from the current room
        room.addCreatureToList(this.type);                      // adding the creature to new room
    }
}
