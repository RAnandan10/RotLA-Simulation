package RotLA;

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
        if(probability == 0){
            // escape the fight
            System.out.println("You found nothing");
        }
        else{
            // fight
            System.out.println("You found something");
        }
        
    }
}

class Quick implements Search{
    public void search(){
        int advRoll = Random.RollTwoDice();
        int creRoll = Random.RollTwoDice();
        if (advRoll > creRoll){
            System.out.println("You found something!");
        }
        else if(advRoll < creRoll){
            System.out.println("You found nothing.");
        }
        else{
            System.out.println("You found something!");
        }
    }   
}

class Careless implements Search{
    public void search(){
        int advRoll = Random.RollTwoDice();
        int creRoll = Random.RollTwoDice();
        if (advRoll > creRoll){
            System.out.println("You found something!");
        }
        else if(advRoll < creRoll){
            System.out.println("You found nothing.");
        }
        else{
            System.out.println("You found something!");
        }
    }
}