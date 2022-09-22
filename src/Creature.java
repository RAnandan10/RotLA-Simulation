import java.util.ArrayList;
import java.util.Random;
public class Creature {
    public Boolean isAlive;
    public String currentLocation;
    public String creatureId;
    public String type;

    Creature(){
        this.isAlive = Boolean.TRUE;
    }

    public void move(ArrayList<Room> facility){
        System.out.println("parent");
    }

    public void updateFightOutcome(){
        this.isAlive = Boolean.FALSE;
    }

    public int rollDice(){
        Random random = new Random();
        int dice1 = random.nextInt(6)+1;
        int dice2 = random.nextInt(6)+1;
        return dice1 + dice2;  
    }
}
