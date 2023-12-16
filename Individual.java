package GeneticAlgorithm;
public class Individual {
	BoardState state;
    int fitness;

    public Individual(BoardState state) {
        this.state = state;
        this.fitness = calculateFitness();
    }

    private int calculateFitness() {
        int penalty = 0;
        for (int i = 0; i < state.board.length - 1; i++) 
            for (int j = 0; j < state.board[i].length - 1; j++) 
                if (state.board[i][j] == state.board[i + 1][j] || state.board[i][j] == state.board[i][j + 1]) 
                    penalty++;     
        return -penalty;
    }
}
