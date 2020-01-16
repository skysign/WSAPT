import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int N = sc.nextInt();
	    int K = sc.nextInt();
	    int[] as = new int[N];
	    boolean[] Rs = new boolean[K+1];

	    for(int i=0; i<N; ++i) {
            as[i] = sc.nextInt();
        }

	    // Rs[0] false 임으로 Taro가 이기는 경우 입니다.
        for(int i=1; i<=K; ++i) {
            boolean bRi = false;
            for(int j=0; j<N && (bRi == false); ++j) {
                boolean tRi = false;
                int r = i - as[j];
                if(r < 0) {
                    tRi = false;
                }
                else {
                    tRi = !Rs[r];
                }
                bRi = (bRi | tRi);
            }
            Rs[i] = bRi;
        }

        System.out.println(Rs[K]? "First": "Second");
    }
}
