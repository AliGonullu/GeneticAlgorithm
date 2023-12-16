package GeneticAlgorithm;
import java.util.Arrays;

public class BoardState {
	Color[][] board;

    public BoardState(Color[][] board) {
        this.board = board;
    }

    public BoardState copy() {
        Color[][] copyBoard = new Color[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) 
            copyBoard[i] = Arrays.copyOf(board[i], board[i].length);
        
        return new BoardState(copyBoard);
    }
}
