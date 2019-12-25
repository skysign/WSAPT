import java.util.Scanner;

// Hamiltonian Path / geeksforgeeks.org
// 문제 링크 : https://practice.geeksforgeeks.org/problems/hamiltonian-path/0
// 문제 해설 : https://www.geeksforgeeks.org/hamiltonian-cycle-backtracking-6/

public class GFG {
    /**
     * 2개의 vertext사이에 edge가 존재할 때, 기존에 방문했던 vertex인지 확인하는 메서드
     * @param vertex 방문하려고 하는 vertex 숫자
     * @param vertices 지금까지 방문한 순서대로 vertex 숫자
     * @param idxVertices vertices에 저장되어 있는 vertex의 갯수
     * @return 방문했던 vertex true, 아니면 false
     */
    public static boolean isVisited(int vertex, int[] vertices, int idxVertices){
        for(int i=1; i<idxVertices; ++i) {
            if(vertex == vertices[i])
                return true;
        }

        return false;
    }

    /**
     * @param J 방문한 vertext 숫자
     * @param m 2개의 vertex에 edge가 있으면 true, 아니면 false
     * @param vertices 방문한 순서대로  vertext 숫자를 저장한 배열
     * @param idxVertices vertices에 vertext 숫자를 저장할 인덱스
     * @param numOfVertices vertices의 길이, 항상 N+1 입니다.
     * @return Hamiltonian Path를 찾았으면 1, 못 찾았으면, 0
     */
    public static int path(int J, boolean[][] m, int[] vertices, int idxVertices, int numOfVertices) {
        // 마지막 vertex까지 다 찾았으면, 리턴
        if(idxVertices == numOfVertices) {
            if(m[vertices[idxVertices-1]][0]){
                return 1;
            }
        }


        int i=J;
        for(int j=1; j<m[0].length; ++j) {
            // i vertex에서 j vertex로 가는 길이 있는지 찾고
            if(m[i][j]) {
                // j vertex가 기존에 방문했던 vertext가 아니면
                if(false == isVisited(j, vertices, idxVertices)) {
                    // 방문한 순서대로 vertices에 j vertex를 저장하고
                    vertices[idxVertices] = j;
                    // j vertex에서 방문 가능한 다음번 vertex를 찾아 갑니다.
                    if(1 == path(j, m, vertices, 1+idxVertices, numOfVertices)) {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    public static int HamiltonianPath(int N, int M, int[] p) {
        int r = 0;
        // 문제를 좀 쉽게 풀기 위해서,
        // vertex 개수인 N과 m 배열의 인덱스가 서로 같은게 코딩하기도, 코드 읽기도 편하구요,
        // 0 번이 있어야, Hamiltonian Path가 있는지 최종 체크하기가 편하기도 합니다.
        // 실제로 문제에서, 입력은 받지 않지만, 0 번째 vertex가 있다고 가정합니다.
        boolean[][] m = new boolean[N+1][N+1];
        int[] vertices = new int[N+1];

        // 문제의 마지막 문장이 아래와 같이 끝납니다.
        // Then in the next line are M space separated pairs u,v denoting an edge from u to v.
        // 마지막 단락을 해석해 보면, u 에서 v 로 가는 edge가 directed 로 있는 것지만,
        // 실제로는 아닙니다. 문제 두번째 문장에, 'Give an undirected graph' 로 되어 있기 때문에,
        // 1 8 을 입력받으면, 1,8 과 8,1 2개의 방향 모두 이동 가능합니다.
        for(int i=0; i<M*2; i+=2) {
            m[p[i]][p[i+1]] = true;
            m[p[i+1]][p[i]] = true;
        }

        // 0번째 vertex로 가는 edge는 항상 존재 합니다.
        // 즉, 첫번째 vertex에서 마지막 vertex까지 찾은 다음에,
        // 마지막 vertext가 0으로 가는길이 있다면, Hamiltonian Path가 존재 합니다.
        for(int j=1; j<=N; ++j)
            m[j][0] = true;

        // 항상 첫번째 시작은 0번째 vertex부터 시작하구요.
        vertices[0] = 0;

        // 모든 vertex에서 시작할 수 있기 때문에,
        // m[0][i] = true해주고
        // path를 못 찾았으면
        // m[0][i] = false 그다음 i 로 넘어가서 Hamiltonian Path를 찾습니다.
        for(int i=1; i<=N; ++i) {
            m[0][i] = true;
            r = path(0, m, vertices, 1, N+1);
            if(r == 1) {
                return 1;
            }
            m[0][i] = false;
        }

        return r;
    }

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int T=sc.nextInt();

	    while(T-->0) {
	        int N = sc.nextInt();
	        int M = sc.nextInt();
	        int[] a = new int[M*2];

	        for(int i=0; i<M*2; ++i) {
	            a[i] = sc.nextInt();
            }

	        int r = HamiltonianPath(N, M, a);
	        System.out.println(r);
        }
    }
}
