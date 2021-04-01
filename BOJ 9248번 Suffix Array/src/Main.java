import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Pair[] pairs;

    public void solve() throws IOException {
        String dt = br.readLine();
        int l = dt.length();
        pairs = new Pair[l];

        for (int i=0; i<l; ++i) {
            pairs[i] = new Pair<String, Integer>(dt.substring(i, l), i+1);
        }

        Arrays.sort(pairs);

        for (int i=0; i<l; ++i) {
            Integer n = (Integer)(pairs[i].R());
            bw.write(String.valueOf(n) + " ");
        }
        bw.newLine();

        bw.write("x ");

        for (int i=1; i<l; ++i) {
            String t = getCommonPrefix((String)(pairs[i-1].L()), (String)(pairs[i].L()));
            bw.write(String.valueOf(t.length()) + " ");
        }

        bw.close();
    }

    public String getCommonPrefix(String stra, String strb) {
        int l = Math.min(stra.length(), strb.length());
        int r = 0;

        for (int i=0; i<l; ++i) {
            if (stra.charAt(i) == strb.charAt(i))
                r++;
            else
                break;
        }

        return stra.substring(0, r);
    }

    public class Pair<K, V> extends _Pair implements Comparable {
        public Pair(K key, V value) {
            super(key, value);
        }

        @Override
        public int compareTo(Object o) {
            Pair other = (Pair)o;
            String stra = (String)this.L();
            String strb = (String)other.L();

            return stra.compareTo(strb);
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

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}