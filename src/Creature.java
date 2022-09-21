import java.util.Random;
public class Creature {
    public Boolean isAlive;
    public String currentLocation;
    public String creatureId;

    Creature(){
        this.isAlive = Boolean.TRUE;
    }

    public String move(){
        return "";
    }

    public void updateFightOutcome(){
        this.isAlive = Boolean.TRUE;
    }

    public int rollDice(){
        Random random = new Random();
        return random.nextInt(7)+1;  
    }
}
