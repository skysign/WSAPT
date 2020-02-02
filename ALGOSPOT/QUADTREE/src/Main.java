import java.util.Scanner;

/**
 * QUADTREE 쿼드 트리 뒤집기 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/QUADTREE
 * 제출링크 : https://algospot.com/judge/submission/detail/654647
 */
public class Main {
    Scanner sc = new Scanner(System.in);

    public void solve() {
        int T = sc.nextInt();
        sc.nextLine();

        for(int t=0; t<T; ++t) {
            String in = sc.nextLine();
            String r = run(in);
            System.out.println(r);
        }
    }

    public String run(String S) {
        if((S.charAt(0) != 'x') || (S.length() < 1))
            return S;

        String a = "";
        StringBuilder r = new StringBuilder("");
        a = reverse(S, r);
        return r.toString();
    }

    public String reverse(String S, StringBuilder strRtn) {
        StringBuilder[] ss = new StringBuilder[]{
                new StringBuilder(""),
                new StringBuilder(""),
                new StringBuilder(""),
                new StringBuilder("")};
        // 맨앞에 x 가 있기 때문에, x를 잘라내고.
        S = S.substring(1);
        S = parse(S, ss[0]);
        S = parse(S, ss[1]);
        S = parse(S, ss[2]);
        S = parse(S, ss[3]);


        strRtn.append('x');
        strRtn.append(ss[2]);
        strRtn.append(ss[3]);
        strRtn.append(ss[0]);
        strRtn.append(ss[1]);
//        strRtn = 'x' + ss[2] + ss[3] + ss[0] + ss[1];
        return S;
    }

    public String parse(String S, StringBuilder strRtn) {
        if(S.length() < 1)
            return "";

        String strRemained = "";

        if(S.charAt(0) == 'x') {
            strRemained = reverse(S, strRtn);
        }
        else {
            strRtn.append(S.charAt(0));
            strRemained = S.substring(1);
        }

        return strRemained;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}