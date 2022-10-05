package RotLA;

import RotLA.treasures.Treasure;

import java.util.ArrayList;

public class Room {
    public String id;
    public ArrayList<String> connectedRooms;
    public ArrayList<String> adventurers;
    public ArrayList<String> creatures;
    public Boolean isTreasurePresent;
    public ArrayList<Treasure> availableTreasures;

    public Room(String roomId){
        this.id = roomId;
        connectedRooms = new ArrayList<>();
        adventurers = new ArrayList<>();
        creatures = new ArrayList<>();
        availableTreasures = new ArrayList<>();
        isTreasurePresent = Boolean.FALSE;          // Initially no treasure in room
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

    public void setTreasureToRoom(Treasure treasure){
        this.availableTreasures.add(treasure);
        updateTreasureStatus();
    }

    public void TreasureListInRoom(Treasure treasure){
        this.availableTreasures.remove(treasure);
        updateTreasureStatus();
    }

    private void updateTreasureStatus(){
        if(availableTreasures.size() > 0)
            this.isTreasurePresent = Boolean.TRUE;
        else
            this.isTreasurePresent = Boolean.FALSE;
    }
    public ArrayList<Treasure>  getTreasureFromRoom(){
        return this.availableTreasures;
        
    }

}