import java.util.Arrays;
import java.util.Scanner;

// https://practice.geeksforgeeks.org/problems/box-stacking/1
// 문제 설명이 부족한 부분이 있습니다.
// 박스를 돌릴 수 있다고만 설명 되어 있지,
// 돌린 박스는 중복해서 사용할 수 있는 것으로 풀어야 합니다.
// 즉, 1, 2, 3 (h, w, l) 인 박스가 있으면,
// 실제로 박스 쌓을 때는 3개의 박스가 있는 것으로 풀어야 합니다.
// 1, 2, 3 / 3, 1, 2 / 2, 3, 1 이렇게 3개의 박스를 서로 다른 박스로 보고, 쌓을 수 있는 것으로 풀어야 합니다.
public class Geeks {
    static class Box implements Comparable<Box>{
        public int mh, mw, ml;
        public int mArea;
        public Box(int h, int w, int l) {
            mh = h;
            mw = w;
            ml = l;
            mArea = mh * mw;
        }

        // 내 위에, b 박스를 올려 놓을 수 있는가?
        // true 올려 놓을 수 있음
        // false 올려 놓을 수 없음
        public boolean IsAbleToStackAbove(Box b) {
            if(b.mh < mh && b.mw < mw)
                return true;

            return false;
        }

        @Override
        public int compareTo(Box o) {
            return o.mArea - mArea;
        }
    }

    public static int maxHeight(int height[], int width[], int length[], int n) {
        int r = 0;

        Box[] boxes = new Box[n*3];
        int[] msh = new int[n*3];

        // 문제에 잘 설명이 되어 있지를 않아서, 저도 다른 사람의 풀이를 보고 알게 되었습니다.
        // 박스는 돌릴 수 있기 때문에 3개 존재 합니다.
        // 심지어 박스를 돌려서, 돌리기 전의 박스와, 돌린 후의 박스에도 올릴 수 있습니다.
        // 이러면, 좀 문제가 이상해지는 것 같기는 한대....
        // 뭐 그렇게 되어 있네요.
        for(int i=0; i<n; ++i) {
            int h = height[i];
            int w = width[i];
            int l = length[i];

            boxes[i*3+0] = new Box(Math.max(h, w), Math.min(h, w), l);
            boxes[i*3+1] = new Box(Math.max(l, h), Math.min(l, h), w);
            boxes[i*3+2] = new Box(Math.max(w, l), Math.min(w, l), h);
        }

        // 박스 스택 문제에서 중요한 부분입니다.
        // 박스를 크기 순으로 내림 차순으로 정렬합니다.
        Arrays.sort(boxes);
        // 그러면 1번 박스위에 2번 박스가 올라 갈 수 있다고 할 때,
        // 2번 박스의 msh(maximum stack height)는 1번박스 ml + 2번 박스 ml 이 되구요.
        // 어떤 n 번째 있는 박스가  n-1 번째 박스위에 올라 갈 수 있으면
        // msh[n] = n번째 박스.l + msh[n-1] 이 됩니다.
        // 이렇게 풀기 위해서, 박스를 면적의 내림차순으로 정렬해야 합니다.

        for(int i=0; i<n*3; ++i)
            msh[i] = boxes[i].ml;

        for(int i=0; i<n*3; ++i) {
            for(int j=0; j<i; ++j) {
                if(boxes[j].IsAbleToStackAbove(boxes[i])){
                    int tmp = msh[j] + boxes[i].ml;
                    msh[i] = Math.max(msh[i], tmp);
                }
            }
        }

        // 1 부터 시작해도 됩니다.
        // 박스가 1개만 있다고 해도, 앞에서 말씀드린 것 처럼
        // 돌리는 것을 가만해서, 박스가 3개 존재 합니다.
        // 문제의 입력값에 1<=N 으로 되어 있습니다.
        r = msh[0];
        for(int i=1; i<n*3; ++i)
            r = Math.max(r, msh[i]);

        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();

	    for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int h[] = new int[N];
            int w[] = new int[N];
            int l[] = new int[N];

            for(int n=0; n<N; ++n) {
                h[n] = sc.nextInt();
                w[n] = sc.nextInt();
                l[n] = sc.nextInt();
            }

            int r = maxHeight(h, w, l, N);
            System.out.println(r);
        }
    }
}
