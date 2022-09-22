import java.util.ArrayList;

public class Seeker extends Creature{
    Seeker(int number){
        super();
        this.creatureId = "S" + number;
        this.currentLocation = "1-2-2";
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
