package ca.jrvs.apps.grep;

import java.io.File;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.apache.commons.io.FileUtils;

public class JavaGrepLambdaTest {
    JavaGrepLambdaImp javaGrep;

    @Before
    public void setUp() throws Exception {
        javaGrep = new JavaGrepLambdaImp();
        javaGrep.setRootPath("data/txt");
    };

    @Test
    public void testJavaGrep() throws Exception {
        javaGrep.setRegex(".*Romeo.*Juliet.*");
        javaGrep.setOutFile("src/test/java/ca/jrvs/apps/grep/test.txt");

        javaGrep.process();
        File outFile = new File("src/test/java/ca/jrvs/apps/grep/Lambdatest.txt");
        File expectedFile = new File("src/test/java/ca/jrvs/apps/grep/expected.txt");
        Assert.assertTrue("The files differ!", FileUtils.contentEquals(expectedFile, outFile));
    }
}
