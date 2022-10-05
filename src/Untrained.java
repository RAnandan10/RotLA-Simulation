import java.util.Random;
class Trained{
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
        return 1;
                    
    }
}