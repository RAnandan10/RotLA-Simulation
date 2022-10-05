import java.util.Random;
class Stealth extends Adventurer implements Combat {
    public int fight(){
        Random random = new Random();
        int probability = random.nextInt(2); 
        if(probability == 0){
            return 0;
        }
        else{
            
        }              
    }
}