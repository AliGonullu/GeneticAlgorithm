package GeneticAlgorithm;

import java.util.Scanner;

enum Color {Orange, Cyan__, Blue__, Red___}

public class GeneticAlgo {
    public static int BOARD_SIZE = 5;
    private static final int POPULATION_SIZE = 75;
    private static final double CROSSOVER_RATE = 0.4;
    private static final double MUTATION_RATE = 0.01;
    private static final int MAX_GENERATIONS = 2000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Determine The Size of The Table : ");
		int size = scanner.nextInt();
				
		BOARD_SIZE = size;
		
		Population population = new Population(POPULATION_SIZE, true);
        System.out.println("\nInitial Population:");
        printPopulation(population);

        for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
            Population newPopulation = evolvePopulation(population);
            population = newPopulation;
        }

        Individual bestIndividual = population.getFittest();
        System.out.println("\nBest Solution (Fitness: " + bestIndividual.fitness + "):");
        printBoard(bestIndividual.state);
        scanner.close();
    }
    

    private static void printPopulation(Population population) {
        for (int i = 0; i < population.individuals.length; i++) {
            System.out.print("\nIndividual " + i + ": \n-------------------------------------------------");
            printBoard(population.individuals[i].state);
        }
    }

    
    private static void printBoard(BoardState state) {
    	System.out.println();
        for (int i = 0; i < state.board.length; i++) {
            for (int j = 0; j < state.board[i].length; j++) 
                System.out.print(state.board[i][j] + " | ");          
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }

    
    private static Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.individuals.length, false);

        newPopulation.individuals[0] = population.getFittest();

        for (int i = 1; i < population.individuals.length; i++) {
            Individual parent1 = selectParent(population);
            Individual parent2 = selectParent(population);

            Individual child = singlePointCrossover(parent1, parent2);
            mutation(child);

            newPopulation.individuals[i] = child;
        }

        return newPopulation;
    }

    
    private static Individual selectParent(Population population) {
        int tournamentSize = 5;
        Population tournament = new Population(tournamentSize, false);

        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = (int) (Math.random() * population.individuals.length);
            tournament.individuals[i] = population.individuals[randomIndex];
        }

        return tournament.getFittest();
    }
    

    private static Individual singlePointCrossover(Individual parent1, Individual parent2) {
        BoardState childState = parent1.state.copy();
        int crossoverPoint = (int) (Math.random() * childState.board.length * childState.board[0].length);

        int row = crossoverPoint / childState.board[0].length;
        int col = crossoverPoint % childState.board[0].length;

        for (int i = 0; i < childState.board.length; i++) 
            for (int j = 0; j < childState.board[i].length; j++) 
                childState.board[i][j] = (i < row || (i == row && j <= col)) ? parent1.state.board[i][j] : parent2.state.board[i][j];                
        return new Individual(childState);
    }
    

    private static void mutation(Individual individual) {
        for (int i = 0; i < individual.state.board.length; i++) 
            for (int j = 0; j < individual.state.board[i].length; j++) 
                if (Math.random() < MUTATION_RATE) 
                    individual.state.board[i][j] = getRandomColor();     
    }

    
    private static Color getRandomColor() {
        Color[] colors = Color.values();
        return colors[(int) (Math.random() * colors.length)];
    }
}