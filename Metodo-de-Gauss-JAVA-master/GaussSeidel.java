import java.util.Scanner;

public class GaussSeidel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de equações: ");
        int n = scanner.nextInt();

        double[][] coefficients = new double[n][n];
        double[] constants = new double[n];
        double[] initialValues = new double[n];

        System.out.println("Digite os coeficientes das equações:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("a%d%d: ", i + 1, j + 1);
                coefficients[i][j] = scanner.nextDouble();
            }
            System.out.printf("b%d: ", i + 1);  
            constants[i] = scanner.nextDouble();
            System.out.printf("Valor inicial x%d: ", i + 1);
            initialValues[i] = scanner.nextDouble();
        }

        System.out.print("Número máximo de iterações: ");
        int maxIterations = scanner.nextInt();

        System.out.print("Tolerância: ");
        double tolerance = scanner.nextDouble();

        scanner.close();

        double[] solution = gaussSeidel(coefficients, constants, initialValues, maxIterations, tolerance);

        System.out.println("Solução:");
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("x%d = %.4f%n", i + 1, solution[i]);
        }
    }

    public static double[] gaussSeidel(double[][] coefficients, double[] constants, double[] initialValues,
                                      int maxIterations, double tolerance) {
        int n = coefficients.length;
        double[] solution = new double[n];
        double[] prevSolution = new double[n];

        System.arraycopy(initialValues, 0, solution, 0, n);

        for (int iter = 0; iter < maxIterations; iter++) {
            System.arraycopy(solution, 0, prevSolution, 0, n);

            for (int i = 0; i < n; i++) {
                double sum = constants[i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= coefficients[i][j] * solution[j];
                    }
                }
                solution[i] = sum / coefficients[i][i];
            }

            boolean hasConverged = true;
            for (int i = 0; i < n; i++) {
                double error = Math.abs(solution[i] - prevSolution[i]);
                if (error > tolerance) {
                    hasConverged = false;
                    break;
                }
            }

            if (hasConverged) {
                System.out.println("Convergido na iteração " + (iter + 1));
                break;
            }
        }

        return solution;
    }
}
