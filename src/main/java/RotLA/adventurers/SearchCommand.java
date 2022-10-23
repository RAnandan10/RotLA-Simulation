package RotLA.adventurers;

import RotLA.Room;

public class SearchCommand implements Command {
    private Adventurer playingAdv;
    private Room searchRoom;

    public SearchCommand(Adventurer adv, Room room) {
        this.playingAdv = adv;
        this.searchRoom = room;
    }

    public void execute() {
        playingAdv.findTreasure(searchRoom);
    }
}
