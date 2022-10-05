class Trained{
    public int rollDice(){
        Random random = new Random();
        int dice1 = random.nextInt(6)+1;            // Dice1 is rolled
        int dice2 = random.nextInt(6)+1;            // Dice2 is rolled
        return dice1+dice2;                         // Sum of the 2 dice rolls is passed
    }
}