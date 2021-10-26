package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {
    final static Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    public String regex;
    public String rootPath;
    public String outFile;

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            logger.error("Error: Unable to process", ex);
        }
    }

    @Override
    public void process() throws IOException {
        ArrayList<String> matchedLines = new ArrayList<String>();

        for (File file : listFiles(rootPath)) {
            // Iterate through each file and find matching lines
            List<String> lines = readLines(file);
            matchedLines.addAll(lines);
        };

        writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> pathnames;
        File f = new File(rootDir);
        pathnames = Arrays.asList(f.listFiles());
        return pathnames;
    }

    @Override
    public List<String> readLines(File inputFile) {
        try {
            List<String> lines = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(inputFile));

            String line;
            while ((line = br.readLine()) != null) {
                if (containsPattern(line)) {
                    lines.add(line);
                }
            }
            br.close();
            return lines;

        } catch (IOException ex) {
            logger.error("Can't read " + inputFile.getAbsolutePath(), ex);
            return null;
        }
    }

    @Override
    public boolean containsPattern(String line) {
        return Pattern.matches(regex, line);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(outFile)))
            );

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (FileNotFoundException ex) {
            logger.error("Cannot find " + outFile, ex);
        }
    }
}
