public class Brawler extends Adventurer{
    Brawler(){
        super();
        this.type = "B";
    }

    public int rollDice(Brawler brw){
        int sum = super.rollDice();
        if(brw.performAction == "Fight")
            return sum + 2;
        return sum;
    }
}
