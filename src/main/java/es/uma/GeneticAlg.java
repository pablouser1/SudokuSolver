package es.uma;

import com.qqwing.Difficulty;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import java.util.Arrays;

public class GeneticAlg {
    public static void main(String[] args) throws InvalidConfigurationException {
        int option = 1;

        int []puzzle = {};
        switch (option) {
            case 1:
                //Extremely easy puzzle, should be solvable without tuning the parameters of the genetic algorithm
                puzzle = Puzzle.computePuzzleWithNHolesPerRow(3);
                break;
            case 2:
                //Puzzle with difficulty SIMPLE as assessed by QQWing.
                //Should require just minimal tuning of the parameters of the genetic algorithm
                puzzle = Puzzle.computePuzzleByDifficulty(Difficulty.SIMPLE);
                break;
            case 3:
                //Puzzle with difficulty EASY as assessed by QQWing.
                //Should require some tuning of the parameters of the genetic algorithm
                puzzle = Puzzle.computePuzzleByDifficulty(Difficulty.EASY);
                break;
            case 4:
                //Puzzle with difficulty INTERMEDIATE as assessed by QQWing.
                //Should require serious effort tuning the parameters of the genetic algorithm
                puzzle = Puzzle.computePuzzleByDifficulty(Difficulty.INTERMEDIATE);
                break;
            case 5:
                //Puzzle with difficulty EXPERT as assessed by QQWing.
                //Should require great effort tuning the parameters of the genetic algorithm
                puzzle = Puzzle.computePuzzleByDifficulty(Difficulty.EXPERT);
                break;
        }

        int[][] matrix = Helpers.monoToBidi(puzzle, 9, 9);

        printMatrix(matrix);

        boolean valid = Conds.checkValid(matrix);

        if (!valid) {
            System.out.println("Not valid");
            return;
        }

        Configuration conf = new DefaultConfiguration();
        FitnessFunction fit = new SudokuFitnessFunc(matrix);

        conf.setFitnessFunction(fit);
        Gene[] sampleGenes = new Gene[81];
        for (int i = 0; i < 81; i++) {
            sampleGenes[i] = new IntegerGene(conf, 1, 9);
        }

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(50);
        Genotype population = Genotype.randomInitialGenotype(conf);
        for (int i = 0; i < 1000; i++) {
            population.evolve();
        }
        IChromosome fittest = population.getFittestChromosome();
        printSol(fittest.getGenes());
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printSol(Gene[] genes) {
        System.out.println("SOLUTION:");
        int index = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(genes[index++].getAllele() + " ");
            }
            System.out.println();
        }
    }
}
