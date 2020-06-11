public class Main {
    class Solution {
        public int solution(int[][] loc, int[] s, int[] e) {
            int sJ = s[0];
            int sI = s[1];
            int eJ = e[0];
            int eI = e[1];

            int minJ = Math.min(sJ, eJ);
            int maxJ = Math.max(sJ, eJ);
            int minI = Math.min(sI, eI);
            int maxI = Math.max(sI, eI);

            int r = 0;

            for(int j=0; j<loc.length; ++j) {
                for(int i=0; i<loc[0].length; ++i){
                    if(loc[j][i] > 1) {
                        if(minJ <= j && j <= maxJ &&
                                minI <= i && i <= maxI ) {
                            r++;
                            System.out.println(j + " " +i);
                        }
                    }
                }
            }


            return r;
        }
    }

    public void slove() {
        Solution sln = new Solution();
        int v = 0;
        int[][] loc = new int[6][6];

        loc[0][3] = 1;
        loc[1][1] = 1;
        loc[1][5] = 1;
        loc[2][2] = 1;
        loc[3][3] = 1;
        loc[4][0] = 1;

        int[] s = new int[]{1, 4};
        int[] e = new int[]{4, 1};

//        int[] s = new int[]{3, 4};
//        int[] e = new int[]{0, 0};
        int r = sln.solution(loc, s, e);

        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.slove();
    }
}
