import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 1197번 최소 스패닝 트리
 *
 * 유튜브 문제 풀이: https://youtu.be/0gxqAbUMWeE
 *
 * 문제링크: https://www.acmicpc.net/problem/1197
 *
 * 자바소스: https://bit.ly/3v8AThD
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int V, E;
    Vertex[] vertices;
    UndirectedEdge[] edges;
    int[] parents;
    ArrayList<UndirectedEdge> al;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        vertices = new Vertex[V+1];
        edges = new UndirectedEdge[E];
        parents = new int[V+1];
        al = new ArrayList<>();

        for (int i=1; i<=V; ++i) {
            vertices[i] = new Vertex(i);
            parents[i] = i;
        }

        for (int i=0; i<E; ++i) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

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

        long r = 0;

        for (UndirectedEdge edge: al) {
            r += edge.mWeight;
        }

        bw.write(String.valueOf(r));
        bw.close();
    }

    int find(int v) {
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }

        return parents[v];
    }

    void Union(int v1, int v2) {
        if (v1 > v2) {
            parents[v2] = v1;
        }
        else {
            parents[v1] = v2;
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
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