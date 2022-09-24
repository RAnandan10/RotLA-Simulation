import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    public int totalTreasureCount;
    public Boolean gameOver;
    public ArrayList<Adventurer> activeAdventurers;
    public ArrayList<Creature> activeCreatures;
    public ArrayList<Room> facility;

    /*Game engin constructor */
    GameEngine(){
        this.gameOver = Boolean.FALSE;
        this.totalTreasureCount =0;
        this.activeAdventurers = new ArrayList<Adventurer>();
        this.activeCreatures = new ArrayList<Creature>();
        this.facility = new ArrayList<Room>();
    }

    // This method initializes the game
    public void initializer(BoardRenderer board) {
        facilityInitializer();
        adventurersInitializer();
        creaturesInitializer();
        board.render(facility);
        board.gameState(activeAdventurers,activeCreatures);
    }

    // This method initializes the adventurers
    private void adventurersInitializer(){
        Brawler brawler = new Brawler();
        Sneaker sneaker = new Sneaker();
        Runner runner = new Runner();
        Thief thief = new Thief();
        activeAdventurers.add(brawler);
        activeAdventurers.add(sneaker);
        activeAdventurers.add(runner);
        activeAdventurers.add(thief);
        Room startingPoint = facility.get(0);
        for(Adventurer adv: activeAdventurers)
            startingPoint.addAdventurerToList(adv.type);
    }

    // This method initializes the Creatures
    private void creaturesInitializer(){
        Orbiter orbiter1 = new Orbiter(facility);
        Orbiter orbiter2 = new Orbiter(facility);
        Orbiter orbiter3 = new Orbiter(facility);
        Orbiter orbiter4 = new Orbiter(facility);
        activeCreatures.add(orbiter1);
        activeCreatures.add(orbiter2);
        activeCreatures.add(orbiter3);
        activeCreatures.add(orbiter4);
        Seeker seeker1 = new Seeker(facility);
        Seeker seeker2 = new Seeker(facility);
        Seeker seeker3 = new Seeker(facility);
        Seeker seeker4 = new Seeker(facility);
        activeCreatures.add(seeker1);
        activeCreatures.add(seeker2);
        activeCreatures.add(seeker3);
        activeCreatures.add(seeker4);
        Blinker blinker1 = new Blinker(facility);
        Blinker blinker2 = new Blinker(facility);
        Blinker blinker3 = new Blinker(facility);
        Blinker blinker4 = new Blinker(facility);
        activeCreatures.add(blinker1);
        activeCreatures.add(blinker2);
        activeCreatures.add(blinker3);
        activeCreatures.add(blinker4);
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
    private void turn(BoardRenderer board){

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
                fight(getCreatureObjectFromCreatureType(creatureToFight), playingAdv);
            }
            // If Creature is not present in room then search treasure
            else {
                playingAdv.performAction="Treasure";
                Boolean foundTreasure = playingAdv.findTreasure();
                if(foundTreasure){
                    this.totalTreasureCount++;
                }
            }
            // Check if any end game condition is met after a adventurers turn
            if(!shouldGameContinue())
                return;
        }


        // Each alive creature gets to play
        for (Creature playingCre : playingCreatures){
            Room currentRoom = getRoomObjectFromRoomId(playingCre.currentLocation);

            // Don't move if adventurer is in room
            if(currentRoom.isAdventurerPresent()){
                continue;
            }
            else {
                playingCre.move(facility);
                currentRoom.removeCreatureFromList(playingCre.type);
                Room newRoom = getRoomObjectFromRoomId(playingCre.currentLocation);
                newRoom.addCreatureToList(playingCre.type);
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
                fight(playingCre, fightingAdv);
            }

            // Check if any end game condition is met after a creatures turn
            if(!shouldGameContinue())
                return;
        }

        // Prints current board and game status
        board.render(facility);
        board.gameState(activeAdventurers,activeCreatures);
    }

    //Method is used to simulate fight between adventurer and creature. This is a private method. Example of Abstraction
    private void fight(Creature c, Adventurer a){
        Room fightRoom;

        //If adventurer is a sneaker then 50% of the time it escapes
        if (a.involveInFight() == Boolean.FALSE){
            return;
        }
        int c_sum = c.rollDice();               // Creature rolls dice
        int a_sum = a.rollDice();               // Adventurer rolls dice

        if (c_sum > a_sum){                                             // If creature wins fight
            a.updateFightOutcome();                                     // Update adventurer health
            if (!a.isAlive()){                                          // Remove adventurer from room if dead
                fightRoom = getRoomObjectFromRoomId(a.currentLocation);
                fightRoom.removeAdventurerFromList(a.type);
            }
        }
        else if (c_sum < a_sum){                                           // If adventurer wins fight
            c.updateFightOutcome();                                         // Update creature to dead
            fightRoom = getRoomObjectFromRoomId(c.currentLocation);
            fightRoom.removeCreatureFromList(c.type);                     // Remove creature from room
        }
    }

    // This method checks if any termination condition is met and updates attribute gameOver. This is a private method. Example of Abstraction
    private Boolean shouldGameContinue(){

        // Game ends if all creatures are dead
        if (check_creature_count() == 0){
            System.out.println("Game Over : All creatures are dead");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }

        // Game ends if all adventurers are dead
        if (check_adventurer_count() == 0){
            System.out.println("Game Over: All Adventurers are dead");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }

        // Game ends if 10 treasures are found
        if (check_treasure_count() == 10){
            System.out.println("Game Over: 10 treasures found");
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
            turn(board);
            turn ++;
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