import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    @Test
    public void TestSuite() throws IOException {
        System.out.println();
        TestCase("antigreedy1");
        TestCase("antigreedy2");
        TestCase("antigreedy3");
        TestCase("bigcircle1");
        TestCase("bigcircle2");
        TestCase("exponential");
        TestCase("huge1");
        TestCase("huge2");
        TestCase("huge3");
        TestCase("huge4");
        TestCase("huge5");
        TestCase("huge6");
        TestCase("huge7");
        TestCase("huge8");
        TestCase("huge9");
        TestCase("linear1");
        TestCase("linear2");
        TestCase("manyones");
        TestCase("manysmall");
        TestCase("random1");
        TestCase("random2");
        TestCase("random3");
        TestCase("random4");
        TestCase("random5");
    }

    public void TestCase(String inAns) throws IOException {
        System.out.println("Checking TestCase " +"\'" +inAns +"\'" +" in/ans pair");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Main main = new Main();
        main.solve(getInputStream(inAns), getPrintStream(inAns, baos));

        String expectedOutput = file2String(inAns);
        String actualOutput = baos.toString(StandardCharsets.UTF_8.name());

        assertEquals(expectedOutput, actualOutput);

        expectedOutput = expectedOutput.replaceAll("\\s+","");
        actualOutput = actualOutput.replaceAll("\\s+","");
        System.out.println("Expected output : " +expectedOutput);
        System.out.println("Actual output : " +actualOutput);
        System.out.println("Passed : " + "\'" +inAns +"\'" +"in/ans pair");
        System.out.println();
    }

    public FileInputStream getInputStream(String inAns) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new FileInputStream(classLoader.getResource(inAns+".in").getFile());
    }

    public PrintStream getPrintStream(String inAns, ByteArrayOutputStream baos) throws UnsupportedEncodingException {
        return new PrintStream(baos, true, StandardCharsets.UTF_8.name());
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
