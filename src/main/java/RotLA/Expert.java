package RotLA;

public class Expert implements Combat {
    public String fight(int advRoll, int creRoll) {
        advRoll = advRoll + 2;
        if (advRoll > creRoll) {
            return "Adventurer wins!";
        } else if (advRoll < creRoll) {
            return "Creature wins!";
        } else {
            return "Draw";
        }
    }
}
