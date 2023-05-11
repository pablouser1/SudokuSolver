package es.uma;

import com.qqwing.Difficulty;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import java.util.Arrays;

public class GeneticAlg {
    public static void main(String[] args) throws InvalidConfigurationException {
        int option = 1;
        //int option = 2;
        //int option = 3;
        //int option = 4;
        // int option = 5;

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

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
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
        conf.setPopulationSize(81);
        Genotype population = Genotype.randomInitialGenotype(conf);
        for (int i = 0; i < 1000; i++) {
            population.evolve();
            IChromosome fittest = population.getFittestChromosome();
            if (fit.getFitnessValue(fittest) == 0.0) {
                System.out.println("Solution found in " + i + " generations.");
            }
        }


        System.out.println(Arrays.toString(puzzle));
    }
}
