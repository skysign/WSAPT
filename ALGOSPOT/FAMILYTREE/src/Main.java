import java.util.ArrayList;
import java.util.Scanner;

/**
 * FAMILYTREE 족보 탐험 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/FAMILYTREE
 * 제출링크 : 오답처리된 문제는 제출 링크를 찾을 수가 없네요.
 * 문제의 테스트케이스는 통과하고, 제출하면 RTE 오답입니다.
 * (ALGOSPOT은 테스트 케이스를 공개하지 않아서, 디버깅은 하지 않겠습니다.)
 */
public class Main {

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }

    public class Node {
        public Node mFather;
        public ArrayList<Node> mChild;
        public int mV;
        public int mLevel;
        public Node(Node father, int v) {
            mFather = father;
            mChild = new ArrayList();
            mV = v;
            mLevel = (father == null)? 0: father.mLevel+1;
        }
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int P = sc.nextInt();

            int[] fs = new int[N];
            for(int n=1; n<N; ++n) {
                fs[n] = sc.nextInt();
            }

            Node root = new Node(null, 0);

            for(int n=1; n<N; ++n) {
//                Node father = findByBFS(root, fs[n]);
                Node father = findByDFS(root, fs[n]);
//                assert father == null;
                father.mChild.add(new Node(father, n));
            }

            for(int p=0; p<P; ++p) {
                int VFrom = sc.nextInt();
                int VTo = sc.nextInt();

//                Node nodeFrom = findByBFS(root, VFrom);
//                Node nodeTo = findByBFS(root, VTo);
                Node nodeFrom = findByDFS(root, VFrom);
                Node nodeTo = findByDFS(root, VTo);

                Node nodeUpper, nodeLower;
                // level 이 낮은게 upper
                if(nodeFrom.mLevel > nodeTo.mLevel) {
                    nodeUpper = nodeTo;
                    nodeLower = nodeFrom;
                }
                else {
                    nodeUpper = nodeFrom;
                    nodeLower = nodeTo;
                }

                int r = 0;
                while(nodeLower.mLevel > nodeUpper.mLevel) {
                    nodeLower = nodeLower.mFather;
                    r++;
                }

                if(nodeUpper == nodeLower) {
                    System.out.println(r);
                    continue;
                }

                while(nodeUpper != nodeLower) {
                    nodeUpper = nodeUpper.mFather;
                    nodeLower = nodeLower.mFather;
                    r += 2;
                }

                System.out.println(r);
            }
        }
    }

    public Node findByDFS(Node parent, int v) {
        for(Node child: parent.mChild) {
            Node target = findByDFS(child, v);
            if(null != target) {
                return target;
            }
            else if(v == child.mV)
                return child;
        }

        if(parent.mV == v)
            return parent;

        return null;
    }

    public Node findByBFS(Node parent, int v) {
        if(parent.mV == v)
            return parent;

        ArrayList<Node> queue = new ArrayList<Node>();
        for(Node node : parent.mChild)
            queue.add(node);

        while(queue.size() > 0) {
            Node node = queue.get(0);
            queue.remove(0);
            if(node.mV == v) {
                queue.clear();
                return node;
            }

            for(Node child: node.mChild)
                queue.add(child);
        }

        return null;
    }
}
