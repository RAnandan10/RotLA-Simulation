package RotLA.combat;

import RotLA.Random;
interface Combat{
    public int fight();
    //public int rollDice();
}


class UnTrained implements Combat{
    public int fight(){
        int advRoll = Random.RollTwoDice();
        int creRoll = Random.RollTwoDice();
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

class Trained implements Combat{
    public int fight(){
        int advRoll = Random.RollTwoDice();
        int creRoll = Random.RollTwoDice();
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
    public int fight(){

        int advRoll = Random.RollTwoDice() + 2;
        int creRoll = Random.RollTwoDice();
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
    public int fight(){
        Random random = new Random();
        int probability = random.nextInt(2);
        if(probability == 0){
            return 0;
        }
        else{
            return 1;
        }
    }
}