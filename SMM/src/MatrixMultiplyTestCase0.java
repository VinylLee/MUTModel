import java.util.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MatrixMultiplyTestCase0 {

    double[][] a, b;
    Random random = new Random();

    @Before
    public void setUp() {
        // location_input
    }

    public static double[][] realMatrixMultiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < a[0].length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    // This MR is specificly designed to detect Equivalent mutants
    @Test
    public void MR0() {
        double[][] res = MatrixMultiply.matrixMultiply(a, b);
        double[][] real = realMatrixMultiply(a, b);
        assertTrue("res.length is 0", res.length > 0 && real.length > 0);
        assertTrue("res: " + res.length + ", " + res[0].length + "; real: " + real.length + ", " + real[0].length,
                res.length == real.length && res[0].length == real[0].length);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[0].length; j++)
                assertTrue(i + ", " + j + ": " + res[i][j] + ", " + real[i][j], res[i][j] == real[i][j]);
    }

    @Test
    public void MR1() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length;
        double[][] af = new double[n][p], bf = new double[p][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][p - 1 - j];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[p - 1 - i][j];
        double[][] cf = MatrixMultiply.matrixMultiply(af, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j], Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
    }

    @Test
    public void MR2() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length;
        double[][] af = new double[n][p];
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = random.nextDouble();
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][j] * x[i];
        }
        double[][] cf = MatrixMultiply.matrixMultiply(af, b);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                        Math.abs(cs[i][j] * x[i] - cf[i][j]) < 1e-6);
    }

    @Test
    public void MR3() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length;
        double[][] af = new double[n + p][p], bf = new double[p][m + p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][j];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < p; j++)
                af[i + n][j] = i == j ? 1 : 0;
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[i][j];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < p; j++)
                bf[i][j + m] = i == j ? 1 : 0;
        double[][] cf = MatrixMultiply.matrixMultiply(af, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        for (int i = 0; i < cs.length; i++)
            for (int j = 0; j < cs[0].length; j++)
                assertTrue("upper left",
                        Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                assertTrue("upper right", Math.abs(cf[i][j + m] - a[i][j]) < 1e-6);
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                assertTrue("lower left", Math.abs(cf[i + n][j] - b[i][j]) < 1e-6);
        for (int i = 0; i < p; i++)
            for (int j = 0; j < p; j++)
                assertTrue("lower right", cf[i + n][j + m] == (i == j ? 1.0 : 0.0));
    }

    @Test
    public void MR4() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length;
        double[][] af = new double[n][p];
        int i1 = random.nextInt(n), i2 = random.nextInt(n);
        while (i2 == i1)
            i2 = random.nextInt(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][j];
        for (int j = 0; j < p; j++) {
            af[i1][j] = a[i2][j];
            af[i2][j] = a[i1][j];
        }
        double[][] cf = MatrixMultiply.matrixMultiply(af, b);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                if (i != i1 && i != i2)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
                else if (i == i1)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i2][j]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i1][j]) < 1e-6);
    }

    @Test
    public void MR5() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int p = a[0].length, m = b[0].length;
        double[][] bf = new double[p][m];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[i][j];
        int j1 = random.nextInt(m), j2 = random.nextInt(m);
        while (j2 == j1)
            j2 = random.nextInt(m);
        for (int i = 0; i < p; i++) {
            bf[i][j1] = b[i][j2];
            bf[i][j2] = b[i][j1];
        }
        double[][] cf = MatrixMultiply.matrixMultiply(a, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                if (j != j1 && j != j2)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
                else if (j == j1)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i][j2]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i][j1]) < 1e-6);
    }

    @Test
    public void MR6() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length;
        double x = random.nextDouble() + random.nextInt(100), y = random.nextDouble() + random.nextInt(100);
        double[][] af = new double[n][p], bf = new double[p][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][j] * x;
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[i][j] * y;
        double[][] cf = MatrixMultiply.matrixMultiply(af, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                        Math.abs(cs[i][j] * x * y - cf[i][j]) < 1e-6);
    }

    @Test
    public void MR7() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length;
        double x = random.nextDouble() + random.nextInt(100);
        double[][] af = new double[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][j] * x;
        double[][] cf = MatrixMultiply.matrixMultiply(af, b);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                        Math.abs(cs[i][j] * x - cf[i][j]) < 1e-6);
    }

    @Test
    public void MR8() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int p = a[0].length, m = b[0].length;
        double y = random.nextDouble() + random.nextInt(100);
        double[][] bf = new double[p][m];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[i][j] * y;
        double[][] cf = MatrixMultiply.matrixMultiply(a, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                        Math.abs(cs[i][j] * y - cf[i][j]) < 1e-6);
    }

    @Test
    public void MR9() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int p = a[0].length, m = b[0].length;
        int J = random.nextInt(m);
        double[][] bf = new double[p][m];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[i][j];
        for (int i = 0; i < p; i++)
            bf[i][J] = 0;
        double[][] cf = MatrixMultiply.matrixMultiply(a, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                if (j != J)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
                else
                    assertTrue("asd", cf[i][j] == 0);
    }

    @Test
    public void MR10() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length;
        int I = random.nextInt(n);
        double[][] af = new double[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][j];
        for (int j = 0; j < p; j++)
            af[I][j] = 0;
        double[][] cf = MatrixMultiply.matrixMultiply(af, b);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                if (i != I)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            cf[i][j] == 0);
    }

    @Test
    public void MR11() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length;
        double[][] af1 = new double[n][p], af2 = new double[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++) {
                af1[i][j] = -a[i][j] * 2;
                af2[i][j] = a[i][j] - af1[i][j];
            }
        double[][] cf1 = MatrixMultiply.matrixMultiply(af1, b);
        double[][] cf2 = MatrixMultiply.matrixMultiply(af2, b);
        assertTrue("res.length is 0", cs.length > 0 && cf1.length > 0 && cf2.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf1.length + ", " + cf1[0].length,
                cs.length == cf1.length && cs[0].length == cf1[0].length && cs.length == cf2.length
                        && cs[0].length == cf2[0].length);
        for (int i = 0; i < cf1.length; i++)
            for (int j = 0; j < cf1[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf1[i][j],
                        Math.abs(cs[i][j] - cf1[i][j] - cf2[i][j]) < 1e-6);
    }

    @Test
    public void MR12() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int p = a[0].length, m = b[0].length;
        double[][] bf1 = new double[p][m], bf2 = new double[p][m];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++) {
                bf1[i][j] = -b[i][j] * 3.5;
                bf2[i][j] = b[i][j] - bf1[i][j];
            }
        double[][] cf1 = MatrixMultiply.matrixMultiply(a, bf1);
        double[][] cf2 = MatrixMultiply.matrixMultiply(a, bf2);
        assertTrue("res.length is 0", cs.length > 0 && cf1.length > 0 && cf2.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf1.length + ", " + cf1[0].length,
                cs.length == cf1.length && cs[0].length == cf1[0].length && cs.length == cf2.length
                        && cs[0].length == cf2[0].length);
        for (int i = 0; i < cf1.length; i++)
            for (int j = 0; j < cf1[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf1[i][j],
                        Math.abs(cs[i][j] - cf1[i][j] - cf2[i][j]) < 1e-6);
    }

    @Test
    public void MR13() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int p = a[0].length, m = b[0].length, m1 = m / 2, m2 = m - m1;
        double[][] bf1 = new double[p][m1], bf2 = new double[p][m2];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                if (j < m1)
                    bf1[i][j] = b[i][j];
                else
                    bf2[i][j - m1] = b[i][j];
        double[][] cf1 = MatrixMultiply.matrixMultiply(a, bf1);
        double[][] cf2 = MatrixMultiply.matrixMultiply(a, bf2);
        assertTrue("res.length is 0", cs.length > 0 && cf1.length > 0 && cf2.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf1.length + ", " + cf1[0].length,
                cs.length == cf1.length && cs[0].length == cf1[0].length + cf2[0].length);
        for (int i = 0; i < cs.length; i++)
            for (int j = 0; j < cs[0].length; j++)
                if (j < m1)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf1[i][j],
                            Math.abs(cs[i][j] - cf1[i][j]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf2[i][j - m1],
                            Math.abs(cs[i][j] - cf2[i][j - m1]) < 1e-6);
    }

    @Test
    public void MR14() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, n1 = n / 2, n2 = n - n1;
        double[][] af1 = new double[n1][p], af2 = new double[n2][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                if (i < n1)
                    af1[i][j] = a[i][j];
                else
                    af2[i - n1][j] = a[i][j];
        double[][] cf1 = MatrixMultiply.matrixMultiply(af1, b);
        double[][] cf2 = MatrixMultiply.matrixMultiply(af2, b);
        assertTrue("res.length is 0", cs.length > 0 && cf1.length > 0 && cf2.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: ",
                cs.length == cf1.length + cf2.length);
        for (int i = 0; i < cs.length; i++)
            for (int j = 0; j < cs[0].length; j++)
                if (i < n1)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", ", Math.abs(cs[i][j] - cf1[i][j]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", ", Math.abs(cs[i][j] - cf2[i - n1][j]) < 1e-6);
    }

    @Test
    public void MR15() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length, q = random.nextInt((n + p + m) / 2);
        double[][] af = new double[n][p + q], bf = new double[p + q][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p + q; j++)
                af[i][j] = j < p ? a[i][j] : 0;
        for (int i = 0; i < p + q; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = i < p ? b[i][j] : 0;
        double[][] cf = MatrixMultiply.matrixMultiply(af, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j], Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
    }

    @Test
    public void MR16() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, m = b[0].length, q1 = random.nextInt((n + p) / 2),
                q2 = random.nextInt((p + m) / 2);
        double[][] af = new double[n + q1][p], bf = new double[p][m + q2];
        for (int i = 0; i < n + q1; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = i < n ? a[i][j] : 0;
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m + q2; j++)
                bf[i][j] = j < m ? b[i][j] : 0;
        double[][] cf = MatrixMultiply.matrixMultiply(af, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length <= cf.length && cs[0].length <= cf[0].length);
        for (int i = 0; i < n + q1; i++)
            for (int j = 0; j < m + q2; j++)
                if (i < n && j < m)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j], Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cf[i][j], cf[i][j] == 0);
    }

    @Test
    public void MR17() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int n = a.length, p = a[0].length, i1 = random.nextInt(n), i2 = random.nextInt(n);
        double x = random.nextInt(n) + random.nextDouble();
        while (i2 == i1)
            i2 = random.nextInt(n);
        double[][] af = new double[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                af[i][j] = a[i][j];
        for (int j = 0; j < p; j++)
            af[i1][j] += af[i2][j] * x;
        double[][] cf = MatrixMultiply.matrixMultiply(af, b);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                if (i != i1)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j], Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] + cs[i2][j] * x - cf[i][j]) < 1e-6);

    }

    @Test
    public void MR18() {
        double[][] cs = MatrixMultiply.matrixMultiply(a, b);
        int p = a[0].length, m = b[0].length, j1 = random.nextInt(m), j2 = random.nextInt(m);
        double y = random.nextInt(m) + random.nextDouble();
        while (j2 == j1)
            j2 = random.nextInt(m);
        double[][] bf = new double[p][m];
        for (int i = 0; i < p; i++)
            for (int j = 0; j < m; j++)
                bf[i][j] = b[i][j];
        for (int i = 0; i < p; i++)
            bf[i][j1] += bf[i][j2] * y;
        double[][] cf = MatrixMultiply.matrixMultiply(a, bf);
        assertTrue("res.length is 0", cs.length > 0 && cf.length > 0);
        assertTrue("res: " + cs.length + ", " + cs[0].length + "; real: " + cf.length + ", " + cf[0].length,
                cs.length == cf.length && cs[0].length == cf[0].length);
        for (int i = 0; i < cf.length; i++)
            for (int j = 0; j < cf[0].length; j++)
                if (j != j1)
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j], Math.abs(cs[i][j] - cf[i][j]) < 1e-6);
                else
                    assertTrue(i + ", " + j + ": " + cs[i][j] + ", " + cf[i][j],
                            Math.abs(cs[i][j] + cs[i][j2] * y - cf[i][j]) < 1e-6);

    }

}
