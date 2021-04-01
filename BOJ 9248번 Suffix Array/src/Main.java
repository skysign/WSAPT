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
            pairs[i] = new Pair(dt.substring(i, l).toCharArray(), i+1);
        }

        Arrays.sort(pairs);

        for (int i=0; i<l; ++i) {
            int n = pairs[i].mn;
            bw.write(String.valueOf(n) + " ");
        }
        bw.newLine();

        bw.write("x ");

        for (int i=1; i<l; ++i) {
            int n = getCommonPrefix(pairs[i-1].mc, pairs[i].mc);
            bw.write(String.valueOf(n) + " ");
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

    public int getCommonPrefix(char[] stra, char[] strb) {
        int l = Math.min(stra.length, strb.length);
        int r = 0;

        for (int i=0; i<l; ++i) {
            if (stra[i] == strb[i])
                r++;
            else
                break;
        }

        return r;
    }

    class Pair implements Comparable{
        char[] mc;
        int    mn;

        Pair(char[] c, int n) {
            mc = c;
            mn = n;
        }

        @Override
        public int compareTo(Object _o) {
            Pair o = (Pair)_o;
            int l = Math.min(this.mc.length, o.mc.length);

            for (int i=0; i<l; ++i) {
                if (this.mc[i] == o.mc[i])
                    continue;
                else
                    return (int)(mc[i] - o.mc[i]);
            }

            return (mc.length - o.mc.length);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}