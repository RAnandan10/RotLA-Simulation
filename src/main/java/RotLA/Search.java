package RotLA;
import RotLA.adventurers.Adventurer;
import RotLA.treasures.Treasure;
import java.util.ArrayList;


public interface Search {
    public int search(Adventurer a, Room r);
    public default Treasure pickTreasure(ArrayList<Treasure> treasuresAvailable, ArrayList<Treasure> treasuresOwned) {
        for (Treasure availTreasure : treasuresAvailable) {
            if(treasuresOwned.size() == 0 || availTreasure.treasureType.equals("Trap")) {
                return availTreasure;
            }
            boolean isOwned = false;
            for (Treasure ownedTreasure : treasuresOwned) {
                if (ownedTreasure.treasureType.equals(availTreasure.treasureType)){
                    isOwned = true;
                    break;
                    
                }
            }
            if (!isOwned) {
                return availTreasure;
            }

        }
        return null;
    }
}

class Careful implements Search{
    public int search(Adventurer a, Room r) {
        int probability = Random.nextInt(2);
        int advRoll = Random.RollTwoDice();
        if (advRoll > 4) {
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
            treasure.treasureEffect(a);
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

        int advRoll = Random.RollTwoDice();
        if(advRoll > 6){
            Treasure treasure = pickTreasure(r.getTreasureFromRoom(), a.treasureRetrieved);
            if (treasure == null) {
                return 0;
            }
            a.treasureRetrieved.add(treasure);
            a.treasureCount++;
            treasure.treasureEffect(a);
            r.removeTreasure(treasure);
            a.notifySubscribers(a.type + " treasure " + treasure.treasureType);
            return 1;
        }
        return 0;
    }
}

