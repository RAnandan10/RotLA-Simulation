import java.util.ArrayList;
import java.util.Random;
public class Creature {
    public Boolean isAlive;             // Tells us if creature is alive or not
    public String currentLocation;      // Gives us the current location of the creature
    public String type;                 // Tells us what type of creature

    //Constructor for creature class
    Creature(){
        this.isAlive = Boolean.TRUE;       // Creature is alive when we instantiate it
    }

    // This method is used to get the Room object from facility using room id. Method is protected. Example for abstraction
    protected Room getRoomObjectFromRoomId(String id, ArrayList<Room> facility){
        Room r = new Room(null);
        for (Room room : facility) {
            if (room.id.equals(id))
                r = room;
        }
        return r;
    }
    
    public void move(ArrayList<Room> facility){
    }

    public void updateFightOutcome(){
        this.isAlive = Boolean.FALSE;           // Creature dies when fight with an adventurer is lost
    }

    public int rollDice(){
        Random random = new Random();
        int dice1 = random.nextInt(6)+1;        // Dice1 is rolled
        int dice2 = random.nextInt(6)+1;        // Dice2 is rolled
        return dice1 + dice2;                         // Sum of the 2 dice rolls is passed
    }
}
