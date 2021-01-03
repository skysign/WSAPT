import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    @Test
    public void TestSuite() throws IOException {
        System.out.println();
        TestCase("TestCase_1");
        TestCase("TestCase_3");
    }

    public void TestCase(String inAns) throws IOException {
        System.out.println("Checking TestCase " +"\'" +inAns +"\'" +" in/ans pair");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String utf8 = StandardCharsets.UTF_8.name();
        PrintStream fout = new PrintStream(baos, true, utf8);

        Main main = new Main();
        main.solve(getInputStream(inAns), fout);

        String expectedOutput = file2String(inAns);
        String actualOutput = baos.toString(utf8);

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
