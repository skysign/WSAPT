import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void ex1() {
        Main main = new Main();
//        6 	[[1, 2, 3], [2, 3, 5], [2, 4, 2], [2, 5, 4], [3, 4, 4], [4, 5, 3], [4, 6, 1], [5, 6, 1]] 	[1, 3] 	[5] 	[5, 3]
        assertArrayEquals(
                new int[]{5, 3},
                main.solution(6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                        new int[]{1, 3}, new int[]{5})
        );
    }

    @Test
    void ex2() {
        Main main = new Main();
//        7 	{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}} 	{1} 	{2, 3, 4} 	{3, 4}
        assertArrayEquals(
                new int[]{3, 4},
                main.solution(7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
                        new int[]{1}, new int[]{2, 3, 4})
        );
    }

    @Test
    void ex3() {
        Main main = new Main();
//        7 	{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}} 	{3, 7} 	{1, 5} 	{5, 1}
        assertArrayEquals(
                new int[]{5, 1},
                main.solution(7, new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}},
                        new int[]{3, 7}, new int[]{1, 5})
        );
    }

    @Test
    void ex4() {
        Main main = new Main();
//        5 	{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}} 	{1, 2} 	{5} 	{5, 6}
        assertArrayEquals(
                new int[]{5, 6},
                main.solution(5, new int[][]{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}},
                        new int[]{1, 2}, new int[]{5})
        );
    }
}