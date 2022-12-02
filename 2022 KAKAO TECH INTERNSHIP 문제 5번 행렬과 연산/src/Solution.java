import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Solution {
    class RC {
        int r, c;

        public RC(int sr, int sc) {
            r = sr;
            c = sc;
        }
    }

    Deque<Integer> qLft = new ArrayDeque<>();
    Deque<Integer> qTop = new ArrayDeque<>();
    Deque<Deque<Integer>> qCtr = new ArrayDeque<>();
    Deque<Integer> qBtm = new ArrayDeque<>();
    Deque<Integer> qRht = new ArrayDeque<>();

    public int[][] solution(int[][] dt, String[] operations) {
        int R = dt.length;
        int C = dt[0].length;

        qLft.clear();
        qTop.clear();
        for (Deque<Integer> ctr : qCtr) {
            ctr.clear();
        }
        qCtr.clear();
        qBtm.clear();
        qRht.clear();

        for (int r = 0; r < R; ++r) {
            qLft.add(dt[r][0]);
            qRht.add(dt[r][C - 1]);
        }

        for (int r = 0; r < R; ++r) {
            Deque<Integer> queue = new ArrayDeque<>();
            for (int c = 1; c < C-1; ++c) {
                queue.add(dt[r][c]);
            }
            qCtr.add(queue);
        }

        for (String op : operations) {
            if (op.equals("Rotate")) {
                rotate();
            } else if (op.equals("ShiftRow")) {
                shiftRow();
            }
        }

        for (int r = 0; r < R; ++r) {
            dt[r][0] = qLft.pollFirst();
            dt[r][C - 1] = qRht.pollFirst();
        }

        Iterator<Deque<Integer>> iter = qCtr.iterator();

        for (int r = 0; r < R; ++r) {
            Deque<Integer> queue = iter.next();
            for (int c = 1; c < C-1; ++c) {
                dt[r][c] = queue.pollFirst();
            }
        }

        return dt;
    }

    private void shiftRow() {
        qCtr.push(qCtr.pollLast());

        qLft.push(qLft.pollLast());
        qRht.push(qRht.pollLast());
    }

    private void rotate() {
        qTop = qCtr.peekFirst();
        qBtm = qCtr.peekLast();

        int v = qLft.pollFirst();
        qTop.push(v);
        v = qTop.pollLast();
        qRht.push(v);
        v = qRht.pollLast();
        qBtm.add(v);
        v = qBtm.pollFirst();
        qLft.add(v);
    }
}
