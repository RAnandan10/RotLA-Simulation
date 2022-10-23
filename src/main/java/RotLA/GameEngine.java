package RotLA;

import RotLA.adventurers.*;
import RotLA.creatures.*;
import RotLA.treasures.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

// This class drives the game. This class has public and private methods which are highly cohesive. This class is an example of Encapsulation and cohesion
public class GameEngine {

    public int totalTreasureCount;
    public Boolean gameOver;
    public ArrayList<Adventurer> adventurers;
    public ArrayList<Creature> creatures;
    public ArrayList<Room> facility;

    public Tracker track = Tracker.getInstance();
    private Command command;

    /*Game engin constructor */
    GameEngine(){
        this.gameOver = Boolean.FALSE;
        this.totalTreasureCount =0;
        this.adventurers = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.facility = new ArrayList<>();
    }

    // This method initializes the game
    public void initializer(BoardRenderer board) {
        facilityInitializer();
        treasureInitializer();
        adventurersInitializer();
        creaturesInitializer();
        board.render(facility);
        board.gameState(adventurers,creatures);
    }

    // This method initializes the adventurers
    private void adventurersInitializer(){
        System.out.println("Choose type of Adventurer from below options:\n1. Brawler\n2. Sneaker\n3. Runner\n4. Thief\nEnter option number");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        // Adventurer is initialized based on user input using factory pattern
        switch (choice) {
            case 1 -> {
                Adventurer brawler = new BrawlerCreator().createAdventurer();
                adventurers.add(brawler);
            }
            case 2 -> {
                Adventurer sneaker = new SneakerCreator().createAdventurer();
                adventurers.add(sneaker);
            }
            case 3 -> {
                Adventurer runner = new RunnerCreator().createAdventurer();
                adventurers.add(runner);
            }
            case 4 -> {
                Adventurer thief = new ThiefCreator().createAdventurer();
                adventurers.add(thief);
            }
            default -> {
                System.out.println("Invalid choice");
                gameOver = Boolean.TRUE;
            }
        }

        System.out.println("Enter custom name for Adventurer");
        scan = new Scanner(System.in);
        adventurers.get(0).name = scan.nextLine();
        Room startingPoint = facility.get(0);
        for(Adventurer adv: adventurers){
            startingPoint.addAdventurerToList(adv.type);
            // Tracker is registered when an adventurer is initialized
            adv.registerSubscriber(track);
            adv.notifySubscribers(adv.type + " initialised " + adv.currentLocation);
        }

        System.out.println("Custom adventurer "+ adventurers.get(0).name +" of type "+ adventurers.get(0).type +" created!");
    }

    // This method initializes the Creatures
    private void creaturesInitializer(){
        for (int i = 1; i < 5; i++){
            Creature orbiter = new OrbiterCreator().createCreature(facility,i);
            orbiter.registerSubscriber(track);
            orbiter.notifySubscribers(orbiter.type + " initialised " + orbiter.currentLocation);
            Creature seeker = new SeekerCreator().createCreature(facility,i);
            seeker.registerSubscriber(track);
            seeker.notifySubscribers(seeker.type + " initialised " + seeker.currentLocation);
            Creature blinker = new BlinkerCreator().createCreature(facility,i);
            blinker.registerSubscriber(track);
            blinker.notifySubscribers(blinker.type + " initialised " + blinker.currentLocation);
            creatures.add(orbiter);
            creatures.add(seeker);
            creatures.add(blinker);
        }
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
        // Pick a random room from the facility and add a treasure
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
        for (Adventurer activeAdventurer : adventurers) {
            if (activeAdventurer.isAlive()) {
                adventurerCount++;
            }
        }
        return adventurerCount;
    }

    // Method checks how many creatures are alive
    private int check_creature_count(){
        int creatureCount = 0;
        for (Creature activeCreature : creatures) {
            if (activeCreature.isAlive) {
                creatureCount++;
            }
        }
        return creatureCount;
    }
    private void turn(BoardRenderer board, int turn){

        Logger log = Logger.getInstance(turn);
        Scanner scan;
        Creature fightingCre;

        // Gets all adventurers that are alive
        ArrayList<Adventurer> activeAdventurers = new ArrayList<>();
        for (Adventurer adv : adventurers){
            if(adv.isAlive())
                activeAdventurers.add(adv);
        }

        // Gets all creatures that are alive
        ArrayList<Creature> activeCreatures = new ArrayList<>();
        for (Creature cre : creatures){
            if(cre.isAlive)
                activeCreatures.add(cre);
        }

        // Logger is subscribed for all events of playing Adventurers in a turn
        for (Adventurer adv : activeAdventurers){
            adv.registerSubscriber(log);
        }

        // Logger is subscribed for all events of playing creatures in a turn
        for (Creature cre : activeCreatures){
            cre.registerSubscriber(log);
        }

        // Each alive adventurer gets to play
        for (Adventurer playingAdv : activeAdventurers){
            // User defines action for adventurer
            System.out.println("Enter action to be performed by Adventurer: " + playingAdv.name);
            Room currentRoom = getRoomObjectFromRoomId(playingAdv.currentLocation);
            if(currentRoom.isCreaturePresent()){
                System.out.println("Creature(s) present in Room. You can perform one of the fo1lowing actions\n1. Fight\n2. Move\nEnter action :");
            }
            else {
                System.out.println("Empty Room. You can perform one of the fo1lowing actions\n1. Move\n2. Search\n3. Celebrate\nEnter action :");
            }
            scan = new Scanner(System.in);
            String choice = scan.nextLine();
            switch (choice) {
                case "Move" -> {
                    int index =1;
                    ArrayList<String> connectedRooms = currentRoom.getConnectedRooms();
                    System.out.println("Select a room to move to");
                    for(String option:connectedRooms) {
                        System.out.println(index + ". " + option);
                        index++;
                    }
                    scan= new Scanner(System.in);
                    index = scan.nextInt();
                    Room newRoom = getRoomObjectFromRoomId(connectedRooms.get(index-1));
                    command = new MoveCommand(playingAdv,currentRoom,newRoom);
                    command.execute();
                    if(currentRoom.isCreaturePresent()){
                        System.out.println("You moved when Creature present in room. You will suffer 1 point damage for each creature in room!");
                        int count =0;
                        while ( count< currentRoom.getCreaturesInRoom().size()) {
                            playingAdv.updateFightOutcome();
                            count++;
                        }
                    }
                }

                case "Fight" -> {
                    playingAdv.performAction = "Fight";
                    ArrayList<String> creaturesInRoom = new ArrayList<>(currentRoom.getCreaturesInRoom());
                    for(String creatureToFight: creaturesInRoom) {
                        fightingCre = getCreatureObjectFromCreatureType(creatureToFight);
                        command = new FightCommand(playingAdv,fightingCre,currentRoom);
                        command.execute();
                        //playingAdv.fight(playingAdv,fightingCre,currentRoom);
                        // If playing Adventurer is dead after fight
                        if (!playingAdv.isAlive()) {
                            playingAdv.removeSubscriber(track);
                        }
                        // If fighting Creature is dead after fight
                        if (!fightingCre.isAlive) {
                            fightingCre.removeSubscriber(track);
                            activeCreatures.remove(fightingCre);
                        }
                    }
                }
                case "Search" -> {
                    playingAdv.performAction="Treasure";
                    int beforeSearch = playingAdv.treasureCount;
                    Boolean foundTreasure = Boolean.FALSE;
                    command = new SearchCommand(playingAdv,currentRoom);
                    command.execute();
                    if(playingAdv.treasureCount>beforeSearch)
                        foundTreasure = Boolean.TRUE;
                    if(foundTreasure){
                        this.totalTreasureCount++;                  //Treasure count increased if treasure is found
                        //Extra move for adventurer if treasure found was Portal
                        if(Objects.equals(playingAdv.treasureRetrieved.get(playingAdv.treasureRetrieved.size() - 1).treasureType, "Portal")){
                            System.out.println("Treasure found is Portal. Adventurer gets extra move!!");
                            // logic for extra move without damage
                        }
                    }
                }
                case "Celebrate" -> {
                    command = new CelebrateCommand(playingAdv);
                    command.execute();
                }
                default -> {
                    System.out.println("Invalid choice");
                    //gameOver = Boolean.TRUE;
                }
            }

            // Check if any end game condition is met after an adventurers turn
            if(!shouldGameContinue())
                return;
        }

        activeCreatures.removeIf(playingCre -> !playingCre.isAlive);


        // Each alive creature gets to play
        for (Creature playingCre : activeCreatures){
            Room currentRoom = getRoomObjectFromRoomId(playingCre.currentLocation);

            // Move if no adventurer is in room
            if(!currentRoom.isAdventurerPresent()){
                playingCre.move(facility);
            }

            currentRoom = getRoomObjectFromRoomId(playingCre.currentLocation);

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
                command = new FightCommand(fightingAdv,playingCre,currentRoom);
                command.execute();
                // If playing Creature is dead after fight
                if (!playingCre.isAlive) {
                    playingCre.removeSubscriber(track);
                }
                // If fighting Adventurer is dead after fight
                if (!fightingAdv.isAlive()) {
                    fightingAdv.removeSubscriber(track);
                    activeAdventurers.remove(fightingAdv);
                }
            }
            // Check if any end game condition is met after a creatures turn
            if(!shouldGameContinue())
                return;
        }

        // Logger is unsubscribed from playing Adventurers after a turn is over
        for (Adventurer adv : activeAdventurers){
            adv.removeSubscriber(log);
        }

        // Logger is unsubscribed from playing creatures after a turn is over
        for (Creature cre : activeCreatures){
            cre.removeSubscriber(log);
        }

        // Prints current board and game status
        board.render(facility);
        board.gameState(adventurers,creatures);
    }

    // This method checks if any termination condition is met and updates attribute gameOver. This is a private method. Example of Abstraction
    private Boolean shouldGameContinue(){

        // Game ends if all creatures are dead
        if (check_creature_count() == 0){
            if(!Objects.equals(adventurers.get(0).currentLocation, "0-1-1"))
                return Boolean.TRUE;
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

        // Game ends if all treasures are found
        if (check_treasure_count() == 24){
            if(!Objects.equals(adventurers.get(0).currentLocation, "0-1-1"))
                return Boolean.TRUE;
            System.out.println("    Game Over: All treasures found\n");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }

        if(Objects.equals(adventurers.get(0).currentLocation, "0-1-1")){
            System.out.println("    Game Over: Adventurer entered room 0-1-1\n");
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
            turn(board,turn);
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
        for (Creature creature : creatures) {
            if (creature.type.equals(id))
                cre = creature;
        }
        return cre;
    }

    //Method to get Adventurer object using Adventurer type
    private Adventurer getAdventurerObjectFromAdventurerType(String id){
        Adventurer adv = new Adventurer();
        for (Adventurer adventurer : adventurers) {
            if (adventurer.type.equals(id))
                adv = adventurer;
        }
        return adv;
    }
}
