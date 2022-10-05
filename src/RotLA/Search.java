package RotLA;
import java.util.Random;
interface Search {
    public void search();
    
}

/* Assumptin is the Game engin will call move which is implemented in adventure class
 * and move will call search which is implemented in search class.
 * 
 */

class Careful implements Search{
    public void search(){
        Random random = new Random();
        int probability = random.nextInt(2); 
        int advRoll = Random.RollTwoDice();
        if(advRoll >= 6){
            //tressure found
            if (probability == 0){
                //escape the trap
            }
            else{
                //tressure not found
            }
            System.out.println("You found nothing");
        }
        
    }
}

class Quick implements Search{
    public void search(){
        Random random = new Random();
        int probability = random.nextInt(3); 
        int advRoll = Random.RollTwoDice();
        if (probability == 2){
            //skip the search
        }
        else if(advRoll > 9){
            //tressure found
            System.out.println("You found something");
        }
}

class Careless implements Search{
    public void search(){
        Random random = new Random();
        int advRoll = Random.RollTwoDice();

        if(advRoll > 10){
            //tressure found
            System.out.println("You found something");
        }
        
    }
}