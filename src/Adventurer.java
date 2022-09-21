abstract class Adventurer {

    int damage;
    int treasureCount;
    String type;
    String currentLocation;
    String performAction;

    Adventurer(){
        this.damage = 0;
        this.treasureCount = 0;
        this.currentLocation = "0-1-1";
        this.performAction = null;
    }

    public Boolean isAlive(Adventurer adv){
        if(adv.damage < 3)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public String move(){
        return "";
    }

    public Boolean findTreasure(Adventurer adv){
        int sum = adv.rollDice();
        if(sum>= 10){
            adv.treasureCount++;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    //https://math.hws.edu/eck/cs124/javanotes4/c5/ex-5-1-answer.html
    public int rollDice(){
        int die1 = (int)(Math.random()*6) + 1;
        int die2 = (int)(Math.random()*6) + 1;
        return die1+die2;
    }

    public void updateFightOutcome(Adventurer adv){
        adv.damage++;
    }

    public float fightChance(){
        return 1;
    }
}
