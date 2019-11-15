import java.lang.Exception;
import java.util.*;
public class GraphImplementation implements Graph {
    int[][]matrix;
    public GraphImplementation(int vertices) {
        try {
            if (vertices <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {

        }
        matrix = new int[vertices][vertices];
    }


    public void addEdge(int v1, int v2) throws Exception{
        if(v1<0 || v1 > matrix[0].length || v2 < 0 || v2 > matrix.length){
            throw new Exception();
        }
        matrix[v1][v2] = 1;
    }

    public List<Integer> topologicalSort() {
        return topologicalSort(matrix.length);
    }

    public List<Integer> topologicalSort(int vertices){
        List<Integer> schedule = new ArrayList<Integer>();
        int [] sum = new int[vertices];
        for (int i = 0; i < vertices; i++){
            for (int j = 0; j < vertices; j++){
                sum[i] += matrix[j][i];
            }
        }

        int n = findZero(sum);

        for(int count = 0; count < vertices; count ++){
            if(n == -1){
                System.out.println("The graph is cyclic or there is a disjoined edge in the graph. Here is the partial topological sort:");
                return schedule;
            }
            sum[n] = -1;
            schedule.add(n);
            for(int i = 0; i < vertices; i++){
                if (matrix[n][i] == 1){
                    sum[i]--;
                }
            }
            n = findZero(sum);
        }
        return schedule;
    }
    public List<Integer> neighbors(int vertex) throws Exception{
        List<Integer> neighbour = new ArrayList<Integer>();
        if (vertex < 0 || vertex > matrix.length){
            throw new Exception();
        }
        for (int i = 0; i < matrix.length; i++){
            if (matrix[vertex][i] == 1){
                neighbour.add(i);
            }
        }
        return neighbour;
    }

    public int findZero(int[] sum){
        for (int i = 0; i < sum.length; i++){
            if (sum[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public void printGraph(){
        for (int i = 0; i<matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}