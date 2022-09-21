public class Thief extends Adventurer{
    Thief(){
        super();

    }

    public int rollDice(){
        int sum = super.rollDice();
        return sum + 1;
    }
}
