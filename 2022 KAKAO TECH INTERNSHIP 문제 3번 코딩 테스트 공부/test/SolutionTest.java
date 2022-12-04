import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @Test
    void ex1() {
        Solution main = new Solution();
        assertEquals(
                15,
                main.solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}})
        );
    }

    @Test
    void ex2() {
        Solution main = new Solution();
        assertEquals(
                13,
                main.solution(0, 0, new int[][]{{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}})
        );
    }

//    @Test
//    void ex2() {
//        Solution main = new Solution();
//        assertEquals(
//                15,
//                main.solution(0, 0, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}})
//        );
//    }
}