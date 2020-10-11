import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * BOJ 2644번 촌수계산
 * 문제링크 : https://www.acmicpc.net/problem/2644
 */

public class Main {
    public void solve() throws IOException {
        int N = sc.nextInt();
        int P1 = sc.nextInt();
        int P2 = sc.nextInt();
        int M = sc.nextInt();

        HashMap<Integer, MNode<Integer>> map = new HashMap<>();

        for(int i=0; i<M; ++i) {
            int nParent = sc.nextInt();
            int nChild = sc.nextInt();
            MNode<Integer> nodeParent = map.get(nParent);
            MNode<Integer> nodeChild = map.get(nChild);

            if (null == nodeParent) {
                nodeParent = new MNode<Integer>(nParent);
                map.put(nParent, nodeParent);
            }
            if (null == nodeChild) {
                nodeChild = new MNode<Integer>(nChild);
                map.put(nChild, nodeChild);
            }

            nodeParent.addChild(nodeChild);
        }

        MNode<Integer> nodeP1 = map.get(P1);
        MNode<Integer> nodeP2 = map.get(P2);

        Pair<Integer, MNode<Integer>> rltP1 = getLevel(nodeP1);
        Pair<Integer, MNode<Integer>> rltP2 = getLevel(nodeP2);

        // LCA가 있는 경우
        if(rltP1.getValue() == rltP2.getValue()) {
            int level1 = (Integer) rltP1.getKey();
            int level2 = (Integer) rltP2.getKey();

            // nodeP1,nodeP2의 Level이 같은 상태로 맞추기
            int deltaLevel = 0;

            if (level1 < level2) {
                deltaLevel = level2 - level1;

                for(int i=0; i<deltaLevel; ++i) {
                    nodeP2 = (MNode<Integer>)nodeP2.getParent();
                }
            }

            if (level1 > level2) {
                deltaLevel = level1 - level2;

                for(int i=0; i<deltaLevel; ++i) {
                    nodeP1 = (MNode<Integer>)nodeP1.getParent();
                }
            }

            int commonLevel = 0;
            // LCA가 있고, 2개의 노드가 같은 level에서 Parent를 찾아 간다.
            // 따라서, Parent를 찾아 반복해서 찾으면, 2개의 노드가 결국은 같은 노드가 된다
            while(nodeP1 != nodeP2) {
                commonLevel++;
                nodeP1 = (MNode<Integer>)nodeP1.getParent();
                nodeP2 = (MNode<Integer>)nodeP2.getParent();
            }

            int r = (commonLevel << 1) + deltaLevel;
            println(r);
        }
        else {  // LCA가 없는 경우
            println(-1);
        }
    }

    public Pair<Integer, MNode<Integer>> getLevel(MNode<Integer> node) {
        int level = 0;

        while(node.getParent() != null) {
            level++;
            node = (MNode<Integer>)node.getParent();
        }

        Pair<Integer, MNode<Integer>> rltPair = new Pair(level, node);

        return rltPair;
    }

    public void _solve() throws IOException {
        solve();
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main._solve();
    }

    public class MNode<TV> extends Node {
        ArrayList<MNode<TV>> mChidren ;

        MNode(TV v) {
            super(v);
            mChidren = new ArrayList<>();
        }

        public void addChild(MNode<TV> nodeChild) {
            nodeChild.setParent(this);
            mChidren.add(nodeChild);
        }
    }

    public class BNode<TV> extends Node {
        BNode<TV> mL;
        BNode<TV> mR;

        BNode(TV v) {
            super(v);
        }
    }

    public class Node<TV> {
        public TV mV;
        public Node<TV> mParent;

        Node(TV v) {
            setParent(null);
            set(v);
        }

        public void set(TV v) {
            mV = v;
        }

        public void setParent(Node<TV> parent) {
            mParent = parent;
        }

        public Node<TV> getParent() {
            return mParent;
        }
    }

    public class Pair<K, V> extends _Pair implements Comparable {
        public Pair(K key, V value) {
            super(key, value);
        }

        @Override
        public int compareTo(Object o) {
            Pair other = (Pair)o;
            int k1 = Integer.parseInt(this.getKey().toString());
            int k2 = Integer.parseInt(other.getKey().toString());

            if((k1 - k2) == 0) {
                int v1 = Integer.parseInt(this.getValue().toString());
                int v2 = Integer.parseInt(other.getValue().toString());
                return v1 - v2;
            }

            return k1 - k2;
        }
    }

    // http://cr.openjdk.java.net/~vadim/8140503/webrev.01/modules/base/src/main/java/javafx/util/Pair.java.html
    public class _Pair<K,V> {
        private K key;
        public K getKey() { return key; }
        public K L() { return getKey(); }
        public void setKey(K kk) { key = kk; }
        private V value;
        public V getValue() { return value; }
        public V R() { return getValue(); }
        public void setValue(V vv) { value = vv; }

        public void set(K kk, V vv) { key = kk; value = vv; }

        public _Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof _Pair) {
                _Pair pair = (_Pair) o;
                if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
                if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
                return true;
            }
            return false;
        }
    }

    // Normally used MIN/MAX number
    public final int _1087654321 = 1087654321;
    public final int __1087654321 = -1087654321;
    public final int _987654321 = 987654321;
    public final int __987654321 = -987654321;

    // Commonly used numbers as a divider of mod operation to prevent overflow
    public final int _10_007 = 10007;
    public final int _1_000_000_007 = 1000000007;
    final int MOD = _1_000_000_007;

    public BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(int v) throws IOException {
        print(v+"");
    }

    public void print(char c) throws IOException {
        bw.write(c);
    }

    public void print(String s) throws IOException {
        bw.write(s);
    }

    public void println() throws IOException {
        print("\n");
    }

    public void println(int a) throws IOException {
        print(a+"\n");
    }

    public void println(long a) throws IOException {
        print(a+"\n");
    }

    public void println(double a) throws IOException {
        print(a+"\n");
    }

    public void println(String s) throws IOException {
        bw.write(s+"\n");
    }

    public Reader sc = new Reader();
//    Sometimes, Reader class cause unknown problem, when I submit my java code to judge server.
//    For more detail, please see https://algospot.com/forum/read/4731/
//    public Scanner sc = new Scanner(System.in);

    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
    public class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String nextLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}