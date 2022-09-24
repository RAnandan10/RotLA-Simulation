import java.util.Random;

// Sneaker class inherits Adventurer class. This is an example of Inheritance
public class Sneaker extends Adventurer{
    Sneaker(){
        super();
        this.type = "S";
    }

    
    /* (non-Javadoc)
     * This method overrides the Adventurer involveInFight() method. This is an example for Polymorphism
     * @see Adventurer#involveInFight()
     */
    public Boolean involveInFight(){
        Random random = new Random();
        int chance = random.nextInt(2);
        if (chance == 1){                       // Sneaker has 50% chance to fight. This is handled using random
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
