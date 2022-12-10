import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @Test
    void ex1() {
        Solution sln = new Solution();
        assertEquals("TCMA",
                sln.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5})
        );
    }
    @Test
    void ex2() {
        Solution sln = new Solution();
        assertEquals("RCJA",
                sln.solution(new String[]{"TR", "RT", "TR"}, new int[]{7, 1, 3})
        );
    }
}