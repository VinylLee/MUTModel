public class MatrixMultiply {

    public static double[][] matrixMultiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < a[0].length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    public static void main(String[] args) {
        double[][] a, b;
        a = new double[][] { { 0.9931935654754407 }, { 0.22542043678267343 }, { 0.1550506570058673 },
                { 0.4409735951932443 }, { 0.029224669945797976 }, { 0.10829436014500449 }, { 0.18983523917371625 },
                { 0.19690049404528887 } };
        b = new double[][] { { 0.08655276081593555, 0.8447107015771335, 0.5254592274787552, 0.6245268858109303 } };

        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length;
        double[][] af = new double[n][p], bf = new double[p][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][p - 1 - j];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[p - 1 - i][j];
        double[][] cf = matrixMultiply(af, bf);
        for (int i = 0; i < cf.length; i++) {
            for (int j = 0; j < cf[0].length; j++)
                System.out.print((cs[i][j] == cf[i][j]) + " ");
            System.out.println();
        }
    }
}