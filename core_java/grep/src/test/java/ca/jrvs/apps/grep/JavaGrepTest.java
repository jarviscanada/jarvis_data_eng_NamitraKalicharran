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
        javaGrep.setRegex(".*Romeo.*Juliet.*");
        javaGrep.setRootPath("data/txt");
        javaGrep.setOutFile("src/test/java/ca/jrvs/apps/grep/test.txt");
    };

    @Test
    public void testJavaGrep() throws Exception {
        javaGrep.process();
        File outFile = new File("test.txt");
        File expectedFile = new File("expected.txt");
        Assert.assertTrue("The files differ!", FileUtils.contentEquals(expectedFile, outFile));
    }
}
