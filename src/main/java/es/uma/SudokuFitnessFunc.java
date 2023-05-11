package es.uma;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class SudokuFitnessFunc extends FitnessFunction {
    private final int[][] matrix;

    public SudokuFitnessFunc(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    protected double evaluate(IChromosome iChromosome) {
        return Conds.getFit(this.matrix);
    }
}
