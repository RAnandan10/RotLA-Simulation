package RotLA;

import RotLA.adventurers.*;
import RotLA.creatures.*;
import RotLA.treasures.*;

import java.util.ArrayList;
import java.util.Random;

// This class drives the game. This class has public and private methods which are highly cohesive. This class is an example of Encapsulation and cohesion
public class GameEngine {

    public int totalTreasureCount;
    public Boolean gameOver;
    public ArrayList<Adventurer> activeAdventurers;
    public ArrayList<Creature> activeCreatures;
    public ArrayList<Room> facility;

    public Tracker track = new Tracker();

    /*Game engin constructor */
    GameEngine(){
        this.gameOver = Boolean.FALSE;
        this.totalTreasureCount =0;
        this.activeAdventurers = new ArrayList<>();
        this.activeCreatures = new ArrayList<>();
        this.facility = new ArrayList<>();
    }

    // This method initializes the game
    public void initializer(BoardRenderer board) {
        facilityInitializer();
        treasureInitializer();
        adventurersInitializer();
        creaturesInitializer();
        board.render(facility);
        board.gameState(activeAdventurers,activeCreatures);
    }

    // This method initializes the adventurers
    private void adventurersInitializer(){
        Adventurer brawler = new Adventurer(new Expert(), new Careless(), "Brawler");
        Adventurer sneaker = new Adventurer(new Stealth(), new Quick(), "Sneaker");
        Adventurer runner = new Adventurer(new Untrained(), new Quick(), "Runner");
        Adventurer thief = new Adventurer(new Trained(), new Careful(), "Thief");
        activeAdventurers.add(brawler);
        activeAdventurers.add(sneaker);
        activeAdventurers.add(runner);
        activeAdventurers.add(thief);
        Room startingPoint = facility.get(0);
        for(Adventurer adv: activeAdventurers){
            startingPoint.addAdventurerToList(adv.type);
            adv.registerSubscriber(track);
            adv.notifySubscribers(adv.type + " initialised " + adv.currentLocation);
        }
    }

    // This method initializes the Creatures
    private void creaturesInitializer(){
        for (int i = 1; i < 5; i++){
            Orbiter orbiter = new Orbiter(facility,i);
            orbiter.registerSubscriber(track);
            orbiter.notifySubscribers(orbiter.type + " initialised " + orbiter.currentLocation);
            Seeker seeker = new Seeker(facility,i);
            seeker.registerSubscriber(track);
            seeker.notifySubscribers(seeker.type + " initialised " + seeker.currentLocation);
            Blinker blinker = new Blinker(facility,i);
            blinker.registerSubscriber(track);
            blinker.notifySubscribers(blinker.type + " initialised " + blinker.currentLocation);
            activeCreatures.add(orbiter);
            activeCreatures.add(seeker);
            activeCreatures.add(blinker);
        }
        System.out.println(activeCreatures.size());
    }

    // This method initializes the facility
    private void facilityInitializer(){
        ArrayList<String> connectedRooms = new ArrayList<>();
        String connectedRoomId;
        String id;

        //For room at ground level
        Room groundLevelRoom = new Room("0-1-1");
        connectedRooms.add("1-1-1");
        groundLevelRoom.setRoomConnections(connectedRooms);
        facility.add(groundLevelRoom);
        for (int i = 1; i < 5; i++){
            for (int j = 0 ; j < 3; j++){
                for (int k = 0; k < 3; k++){
                    id = i +"-"+ j +"-"+ k;
                    Room r = new Room(id);
                    connectedRooms = new ArrayList<>();

                    //If center room on a level
                    if(j == 1 && k == 1){
                        for (int a = 0 ; a < 3; a++) {
                            for (int b = 0; b < 3; b++) {
                                //Add adjacent rooms in level except center room on that level
                                if(a != 1 || b != 1) {
                                    connectedRoomId = i + "-" + a + "-" + b;
                                    connectedRooms.add(connectedRoomId);
                                }
                            }
                        }

                        if(i!=4){
                            connectedRooms.add((i-1) + "-1-1");
                            connectedRooms.add((i+1) + "-1-1");
                        }

                        if(i==4) {
                            connectedRooms.add((i - 1) + "-1-1");
                        }
                    }

                    //If not a center room add only center room on that level
                    if(j != 1 || k != 1){
                        connectedRoomId = i + "-1-1";
                        connectedRooms.add(connectedRoomId);
                    }
                    r.setRoomConnections(connectedRooms);
                    facility.add(r);
                }
            }
        }
    }

    // This method initialises the treasures in random rooms
    private void treasureInitializer(){
        Room room;
        Random rand = new Random();
        for (int i =0; i<4 ; i++){
            Sword sword = new Sword();
            room = facility.get(rand.nextInt(facility.size()));
            room.setTreasureToRoom(sword);

            Armor armor = new Armor();
            room = facility.get(rand.nextInt(facility.size()));
            room.setTreasureToRoom(armor);

            Gem gem = new Gem();
            room = facility.get(rand.nextInt(facility.size()));
            room.setTreasureToRoom(gem);

            Portal portal = new Portal();
            room = facility.get(rand.nextInt(facility.size()));
            room.setTreasureToRoom(portal);

            Potion potion = new Potion();
            room = facility.get(rand.nextInt(facility.size()));
            room.setTreasureToRoom(potion);

            Trap trap = new Trap();
            room = facility.get(rand.nextInt(facility.size()));
            room.setTreasureToRoom(trap);

        }
    }

    // Returns total treasure count
    private int check_treasure_count(){
        return totalTreasureCount;
    }

    // Method checks how many adventurers are alive
    private int check_adventurer_count(){
        int adventurerCount = 0;
        for (Adventurer activeAdventurer : activeAdventurers) {
            if (activeAdventurer.isAlive()) {
                adventurerCount++;
            }
        }
        return adventurerCount;
    }

    // Method checks how many creatures are alive
    private int check_creature_count(){
        int creatureCount = 0;
        for (Creature activeCreature : activeCreatures) {
            if (activeCreature.isAlive) {
                creatureCount++;
            }
        }
        return creatureCount;
    }
    private void turn(BoardRenderer board, Tracker track, int turn){

        Logger log = new Logger(turn);

        // Gets all adventurers that are alive
        ArrayList<Adventurer> playingAdventures = new ArrayList<>();
        for (Adventurer adv : activeAdventurers){
            if(adv.isAlive())
                playingAdventures.add(adv);
        }

        // Gets all creatures that are alive
        ArrayList<Creature> playingCreatures = new ArrayList<>();
        for (Creature cre : activeCreatures){
            if(cre.isAlive)
                playingCreatures.add(cre);
        }

        // Each alive adventurer gets to play
        for (Adventurer playingAdv : playingAdventures){
            playingAdv.registerSubscriber(log);
            playingAdv.registerSubscriber(track);
            // Adventurer moves to next location
            Room currentRoom = getRoomObjectFromRoomId(playingAdv.currentLocation);
            playingAdv.move(facility);
            currentRoom.removeAdventurerFromList(playingAdv.type);
            Room newRoom = getRoomObjectFromRoomId(playingAdv.currentLocation);
            newRoom.addAdventurerToList(playingAdv.type);

            // Check if Creature present in room. If so fight
            if(newRoom.isCreaturePresent()){
                int index =0;
                ArrayList<String> creaturesInRoom = newRoom.getCreaturesInRoom();
                String creatureToFight;
                if (creaturesInRoom.size() > 1){
                    int size =  creaturesInRoom.size();
                    Random random = new Random();
                    index = random.nextInt(size);
                }
                creatureToFight = creaturesInRoom.get(index);
                playingAdv.performAction="Fight";
                //fight(getCreatureObjectFromCreatureType(creatureToFight), playingAdv);
                playingAdv.fight(playingAdv,getCreatureObjectFromCreatureType(creatureToFight), newRoom);
            }
            // If Creature is not present in room then search treasure
            else {
                playingAdv.performAction="Treasure";
                Boolean foundTreasure = playingAdv.findTreasure();
                if(foundTreasure){
                    this.totalTreasureCount++;
                }
            }

            playingAdv.removeSubscriber(log);
            playingAdv.removeSubscriber(track);

            // Check if any end game condition is met after an adventurers turn
            if(!shouldGameContinue())
                return;
        }


        // Each alive creature gets to play
        for (Creature playingCre : playingCreatures){

            playingCre.registerSubscriber(log);
            playingCre.registerSubscriber(track);

            Room currentRoom = getRoomObjectFromRoomId(playingCre.currentLocation);

            // Don't move if adventurer is in room
            if(currentRoom.isAdventurerPresent()){
                continue;
            }
            else {
                playingCre.move(facility);
                /*currentRoom.removeCreatureFromList(playingCre.type);
                Room newRoom = getRoomObjectFromRoomId(playingCre.currentLocation);
                newRoom.addCreatureToList(playingCre.type);*/
            }

            // After creature move, check if adventurer is present and fight
            if(currentRoom.isAdventurerPresent()){
                int index =0;
                ArrayList<String> adventuresInRoom = currentRoom.getAdventurersInRoom();
                String adventurerToFight;
                if (adventuresInRoom.size() > 1){
                    int size =  adventuresInRoom.size();
                    Random random = new Random();
                    index = random.nextInt(size);
                }
                adventurerToFight = adventuresInRoom.get(index);
                Adventurer fightingAdv = getAdventurerObjectFromAdventurerType(adventurerToFight);
                fightingAdv.performAction="Fight";
                //fight(playingCre, fightingAdv);
                fightingAdv.fight(fightingAdv,playingCre, currentRoom);
            }

            playingCre.removeSubscriber(log);
            playingCre.removeSubscriber(track);

            // Check if any end game condition is met after a creatures turn
            if(!shouldGameContinue())
                return;
        }

        // Prints current board and game status
        board.render(facility);
        board.gameState(activeAdventurers,activeCreatures);
    }

    // This method checks if any termination condition is met and updates attribute gameOver. This is a private method. Example of Abstraction
    private Boolean shouldGameContinue(){

        // Game ends if all creatures are dead
        if (check_creature_count() == 0){
            System.out.println("    Game Over : All creatures are dead\n");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }

        // Game ends if all adventurers are dead
        if (check_adventurer_count() == 0){
            System.out.println("    Game Over: All Adventurers are dead\n");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }

        // Game ends if 10 treasures are found
        if (check_treasure_count() == 25){
            System.out.println("    Game Over: 10 treasure found\n");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }

    // This method is used to initialize the game and play the game until a termination condition is met
    public void playGame(BoardRenderer board){
        int turn = 1;
        while(!gameOver){
            System.out.println("Turn" + turn);
            turn(board,track,turn);
            turn ++;
            track.printTracker();
        }   
    }

    //Method to get a Room object using room id
    private Room getRoomObjectFromRoomId(String id){
        Room r = new Room(null);
        for (Room room : facility) {
            if (room.id.equals(id))
                r = room;
        }
        return r;
    }

    //Method to get a Creature object using Creature type
    private Creature getCreatureObjectFromCreatureType(String id){
        Creature cre = new Creature();
        for (Creature creature : activeCreatures) {
            if (creature.type.equals(id))
                cre = creature;
        }
        return cre;
    }

    //Method to get Adventurer object using Adventurer type
    private Adventurer getAdventurerObjectFromAdventurerType(String id){
        Adventurer adv = new Adventurer();
        for (Adventurer adventurer : activeAdventurers) {
            if (adventurer.type.equals(id))
                adv = adventurer;
        }
        return adv;
    }
}
