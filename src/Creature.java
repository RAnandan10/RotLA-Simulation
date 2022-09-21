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

    public void updateFightOutcome(Creature c){
        c.isAlive = Boolean.FALSE;
    }

    public int rollDice(){
        return 0;
    }
}
