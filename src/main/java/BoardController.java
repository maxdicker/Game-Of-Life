public class BoardController {
    private BoardRuler ruler = new BoardRuler();
    private BoardActioner actioner = new BoardActioner();

    public Board nextGeneration(Board board) {
        BoardAction action = ruler.determineBoardAction(board);
        board = actioner.transformBoard(board, action);

        return board;
    }

}
