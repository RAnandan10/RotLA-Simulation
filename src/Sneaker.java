public class Sneaker extends Adventurer{
    Sneaker(){
        super();
    }

    public Boolean involveInFight(){
        Random random = new Random();
        int chance = random.nextInt(2);
        if (chance){
            return TRUE
        }
        return FALSE

    }
}
