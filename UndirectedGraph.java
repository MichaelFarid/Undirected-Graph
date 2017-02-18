import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UndirectedGraph {
    private ArrayList<ArrayList<Integer>> adjacencylist;
    private int v;
    private int e = 0;

    public UndirectedGraph(int V) {
        if (V < 1)
            throw new IllegalArgumentException("Number of vertices must be greater than zero");
        v = V;
        adjacencylist = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjacencylist.add(i, new ArrayList<Integer>());
        }
    }

    public void addEdge(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("Vertices' IDs must be greater than zero ");
        if (x >= v || y >= v)
            throw new IllegalArgumentException("One or both vertices' IDs inputted do not exist");
        if (x != y) {
            adjacencylist.get(x).add(y);
            adjacencylist.get(y).add(x);
        } else {
            adjacencylist.get(x).add(y);
        }
        e++;
    }

    public void removeEdge(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("Vertices' IDs must be greater than zero ");
        if (x >= v || y >= v)
            throw new IllegalArgumentException("One or both vertices' IDs inputted do not exist");
        if (!isConnected(x, y))
            throw new IllegalArgumentException("The vertices are not connected");
        else if (x != y) {
            adjacencylist.get(x).remove(y);
            adjacencylist.get(y).remove(x);
        } else {
            adjacencylist.get(x).remove(y);
        }
    }

    public boolean isConnected(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("Vertices' IDs must be greater than zero ");
        if (x >= v || y >= v)
            throw new IllegalArgumentException("One or both vertices' IDs inputted do not exist");
        return adjacencylist.get(x).contains(y);
    }

    public int getDegree(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Vertices' IDs must be greater than zero ");
        if (n >= v)
            throw new IllegalArgumentException("Vertices' IDs inputted do not exist");
        return adjacencylist.get(n).size();
    }

    public int getMaxDegree() {
        int max = -1;
        for (int i = 0; i < v; i++) {
            max = Math.max(max, adjacencylist.get(i).size());
        }
        return max;
    }

    public double getAverageDegree() {
        double result = 2.00 * ((double) e) / ((double) v);
        return result;
    }

    public int getNumVertextSelfLoop(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Vertex ID must be greater than zero ");
        if (n >= v)
            throw new IllegalArgumentException("Vertex ID inputted do not exist");
        int count = 0;
        for (int i : adjacencylist.get(n)) {
            if (i == n)
                count++;
        }
        return count;
    }

    public int getNumtAllVerticesSelfLoops() {
        int count = 0;
        for (int j = 0; j < adjacencylist.size(); j++) {
            for (int k : adjacencylist.get(j)) {
                if (k == j)
                    count++;
            }
        }
        return count;
    }

    public int getNumEdges() {
        return e;
    }

    public int getNumvertices() {
        return v;
    }

    public String toString() {
        String output = "";
        output += "V: " + v + "\n";
        output += "E: " + e + "\n";
        for (int i = 0; i < adjacencylist.size(); i++) {
            output += i + ": ";
            for (int j : adjacencylist.get(i)) {
                output += j + " ";
            }
            output += "\n";
        }
        return output;
    }

    public int[] getDFSPath(int a, int b) {
        if (a < 0 || b < 0)
            throw new IllegalArgumentException("Vertices' ID must be greater than zero ");
        if (a >= v || b >= v)
            throw new IllegalArgumentException("One or both vertices' IDs inputted do not exist");
        int start = a;
        int end = b;
        Stack<Integer> steps = new Stack<>();
        ArrayList<Integer> visited = new ArrayList<>();
        steps.add(start);
        visited.add(start);
        while (!steps.isEmpty()) {
            int current = steps.peek();
            int initialsize = steps.size();
            if (current == end) {
                int[] results = new int[steps.size()];
                for (int i = 0; i < steps.size(); i++) {
                    results[i] = steps.get(i);
                }
                return results;
            }
            for (int i : adjacencylist.get(current)) {
                if (!(visited.contains(i))) {
                    visited.add(i);
                    steps.push(i);
                    break;
                }
            }
            if (steps.size() == initialsize)
                steps.pop();

        }
        return new int[0];

    }

    public int[] getBFSPath(int a, int b) {
        if (a < 0 || b < 0)
            throw new IllegalArgumentException("Vertices' IDs must be greater than zero ");
        if (a >= v || b >= v)
            throw new IllegalArgumentException("One or both vertices' IDs inputted do not exist");
        point start = new point(a);
        Queue<point> steps = new LinkedList<>();
        steps.add(start);
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(start.number);
        while (!steps.isEmpty()) {
            point temp = steps.poll();
            if (temp.number == b) {
                ArrayList<Integer> inter = new ArrayList<>();
                while ((temp.prev != (null))) {
                    inter.add(temp.number);
                    temp = temp.prev;
                }
                inter.add(a);
                int[] result = new int[inter.size()];
                for (int i = inter.size() - 1; i > -1; i--) {
                    result[inter.size() - i - 1] = inter.get(i);
                }
                return result;
            } else {
                for (int i : adjacencylist.get(temp.number)) {
                    if (!visited.contains(i)) {
                        steps.add(new point(i, temp));
                        visited.add(i);
                    }
                }
            }
        }
        return new int[0];
    }

    public int[] getBFSTraversal(int a) {
        if (a < 0)
            throw new IllegalArgumentException("Vertex's ID must be greater than zero ");
        if (a >= v)
            throw new IllegalArgumentException("Vertex's ID inputted do not exist");
        Queue<Integer> steps = new LinkedList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(a);
        steps.add(a);
        while (!steps.isEmpty()) {
            int temp = steps.poll();
            for (int i : adjacencylist.get(temp)) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    steps.add(i);
                }
            }

        }
        int[] results = new int[visited.size()];
        for (int i = 0; i < visited.size(); i++) {
            results[i] = visited.get(i);
        }
        return results;
    }

    public int[] getDFSTraversal(int a) {
        if (a < 0)
            throw new IllegalArgumentException("Vertex's ID must be greater than zero ");
        if (a >= v)
            throw new IllegalArgumentException("Vertex's ID inputted do not exist");
        int start = a;
        Stack<Integer> steps = new Stack<>();
        ArrayList<Integer> visited = new ArrayList<>();
        steps.add(start);
        visited.add(start);
        while (!steps.isEmpty()) {
            int current = steps.peek();
            int initialsize = steps.size();
            for (int i : adjacencylist.get(current)) {
                if (!(visited.contains(i))) {
                    visited.add(i);
                    steps.push(i);
                    break;
                }
            }
            if (steps.size() == initialsize)
                steps.pop();

        }
        int[] results = new int[visited.size()];
        for (int i = 0; i < visited.size(); i++) {
            results[i] = visited.get(i);
        }
        return results;
    }

    class point {
        point prev;
        int number;

        public point(int n, point previous) {
            number = n;
            prev = previous;
        }

        public point(int n) {
            number = n;
            prev = null;
        }
    }

}