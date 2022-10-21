package RotLA;

public class RotLA {
    public static void main(String[] args) {
        for (int i =0; i< 1 ; i++){

            System.out.println("Game simulation " + (i+1));
            GameEngine g = new GameEngine();
            BoardRenderer board = new BoardRenderer();
            g.initializer(board);
            g.playGame(board);

        }

    }

}
