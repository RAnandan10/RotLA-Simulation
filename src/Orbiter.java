import java.util.Random;

public class Orbiter extends Creature{
    String direction;
    Boolean clockWise;

    Orbiter(int number){
        super();
        this.creatureId = "O" + number;
        
        Random random = new Random(); 
        if (random.nextInt(2) == 1){
            this.clockWise = Boolean.TRUE;
        }
        
        this.clockWise = Boolean.FALSE;
    }

    // Generate a  room number on the same floor
    private String adjacentRoom(){
        if (clockWise){
            
        }
        else{

        }

        return ;
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
