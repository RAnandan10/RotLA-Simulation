import java.util.Random;
public class Creature {
    public Boolean isAlive;
    public String currentLocation;
    public String creatureId;
    public String type;

    Creature(){
        this.isAlive = Boolean.TRUE;
    }

    public String move(){
        return "";
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
