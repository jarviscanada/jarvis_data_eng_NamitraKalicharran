package ca.jrvs.apps.grep;
import org.junit.Test;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.apache.commons.io.FileUtils;

public class JavaGrepTest {
    JavaGrepImp javaGrep;

    @Before
    public void setUp() throws Exception {
        javaGrep = new JavaGrepImp();
        javaGrep.setRootPath("data/txt");
    };

    @Test
    public void testJavaGrep() throws Exception {
        javaGrep.setRegex(".*Romeo.*Juliet.*");
        javaGrep.setOutFile("src/test/java/ca/jrvs/apps/grep/test.txt");

        javaGrep.process();
        File outFile = new File("src/test/java/ca/jrvs/apps/grep/test.txt");
        File expectedFile = new File("src/test/java/ca/jrvs/apps/grep/expected.txt");
        Assert.assertTrue("The files differ!", FileUtils.contentEquals(expectedFile, outFile));
    }

    @Test
    public void testJavaGrep2() throws Exception {
        javaGrep.setRegex(".*Hamlet.*");
        javaGrep.setOutFile("src/test/java/ca/jrvs/apps/grep/test2.txt");

        javaGrep.process();
        File outFile = new File("src/test/java/ca/jrvs/apps/grep/test2.txt");
        File expectedFile = new File("src/test/java/ca/jrvs/apps/grep/expected2.txt");
        Assert.assertTrue("The files differ!", FileUtils.contentEquals(expectedFile, outFile));
    }
}
