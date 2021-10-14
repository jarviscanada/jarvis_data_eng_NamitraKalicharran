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
    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

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
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }
    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<String>();

        for (File file : listFiles(rootPath)) {
            // Iterate through each file and find matching lines
            matchedLines.addAll(readLines(file));
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
        FileReader fileReader;
        try {
            fileReader = new FileReader(inputFile);
        } catch (FileNotFoundException ex) {
            logger.error("Cannot read from " + inputFile, ex);
            return null;
        }
        
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> lines = new ArrayList<String>();

        // Try and read first line from input file
        // resistant to file deletion mid call
        String line;
        try {
            line = bufferedReader.readLine();
        } catch (IOException ex) {
            logger.error("Error reading line from input file", ex);

            try {
                bufferedReader.close();
            } catch (IOException ex2) {
                logger.error("Cannot find from " + inputFile, ex2);
            }
            return null;
        }

        // iterate through each line in input file
        while (line != null) {
            // Check if line contains matching regex
            // add to lines list
            if (containsPattern(line)) {
                lines.add(line);
            }
            
            // Try and read next line
            // resistant to file deletion mid call
            try {
                line = bufferedReader.readLine();
            } catch (IOException ex) {
                logger.error("Error reading line from input file", ex);

                try {
                    bufferedReader.close();
                } catch (IOException ex2) {
                    logger.error("Cannot find from " + inputFile, ex2);
                }
                return lines;
            }
        }

        // close BufferedReader
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            logger.error("Cannot find from " + inputFile, ex);
            return lines;
        }
        return lines;
    }

    @Override
    public boolean containsPattern(String line) {
        return Pattern.matches(regex, line);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outFile))
            );
        } catch (FileNotFoundException ex) {
            logger.error("Cannot find " + outFile, ex);
            return;
        }

        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }
}
