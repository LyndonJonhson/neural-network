public class Matrix {

    public static Double[][] arrayToMatrix_Double(Double[] A) {
        Double[][] matrix = new Double[A.length][1];
        for (int i = 0; i < A.length; i++) {
            matrix[i][0] = A[i];
        }
        return matrix;
    }

    public static Integer[][] arrayToMatrix_Integer(Integer[] A) {
        Integer[][] matrix = new Integer[A.length][1];
        for (int i = 0; i < A.length; i++) {
            matrix[i][0] = A[i];
        }
        return matrix;
    }

    public static Double[] matrixToArray(Double[][] A) {
        Double[] array = new Double[A.length];
        for (int i = 0; i < A.length; i++) {
            array[i] = A[i][0];
        }
        return array;
    }

    public static Double[][] mult(Double[][] A, Double[][] B) {
        Double[][] matrix = new Double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                matrix[i][j] = 0d;
                for (int k = 0; k < B.length; k++) {
                    matrix[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return matrix;
    }

    public static Double[][] hadamard(Double[][] A, Double[][] B) {
        Double[][] matrix = new Double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                matrix[i][j] = A[i][j] * B[i][j];
            }
        }
        return matrix;
    }

    public static Double[][] scalarMult(Double[][] A, Double scalar) {
        Double[][] matrix = new Double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                matrix[i][j] = A[i][j] * scalar;
            }
        }
        return matrix;
    }

    public static Double[][] add(Double[][] A, Double[][] B) {
        Double[][] matrix = new Double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                matrix[i][j] = A[i][j] + B[i][j];
            }
        }
        return matrix;
    }

    public static Double[][] sub(Integer[][] A, Double[][] B) {
        Double[][] matrix = new Double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                matrix[i][j] = A[i][j] - B[i][j];
            }
        }
        return matrix;
    }

    public static Double[][] transpose(Double[][] A) {
        Double[][] matrix = new Double[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                matrix[j][i] = A[i][j];
            }
        }
        return matrix;
    }

}
