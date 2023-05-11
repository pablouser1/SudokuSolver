package es.uma;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class SudokuFitnessFunc extends FitnessFunction {
    private final int[][] matrix;

    public SudokuFitnessFunc(int[][] matrix) {
        this.matrix = matrix;
    }

    private int checkRows() {
        int broken = 0;

        for (int i = 0; i < 9; i++) {
            int[] row = matrix[i];

            for (int j = 0; j < 9; j++) {
                int num = row[j];

                for (int k = 0; k < 9; k++) {
                    if (j != k) {
                        if (num == matrix[i][k]) {
                            broken++;
                        }
                    }
                }
            }
        }

        return broken;
    }

    private int checkCols() {
        int broken = 0;

        int[] cols = this.matrix[0];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = cols[i];
                if (i != j) {
                    if  (num != cols[j]) {
                        broken++;
                    }
                }
            }
        }

        return broken;
    }

    private int checkSector() {
        int broken = 0;
        int st = matrix.length / 3;

        for (int i = 0; i < st; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                if (i != j) {
                    if (this.matrix[i][j] != this.matrix[st][j]) {
                        broken++;
                    }
                }
            }
        }
        return broken;
    }

    @Override
    protected double evaluate(IChromosome iChromosome) {
        int brokenNumRows = this.checkRows();
        int brokenNumCols = this.checkCols();
        int brokenNumSector = this.checkSector();

        return brokenNumCols + brokenNumRows + brokenNumSector;
    }
}
