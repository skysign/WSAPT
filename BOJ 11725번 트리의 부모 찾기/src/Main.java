import java.util.*;
/**
 * BOJ 11725번 트리의 부모 찾기
 *
 * 유튜브 문제 풀이
 * https://youtu.be/M-O1OqWFKMo
 *
 * 문제링크 : https://www.acmicpc.net/problem/11725
 *
 * 자바소스 : https://bit.ly/36jKDuD
 */
public class Main {
    public HashMap<Integer, Vertex<Integer>> mapVertex;
    int nNode;
    int[] parents;

    public void solve() {
        Scanner sc = new Scanner(System.in);

        nNode = sc.nextInt();
        sc.nextLine();

        mapVertex = new HashMap<>();

        for (int i = 1; i <= nNode; ++i) {
            mapVertex.put(i, new Vertex<Integer>(i));
        }

        for (int i = 0; i < nNode-1; ++i) {
            int v1Value = sc.nextInt();
            int v2Value = sc.nextInt();
            sc.nextLine();

            Vertex<Integer> v1 = mapVertex.get(v1Value);
            Vertex<Integer> v2 = mapVertex.get(v2Value);

            UndirectedEdge<Integer> edge = new UndirectedEdge(v1, v2);
        }

        solve1();
        // solve2();
    }

    public void solve1() {
        parents = new int[nNode +1];
        parents[1] = 1;
        dfs_setParent(1);

        for(int i = 2; i<=nNode; ++i) {
            System.out.println(parents[i]);
        }
    }

    public void dfs_setParent(int parent) {
        Vertex<Integer> verParent = mapVertex.get(parent);

        for(int i=0; i<verParent.mEdges.size(); ++i) {
            Vertex<Integer> verChild = verParent.getConnectedVertexFromEdge(i);

            if (parents[verChild.mV] == 0) {
                parents[verChild.mV] = verParent.mV;
                dfs_setParent(verChild.mV);
            }
        }
    }

    public void solve2() {
        Integer[] keys = mapVertex.keySet().toArray(new Integer[0]);
        Arrays.sort(keys);

        for(int i=1; i<keys.length; ++i) {
            int r = 0;
            Vertex<Integer> v = mapVertex.get(keys[i]);

            if (v.mEdges.size() == 1) {
                r = v.getConnectedVertexFromEdge(0).mV;
                System.out.println(r);
            }
            else {
                for (int j = 0; j < v.mEdges.size(); ++j) {
                    boolean b  = bfs_vertex_edge(v.getConnectedVertexFromEdge(j).mV, v.mV);
                    if (true == b) {
                        r = v.getConnectedVertexFromEdge(j).mV;
                        System.out.println(r);
                        break;
                    }
                }
            }
        }
    }

    boolean bfs_vertex_edge(int begin, int fr) {
        ArrayList<Integer> mVisited = new ArrayList();
        mVisited.add(fr);

        Deque<Integer> queue = new ArrayDeque();
        queue.add(begin);

        while (queue.size() > 0) {
            int to = queue.pop();

            if (to == 1) {
                mVisited.clear();
                queue.clear();
                return true;
            }

            Vertex<Integer> v = mapVertex.get(to);
            for(int i=0; i<v.mEdges.size(); ++i) {
                Vertex<Integer> vertexTo = v.getConnectedVertexFromEdge(i);

                if (!queue.contains(vertexTo.mV)) {
                    if (!mVisited.contains(vertexTo.mV)) {
                        mVisited.add(vertexTo.mV);
                        queue.add(vertexTo.mV);
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
        // main.solve2();
    }

    public class Vertex<TV> {
        public TV mV;
        ArrayList<UndirectedEdge> mEdges = new ArrayList();

        Vertex(TV v) {
            mV = v;
        }

        public void addEdge(UndirectedEdge<TV> edge) {
            mEdges.add(edge);
        }

        public Vertex<TV> getConnectedVertexFromEdge(int i) {
            Vertex<TV> v1 = this.mEdges.get(i).mV1;
            Vertex<TV> v2 = this.mEdges.get(i).mV2;

            if (this == v1)
                return v2;

            return v1;
        }
    }

    public class UndirectedEdge<TV> {
        public Vertex<TV> mV1;
        public Vertex<TV> mV2;

        UndirectedEdge(Vertex<TV> v1, Vertex<TV> v2) {
            mV1 = v1;
            mV2 = v2;
            mV1.addEdge(this);
            mV2.addEdge(this);
        }
    }
}