public class RotLA {
    public static void main(String[] args) {
        for (int i =0; i< 30 ; i++){

            System.out.println("Game simulation " + i);
            GameEngine g = new GameEngine();
            BoardRenderer board = new BoardRenderer();
            g.initializer(board);
            g.playGame(board);

        }

    }

}
