package es.uma;

public class Helpers {
    public static int[][] monoToBidi(final int[] array, final int rows, final int cols) {
        if (array.length != (rows * cols)) {
            throw new IllegalArgumentException("Invalid array length");
        }

        int[][] bidi = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(array, (i * cols), bidi[i], 0, cols);
        }

        return bidi;
    }
}
