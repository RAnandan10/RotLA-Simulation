// Thief class inherits Adventurer class. This is an example of Inheritance
public class Thief extends Adventurer{
    Thief(){
        super();
        this.type = "T";
    }

    // This method overrides the Adventurer rollDice() method. This is an example for Polymorphism
    public int rollDice(){
        int sum = super.rollDice();
        return sum + 1;                 // Thief has +1 while fighting creature and finding a treasure
    }
}
