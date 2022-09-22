import java.util.Random;

public class Sneaker extends Adventurer{
    Sneaker(){
        super();
        this.type = "S";
    }

    public Boolean involveInFight(){
        Random random = new Random();
        int chance = random.nextInt(2);
        if (chance == 1){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }
}
