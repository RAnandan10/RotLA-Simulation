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

    public void initializer(BoardRenderer board) {
        facilityInitializer();
        adventurersInitializer();
        creaturesInitializer();
        board.render(facility);
        board.gameState(activeAdventurers,activeCreatures);
    }

    private Room getRoomObjectFromRoomId(String id){
        Room r = new Room(null);
        for (Room room : facility) {
            if (room.id.equals(id))
                r = room;
        }
        return r;
    }

    private Creature getCreatureObjectFromRoomId(String id){
        Creature cre = new Creature();
        for (Creature creature : activeCreatures) {
            if (creature.type.equals(id))
                cre = creature;
        }
        return cre;
    }
    private Adventurer getAdventurerObjectFromRoomId(String id){
        Adventurer adv = new Adventurer();
        for (Adventurer adventurer : activeAdventurers) {
            if (adventurer.type.equals(id))
                adv = adventurer;
        }
        return adv;
    }

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

    private void creaturesInitializer(){
        for (int i = 1; i < 5; i++){
            Orbiter orbiter = new Orbiter(facility);
            activeCreatures.add(orbiter);
            Seeker seeker = new Seeker(facility);
            activeCreatures.add(seeker);
            Blinker blinker = new Blinker(facility);
            activeCreatures.add(blinker);
        }
        System.out.println(activeCreatures.size());
    }

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

    private int check_treasure_count(){
        return totalTreasureCount;
    }

    private int check_adventurer_count(){
        int adventurerCount = 0;
        for (int i = 0; i<activeAdventurers.size(); i++){
            if (activeAdventurers.get(i).isAlive()){
                adventurerCount ++;
            }
        }
        return adventurerCount;
    }

    private int check_creature_count(){
        int creatureCount = 0;
        for (int i = 0; i<activeCreatures.size(); i++){
            if (activeCreatures.get(i).isAlive){
                creatureCount++;
            }
        }
        return creatureCount;
    }
    private void turn(BoardRenderer board){

        ArrayList<Adventurer> playingAdventures = new ArrayList<>();
        for (Adventurer adv : activeAdventurers){
            if(adv.isAlive())
                playingAdventures.add(adv);
        }

        ArrayList<Creature> playingCreatures = new ArrayList<>();
        for (Creature cre : activeCreatures){
            if(cre.isAlive)
                playingCreatures.add(cre);
        }

        for (Adventurer playingAdv : playingAdventures){
            Room currentRoom = getRoomObjectFromRoomId(playingAdv.currentLocation);
            playingAdv.move(facility);
            currentRoom.removeAdventurerFromList(playingAdv.type);
            Room newRoom = getRoomObjectFromRoomId(playingAdv.currentLocation);
            newRoom.addAdventurerToList(playingAdv.type);
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
                fight(getCreatureObjectFromRoomId(creatureToFight), playingAdv);
            }
            else {
                playingAdv.performAction="Treasure";
                Boolean foundTreasure = playingAdv.findTreasure();
                if(foundTreasure){
                    this.totalTreasureCount++;
                }
            }
            if(!shouldGameContinue())
                return;
        }


        //Creature turn
        for (Creature playingCre : playingCreatures){
            Room currentRoom = getRoomObjectFromRoomId(playingCre.currentLocation);
            if(currentRoom.isAdventurerPresent()){
                continue;
            }
            else {
                playingCre.move(facility);
                currentRoom.removeCreatureFromList(playingCre.type);
                Room newRoom = getRoomObjectFromRoomId(playingCre.currentLocation);
                newRoom.addCreatureToList(playingCre.type);
            }

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
                Adventurer fightingAdv = getAdventurerObjectFromRoomId(adventurerToFight);
                fightingAdv.performAction="Fight";
                fight(playingCre, fightingAdv);
            }
            board.render(facility);
            board.gameState(activeAdventurers,activeCreatures);

            if(!shouldGameContinue())
                return;
        }
    }
    /* roll the dice and update the outcome */
    private void fight(Creature c, Adventurer a){
        Room fightRoom;
        //if adventurer is a sneeker then 50% of thime it escapes
        if (a.involveInFight() == Boolean.FALSE){
            return;
        }
        int c_sum = c.rollDice();
        int a_sum = a.rollDice();

        if (c_sum > a_sum){
            a.updateFightOutcome();
            if (!a.isAlive()){
                fightRoom = getRoomObjectFromRoomId(a.currentLocation);
                fightRoom.removeAdventurerFromList(a.type);
            }
        }
        else{
            c.updateFightOutcome();
            fightRoom = getRoomObjectFromRoomId(c.currentLocation);
            fightRoom.removeAdventurerFromList(c.type);
        }
        //if creature won the fight 
    }

    private Boolean shouldGameContinue(){
        if (check_creature_count() == 0){
            System.out.println("Game Over : All creatures are dead");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
            
        }

        if (check_adventurer_count() == 0){
            System.out.println("Game Over: All Adventurers are dead");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }

        if (check_treasure_count() == 10){
            System.out.println("Game Over: 10 tressure found");
            gameOver = Boolean.TRUE;
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }

    public void playGame(BoardRenderer board){
        int turn = 1;
        while(!gameOver){
            System.out.println("Turn" + turn);
            turn ++;
            turn(board);
        }   
    }
}