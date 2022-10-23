package RotLA.adventurers;

import RotLA.Room;

public class MoveCommand implements Command {
    private Adventurer playingAdv;
    private Room currentRoom;
    private Room newRoom;

    public MoveCommand(Adventurer adv, Room currentRoom, Room newRoom) {
        this.playingAdv = adv;
        this.currentRoom = currentRoom;
        this.newRoom=newRoom;
    }

    public void execute() {
        playingAdv.move(currentRoom,newRoom);
    }
}
