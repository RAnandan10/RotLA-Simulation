package RotLA;

import RotLA.adventurers.Adventurer;
import RotLA.treasures.Treasure;

public class Careless implements Search {
    public int search(Adventurer a, Room r) {
        int advRoll = Random.RollTwoDice();
        if (advRoll > 7) {
            Treasure treasure = pickTreasure(r.getTreasureFromRoom(), a.treasureRetrieved);
            if (treasure == null) {
                return 0;
            }
            a.treasureRetrieved.add(treasure);
            a.treasureCount++;
            r.removeTreasure(treasure);
            treasure.treasureEffect(a);
            a.notifySubscribers(a.type + " treasure " + treasure.treasureType);
            return 1;
        }
        return 0;
    }
}
