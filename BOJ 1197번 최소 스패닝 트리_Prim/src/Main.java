import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] strs;

    int V, E;
    HashMap<Integer, PriorityQueue<Edge>> edges;
    Vertex[] vertices;
    boolean[] visited;
    PriorityQueue<Vertex> que;

    void solve() throws IOException {
        strs = br.readLine().split(" ");
        V = Integer.parseInt(strs[0]);
        E = Integer.parseInt(strs[1]);

        edges = new HashMap<Integer, PriorityQueue<Edge>>();
        vertices = new Vertex[V+1];
        visited = new boolean[V+1];
        que = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                if (v1.mV < v2.mV)
                    return -1;
                else if (v1.mV > v2.mV)
                    return 1;

                return 0;
            }
        });

        for (int i=0; i<E; ++i) {
            strs = br.readLine().split(" ");
            int v1 = Integer.parseInt(strs[0]);
            int v2 = Integer.parseInt(strs[1]);
            int w = Integer.parseInt(strs[2]);

            if (edges.containsKey(v1) == false)
                edges.put(v1, newPrioirityQue());
            if (edges.containsKey(v2) == false)
                edges.put(v2, newPrioirityQue());

            Edge e = new Edge(v1, v2, w);

            edges.get(v1).add(e);
            edges.get(v2).add(e);
        }

        for (int i=1; i<V+1; ++i) {
            vertices[i] = new Vertex(i, Integer.MAX_VALUE, -1);
        }

        MST_prim(1);

        int r = 0;

        for (int i=1; i<V+1; ++i) {
            r += vertices[i].mV;
        }

        bw.write(String.valueOf(r));
        bw.close();
    }

    void MST_prim(int fr) {
        Vertex vtx1 = vertices[fr];

        vtx1.mV = 0;
        vtx1.mLevel = 0;
        que.add(vertices[fr]);

        while (que.size() > 0) {
            vtx1 = que.poll();

            if (visited[vtx1.mIdx])
                continue;
            else
                visited[vtx1.mIdx] = true;

            for (Edge edge: edges.get(vtx1.mIdx)) {
                int vtx2idx = edge.getConnectedVertex(vtx1.mIdx);
                Vertex vtx2 = vertices[vtx2idx];

                if (visited[vtx2idx]) {
                    vtx1.mV = Math.min(vtx1.mV, edge.mW);
                }
                else {
                    vtx2.mV = Math.min(vtx2.mV, edge.mW);
                    if (que.contains(vtx2)) {
                        que.remove(vtx2);
                    }
                    que.add(vtx2);
                }
            }
        }
    }

    PriorityQueue<Edge> newPrioirityQue() {
        return new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                if (e1.mW < e2.mW)
                    return -1;
                else if (e1.mW > e2.mW)
                    return 1;

                return 0;
            }
        });
    }

    class Vertex {
        int mIdx;
        int mV;
        int mLevel;

        Vertex(int idx, int v, int level) {
            mIdx = idx;
            mV   = v;
            mLevel = level;
        }
    }

    class Edge {
        int mV1, mV2, mW;

        Edge(int v1, int v2, int w) {
            mV1 = v1;
            mV2 = v2;
            mW = w;
        }

        int getConnectedVertex(int v) {
            if (mV1 == v)
                return mV2;

            return mV1;
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}