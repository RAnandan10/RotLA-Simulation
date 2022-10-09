package RotLA;

public interface Combat{
    String fight(int advDice, int creDice);
}

class Trained implements Combat{
public String fight(int advRoll, int creRoll){
        advRoll = advRoll + 1;
        if (advRoll > creRoll){
            return "Adventurer wins!";
        }
        else if(advRoll < creRoll){
            return "Creature wins!";
        }
        else{
            return "Draw";
        }
    }
}

class Untrained implements Combat{
    public String fight(int advRoll, int creRoll){
        if (advRoll > creRoll){
            return "Adventurer wins!";
        }
        else if(advRoll < creRoll){
            return "Creature wins!";
        }
        else{
            return "Draw";
        }
    }
}
class Expert implements Combat{
    public String fight(int advRoll, int creRoll){
        advRoll = advRoll + 2;
        if (advRoll > creRoll){
            return "Adventurer wins!";
        }
        else if(advRoll < creRoll){
            return "Creature wins!";
        }
        else{
            return "Draw";
        }
    }
}

class Stealth implements Combat {
    public String fight(int advRoll, int creRoll){
        int probability = Random.nextInt(2);
        if(probability == 0){
            return "No Fight";
        }
        if (advRoll > creRoll){
            return "Adventurer wins!";
        }
        else if(advRoll < creRoll){
            return "Creature wins!";
        }
        else{
            return "Draw";
        }   
    }
}