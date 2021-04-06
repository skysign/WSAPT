import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int V, E;
    Vertex[] vertices;
    UndirectedEdge[] edges;
    SubSet[] subSets;
    ArrayList<UndirectedEdge> al;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        vertices = new Vertex[V+1];
        edges = new UndirectedEdge[E];
        subSets = new SubSet[V+1];
        al = new ArrayList<>();

        for (int i=1; i<=V; ++i) {
            vertices[i] = new Vertex(i);
            subSets[i] = new SubSet();
            subSets[i].parent = i;
            subSets[i].rank = 0;
        }

        for (int i=0; i<E; ++i) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if (v1 > v2) {
                int t = v1;
                v1 = v2;
                v2 = t;
            }

            int w = Integer.parseInt(st.nextToken());
            edges[i] = new UndirectedEdge(w, vertices[v1], vertices[v2]);
        }

        Arrays.sort(edges);

        for (int i=0; i<edges.length; ++i) {
            UndirectedEdge edge = edges[i];
            int v1 = edge.mV1.mV;
            int v2 = edge.mV2.mV;

            int v1Root = find(v1);
            int v2Root = find(v2);

            if (v1Root != v2Root) {
                al.add(edge);
                Union(v1Root, v2Root);
            }
        }

        int r = 0;

        for (UndirectedEdge edge: al) {
            r += edge.mWeight;
        }

        bw.write(String.valueOf(r));
        bw.close();
    }

    int find(int v) {
        if (subSets[v].parent != v) {
            subSets[v].parent = find(subSets[v].parent);
        }

        return subSets[v].parent;
    }

    void Union(int v1, int v2) {
        if (subSets[v1].rank > subSets[v2].rank) {
            subSets[v2].parent = v1;
        }
        else if (subSets[v1].rank < subSets[v2].rank) {
            subSets[v1].parent = v2;
        }
        else {
            subSets[v2].parent = v1;
            subSets[v1].rank++;
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    class SubSet {
        public int parent;
        public int rank;
    }

    public class Vertex {
        public int mV;
        ArrayList<UndirectedEdge> mEdges = new ArrayList();

        Vertex(int v) {
            mV = v;
        }

        public void addEdge(UndirectedEdge edge) {
            mEdges.add(edge);
        }

        public Vertex getConnectedVertexFromEdge(int i) {
            Vertex v1 = this.mEdges.get(i).mV1;
            Vertex v2 = this.mEdges.get(i).mV2;

            if (this == v1)
                return v2;

            return v1;
        }
    }

    public class UndirectedEdge implements Comparable{
        public long mWeight;
        public Vertex mV1;
        public Vertex mV2;

        UndirectedEdge(long weight, Vertex v1, Vertex v2) {
            mWeight = weight;
            mV1 = v1;
            mV2 = v2;
            mV1.addEdge(this);
            mV2.addEdge(this);
        }

        @Override
        public int compareTo(Object o) {
            UndirectedEdge e = (UndirectedEdge)o;
            return (int)(this.mWeight) - (int)(e.mWeight);
        }
    }
}