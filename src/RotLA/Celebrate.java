package RotLA;

public abstract class Celebrate implements Combat{
    public Combat combat;

    Celebrate(Combat c){
        this.combat = c;
    }
    public void celebrate(){
    }
    public int fight(int advRoll, int creRoll){
        int outcome = combat.fight(advRoll, creRoll);
        if(outcome==1)
            this.celebrate();
        return outcome;
    }
}


