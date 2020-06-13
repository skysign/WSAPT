package test;
import java.util.PriorityQueue;

public class maze {
	static class situation implements Comparable<situation> {
		int Ti, Tj, Mi, Mj, goalI, goalJ, cnt;
		String way;

		situation(int Ti, int Tj, int Mi, int Mj, int goalI, int goalJ, String way, int cnt) {
			this.Ti = Ti;
			this.Tj = Tj;
			this.Mi = Mi;
			this.Mj = Mj;
			this.goalI = goalI;
			this.goalJ = goalJ;
			this.way = way;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(situation o) {
			// TODO Auto-generated method stub
			if (this.cnt - o.cnt == 0) {
				int goalI = this.goalI;
				int goalJ = this.goalJ;
				int difGoalThisI = Math.abs(goalI - this.Ti);
				int difGoalThisJ = Math.abs(goalJ - this.Tj);
				int difThis = difGoalThisI + difGoalThisJ;
				int difGoalOI = Math.abs(goalI - o.Ti);
				int difGoalOJ = Math.abs(goalJ - o.Tj);
				int difO = difGoalOI + difGoalOJ;

				return difThis - difO;
			}
			return this.cnt - o.cnt;
		}

		@Override
		public String toString() {
			return "situation [Ti=" + Ti + ", Tj=" + Tj + ", Mi=" + Mi + ", Mj=" + Mj + ", goalI=" + goalI + ", goalJ="
					+ goalJ + ", cnt=" + cnt + ", way=" + way + "]";
		}
	}

	static String result;
	public static void main(String[] args) {
		int[][] maze = {{12, 5, 0, 4, 6, 4 , 6, 5, 4},
				{6, 4, 13, 8, 8, 8, 8, 10, 8},
				{8, 9, 4, 1, 7, 9, 7, 6, 2},
				{9, 4, 8, 9, 4, 6, 5, 3, 7},
				{11, 8, 9, 0, 7, 9, 5, 5, 4},
				{1, 7, 6, 7, 6, 0, 5, 5, 7},
				{8, 11, 1, 10, 8, 9, 5, 5, 4},
				{13, 1, 3, 5, 3, 10, 12, 5, 7},
				{4, 16, 6, 0, 0, 0, 0, 0, 0}};
		int Ti = 0;
		int Tj = 0;
		int Mi = 2;
		int Mj = 2;
		int goalI = 8;
		int goalJ = 1;
		result = "Fail";
		bfs(maze, Ti, Tj, Mi, Mj, goalI, goalJ);
		System.out.println(result);

		int[][] maze2 = {{15, 15, 15, 16, 15, 15, 15, 15, 15},
				{12, 5, 0, 17, 0, 5, 6, 0, 0, 4},
				{6, 0, 17, 17, 17, 0, 17, 17, 2},
				{1, 17, 17, 3, 17, 17, 17, 17, 2},
				{8, 9, 2, 11, 1, 17, 17, 2, 8},
				{8, 6, 17, 17, 17, 17, 17, 7, 8},
				{8, 1, 17, 17, 17, 17, 17, 4, 8},
				{1, 17, 17, 3, 17, 17, 17, 2, 8},
				{9, 3, 3, 5, 3, 3, 3, 3, 7}};
		Ti = 1;
		Tj = 8;
		Mi = 1;
		Mj = 0;
		goalI = 0;
		goalJ = 3;
		result = "Fail";
		bfs(maze2, Ti, Tj, Mi, Mj, goalI, goalJ);
		System.out.println(result);

		int[][] maze3 = {{16, 4, 6, 0, 0, 5, 0, 10},
				{15, 1, 17, 17, 17, 17, 0, 2, 11},
				{15, 1, 17, 17, 17, 2, 1, 17, 2},
				{15, 1, 3, 17, 2, 1, 3, 17, 2},
				{15, 1, 0, 2, 1, 17, 0, 17, 2},
				{15, 13, 8, 1, 17, 17, 2, 9, 2},
				{15, 12, 2, 1, 2, 9, 17, 0, 2},
				{15, 12, 3, 3, 3, 10, 9, 3, 7}};
		Ti = 0;
		Tj = 1;
		Mi = 1;
		Mj = 2;
		goalI = 0;
		goalJ = 0;
		result = "Fail";
		bfs(maze3, Ti, Tj, Mi, Mj, goalI, goalJ);
		System.out.println(result);
	}

	/*
	 * 0 : U 1 : L 2 : R 3 : D 4 : UR 5 : UD 6 : LR 7 : RD 8 : RL 9 : DL 10 : URD 11
	 * : URL 12 : UDL 13 : RDL 14 : URLD 15 : X 16 : Exit
	 * 
	 * 위를 막음 0, 4, 5, 6, 10, 11, 12, 14 아래를 막음 3, 5, 7, 9, 10, 12, 13, 14 왼쪽을 막음 1,
	 * 6, 8, 9, 11, 12, 13, 14 오른쪽을 막음 2, 4, 7, 8, 10, 11, 13, 14
	 */

	static void bfs(int[][] maze, int Ti, int Tj, int Mi, int Mj, int goalI, int goalJ) {
		PriorityQueue<situation> pq = new PriorityQueue<situation>();
		situation s = new situation(Ti, Tj, Mi, Mj, goalI, goalJ, "", 0);
		int[] dx = { 0, 0, 1, -1, 0 };
		int[] dy = { 1, -1, 0, 0, 0 };
		String[] direct = { "R", "L", "D", "U", "S" };
		pq.offer(s);
		while (!pq.isEmpty()) {
			s = pq.poll();
			if (s.Mi == s.Ti && s.Mj == s.Tj)
				continue;
			if (s.Ti == goalI && s.Tj == goalJ) {
				result = s.way;
				return;
			}

			for (int i = 0; i < 5; ++i) {
				if (i == 0) { // 오른쪽
					if (isRightBlock(maze, s.Ti, s.Tj))
						continue;
				} else if (i == 1) { // 왼쪽
					if (isLeftBlock(maze, s.Ti, s.Tj))
						continue;
				} else if (i == 2) { // 아래로
					if (isDownBlock(maze, s.Ti, s.Tj))
						continue;
				} else if (i == 3) { // 위로
					if (isUpBlock(maze, s.Ti, s.Tj))
						continue;
				}
				int tempTi = s.Ti + dx[i];
				int tempTj = s.Tj + dy[i];
				if (tempTi < 0 || tempTj < 0 || tempTi >= maze.length || tempTj >= maze[0].length
						|| maze[tempTi][tempTj] == 15)
					continue;
				if (tempTi == s.Mi && tempTj == s.Mj)
					continue;

				String temp = s.way + direct[i] + " ";
				
				int tempMi;
				int tempMj;
				int dir;
				tempMi = s.Mi;
				tempMj = s.Mj;
				dir = directDecide(tempMi, tempMj, tempTi, tempTj);
				boolean goFlag = true;
				if (dir == 0) { // 오른쪽
					if (isRightBlock(maze, tempMi, tempMj))
						goFlag = false;
				} else if (dir == 1) { // 왼쪽
					if (isLeftBlock(maze, tempMi, tempMj))
						goFlag = false;
				} else if (dir == 2) { // 아래로
					if (isDownBlock(maze, tempMi, tempMj))
						goFlag = false;
				} else if (dir == 3) { // 위로
					if (isUpBlock(maze, tempMi, tempMj))
						goFlag = false;
				}
				if(goFlag) {					
					tempMi += dx[dir];
					tempMj += dy[dir];
				}
				dir = directDecide(tempMi, tempMj, tempTi, tempTj);
				goFlag = true;
				if (dir == 0) { // 오른쪽
					if (isRightBlock(maze, tempMi, tempMj))
						goFlag = false;
				} else if (dir == 1) { // 왼쪽
					if (isLeftBlock(maze, tempMi, tempMj))
						goFlag = false;
				} else if (dir == 2) { // 아래로
					if (isDownBlock(maze, tempMi, tempMj))
						goFlag = false;
				} else if (dir == 3) { // 위로
					if (isUpBlock(maze, tempMi, tempMj))
						goFlag = false;
				}
				if(goFlag) {					
					tempMi += dx[dir];
					tempMj += dy[dir];
				}
				if (!(tempMi == tempTi && tempMj == tempTj)) {					
					pq.offer(new situation(tempTi, tempTj, tempMi, tempMj, goalI, goalJ, temp, s.cnt + 1));
				}
				
			}
		}

	}
	static int directDecide(int Mi, int Mj, int Ti, int Tj) {
		if (Mj - Tj < 0) {
			// Tj가 더 크다 오른쪽이다.
			return 0;
		} else if (Mj - Tj > 0) {
			return 1;
		} else if (Mi - Ti < 0) {
			// Ti가 더 크다 밑에 있다.
			return 2;
		} else if (Mi - Ti > 0) {
			return 3;
		}
		return 4;
	}
	static boolean isLeftBlock(int[][] maze, int i, int j) {
		int value = maze[i][j];
		if (value == 1 || value == 6 || value == 8 || value == 9 || value == 11 || value == 12 || value == 13
				|| value == 14) {
			return true;
		}
		return false;
	}

	static boolean isRightBlock(int[][] maze, int i, int j) {
		int value = maze[i][j];
		if (value == 2 || value == 4 || value == 7 || value == 8 || value == 10 || value == 11 || value == 13
				|| value == 14) {
			return true;
		}
		return false;
	}

	static boolean isUpBlock(int[][] maze, int i, int j) {
		int value = maze[i][j];
		if (value == 0 || value == 4 || value == 5 || value == 6 || value == 10 || value == 11 || value == 12
				|| value == 14) {
			return true;
		}
		return false;
	}

	static boolean isDownBlock(int[][] maze, int i, int j) {
		int value = maze[i][j];
		if (value == 3 || value == 5 || value == 7 || value == 9 || value == 10 || value == 12 || value == 13
				|| value == 14) {
			return true;
		}
		return false;
	}


}
