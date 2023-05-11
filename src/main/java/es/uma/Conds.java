package es.uma;

public class Conds {
    static public int checkRows(int[][] matrix) {
        int broken = 0;

        for (int i = 0; i < 9; i++) {
            int[] row = matrix[i];

            for (int j = 0; j < 9; j++) {
                int num = row[j];

                for (int k = 0; k < 9; k++) {
                    if (j != k && num != 0) {
                        if (num == matrix[i][k]) {
                            broken++;
                        }
                    }
                }
            }
        }

        return broken;
    }

    static public int checkCols(int[][] matrix) {
        int broken = 0;

        for (int i = 0; i < 9; i++) {
            int[] cols = getColumn(matrix, i);
            for (int j = 0; j < 9; j++) {
                int num = cols[j];
                for (int k = 0; k < 9; k++) {
                    if (j != k && num != 0) {
                        if (num == matrix[k][i]) {
                            broken++;
                        }
                    }
                }
            }
        }

        return broken;
    }

    static public int checkBlock(int[][] matrix) {
        int broken = 0;
        int st = matrix.length / 3;

        for (int i = 0; i < st; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != j && matrix[i][j] != 0) {
                    if (matrix[i][j] != matrix[st][j]) {
                        broken++;
                    }
                }
            }
        }
        return broken;
    }

    static public boolean checkValid(int[][] matrix) {
        return checkRows(matrix) == 0 && checkCols(matrix) == 0 && checkBlock(matrix) == 0;
    }

    static public int getFit(int[][] matrix) {
        return checkRows(matrix) + checkCols(matrix) + checkBlock(matrix);
    }

    public static int[] getColumn(int[][] mat, int index){
        int[] column = new int[9];
        for(int i=0; i<9; i++){
            column[i] = mat[i][index];
        }
        return column;
    }
}
