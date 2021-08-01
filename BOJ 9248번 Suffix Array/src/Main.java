import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    int N;
    char[]  cdt;
    Integer[] SA;
    String  strDt;
    int[] group;
    int[] tempGroup;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public int myCompare(Integer n1, Integer n2) {
        if (group[n1] == group[n2])
            return (group[n1+1] < group[n2+1]) ? -1 : ((group[n1+1] == group[n2+1]) ? 0 : 1);

        return (group[n1] < group[n2]) ? -1 : ((group[n1] == group[n2]) ? 0 : 1);
    }

    public void solve() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        strDt = br.readLine();
//        String  strDt = "banana";
//        String  strDt = "abracadabra";

        cdt = strDt.toCharArray();
        N = strDt.length();
        SA = new Integer[N];
        group = new int[N+1];
        tempGroup = new int[N+1];

        for (int i=0; i<N; ++i) {
            SA[i] = i;
            group[i] = cdt[SA[i]] - 'a';
//            group[i] = strDt.charAt(SA[i]) - 'a';
        }

        for (int idxCmp=1; idxCmp<N; idxCmp*=2) {
            group[N] = Integer.MIN_VALUE;

            Arrays.sort(SA, new Comparator<Integer>() {
                @Override
                public int compare(Integer n1, Integer n2) {
                    return myCompare(n1, n2);
                }
            });

            tempGroup[SA[0]] = 0;

            for (int i = 1; i < N; i++)
            {
                if (myCompare(SA[i - 1], SA[i]) != 0)
                    tempGroup[SA[i]] = tempGroup[SA[i - 1]] + 1;
                else
                    tempGroup[SA[i]] = tempGroup[SA[i - 1]];
            }

            for (int i = 0; i < N; i++) {
                group[i] = tempGroup[i];
            }

//            bw.write(String.valueOf(idxCmp));
//            bw.newLine();
//            for (int v: SA) {
//                bw.write(String.valueOf(group[v]));
//                bw.write(' ');
//                bw.write(String.valueOf(v));
//                bw.write(' ');
//                bw.write(strDt.substring(v));
//                bw.newLine();
//            }
//            bw.write("------");
//            bw.newLine();
        }


//        StringBuilder sb = new StringBuilder();

        for (int i=0; i<N; ++i) {
            int v = SA[i];
//            sb.append(String.valueOf(v+1));
//            sb.append(' ');
            bw.write(String.valueOf(v+1));
            bw.write(' ');
//            bw.write(strDt.substring(v));
//            bw.newLine();
        }
//        System.out.println(sb.toString());
        bw.newLine();

//        sb = new StringBuilder();
//        sb.append(String.valueOf('x'));
//        sb.append(' ');
        bw.write(String.valueOf('x'));
        bw.write(' ');

        for (int i=1; i<N; ++i) {
            int r = myLCP(SA[i-1], SA[i]);
//            sb.append(String.valueOf(r));
//            sb.append(' ');
            bw.write(String.valueOf(r));
            bw.write(' ');
        }

//        System.out.println(sb.toString());
//        int[] orderedSuffixes = new int[N];
//
//        for (int i=0; i<N; ++i) {
//            orderedSuffixes[SA[i]] = i;
//        }
//
//        for (int i=0; i<N; ++i) {
//            int idxInDT = orderedSuffixes[i];
//
//            for (int j=idxInDT; j<N; ++j) {
//                bw.write(cdt[j]);
//            }
//            bw.newLine();
//        }

        bw.flush();
    }

    int myLCP(int idx1, int idx2) {
        int l = Math.min(N-idx1, N-idx2);
        int r = 0;

        for (int i=0; i<l; ++i) {
//            if (strDt.charAt(idx1 +i) == strDt.charAt(idx2 +i))
//                ++r;
            if (cdt[idx1 +i] == cdt[idx2 +i])
                ++r;
            else
                break;
        }

        return r;
    }
}