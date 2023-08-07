/**
 * 221. Maximal Square / leetCode
 * 문제링크 : https://leetcode.com/problems/maximal-square/
 * Submission : https://leetcode.com/submissions/detail/298397463/
 */
public class Main {
    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }

    public void solve() {
//      char[][] map = new char[][]{
//        {'1','1','1','1'},
//        {'1','1','1','1'},
//        {'1','1','1','1'}};
        char[][] map = new char[][]{
                {'0','0','0','1'},
                {'1','1','0','1'},
                {'1','1','1','1'},
                {'0','1','1','1'},
                {'0','1','1','1'}};
        int r = maximalSquare(map);
        System.out.println(r);
    }

    public int maximalSquare(char[][] matrix) {
        int r = 0;

        for(int i=0; i<matrix.length; ++i) {
            for(int j=0; j<matrix[0].length; ++j) {
                if('1' == matrix[i][j])
                    r = Math.max(r, adj1(matrix, i, j, 0, matrix.length, matrix[0].length));
            }
        }

        return r;
    }

    public int adj1(char[][] map, int i, int j, int d, int M, int N) {
        if(!((i+d)<M))
            return 0;

        if(!((j+d)<N))
            return 0;

        int I = i +d;
        int J = j +d;
        boolean br = true;

        for(int tj=j; tj<J; ++tj) {
            br &= (map[I][tj] == '1');
            if(!br)
                return 0;
        }

        for(int ti=i; ti<I; ++ti) {
            br &= (map[ti][J] == '1');
            if(!br)
                return 0;
        }

        if('1' != map[I][J])
            return 0;

        int r = (int)Math.pow(d+1, 2);

        return Math.max(r, adj1(map, i, j, d+1, M, N));
    }
}
