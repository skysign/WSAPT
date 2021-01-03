import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    @Test
    public void test2() throws IOException {
        System.out.println();
        BOJ_10265("1");
        BOJ_10265("2");
        BOJ_10265("3");
        BOJ_10265("antigreedy1");
        BOJ_10265("antigreedy2");
        BOJ_10265("antigreedy3");
        BOJ_10265("bigcircle1");
        BOJ_10265("bigcircle2");
        BOJ_10265("exponential");
        BOJ_10265("huge1");
        BOJ_10265("huge2");
        BOJ_10265("huge3");
        BOJ_10265("huge4");
        BOJ_10265("huge5");
        BOJ_10265("huge6");
        BOJ_10265("huge7");
        BOJ_10265("huge8");
        BOJ_10265("huge9");
        BOJ_10265("linear1");
        BOJ_10265("linear2");
        BOJ_10265("manyones");
        BOJ_10265("manysmall");
        BOJ_10265("random1");
        BOJ_10265("random2");
        BOJ_10265("random3");
        BOJ_10265("random4");
        BOJ_10265("random5");
    }

    public void BOJ_10265(String inAns) throws IOException {
        System.out.println("BOJ_10265 checking file " +"\'" +inAns +"\'" +" in/ans pair");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String utf8 = StandardCharsets.UTF_8.name();
        PrintStream fout = new PrintStream(baos, true, utf8);

        Main main = new Main();
        main.solve(getInputStream(inAns), fout);

        String expectedOut = file2String(inAns);
        String actualOut = baos.toString(utf8);

        assertEquals(expectedOut, actualOut);

        expectedOut = expectedOut.replaceAll("\\s+","");
        actualOut = actualOut.replaceAll("\\s+","");
        System.out.println("Expected output : " +expectedOut);
        System.out.println("Actual output : " +actualOut);
        System.out.println("Passed : " + "\'" +inAns +"\'" +"in/ans pair");
        System.out.println();
    }

    public FileInputStream getInputStream(String inAns) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new FileInputStream(classLoader.getResource(inAns+".in").getFile());
    }

    public String file2String(String inAns) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        FileInputStream fisAns = new FileInputStream(classLoader.getResource(inAns + ".ans").getFile());

        StringBuilder sb = new StringBuilder();
        Reader r = new InputStreamReader(fisAns, "UTF-8");  //or whatever encoding
        char[] buf = new char[1024];
        int amt = r.read(buf);
        while(amt > 0) {
            sb.append(buf, 0, amt);
            amt = r.read(buf);
        }
        return sb.toString();
    }
}
