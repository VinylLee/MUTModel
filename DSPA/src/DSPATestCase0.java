import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class DSPATestCase0 {

    double[][] adjMatrix;
    int start, end;

    @Before
    public void setUp() {
        // location_input
    }

    // This MR is specificly designed to detect Equivalent mutants
    // @Test(timeout=1)
    // public void MR0() {
    // double[] test = DSPA.shortestPath(adjMatrix, start, end), real =
    // shortestPath(adjMatrix, start, end);
    // for (int i = 0; i < test.length; ++i)
    // assertTrue(test[i] + " " + real[i], test[i] == real[i]);
    // }

    public static double[][] permuteVertices(double[][] adjMatrix, int[] permutation) {
        int size = adjMatrix.length;
        double[][] newAdjMatrix = new double[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                newAdjMatrix[permutation[i]][permutation[j]] = adjMatrix[i][j];
        return newAdjMatrix;
    }

    @Test
    public void MR1() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end),
                follow = DSPA.shortestPath(adjMatrix, end, start);
        if (Double.isInfinite(origin[0]) || Double.isInfinite(follow[0]))
            assertTrue(origin[0] + " " + origin[0], Double.isInfinite(origin[0]) && Double.isInfinite(follow[0]));
        else
            assertTrue(origin[0] + " " + origin[0], Math.abs(origin[0] - follow[0]) < 1e-6);
    }

    @Test
    public void MR2() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[2], end);
            if (Double.isInfinite(origin[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void MR3() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[origin.length - 2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[origin.length - 2], end);
            if (Double.isInfinite(origin[0]) || Double.isInfinite(follow1[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void MR4() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        int[] permuteOrder = new int[adjMatrix.length];
        for (int i = 0; i < permuteOrder.length; i++)
            permuteOrder[i] = (i + 1) % permuteOrder.length;
        double[][] permutation = permuteVertices(adjMatrix, permuteOrder);
        double[] follow = DSPA.shortestPath(permutation, (start + 1) % permuteOrder.length,
                (end + 1) % permuteOrder.length);
        if (Double.isInfinite(origin[0]) || Double.isInfinite(follow[0]))
            assertTrue(origin[0] + " " + follow[0], Double.isInfinite(origin[0]) && Double.isInfinite(follow[0]));
        else
            assertTrue(origin[0] + " ", Math.abs(origin[0] - follow[0]) < 1e-6);
    }

    @Test
    public void MR5() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 2;
        double[] real = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(real[0]))
            assertTrue(test[0] + " " + real[0], Double.isInfinite(test[0]) && Double.isInfinite(real[0]));
        else
            assertTrue(test[0] + " " + real[0], Math.abs(test[0] * 2 - real[0]) < 1e-6);
    }

    @Test
    public void MR6() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 2;
        double[] real = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(real[0]))
            assertTrue(test[0] + " " + real[0], Double.isInfinite(test[0]) && Double.isInfinite(real[0]));
        else
            assertTrue(test[0] + " " + real[0], Math.abs(test[0] / 2 - real[0]) < 1e-6);
    }

    @Test
    public void MR7() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 3;
        double[] real = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(real[0]))
            assertTrue(test[0] + " " + real[0], Double.isInfinite(test[0]) && Double.isInfinite(real[0]));
        else
            assertTrue(test[0] + " " + real[0], Math.abs(test[0] * 3 - real[0]) < 1e-6);
    }

    @Test
    public void MR8() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 3;
        double[] real = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(real[0]))
            assertTrue(test[0] + " " + real[0], Double.isInfinite(test[0]) && Double.isInfinite(real[0]));
        else
            assertTrue(test[0] + " " + real[0], Math.abs(test[0] / 3 - real[0]) < 1e-6);
    }

    @Test
    public void MR9() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        double[][] copy = new double[adjMatrix.length][];
        Random random = new Random();
        for (int i = 0; i < adjMatrix.length; i++)
            copy[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] = -1;
            adjMatrix[(int) test[i + 1]][(int) test[i]] = -1;
        }
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] != -1 && random.nextInt(10) > 9) {
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                } else if (adjMatrix[i][j] == -1) {
                    adjMatrix[i][j] = copy[i][j];
                    adjMatrix[j][i] = copy[j][i];
                }
        double[] next = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], Math.abs(test[0] - next[0]) < 1e-6);
    }

    @Test
    public void MR10() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] += 5;
            adjMatrix[(int) test[i + 1]][(int) test[i]] += 5;
        }
        double[] next = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], test[0] <= next[0]);
    }

    @Test
    public void MR11() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] -= 5;
            adjMatrix[(int) test[i + 1]][(int) test[i]] -= 5;
        }
        double[] next = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], test[0] >= next[0]);
    }

    @Test
    public void CMR1_9() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        double[][] copy = new double[adjMatrix.length][];
        Random random = new Random();
        for (int i = 0; i < adjMatrix.length; i++)
            copy[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] = -1;
            adjMatrix[(int) test[i + 1]][(int) test[i]] = -1;
        }
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] != -1 && random.nextInt(10) > 9) {
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                } else if (adjMatrix[i][j] == -1) {
                    adjMatrix[i][j] = copy[i][j];
                    adjMatrix[j][i] = copy[j][i];
                }
        double[] next = DSPA.shortestPath(adjMatrix, end, start);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], Math.abs(test[0] - next[0]) < 1e-6);
    }

    @Test
    public void CMR2_7() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 3;
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[2], end);
            if (Double.isInfinite(origin[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] * 3 - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void CMR2_6() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 2;
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[2], end);
            if (Double.isInfinite(origin[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] / 2 - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void CMR2_5() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 2;
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[2], end);
            if (Double.isInfinite(origin[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] * 2 - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void CMR2_8() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 3;
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[2], end);
            if (Double.isInfinite(origin[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] / 3 - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void CMR1_10() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] += 5;
            adjMatrix[(int) test[i + 1]][(int) test[i]] += 5;
        }
        double[] next = DSPA.shortestPath(adjMatrix, end, start);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], test[0] <= next[0]);
    }

    @Test
    public void CMR1_11() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] -= 5;
            adjMatrix[(int) test[i + 1]][(int) test[i]] -= 5;
        }
        double[] next = DSPA.shortestPath(adjMatrix, end, start);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], test[0] >= next[0]);
    }

    @Test
    public void CMR2_4() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        int[] permuteOrder = new int[adjMatrix.length];
        for (int i = 0; i < permuteOrder.length; i++)
            permuteOrder[i] = (i + 1) % permuteOrder.length;
        double[][] permutation = permuteVertices(adjMatrix, permuteOrder);
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(permutation, (start + 1) % permuteOrder.length,
                    ((int) origin[2] + 1) % permuteOrder.length);
            double[] follow2 = DSPA.shortestPath(permutation, ((int) origin[2] + 1) % permuteOrder.length,
                    (end + 1) % permuteOrder.length);
            if (Double.isInfinite(origin[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void CMR3_7() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 3;
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[origin.length - 2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[origin.length - 2], end);
            if (Double.isInfinite(origin[0]) || Double.isInfinite(follow1[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] * 3 - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void CMR3_6() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 2;
        if (origin.length >= 3) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[origin.length - 2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[origin.length - 2], end);
            if (Double.isInfinite(origin[0]) || Double.isInfinite(follow1[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] / 2 - follow1[0] - follow2[0]) < 1e-6);
        }
    }

    @Test
    public void BadCMR2_3() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        if (origin.length >= 4) {
            double[] follow1 = DSPA.shortestPath(adjMatrix, start, (int) origin[2]);
            double[] follow2 = DSPA.shortestPath(adjMatrix, (int) origin[2], (int) origin[origin.length - 2]);
            double[] follow3 = DSPA.shortestPath(adjMatrix, (int) origin[origin.length - 2], end);
            if (Double.isInfinite(origin[0]))
                assertTrue(origin[0] + " " + follow1[0],
                        Double.isInfinite(origin[0])
                                && (Double.isInfinite(follow1[0]) || Double.isInfinite(follow2[0])));
            else
                assertTrue(origin[0] + " " + follow1[0] + follow2[0],
                        Math.abs(origin[0] - follow1[0] - follow2[0] - follow3[0]) < 1e-6);
        }
    }

    @Test
    public void BadCMR5_9() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 2;
        double[][] copy = new double[adjMatrix.length][];
        Random random = new Random();
        for (int i = 0; i < adjMatrix.length; i++)
            copy[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] = -1;
            adjMatrix[(int) test[i + 1]][(int) test[i]] = -1;
        }
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] != -1 && random.nextInt(10) > 9) {
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                } else if (adjMatrix[i][j] == -1) {
                    adjMatrix[i][j] = copy[i][j];
                    adjMatrix[j][i] = copy[j][i];
                }
        double[] real = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(real[0]))
            assertTrue(test[0] + " " + real[0], Double.isInfinite(test[0]) && Double.isInfinite(real[0]));
        else
            assertTrue(test[0] + " " + real[0], Math.abs(test[0] * 2 - real[0]) < 1e-6);
    }

    @Test
    public void BadCMR6_9() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        double[][] copy = new double[adjMatrix.length][];
        Random random = new Random();
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 2;
        for (int i = 0; i < adjMatrix.length; i++)
            copy[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] = -1;
            adjMatrix[(int) test[i + 1]][(int) test[i]] = -1;
        }
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] != -1 && random.nextInt(10) > 9) {
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                } else if (adjMatrix[i][j] == -1) {
                    adjMatrix[i][j] = copy[i][j];
                    adjMatrix[j][i] = copy[j][i];
                }
        double[] next = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], Math.abs(test[0] / 2 - next[0]) < 1e-6);
    }

    @Test
    public void BadCMR7_9() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        double[][] copy = new double[adjMatrix.length][];
        Random random = new Random();
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 3;
        for (int i = 0; i < adjMatrix.length; i++)
            copy[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] = -1;
            adjMatrix[(int) test[i + 1]][(int) test[i]] = -1;
        }
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] != -1 && random.nextInt(10) > 9) {
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                } else if (adjMatrix[i][j] == -1) {
                    adjMatrix[i][j] = copy[i][j];
                    adjMatrix[j][i] = copy[j][i];
                }
        double[] next = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], Math.abs(test[0] * 3 - next[0]) < 1e-6);
    }

    @Test
    public void BadCMR8_9() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        double[][] copy = new double[adjMatrix.length][];
        Random random = new Random();
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 3;
        for (int i = 0; i < adjMatrix.length; i++)
            copy[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] = -1;
            adjMatrix[(int) test[i + 1]][(int) test[i]] = -1;
        }
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] != -1 && random.nextInt(10) > 9) {
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                } else if (adjMatrix[i][j] == -1) {
                    adjMatrix[i][j] = copy[i][j];
                    adjMatrix[j][i] = copy[j][i];
                }
        double[] next = DSPA.shortestPath(adjMatrix, start, end);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], Math.abs(test[0] / 3 - next[0]) < 1e-6);
    }

    @Test
    public void BadCMR4_5() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 2;
        int[] permuteOrder = new int[adjMatrix.length];
        for (int i = 0; i < permuteOrder.length; i++)
            permuteOrder[i] = (i + 1) % permuteOrder.length;
        double[][] permutation = permuteVertices(adjMatrix, permuteOrder);
        double[] follow = DSPA.shortestPath(permutation, (start + 1) % permuteOrder.length,
                (end + 1) % permuteOrder.length);
        if (Double.isInfinite(origin[0]) || Double.isInfinite(follow[0]))
            assertTrue(origin[0] + " " + follow[0], Double.isInfinite(origin[0]) && Double.isInfinite(follow[0]));
        else
            assertTrue(origin[0] + " ", Math.abs(origin[0] * 2 - follow[0]) < 1e-6);
    }

    @Test
    public void BadCMR4_6() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 2;
        int[] permuteOrder = new int[adjMatrix.length];
        for (int i = 0; i < permuteOrder.length; i++)
            permuteOrder[i] = (i + 1) % permuteOrder.length;
        double[][] permutation = permuteVertices(adjMatrix, permuteOrder);
        double[] follow = DSPA.shortestPath(permutation, (start + 1) % permuteOrder.length,
                (end + 1) % permuteOrder.length);
        if (Double.isInfinite(origin[0]) || Double.isInfinite(follow[0]))
            assertTrue(origin[0] + " " + follow[0], Double.isInfinite(origin[0]) && Double.isInfinite(follow[0]));
        else
            assertTrue(origin[0] + " ", Math.abs(origin[0] / 2 - follow[0]) < 1e-6);
    }

    @Test
    public void BadCMR4_7() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] *= 3;
        int[] permuteOrder = new int[adjMatrix.length];
        for (int i = 0; i < permuteOrder.length; i++)
            permuteOrder[i] = (i + 1) % permuteOrder.length;
        double[][] permutation = permuteVertices(adjMatrix, permuteOrder);
        double[] follow = DSPA.shortestPath(permutation, (start + 1) % permuteOrder.length,
                (end + 1) % permuteOrder.length);
        if (Double.isInfinite(origin[0]) || Double.isInfinite(follow[0]))
            assertTrue(origin[0] + " " + follow[0], Double.isInfinite(origin[0]) && Double.isInfinite(follow[0]));
        else
            assertTrue(origin[0] + " ", Math.abs(origin[0] * 3 - follow[0]) < 1e-6);
    }

    @Test
    public void BadCMR4_8() {
        double[] origin = DSPA.shortestPath(adjMatrix, start, end);
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                adjMatrix[i][j] /= 3;
        int[] permuteOrder = new int[adjMatrix.length];
        for (int i = 0; i < permuteOrder.length; i++)
            permuteOrder[i] = (i + 1) % permuteOrder.length;
        double[][] permutation = permuteVertices(adjMatrix, permuteOrder);
        double[] follow = DSPA.shortestPath(permutation, (start + 1) % permuteOrder.length,
                (end + 1) % permuteOrder.length);
        if (Double.isInfinite(origin[0]) || Double.isInfinite(follow[0]))
            assertTrue(origin[0] + " " + follow[0], Double.isInfinite(origin[0]) && Double.isInfinite(follow[0]));
        else
            assertTrue(origin[0] + " ", Math.abs(origin[0] / 3 - follow[0]) < 1e-6);
    }

    @Test
    public void BadCMR4_9() {
        double[] test = DSPA.shortestPath(adjMatrix, start, end);
        double[][] copy = new double[adjMatrix.length][];
        Random random = new Random();
        for (int i = 0; i < adjMatrix.length; i++)
            copy[i] = Arrays.copyOf(adjMatrix[i], adjMatrix[i].length);
        for (int i = 1; i < test.length - 1; i++) {
            adjMatrix[(int) test[i]][(int) test[i + 1]] = -1;
            adjMatrix[(int) test[i + 1]][(int) test[i]] = -1;
        }
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] != -1 && random.nextInt(10) > 9) {
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                } else if (adjMatrix[i][j] == -1) {
                    adjMatrix[i][j] = copy[i][j];
                    adjMatrix[j][i] = copy[j][i];
                }
        int[] permuteOrder = new int[adjMatrix.length];
        for (int i = 0; i < permuteOrder.length; i++)
            permuteOrder[i] = (i + 1) % permuteOrder.length;
        double[][] permutation = permuteVertices(adjMatrix, permuteOrder);
        double[] next = DSPA.shortestPath(permutation, (start + 1) % permuteOrder.length,
                (end + 1) % permuteOrder.length);
        if (Double.isInfinite(test[0]) || Double.isInfinite(next[0]))
            assertTrue(test[0] + " " + next[0], Double.isInfinite(test[0]) && Double.isInfinite(next[0]));
        else
            assertTrue(test[0] + " " + next[0], Math.abs(test[0] - next[0]) < 1e-6);
    }
}
