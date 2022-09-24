// Thief class inherits Adventurer class. This is an example of Inheritance
public class Thief extends Adventurer{
    Thief(){
        super();
        this.type = "T";        // Thief(s) are Adventurers who are identified through type 'T'. This is an example of Identity OO concept
    }

    
    /* (non-Javadoc)
     * @see Adventurer#rollDice()
     * This method overrides the Adventurer rollDice() method. This is an example for Polymorphism
     */
    public int rollDice(){
        int sum = super.rollDice();
        return sum + 1;                 // Thief has +1 while fighting creature and finding a treasure
    }
}
