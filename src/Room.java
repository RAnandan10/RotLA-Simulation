import java.util.ArrayList;
import java.util.Random;

public class Room {
    public String id;
    public ArrayList<String> connectedRooms;
    public ArrayList<String> adventurers;
    public ArrayList<String> creatures;

    Room(String roomId){
        this.id = roomId;
        connectedRooms = new ArrayList<>();
        adventurers = new ArrayList<>();
        creatures = new ArrayList<>();
    }

    public void setRoomConnections(ArrayList<String> arr){
        this.connectedRooms.addAll(arr);
    }

    public void addAdventurerToList(String adventurerType){
        this.adventurers.add(adventurerType);
    }

    public void addCreatureToList(String type){
        this.creatures.add(type);
    }

    public void removeAdventurerFromList(String adventurerType){
        this.adventurers.remove(adventurerType);
    }

    public void removeCreatureFromList(String type){
        this.creatures.remove(type);
    }

    public ArrayList<String> getAdventurersInRoom(){
        return adventurers;
    }

    public ArrayList<String> getCreaturesInRoom(){
        return creatures;
    }

    public Boolean isAdventurerPresent(){
        ArrayList<String> occupants = new ArrayList<>(getAdventurersInRoom());
        if (occupants.isEmpty())
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public Boolean isCreaturePresent(){
        ArrayList<String> occupants = new ArrayList<>(getCreaturesInRoom());
        if (occupants.isEmpty())
            return Boolean.FALSE;
        return Boolean.TRUE;
    }
}