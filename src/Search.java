import java.util.Random;
interface Search {
    public void search();
    
}

class Careful implements Search{
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