package RotLA;

public interface Combat{
    public int fight(int advDice, int creDice);
}

class Trained implements Combat{
public int fight(int advRoll, int creRoll){
        advRoll = advRoll + 1;
        if (advRoll > creRoll){
            return 1;
        }
        else if(advRoll < creRoll){
            return -1;
        }
        else{
            return 0;
        }
    }
}

class Untrained implements Combat{
    public int fight(int advRoll, int creRoll){
        if (advRoll > creRoll){
            return 1;
        }
        else if(advRoll < creRoll){
            return -1;
        }
        else{
            return 0;
        }
    }
}
class Expert implements Combat{
    public int fight(int advRoll, int creRoll){
        advRoll = advRoll + 2;
        if (advRoll > creRoll){
            return 1;
        }
        else if(advRoll < creRoll){
            return -1;
        }
        else{
            return 0;
        }
    }
}

class Stealth implements Combat {
    public int fight(int advRoll, int creRoll){
        int probability = Random.nextInt(2);
        if(probability == 0){
            return 0;
        }

        if (advRoll > creRoll){
            return 1;
        }
        else if(advRoll < creRoll){
            return -1;
        }
        else{
            return 0;
        }   
    }
}