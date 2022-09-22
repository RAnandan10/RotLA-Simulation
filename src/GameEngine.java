import java.util.ArrayList;

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

    public void initializer() {
        facilityInitializer();
        adventurersInitializer();
        creaturesInitializer();

        BoardRenderer rend = new BoardRenderer();
        rend.render(facility);

    }

    private Room getRoomObjectFromRoomId(String id){
        Room r = new Room(null);
        for (Room room : facility) {
            if (room.id.equals(id))
                r = room;
        }
        return r;
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
            Orbiter orbiter = new Orbiter(i);
            activeCreatures.add(orbiter);
            Seeker seeker = new Seeker(i);
            activeCreatures.add(seeker);
            Blinker blinker = new Blinker(i);
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
    private void turn(){
        //get adventure list
        //for all adventurer{
            //move(facility)
            //if creature then fight
            //else find tressure

            //if sneeker fight twice
            /*if (a.findTreasure){
                //update total tressure count
            }*/
             //check for end game
        //}
       

        //getcreature list
        //for all creature{
            //move(facility)
            //check for end game
        //}


    }
    /* roll the dice and update the outcome */
    private void fight(Creature c, Adventurer a){    
        //if adventurer is a sneeker then 50% of thime it escapes
        if (a.involveInFight() == Boolean.FALSE){
            return;
        }
        int c_sum = c.rollDice();
        int a_sum = a.rollDice();

        if (c_sum > a_sum){
            a.updateFightOutcome();
            if (!a.isAlive()){
                // update adventure list in room
                //a.currentLocation()

            }
        }
        else{
            c.updateFightOutcome();
            // update adventure list in room
            //a.currentLocation() 
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

    private void playGame(){
        int turnCount = 0;
        while(!gameOver){
            System.out.println("Turn Count",turnCount);
            turnCount ++;
            turn();
        }   
    }
}