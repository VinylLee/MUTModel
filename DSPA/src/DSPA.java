import java.util.Arrays;
import java.util.Random;

public class DSPA {

    public static double[] shortestPath(double[][] graph, int start, int end) {
        if (start == end)
            return new double[] { 0, start, end };
        int numVertices = graph.length;
        boolean[] visited = new boolean[numVertices];
        double[] distance = new double[numVertices];
        int[] prevNode = new int[numVertices];
        Arrays.fill(distance, Double.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(prevNode, -1);
        distance[start] = 0;
        for (int i = 0; i < numVertices - 1; i++) {
            int minVertex = -1;
            for (int j = 0; j < numVertices; j++)
                if (!visited[j] && (minVertex == -1 || distance[j] < distance[minVertex]))
                    minVertex = j;
            visited[minVertex] = true;
            for (int k = 0; k < numVertices; k++)
                if (graph[minVertex][k] != 0 && !visited[k]) {
                    double newDistance = distance[minVertex] + graph[minVertex][k];
                    if (newDistance < distance[k]) {
                        distance[k] = newDistance;
                        prevNode[k] = minVertex;
                    }
                }
        }
        if (prevNode[end] == -1)
            return new double[] { Double.POSITIVE_INFINITY };
        int[] path = new int[numVertices];
        int currentNode = end, pathIndex = 0;
        while (currentNode != start) {
            path[pathIndex] = currentNode;
            currentNode = prevNode[currentNode];
            pathIndex++;
        }
        path[pathIndex] = start;
        double[] reversedPath = new double[pathIndex + 2];
        reversedPath[0] = distance[end];
        for (int i = pathIndex; i >= 0; i--)
            reversedPath[pathIndex + 1 - i] = path[i];
        return reversedPath;
    }

    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);

    // int n = scanner.nextInt();
    // int m = scanner.nextInt();

    // double[][] adjList = new double[n][n];
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < n; j++) {
    // adjList[i][j] = 0;
    // }
    // }

    // for (int i = 0; i < m; i++) {
    // int s = scanner.nextInt();
    // int e = scanner.nextInt();
    // double w = scanner.nextInt();
    // adjList[s - 1][e - 1] = w;
    // // adjList[e - 1][s - 1] = w;
    // }

    // // for (int i = 0; i < n; i++) {
    // // System.out.print("{");
    // // for (int j = 0; j < n; j++) {
    // // System.out.print(adjList[i][j] + ", ");
    // // }
    // // System.out.println("}");
    // // }

    // scanner.close();
    // double[] shortestPath = shortestPath(adjList, 0, n - 1);
    // // for (double d : shortestPath) {
    // // System.out.print(d + " ");
    // // }
    // System.out.println((int) shortestPath[0]);
    // }

    public static void main(String[] args) {
        double[][] adjMatrix = new double[][] { { 0, 0, 13, 15, 0, 0, 13, 0, 14, 0, 19 },
                { 0, 0, 13, 9, 0, 13, 0, 12, 7, 0, 11 }, { 13, 13, 0, 0, 13, 0, 0, 0, 19, 8, 0 },
                { 15, 9, 0, 0, 0, 18, 8, 0, 0, 17, 16 }, { 0, 0, 13, 0, 0, 8, 19, 0, 0, 8, 18 },
                { 0, 13, 0, 18, 8, 0, 0, 0, 0, 0, 0 }, { 13, 0, 0, 8, 19, 0, 0, 19, 10, 8, 0 },
                { 0, 12, 0, 0, 0, 0, 19, 0, 9, 0, 0 }, { 14, 7, 19, 0, 0, 0, 10, 9, 0, 0, 20 },
                { 0, 0, 8, 17, 8, 0, 8, 0, 0, 0, 8 }, { 19, 11, 0, 16, 18, 0, 0, 0, 20, 8, 0 } };
        int start = 8;
        int end = 4;

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
        for (int i = 0; i < next.length; i++) {
            System.out.println(next[i]);
        }
    }
}
