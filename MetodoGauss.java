import java.util.Scanner;

public class MetodoGauss {

    private static final double nuloNum = 1e-5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obter o tamanho do vetor
        System.out.print("Digite o tamanho do vetor: ");
        int tamanhoVetor = scanner.nextInt();

        double[][] A = new double[tamanhoVetor][tamanhoVetor];
        double[] igual = new double[tamanhoVetor];

        // Preenchendo a matriz A
        System.out.println("Digite os valores da matriz A:");
        for (int i = 0; i < tamanhoVetor; i++) {
            for (int j = 0; j < tamanhoVetor; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }

        // Preenchendo o vetor igual
        System.out.println("Digite os valores do vetor igual:");
        for (int i = 0; i < tamanhoVetor; i++) {
            igual[i] = scanner.nextDouble();
        }

        scanner.close();

        double[] x = LinearS(A, igual);
        int num = 1;

        // Imprimir as soluções
        for (int i = 0; i < tamanhoVetor; i++) {
            System.out.printf("x%d = %.4f\n", num, x[i]);
            num++;
        }

    }

    // Utilizando o método de Gauss para eliminação e seleção de pivô
    public static double[] LinearS(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

            // Encontra a coluna Pivô e substitui
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // Verifica se a matriz tem inversa
            if (Math.abs(A[p][p]) <= nuloNum) {
                throw new ArithmeticException("Determinante é 0");
            }

            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // Substituição de volta
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

}
