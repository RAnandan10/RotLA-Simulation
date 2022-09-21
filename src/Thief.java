public class Thief extends Adventurer{
    Thief(){
        super();
        this.type = "T";
    }

    public int rollDice(){
        int sum = super.rollDice();
        return sum + 1;
    }
}
