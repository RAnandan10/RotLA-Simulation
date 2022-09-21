public class Seeker extends Creature{
    Seeker(int number){
        super();
    }

    public void move(String room){
        self.currentLocation = room;
    }
}
