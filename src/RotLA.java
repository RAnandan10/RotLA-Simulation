public class RotLA {
    public static void main(String[] args) {
        GameEngine g = new GameEngine();
        BoardRenderer board = new BoardRenderer();
        g.initializer(board);
        g.playGame(board);
    }

}
