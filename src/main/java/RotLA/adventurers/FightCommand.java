package RotLA.adventurers;

import RotLA.Room;
import RotLA.creatures.Creature;

public class FightCommand implements Command {
    private Adventurer fightingAdv;
    private Creature fightingCreature;
    private Room fightRoom;

    public FightCommand(Adventurer fightingAdv, Creature fightingCre, Room room) {
        this.fightingAdv = fightingAdv;
        this.fightingCreature = fightingCre;
        this.fightRoom = room;
    }

    public void execute() {
        fightingAdv.fight(fightingAdv, fightingCreature, fightRoom);
    }
}
