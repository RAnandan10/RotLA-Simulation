import java.util.ArrayList;

 class Adventurer {

    int damage;
    int treasureCount;
    String type;
    String currentLocation;
    String performAction;

    Adventurer(){
        this.damage = 0;
        this.treasureCount = 0;
        this.currentLocation = "0-1-1";
        this.performAction = null;
    }

    public Boolean isAlive(){
        if(this.damage < 3)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public String move(ArrayList<Room> facility){
        Room currentRoom = getRoomObjectFromRoomId(this.currentLocation,facility);
        ArrayList<String> neighbouringRooms = currentRoom.connectedRooms;
        int options = neighbouringRooms.size();
        int index = (int)(Math.random()*options); //Gives random index in neighbouringRooms to go to
        this.currentLocation = neighbouringRooms.get(index);
        return this.currentLocation;
    }

    public Boolean findTreasure(){
        int sum = this.rollDice();
        if(sum>= 10){
            this.treasureCount++;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    //https://math.hws.edu/eck/cs124/javanotes4/c5/ex-5-1-answer.html
    public int rollDice(){
        int die1 = (int)(Math.random()*6) + 1;
        int die2 = (int)(Math.random()*6) + 1;
        return die1+die2;
    }

    public void updateFightOutcome(){
        this.damage++;
    }

    public Boolean involveInFight(){
        return Boolean.TRUE;
    }

     public Room getRoomObjectFromRoomId(String id, ArrayList<Room> facility){
         Room r = new Room(null);
         for (Room room : facility) {
             if (room.id.equals(id))
                 r = room;
         }
         return r;
     }
}
