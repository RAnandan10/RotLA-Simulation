import java.util.ArrayList;


import static java.lang.System.out;

public class BoardRenderer {
    public void render(ArrayList<Room> facility){
        String roomid;
        Room r = facility.get(0);
        //r.adventurers.toString().replaceAll(",","").replace("[","").replace("]","")
        out.println("\n" + r.id + ":" + r.adventurers + ":" + r.creatures);
        int i =1;
        while(i<facility.size()){
            r = facility.get(i);
            roomid = r.id;
            while (roomid.contains("-0-")){
                out.print(r.id + ":" + r.adventurers + ":" + r.creatures + "        ");
                r=facility.get(++i);
                roomid=r.id;
            }
            out.println();

            while (roomid.contains("-1-")){
                System.out.print(r.id + ":" + r.adventurers + ":" + r.creatures + "        ");
                r=facility.get(++i);
                roomid=r.id;
            }
            out.println();

            while (roomid.contains("-2-")){
                out.print(r.id + ":" + r.adventurers + ":" + r.creatures + "        ");
                i++;
                if(i == facility.size())
                    break;
                r=facility.get(i);
                roomid=r.id;
            }
            out.println();
        }
    }

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