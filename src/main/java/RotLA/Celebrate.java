package RotLA;

public abstract class Celebrate implements Combat{
    private Combat combat;

    Celebrate(Combat c){
        this.combat = c;
    }
    abstract public String celebrate();
    public String fight(int advRoll, int creRoll){
        String outcome = combat.fight(advRoll, creRoll);
        if(outcome.contains("Adventurer wins"))
            return outcome + this.celebrate() + " ";
        return outcome;
    }
}


