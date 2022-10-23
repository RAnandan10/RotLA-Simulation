package RotLA.adventurers;

import RotLA.*;
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
    public ArrayList<Treasure> treasureRetrieved;
    public String name;

    /**
     * 
     */
    public Adventurer(Combat combat, Search search, String type, int maxDamage){
        this.damage = 0;                      //Initial damage is 0
        this.maxDamage = maxDamage;
        this.treasureCount = 0;                 //Initial treasure found is 0
        this.currentLocation = "0-1-1";         //Starting location for adventures is always 0-1-1
        this.performAction = null;
        this.combat = combat;
        this.search = search;
        this.type = type;
        this.treasureRetrieved = new ArrayList<>();
    }

    public Adventurer(){
    }

    /**
     * @return
     */
    public Boolean isAlive(){
        //If damage is equal to maximum damage Adventurer can take then Adventurer is dead
        if(this.damage < this.maxDamage){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * @param currentRoom: Room object where adventurer is present
     * @param newRoom: Room object where adventurer will move to
     */
    public void move(Room currentRoom, Room newRoom){
        currentRoom.removeAdventurerFromList(this.type);
        this.currentLocation = newRoom.id;
        newRoom.addAdventurerToList(this.type);
        this.notifySubscribers(this.type + " enters " + this.currentLocation);
    }

    /**
     * @return
     * 
     * 
     */
    public void findTreasure(Room room){
        if( room.isTreasurePresent){
            int i = search.search(this,room);
            if(i==1){
                this.treasureCount++;
            }
        }
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
        this.notifySubscribers(this.type + " damage " + this.damage);
        if (!this.isAlive())
            this.notifySubscribers(this.type + " dead " + this.currentLocation);
    }

    /**
     * @return a boolean value indicating if the adventurer is involved in fight or not
     */
    public void fight(Adventurer adv, Creature cre, Room currentRoom){
        int[] diceRolls = new int[2];
        diceRolls[0] = adv.rollDice();
        diceRolls[1] = cre.rollDice();
        for (Treasure t : adv.treasureRetrieved){
            diceRolls = t.treasureEffectOnCombatDiceRolls(diceRolls[0],diceRolls[1]);
        }

        Combat myFight = setCelebrations();

        String fightOutcome = myFight.fight(diceRolls[0],diceRolls[1]);
        if (fightOutcome.contains("Adventurer wins")){
            String[] celebration = fightOutcome.split("!");
            this.notifySubscribers(this.type + " wins combat");
            this.notifySubscribers(cre.type + " loses combat");
            if(celebration.length>1)
                this.notifySubscribers(this.type + " Celebrates: "+ celebration[1]);
            cre.updateFightOutcome();
            currentRoom.removeCreatureFromList(cre.type);
        }
        else if (fightOutcome.contains("Creature wins")){
            this.notifySubscribers(this.type + " loses combat");
            this.notifySubscribers(cre.type + " wins combat");
            adv.updateFightOutcome();
            if (!this.isAlive()){
                currentRoom.removeAdventurerFromList(adv.type);
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

     public Combat setCelebrations(){
         Combat myFight = this.combat;
         Random rand = new Random();
         int number = rand.nextInt(3);
         for(int i = 0; i<number;i++){
             myFight = new Shout(myFight);
         }
         number = rand.nextInt(3);
         for(int i = 0; i<number;i++){
             myFight = new Dance(myFight);
         }
         number = rand.nextInt(3);
         for(int i = 0; i<number;i++){
             myFight = new Jump(myFight);
         }
         number = rand.nextInt(3);
         for(int i = 0; i<number;i++){
             myFight = new Spin(myFight);
         }
        return myFight;
     }
}
