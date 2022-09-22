public class Brawler extends Adventurer{
    Brawler(){
        super();
        this.type = "B";
    }

    public int rollDice(){
        int sum = super.rollDice();
        if(this.performAction.equals("Fight"))
            return sum + 2;
        return sum;
    }
}
