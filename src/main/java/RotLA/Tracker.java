package RotLA;

import java.util.ArrayList;

public class Tracker implements Observers {

    private ArrayList<AdventurerTrackerObject> adventurers;
    private ArrayList<CreatureTrackerObject> creatures;
    private int advCount;
    private int creCount;

    // Eager Instantiation of Singleton Tracker class
    private static Tracker tracker = new Tracker();

    private Tracker(){
        this.adventurers = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.advCount=0;
        this.creCount=0;
    }

    public static Tracker getInstance(){
        return tracker;
    }

    public void update(String event) {
        String[] extracted = event.split(" ");
        String actor = extracted[0];
        String action = extracted[1];
        String result = extracted[2];

        if(action.equals("initialised")){
            for(Adventurers a : Adventurers.values()){
                if(actor.equals(a.toString())){
                    AdventurerTrackerObject adv = new AdventurerTrackerObject(actor);
                    adv.location = result;
                    this.adventurers.add(adv);
                    this.advCount++;
                    return;
                }
            }

            CreatureTrackerObject cre = new CreatureTrackerObject(actor);
            cre.location = result;
            this.creatures.add(cre);
            this.creCount++;
            return;
        }

        for (AdventurerTrackerObject adv: this.adventurers){
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

        for (CreatureTrackerObject cre: this.creatures){
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
    }

    public void printTracker() {
        System.out.println("Total Active Adventurers:" + this.advCount);
        System.out.println("Adventurers\t Room\t Damage\t Treasure");
        for(AdventurerTrackerObject adv : adventurers){
            System.out.println(adv.type+"\t"+adv.location+"\t"+adv.currentDamage+"\t"+adv.treasuresCollected);
        }
        System.out.println("\nTotal Active Creatures:" + this.creCount);
        System.out.println("Creatures\t Room");
        for(CreatureTrackerObject cre : creatures){
            if(cre.isAlive)
                System.out.println(cre.type+"\t"+cre.location);
        }
    }

    public ArrayList<Integer> getTurnSummary(){
        ArrayList<Integer> turnStatistics = new ArrayList<>();
        turnStatistics.add(this.adventurers.get(0).treasuresCollected.size());
        turnStatistics.add(this.adventurers.get(0).currentDamage);
        turnStatistics.add(this.creCount);
        return turnStatistics;
    }
}

class AdventurerTrackerObject{
    String type;
    String location;
    int currentDamage;
    ArrayList<String> treasuresCollected;
    Boolean isAlive;

    AdventurerTrackerObject(String type){
        this.type = type;
        this.location = "0-1-1";
        this.currentDamage=0;
        this.treasuresCollected=new ArrayList<>();
        this.isAlive = true;
    }
}

class CreatureTrackerObject{
    String type;
    String location;
    Boolean isAlive;

    CreatureTrackerObject(String type){
        this.type = type;
        this.location = "";
        this.isAlive = true;
    }
}

enum Adventurers{
    Brawler, Runner, Sneaker, Thief
}