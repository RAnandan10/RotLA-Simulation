import java.util.ArrayList;
import java.util.Random;

class Adventurer {
    int damage;                     // Gives us damage status to adventurer
    int treasureCount;              // Tells us number of treasures found by adventurer
    String type;                    // Tells us type of adventurer
    String currentLocation;         // Gives us the current room location of adventurer
    String performAction;           // What action is to be performed by adventurer based on room status

    Adventurer(){
        this.damage = 0;                        //Initial damage is 0
        this.treasureCount = 0;                 //Initial treasure found is 0
        this.currentLocation = "0-1-1";         //Starting location for adventures is always 0-1-1
        this.performAction = null;
    }

    public Boolean isAlive(){
        if(this.damage < 3)             //If damage is 3 then Adventurer is dead
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void move(ArrayList<Room> facility){
        Room currentRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        ArrayList<String> neighbouringRooms = currentRoom.connectedRooms;
        int options = neighbouringRooms.size();                                     // Gives us the possible neighbouring rooms the adventurer can move to
        int index = (int)(Math.random()*options);                                   // Gives random index in neighbouringRooms to go to
        this.currentLocation = neighbouringRooms.get(index);                        // Current location of adventurer is updated
    }

    public Boolean findTreasure(){
        int sum = this.rollDice();
        if(sum>= 10){
            this.treasureCount++;               // Treasure is found if dice sum => 10
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public int rollDice(){
        Random random = new Random();
        int dice1 = random.nextInt(6)+1;            // Dice1 is rolled
        int dice2 = random.nextInt(6)+1;            // Dice2 is rolled
        return dice1+dice2;                         // Sum of the 2 dice rolls is passed
    }

    public void updateFightOutcome(){
        this.damage++;                      // If adventurer losses the fight then damage is increased by 1
    }

    public Boolean involveInFight(){
        return Boolean.TRUE;                // Tell us the chance of an adventurer to involve in a fight. It is 100% by default
    }

    // method to retrieve room object for a given room id
    public Room getRoomObjectFromRoomId(String id, ArrayList<Room> facility){
         Room r = new Room(null);
         for (Room room : facility) {
             if (room.id.equals(id))
                 r = room;
         }
         return r;
     }
}
