package RotLA;
import RotLA.adventurers.Adventurer;
import RotLA.treasures.Treasure;
import java.util.ArrayList;


public interface Search {
    public int search(Adventurer a, Room r);
    public default Treasure pickTreasure(ArrayList<Treasure> treasuresAvailable, ArrayList<Treasure> treasuresOwned) {
        for (Treasure treasure : treasuresAvailable) {
            if (!treasuresOwned.contains(treasure) || treasure.treasureType.equals("Trap")) {
                return treasure;
            }
        }
        return null;
    }
}
class Careful implements Search{
    public int search(Adventurer a, Room r) {
        int probability = Random.nextInt(2);
        int advRoll = Random.RollTwoDice();
        if (advRoll > 7) {
            Treasure treasure = pickTreasure(r.getTreasureFromRoom(), a.treasureRetrieved);
            if (treasure == null) {
                return 0;
            }
            if (treasure.treasureType == "Trap") {
                if (probability == 0) {
                    return 0;
                }
            }
            a.treasureRetrieved.add(treasure);
            a.treasureCount++;
            r.removeTreasure(treasure);
            a.notifySubscribers(a.type + " treasure " + treasure.treasureType);
            return 1;
        }
        return 0;
        
    }
}

class Quick implements Search{
    public int search(Adventurer a, Room r){
        
        int probability = Random.nextInt(3); 
        int advRoll = Random.RollTwoDice();
        if (probability == 2){
            return 0;
        }
        if(advRoll > 9){
            Treasure treasure = pickTreasure(r.getTreasureFromRoom(), a.treasureRetrieved);
            if (treasure == null) {
                return 0;
            }
            a.treasureRetrieved.add(treasure);
            a.treasureCount++;
            r.removeTreasure(treasure);
            a.notifySubscribers(a.type + " treasure " + treasure.treasureType);
            return 1;
        }
        return 0;
    }
}

class Careless implements Search{
    public int search(Adventurer a, Room r){
        int advRoll = Random.RollTwoDice();
        if(advRoll > 10){
            Treasure treasure = pickTreasure(r.getTreasureFromRoom(), a.treasureRetrieved);
            if (treasure == null) {
                return 0;
            }
            a.treasureRetrieved.add(treasure);
            a.treasureCount++;
            r.removeTreasure(treasure);
            a.notifySubscribers(a.type + " treasure " + treasure.treasureType);
            return 1;
        }
        return 0;
    }
}