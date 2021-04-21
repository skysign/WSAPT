import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int wOfMST;
    String[] strs = null;
    Edge[] edges;
    Edge[] edgesSort;
    HashMap[] edgeByV;
    ArrayList[] edgesOfMST;
    int[] parents;
    int[] parents2;
    HashMap<Edge, Boolean> hm;
    HashSet<Edge> hs1;
    HashSet<Edge> hs2;

    void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        parents = new int[N+1];
        parents2 = new int[N+1];
        initParents();
        edges = new Edge[M];
        edgesSort = new Edge[M];
        edgeByV = new HashMap[N+1];
        edgesOfMST = new ArrayList[N+1];
        hm = new HashMap<>();
        hs1 = new HashSet<>();
        hs2 = new HashSet<>();

        for (int i=0; i<M; ++i) {
            strs = br.readLine().split(" ");
            int v1 = Integer.parseInt(strs[0]);
            int v2 = Integer.parseInt(strs[1]);
            int w = Integer.parseInt(strs[2]);

            int smlV = Math.min(v1, v2);
            int bigV = Math.max(v1, v2);

            edgesSort[i] = edges[i] = new Edge(smlV, bigV, w);

            if (edgeByV[smlV] == null) {
                edgeByV[smlV] = new HashMap<Integer, Edge>();
            }

            edgeByV[smlV].put(bigV, edges[i]);
        }

        Arrays.sort(edgesSort, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                if (e1.mW < e2.mW) {
                    return -1;
                }
                else if (e1.mW > e2.mW) {
                    return 1;
                }

                return 0;
            }
        });

        wOfMST = MST();
        int rootV = findRoot();
        findChild(rootV, 0);

        for (int idx=0; idx<M; ++idx) {
            Edge e = edges[idx];

            if (hm.get(e) == true) {
                bw.write(String.valueOf(wOfMST));
            }
            else {
                int r = mustUseEdge(e);
                bw.write(String.valueOf(r));
            }

            bw.newLine();
            bw.flush();
        }

        bw.close();
    }

    void findChild(int pv, int ppv) {
        for (Object o: edgesOfMST[pv]) {
            Edge e = (Edge)o;

            int childv = e.getConnectedVertex(pv);
            if (childv != ppv) {
                parents2[childv] = pv;
                findChild(childv, pv);
            }
        }
    }

    int findRoot() {
        for (int i=1; i<N+1; ++i) {
            if (parents[i] == i)
                return i;
        }

        return -1;
    }

    int mustUseEdge(Edge e) {
        int wMax = Integer.MIN_VALUE;
        int v1 = e.mV1;
        int v2 = e.mV2;
        Edge e1 = null;
        Edge e2 = null;

        hs1.clear();
        hs2.clear();

        hs1.add(null);

        while ((false == hs1.contains(e2)) && (false == hs2.contains(e1))) {
            e1 = (Edge)edgeByV[v1].get(0);
            hs1.add(e1);
            e2 = (Edge)edgeByV[v2].get(0);
            hs2.add(e2);
        }

//        while (v1 != v2) {
//            vMid = parents2[v2];
//            smlV = Math.min(vMid, v2);
//            bigV = Math.max(vMid, v2);
//
//            eMid = (Edge)edgeByV[smlV].get(bigV); // findEdge(vMid, v2);
//            wMax = Math.max(wMax, eMid.mW);
//            v2 = vMid;
//        }

        return wOfMST + e.mW - wMax;
    }

    int MST() {
        int r = 0;

        for (Edge e: edgesSort) {
            int rootOfV1 = unionFind(e.mV1);
            int rootOfV2 = unionFind(e.mV2);

            int rOfV1 = unionFind2(e.mV1);
            int rOfV2 = unionFind2(e.mV2);

            hm.put(e, false);

            if (rootOfV1 != rootOfV2) {
                hm.put(e, true);

                r += e.mW;

                union(rootOfV1, rootOfV2);
                union2(rOfV1, rOfV2);

                if (edgesOfMST[e.mV1] == null)
                    edgesOfMST[e.mV1] = new ArrayList<Edge>();

                if (edgesOfMST[e.mV2] == null)
                    edgesOfMST[e.mV2] = new ArrayList<Edge>();

                edgesOfMST[e.mV1].add(e);
                edgesOfMST[e.mV2].add(e);
            }
        }

        return r;
    }

    int unionFind(int v) {
        if (parents[v] != v) {
            parents[v] = unionFind(parents[v]);
        }

        return parents[v];
    }

    void union(int v1, int v2) {
        if (v1 > v2) {
            parents[v1] = v2;
        }
        else {
            parents[v2] = v1;
        }
    }

    int unionFind2(int v) {
        int r = 0;

        if (parents2[v] != v) {
            r = unionFind(parents2[v]);
        }

        return r;
    }

    void union2(int v1, int v2) {
        if (v1 > v2) {
            parents2[v1] = v2;
        }
        else {
            parents2[v2] = v1;
        }
    }

    void initParents() {
        for (int i=0; i<N+1; ++i) {
            parents[i] = i;
            parents2[i] = i;
        }
    }

    class Edge {
        int mV1, mV2, mW;

        Edge(int v1, int v2, int w) {
            mV1 = v1;
            mV2 = v2;
            mW = w;
        }

        public int getConnectedVertex(int v) {
            if (v == mV1)
                return mV2;

            return mV1;
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}