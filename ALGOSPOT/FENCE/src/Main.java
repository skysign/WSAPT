import java.util.Scanner;

/**
 * FENCE 울타리 잘라내기  / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/FENCE
 * 제출링크 : https://algospot.com/judge/submission/detail/654724
 */
public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int r = 0;

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int[] d = new int[N];

            for(int i=0; i<N; ++i)
                d[i] = sc.nextInt();

            r = maxRect(d, 0, N-1);
            System.out.println(r);
        }
    }

    public int maxRect(int[] d, int beg, int end) {
        if(beg == end) {
            return d[beg];
        }

        int mid = (beg+end) >> 1;
        int r = Math.max(maxRect(d, beg, mid), maxRect(d, mid+1, end));
        int left = mid, right = mid+1;
        int maxHeight = Math.min(d[left], d[right]);
        int tr = (right-left+1) * maxHeight;

        while((beg<=left) || (right<= end)) {
            int hl = getHeightSafely(d, left-1, beg, end);
            int hr = getHeightSafely(d, right+1, beg, end);

            if(hl == -1 && hr == -1) {
                break;
            }

            if(hl > hr) {
                left--;
                maxHeight = Math.min(maxHeight, hl);
            }
            else {
                right++;
                maxHeight = Math.min(maxHeight, hr);
            }

            tr = Math.max(tr, (right-left+1) * maxHeight);
        }

        return Math.max(r, tr);
    }

    int getHeightSafely(int[] d, int i, int beg, int end) {
        if((beg<=i) && (i<= end)) {
            return d[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
