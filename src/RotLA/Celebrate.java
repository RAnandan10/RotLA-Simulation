package RotLA;

public abstract   class Celebrate implements Combat{
    public Combat combat;
    public void celebrate(){
        System.out.println("Celebrate");
    }
    public int fight(int advRoll, int creRoll){
        return combat.fight(advRoll, creRoll);
    }
}   


class Shout extends Celebrate {        
    public void celebrate() {
        System.out.println("Shout");
    }
}

class  Dance extends Celebrate {
    public void celebrate() {
        System.out.println("Dance");
    }
}

class Jump extends Celebrate{
    public void celebrate() {
        System.out.println("Jump");
    }
}

class Spin extends Celebrate{
    public void celebrate() {
        System.out.println("Spin");
    }
}

