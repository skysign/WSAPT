import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void ex1() {
        Solution main = new Solution();
        assertEquals(
                2,
                main.solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1})
        );
    }

    @Test
    void ex2() {
        Solution main = new Solution();
        assertEquals(
                7,
                main.solution(new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2})
        );
    }

    @Test
    void ex3() {
        Solution main = new Solution();
        assertEquals(
                -1,
                main.solution(new int[]{1, 1}, new int[]{1, 5})
        );
    }
}