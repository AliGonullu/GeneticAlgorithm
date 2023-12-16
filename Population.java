package GeneticAlgorithm;
import java.util.Random;

public class Population {
	Individual[] individuals;

    public Population(int populationSize, boolean initialize) {
        individuals = new Individual[populationSize];
        if (initialize) 
            for (int i = 0; i < populationSize; i++) 
                individuals[i] = new Individual(generateRandomState());                  
    }

    
    private BoardState generateRandomState() {
        Color[][] board = new Color[GeneticAlgo.BOARD_SIZE][GeneticAlgo.BOARD_SIZE];
        Random random = new Random();

        for (int i = 0; i < board.length; i++) 
            for (int j = 0; j < board[i].length; j++) 
                board[i][j] = Color.values()[random.nextInt(Color.values().length)];                 

        return new BoardState(board);
    }

    
    public Individual getFittest() {
        Individual fittest = individuals[0];
        for (Individual individual : individuals) 
            if (individual.fitness > fittest.fitness) 
                fittest = individual;
        
        return fittest;
    }
}
