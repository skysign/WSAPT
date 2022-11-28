import java.io.*;
import java.util.*;

public class Main {
    class Edge implements Comparable<Edge> {
        public int fr, to, wt;

        public Edge(int i, int j, int w) {
            fr = i;
            to = j;
            wt = w;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.wt == o.wt)
                return Integer.compare(this.to, o.to);
            return Integer.compare(this.wt, o.wt);
        }
    }

    int[] map;

    PriorityQueue<Edge> visit = new PriorityQueue<>();
    HashSet<Integer> visited = new HashSet<>();
    HashSet<Integer> summits = new HashSet<>();
    HashMap<Integer, ArrayList<Edge>> edges = new HashMap<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] _summits) {
        visit.clear();
        visited.clear();
        summits.clear();
        edges.clear();
        map = new int[n + 1];
        Arrays.fill(map, Integer.MAX_VALUE);

        Edge answer = new Edge(-1, -1, Integer.MAX_VALUE);

        for (int s : _summits) {
            summits.add(s);
        }

        for (int[] p : paths) {
            Edge e1 = new Edge(p[0], p[1], p[2]);
            Edge e2 = new Edge(p[1], p[0], p[2]);

            if (!edges.containsKey(p[0]))
                edges.put(p[0], new ArrayList<Edge>());
            if (!edges.containsKey(p[1]))
                edges.put(p[1], new ArrayList<Edge>());

            edges.get(p[0]).add(e1);
            edges.get(p[1]).add(e2);
        }

        for (int gate : gates) {
            visit.addAll(edges.get(gate));
            visited.add(gate);
            map[gate] = 0;
        }

        while (!visit.isEmpty()) {
            Edge e = visit.poll();

            if (visited.contains(e.to))
                continue;

            int intensity = Math.max(e.wt, map[e.fr]);
            if (intensity < map[e.to]) {
                map[e.to] = intensity;

                if (summits.contains(e.to)) {
                    Edge eSummit = new Edge(0, e.to, intensity);

                    if (answer.compareTo(eSummit) > 0)
                        answer = eSummit;

                    continue;
                }
            }

            if (summits.contains(e.to)) {
                continue;
            }

            visit.addAll(edges.get(e.to));
            visited.add(e.to);
        }

//        for (int gate : gates) {
//            pqVisit.clear();
//            visited.clear();
//            map = new int[n + 1];
//            Arrays.fill(map, Integer.MAX_VALUE);
//
//            pqVisit.addAll(edges.get(gate));
//            visited.add(gate);
//            map[gate] = 0;
//
//            while (!pqVisit.isEmpty()) {
//                Edge e = pqVisit.poll();
//
//                if (visited.contains(e.to))
//                    continue;
//
//                int intensity = Math.max(e.wt, map[e.fr]);
//                if (intensity < map[e.to]) {
//                    map[e.to] = intensity;
//
//                    if (summits.contains(e.to)) {
//                        Edge eSummit = new Edge(0, e.to, intensity);
//
//                        if (answer.compareTo(eSummit) > 0)
//                            answer = eSummit;
//
//                        continue;
//                    }
//                }
//
//                pqVisit.addAll(edges.get(e.to));
//                visited.add(e.to);
//            }
//        }

        return new int[]{answer.to, answer.wt};
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
//        main.test();
    }
}