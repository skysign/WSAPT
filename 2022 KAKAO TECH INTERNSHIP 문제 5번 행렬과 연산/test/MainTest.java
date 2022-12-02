import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void ex1() {
        Solution main = new Solution();
//[[1, 2, 3], [4, 5, 6], [7, 8, 9]] 	["Rotate", "ShiftRow"] 	[[8, 9, 6], [4, 1, 2], [7, 5, 3]]
        assertArrayEquals(
                main.solution(
                        new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        new String[]{"Rotate", "ShiftRow"}),
                new int[][]{{8, 9, 6}, {4, 1, 2}, {7, 5, 3}}
        );
    }

    @Test
    void ex2() {
        Solution main = new Solution();
//[[8, 6, 3], [3, 3, 7], [8, 4, 9]] 	["Rotate", "ShiftRow", "ShiftRow"] 	[[8, 3, 3], [4, 9, 7], [3, 8, 6]]
        assertArrayEquals(
                main.solution(
                        new int[][]{{8, 6, 3}, {3, 3, 7}, {8, 4, 9}},
                        new String[]{"Rotate", "ShiftRow", "ShiftRow"}),
                new int[][]{{8, 3, 3}, {4, 9, 7}, {3, 8, 6}}
        );
    }

    @Test
    void ex3() {
        Solution main = new Solution();
//[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]] 	["ShiftRow", "Rotate", "ShiftRow", "Rotate"]
//[[1, 6, 7 ,8], [5, 9, 10, 4], [2, 3, 12, 11]]
        assertArrayEquals(
                main.solution(
                        new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}},
                        new String[]{"ShiftRow", "Rotate", "ShiftRow", "Rotate"}),
                new int[][]{{1, 6, 7 ,8}, {5, 9, 10, 4}, {2, 3, 12, 11}}
        );
    }
}