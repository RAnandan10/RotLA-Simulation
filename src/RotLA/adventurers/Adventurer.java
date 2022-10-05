package RotLA.adventurers;

import RotLA.Room;

import java.util.ArrayList;
import java.util.Random;

public class Adventurer {
    public int damage;                     // Gives us current damage status of adventurer
    public int maxDamage;                  // Gives us maximum damage an adventurer can take
    public int treasureCount;              // Tells us number of treasures found by adventurer
    public String type;                    // Tells us type of adventurer
    public String currentLocation;         // Gives us the current room location of adventurer
    public String performAction;           // What action is to be performed by adventurer based on room status

    /**
     * 
     */
    public Adventurer(){
        this.damage = 0;                      //Initial damage is 0
        this.maxDamage = 3;
        this.treasureCount = 0;                 //Initial treasure found is 0
        this.currentLocation = "0-1-1";         //Starting location for adventures is always 0-1-1
        this.performAction = null;
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
        /*
         * If adventurer is in a room with a monster, then adventurer will fight the monster
         * else call search method to search for treasure
         */
    }

    /**
     * @return
     * 
     * TO BE ROMOVED
     */
    public Boolean findTreasure(){
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
    public Boolean involveInFight(){
        return Boolean.TRUE;                // Tell us the chance of an adventurer to involve in a fight. It is 100% by default
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
