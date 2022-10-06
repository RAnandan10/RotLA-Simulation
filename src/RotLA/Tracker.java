package RotLA;

import java.util.ArrayList;

public class Tracker implements Observers {

    private ArrayList<adventurerTrackerObject> adventurers;
    private ArrayList<creatureTrackerObject> creatures;
    private int advCount;
    private int creCount;

    Tracker(){
        this.adventurers = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.advCount=0;
        this.creCount=0;
    }
    public void update(String event) {
        String[] extracted = event.split(" ");
        String actor = extracted[0];
        String action = extracted[1];
        String result = extracted[2];

        if(action.equals("initialised")){
            for(Adventurers a : Adventurers.values()){
                if(actor.equals(a.toString())){
                    adventurerTrackerObject adv = new adventurerTrackerObject(actor);
                    adv.location = result;
                    this.adventurers.add(adv);
                    this.advCount++;
                    return;
                }
            }

            creatureTrackerObject cre = new creatureTrackerObject(actor);
            cre.location = result;
            this.creatures.add(cre);
            this.creCount++;
            return;
        }

        for (adventurerTrackerObject adv: this.adventurers){
            if(actor.equals(adv.type)){
                switch (action) {
                    case "enters":
                        adv.location = result;
                        return;
                    case "dead":
                        adv.isAlive = Boolean.FALSE;
                        this.advCount--;
                        return;
                    case "damage":
                        adv.currentDamage++;
                        return;
                    case "treasure":
                        adv.treasuresCollected.add(result);
                        return;
                    default:
                        return;
                }

            }
        }

        for (creatureTrackerObject cre: this.creatures){
            if(actor.equals(cre.type)){
                if (action.equals("enters")){
                    cre.location = result;
                    return;
                } else if (action.equals("dead")) {
                    cre.isAlive = Boolean.FALSE;
                    this.creCount--;
                    return;
                }
                else
                    return;

            }
        }

        /*for(Adventurers a : Adventurers.values()){
            if(actor.equals(a.toString())){
                adventurerTrackerObject adv = new adventurerTrackerObject(actor);
                this.adventurers.add(adv);
            }

        }*/

    }

    public void printTracker() {
        System.out.println("Total Active Adventurers:" + this.advCount);
        System.out.println("Adventurers\t Room\t Damage\t Treasure");
        for(adventurerTrackerObject adv : adventurers){
            if(adv.isAlive)
                System.out.println(adv.type+"\t"+adv.location+"\t"+adv.currentDamage+"\t"+adv.treasuresCollected);
        }
        System.out.println("\nTotal Active Creatures:" + this.creCount);
        System.out.println("Creatures\t Room");
        for(creatureTrackerObject cre : creatures){
            if(cre.isAlive)
                System.out.println(cre.type+"\t"+cre.location);
        }
    }
}

class adventurerTrackerObject{
    String type;
    String location;
    int currentDamage;
    ArrayList<String> treasuresCollected;
    Boolean isAlive;

    adventurerTrackerObject(String type){
        this.type = type;
        this.location = "0-1-1";
        this.currentDamage=0;
        this.treasuresCollected=new ArrayList<>();
        this.isAlive = true;
    }
}

class creatureTrackerObject{
    String type;
    String location;
    Boolean isAlive;

    creatureTrackerObject(String type){
        this.type = type;
        this.location = "";
        this.isAlive = true;
    }
}

enum Adventurers{
    Brawler, Runner, Sneaker, Thief
}

enum Creatures{
    Orbiter, Seeker, Blinker
}