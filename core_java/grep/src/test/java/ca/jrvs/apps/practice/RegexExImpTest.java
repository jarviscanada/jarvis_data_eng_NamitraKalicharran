package ca.jrvs.apps.practice;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class RegexExImpTest {
    RegexExcImp regex;

    @Before
    public void setUp() throws Exception {
        regex = new RegexExcImp();
    };

    // Test detection of .jpg file
    @Test
    public void testFilename1() {
        Assert.assertEquals(true, regex.matchJpeg("picture.jpg"));
    }

     // Test detection of .jpeg file
    @Test
    public void testFilename2() {
        Assert.assertEquals(true, regex.matchJpeg("file.jpeg"));
    }

     // Test rejection of non .jpg/.jpeg file
    @Test
    public void testFilename3() {
        Assert.assertEquals(false, regex.matchJpeg("file2.txt"));
    }

    // Test rejection of plain text without extension
    @Test
    public void testFilename4() {
        Assert.assertEquals(false, regex.matchJpeg("file"));
    }

    // Test open ip
    @Test
    public void testIp1() {
        Assert.assertEquals(true, regex.matchIp("0.0.0.0"));
    }

    // Test random valid ip
    @Test
    public void testIp2() {
        Assert.assertEquals(true, regex.matchIp("192.168.123.456"));
    }

    // Test open ports
    @Test
    public void testIp3() {
        Assert.assertEquals(true, regex.matchIp("192.168.0.0"));
    }

    @Test
    public void testEmptyLine1() {
        Assert.assertEquals(false, regex.isEmptyLine("blah\n"));
    }

    @Test
    public void testEmptyLine2() {
        Assert.assertEquals(true, regex.isEmptyLine(" "));
    }

    @Test
    public void testEmptyLine3() {
        Assert.assertEquals(true, regex.isEmptyLine("\t"));
    }

    @Test
    public void testEmptyLine4() {
        Assert.assertEquals(true, regex.isEmptyLine("\n"));
    }
}
