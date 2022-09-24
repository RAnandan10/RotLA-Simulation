import java.util.ArrayList;
import static java.lang.System.out;

// This class defines methods to display the board and game status
public class BoardRenderer {

    // This method displays the current board status
    public void render(ArrayList<Room> facility){
        String roomId;
        Room room = facility.get(0);
        //r.adventurers.toString().replaceAll(",","").replace("[","").replace("]","")
        out.println("\n" + room.id + ":" + room.adventurers + ":" + room.creatures);
        int i =1;
        while(i<facility.size()){
            room = facility.get(i);
            roomId = room.id;
            while (roomId.contains("-0-")){
                out.print(room.id + ":" + room.adventurers + ":" + room.creatures + "        ");
                room=facility.get(++i);
                roomId=room.id;
            }
            out.println();

            while (roomId.contains("-1-")){
                System.out.print(room.id + ":" + room.adventurers + ":" + room.creatures + "        ");
                room=facility.get(++i);
                roomId=room.id;
            }
            out.println();

            while (roomId.contains("-2-")){
                out.print(room.id + ":" + room.adventurers + ":" + room.creatures + "        ");
                i++;
                if(i == facility.size())
                    break;
                room=facility.get(i);
                roomId=room.id;
            }
            out.println();
        }
    }

    // This method displays the current game status
    public void gameState(ArrayList<Adventurer> activeAdventurers, ArrayList<Creature> activeCreatures){
        int orbiterCount=0,seekerCount=0,blinkerCount=0;
        for (Adventurer adv :activeAdventurers){
            out.println(adv.type + " - " + adv.treasureCount +" Treasure(s) "+ adv.damage +" Damage");
        }
        out.println();
        for (Creature cre :activeCreatures){
            if (cre.isAlive){
                String str = cre.type;
                if(str.contains("O"))
                    orbiterCount++;
                else if (str.contains("S"))
                    seekerCount++;
                else
                    blinkerCount++;
                }
            }
        out.println("Orbiters - "+ orbiterCount + " Remaining");
        out.println("Seekers - "+ seekerCount + " Remaining");
        out.println("Blinkers - "+ blinkerCount + " Remaining");
    }
}