import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void rotate45byCCW() {
        Main main = new Main();
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.UP)], new int[]{-1, -1});
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.UL)], new int[]{0, -1});
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.LT)], new int[]{1, -1});
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.DL)], new int[]{1, 0});
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.DN)], new int[]{1, 1});
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.DR)], new int[]{0, 1});
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.RT)], new int[]{-1, 1});
        assertArrayEquals(main.dRC[main.rotate45byCCW(main.UR)], new int[]{-1, 0});
    }
}