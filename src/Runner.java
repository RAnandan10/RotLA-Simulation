import java.util.ArrayList;
import java.util.Random;

public class Runner extends Adventurer{
    Runner(){
        super();
        this.type = "R";
    }

public String move(ArrayList<Room> facility) {
        String newLocation = super.move(facility);
        String latestLocation = super.move(facility);
        return latestLocation;
    }
}
