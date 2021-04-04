import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    int cntComputer;/** 컴퓨터의 개수 */
    int cntEdge;    /** 컴퓨터가 서로 연결되어 있는 엣지의 수 */
    int[][] map; /** map[fr][to] ==1 이면, fr 컴퓨터에서 to 컴퓨터로 연결되어 있음 */

    public void solve() {
        Scanner sc = new Scanner(System.in);

        cntComputer = sc.nextInt();
        cntEdge = sc.nextInt();
        map = new int[cntComputer+1][cntComputer+1]; /** 컴퓨터의 번호를 그대로, 인덱스로 사용하기 위해서 +1을 합니다 */

        for(int i=0; i<cntEdge; ++i) {
            int fr = sc.nextInt();
            int to = sc.nextInt();

            map[fr][to] = 1; /** 양방으로 연결되어 있으므로, fr,to 와 to, fr 모두 1 */
            map[to][fr] = 1;
        }

        Deque<Integer> queue = new ArrayDeque<>();      /** BFS로 탐색하기 위한 큐 */
        Deque<Integer> infected = new ArrayDeque<>();   /** 웜에 걸린 컴퓨터의 번호를 저장하는 큐 */
        queue.add(1); /** 문제에 따라서, 1번 컴퓨터가 웜에 걸렸습니다 */
        infected.add(1); /** 1번 컴퓨터를 웜에 걸린 컴퓨터 번호에 넣습니다 */

        while(queue.size() > 0) {
            int fr = queue.pop(); /** 웜에 걸린 컴퓨터 번호 */

            for(int to=1; to<=cntComputer; ++to) {
                if(map[fr][to] == 1) { /** 웜에 걸린 컴퓨터와 연결된 컴퓨터 찾기 */
                    /** 이미 웜에 걸려있던 컴퓨터 인가? */
                    if(infected.contains(to) == false){
                        /** 새로 웜에 걸린 컴퓨터와 연결된 컴퓨터도 웜에 걸리게 하기 위해서
                         *  queue와 infected에 새로 웜에 걸린 컴퓨터 번호를 저장합니다.
                         *
                         *  새로 웜에 걸린 것인지 확인하지 않으면,
                         *  이미 웜에 걸린 컴퓨터인대도,
                         *  중복해서 웜에 걸린 걸로 판단하게 됩니다.
                         *  문제 예제입력 1번에서는 5번 컴퓨터를 2번 감염된 것으로 카운트 하는 오동작을 하게 됩니다.
                         */
                        queue.add(to);
                        infected.add(to);
                    }
                }
            }
        }

        System.out.println(infected.size() -1); /** 웜에 걸린 컴퓨터의 개수에서, 1번 컴퓨터 제외하기 위해서, -1 */
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}