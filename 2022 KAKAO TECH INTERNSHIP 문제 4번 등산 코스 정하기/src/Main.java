import java.io.*;
import java.util.*;

public class Main {
    class Edge {
        public int fr, to, wt;

        public Edge(int i, int j, int w) {
            fr = i;
            to = j;
            wt = w;
        }
    }

    class Passage{
        public int to, intensity;

        public Passage(int _to) {
            to = _to;
            intensity = 0;
        }

        public Passage(int _to, int in) {
            to = _to;
            intensity = in;
        }
    }

    // fr, edge
    HashMap<Integer, ArrayList<Edge>> edges = new HashMap<>();
    HashSet<Integer> visited = new HashSet<>();
    HashSet<Integer> summits = new HashSet<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] _summits) {
        visited.clear();
        summits.clear();
        edges.clear();

        for (int s: _summits) {
            summits.add(s);
        }

        Passage answer = new Passage(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (int[] p: paths) {
            Edge e = new Edge(p[0], p[1], p[2]);
            Edge e1 = new Edge(p[1], p[0], p[2]);

            if (!edges.containsKey(p[0])) {
                edges.put(p[0], new ArrayList<>());
            }
            if (!edges.containsKey(p[1])) {
                edges.put(p[1], new ArrayList<>());
            }

            edges.get(p[0]).add(e);
            edges.get(p[1]).add(e1);
        }

        for(int gate: gates) {
            Deque<Passage> queue = new ArrayDeque<>();
            queue.add(new Passage(gate));
            visited.clear();

            while(!queue.isEmpty()) {
                Passage psgFr = queue.pollFirst();
                visited.add(psgFr.to);

                for (Edge e: edges.get(psgFr.to)) {
                    if (visited.contains(e.to))
                        continue;

                    Passage psgTo = new Passage(e.to, Math.max(psgFr.intensity, e.wt));

                    if (summits.contains(e.to)) {
                        if (psgTo.intensity == answer.intensity) {
                            if (psgTo.to < answer.to)
                                answer = psgTo;
                        }
                        else if (psgTo.intensity < answer.intensity)
                            answer = psgTo;

                        continue;
                    }
                    else {
                        queue.add(psgTo);
                    }
                }
            }
        }

        return new int[]{answer.to, answer.intensity};
    }

    public static void main(String[] args) throws IOException {

    }
}