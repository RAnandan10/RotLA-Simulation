import java.util.ArrayList;

public class GameEngine {

    public int totalTreasureCount;
    public ArrayList<Adventurer> activeAdventurers;
    public ArrayList<Creature> activeCreatures;
    public ArrayList<Room> facility;
    public static void main(String[] args) {
        GameEngine g = new GameEngine();
        g.initializer();
    }
    public void initializer(){
        facilityInitializer();
        adventurersInitializer();
        creaturesInitializer();

        BoardRenderer rend = new BoardRenderer();
        rend.render(facility);
        rend.gameState(activeAdventurers,activeCreatures);
    }

    private void adventurersInitializer(){
        activeAdventurers = new ArrayList<Adventurer>();
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
        activeCreatures = new ArrayList<>();
        for (int i = 1; i < 5; i++){
            Orbiter orbiter = new Orbiter(i);
            activeCreatures.add(orbiter);
            Seeker seeker = new Seeker(i);
            activeCreatures.add(seeker);
            Blinker blinker = new Blinker(i);
            activeCreatures.add(blinker);
        }
    }

    private void facilityInitializer(){
        facility = new ArrayList<Room>();
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
        for (int i = 0; i<n; i++){
            if (activeAdventurers[i].isAlive){
                adventurerCount ++;
            }
        }
        return adventurerCount;
    }

    private int check_creature_count(){
        int creatureCount = 0;
        for (int i = 0; i<n; i++){
            if (activeCreatures[i].isAlive){
                adventurerCount ++;
            }
        }
        return creatureCount;
    }

}