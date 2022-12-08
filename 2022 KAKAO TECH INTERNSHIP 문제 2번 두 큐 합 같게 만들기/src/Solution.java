import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    Deque<Integer> q1 = new ArrayDeque<>();
    Deque<Integer> q2 = new ArrayDeque<>();

    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0;
        int maxCount = (queue1.length + queue2.length) * 2;

        q1.clear();
        q2.clear();

        for (int item: queue1) {
            q1.add(item);
            sum1 += item;
        }

        for (int item: queue2) {
            q2.add(item);
            sum2 += item;
        }

        maxCount *=2;

        int cnt = 0;

        for (cnt = 0; cnt < maxCount; ++cnt) {
            if (sum1 == sum2) {
                break;
            }
            else if (sum1 > sum2) {
                int item = q1.pollFirst();
                sum1 -= item;
                sum2 += item;
                q2.add(item);
            }
            else if (sum1 < sum2) {
                int item = q2.pollFirst();
                sum1 += item;
                sum2 -= item;
                q1.add(item);
            }
        }

        return (cnt < maxCount)? cnt: -1;
    }
}