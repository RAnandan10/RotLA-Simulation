package RotLA.adventurers;

import RotLA.Publisher;
import RotLA.Room;
import RotLA.Combat;
import RotLA.Celebrate;
import RotLA.Search;
import RotLA.creatures.Creature;
import RotLA.treasures.Treasure;

import java.util.ArrayList;
import java.util.Random;

public class Adventurer extends Publisher {
    private Combat combat;
    private Search search;
    
    public int damage;                     // Gives us current damage status of adventurer
    public int maxDamage;                  // Gives us maximum damage an adventurer can take
    public int treasureCount;              // Tells us number of treasures found by adventurer
    public String type;                    // Tells us type of adventurer
    public String currentLocation;         // Gives us the current room location of adventurer
    public String performAction;           // What action is to be performed by adventurer based on room status
    public ArrayList<Treasure> treasureRetrived;

    /**
     * 
     */
    public Adventurer(Combat combat, Search search, String type){
        this.damage = 0;                      //Initial damage is 0
        this.maxDamage = 3;
        this.treasureCount = 0;                 //Initial treasure found is 0
        this.currentLocation = "0-1-1";         //Starting location for adventures is always 0-1-1
        this.performAction = null;
        this.combat = combat;
        this.search = search;
        this.type = type;
        this.treasureRetrived = new ArrayList<>();
    }

    public Adventurer(){
    }

    /**
     * @return
     */
    public Boolean isAlive(){
        if(this.damage < this.maxDamage)             //If damage is equal to maximum damage Adventurer can take then Adventurer is dead
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * @param facility: array containing all room objects
     */
    public void move(ArrayList<Room> facility){
        Room currentRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        ArrayList<String> neighbouringRooms = currentRoom.connectedRooms;
        int options = neighbouringRooms.size();                                     // Gives us the possible neighbouring rooms the adventurer can move to
        int index = (int)(Math.random()*options);                                   // Gives random index in neighbouringRooms to go to
        this.currentLocation = neighbouringRooms.get(index);                        // Current location of adventurer is updated
    }

    /**
     * @return
     * 
     * 
     */
    public Boolean findTreasure(Room room){
        if( room.isTreasurePresent){
            //c call search 

            
        }
        int sum = this.rollDice();
        if(sum>= 10){
            this.treasureCount++;               // Treasure is found if dice sum => 10
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * @return
     */
    public int rollDice(){
        Random random = new Random();
        int dice1 = random.nextInt(6)+1;            // Dice1 is rolled
        int dice2 = random.nextInt(6)+1;            // Dice2 is rolled
        return dice1+dice2;                         // Sum of the 2 dice rolls is passed
    }

    public void updateFightOutcome(){
        this.damage++;                      // If adventurer losses the fight then damage is increased by 1
    }

    /**
     * @return a boolean value indicating if the adventurer is involved in fight or not
     */
    public void fight(Adventurer adv, Creature cre, Room currentRoom){
        int diceRolls[] = new int[2];
        diceRolls[0] = adv.rollDice();
        diceRolls[1] = cre.rollDice();
        for (Treasure t : adv.treasureRetrived){
            diceRolls = t.treasureEffectOnCombatDiceRolls(diceRolls[0],diceRolls[1]);
        }
        int fightOutcome = combat.fight(diceRolls[0],diceRolls[1]);
        if (fightOutcome == 1){
            cre.updateFightOutcome();
            currentRoom.removeCreatureFromList(cre.type);
        }
        else if (fightOutcome == -1){
            adv.updateFightOutcome();
            if (!this.isAlive()){
                currentRoom.removeAdventurerFromList(adv.type);
                //System.out.println("Adventurer " + this.type + " is dead");
            }
        }
    }

    
    /**
     *  method to retrieve room object for a given room id
     * @param id
     * @param facility : array containing all room objects
     * @return roob oject
     */
    public Room getRoomObjectFromRoomId(String id, ArrayList<Room> facility){
         Room r = new Room(null);
         for (Room room : facility) {
             if (room.id.equals(id))
                 r = room;
         }
         return r;
     }
}
