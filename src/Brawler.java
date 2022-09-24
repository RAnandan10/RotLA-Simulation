// Brawler class inherits Adventurer class. This is an example of Inheritance
public class Brawler extends Adventurer{
    Brawler(){
        super();
        this.type = "B";            // Brawlers are Adventurers who are identified through type 'S'. This is an example of Identity OO concept
    }

    
    /* (non-Javadoc)
     *This method overrides the Adventurer rollDice() method. This is an example for Polymorphism
     * @see Adventurer#rollDice()
     */
    public int rollDice(){
        int sum = super.rollDice();
        if(this.performAction.equals("Fight"))      // If action for brawler is fight then dice sum is increased by 2
            return sum + 2;
        return sum;
    }
}
