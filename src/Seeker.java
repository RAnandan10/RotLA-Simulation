public class Seeker extends Creature{
    Seeker(int number){
        super();
    }

    public void move(ArrayList<Room> fecility){
        Room currentRoom;
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
            if( room.id[0] == this.currentLocation[0]){
                if (room.isAdventurerPresent()){
                    this.currentLocation = room.id; // updating the current location in the creature object.
                    currentRoom.removeCreatureFromList(this.type);  // removing the creture from the current room
                    room.addCreatureToList(this.type); // adding the creture to next room
                    break;
                }
            }
        }




  


        // find adjacent adventurers in the same level
        //move to that room 
        

        // update room status
        
    }
}
