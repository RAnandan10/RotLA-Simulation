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
    
    public void shout(){
        System.out.println("Yay! We found the treasure!");
    }
}

class  Dance extends Celebrate {
    public void dance(){
        System.out.println("Yay! We found the treasure!");
    }
}
class Jump extends Celebrate{
    public void jump(){
        System.out.println("Yay! We found the treasure!");
    }
}
class Spin extends Celebrate{
    public void spin(){
        System.out.println("Yay! We found the treasure!");
    }
}

