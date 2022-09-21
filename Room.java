import java.security.PrivateKey;

public class Room {
    public int id;
    public int connectedRooms;
    public int creatures[];
    public int adventurers[];
    
    /* Takes the room  number as input and returns a bool value  */
    public boolean is_empty(int roomNumber){
        if ((creatures[0] != 0) &&(adventurers[0] != 0)){
            return true;
        }
        return false;
    }

    public boolean IsCreaturePresent(){
        if (creatures[0] != 0){
            return true;
        }
        return false;

    }

    public boolean IsAdventurersPresent(){
        if (adventurers[0] != 0){
            return true;
        }
        return false;
    }
}
