package RotLA;

import RotLA.creatures.*;

import java.util.ArrayList;

public class CreatureCreator {
    public Creature createCreature(ArrayList<Room> facility, int number) {
        return new Creature();
    }
}

class OrbiterCreator extends CreatureCreator{
    @Override
    public Creature createCreature(ArrayList<Room> facility, int number) {
        return new Orbiter(facility,number);
    }
}

class BlinkerCreator extends CreatureCreator{
    @Override
    public Creature createCreature(ArrayList<Room> facility, int number) {
        return new Blinker(facility,number);
    }
}

class SeekerCreator extends CreatureCreator{
    @Override
    public Creature createCreature(ArrayList<Room> facility, int number) {
        return new Seeker(facility,number);
    }
}