package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp implements JavaGrepLambda { private

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

        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            javaGrepLambdaImp.process();
        } catch (Exception ex) {
            javaGrepLambdaImp.logger.error("Error: Unable to process", ex);
        }
    }

    @Override
    public void process() throws IOException {
        BasicConfigurator.configure();

        //Stream<String> matchedLines;

        Stream<File> fileStream = listFiles(rootPath);
        Stream<Stream<String>> lineStream = fileStream.map(file -> readLines(file));
        logger.debug("lineStream: " + lineStream.collect(Collectors.toList()).toString());
        //writeToFile(matchedLines);
    }

    @Override
    public Stream<File> listFiles(String rootDir) {
        Stream<File> pathnames;
        File f = new File(rootDir);
        pathnames = Arrays.asList(f.listFiles()).stream();
        return pathnames;
    }

    @Override
    public Stream<String> readLines(File inputFile) {
        logger.debug("input file: " + inputFile);
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            Stream<String> lines = br.lines().filter(s -> containsPattern(s));
            logger.debug("lines: " + lines.collect(Collectors.toList()).toString());
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
    public void writeToFile(Stream<String> lines) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(outFile)))
            );

            lines.forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException ex) {
                    logger.error("Can't write to " + outFile, ex);
                }
            });
            writer.close();

        } catch (FileNotFoundException ex) {
            logger.error("Cannot find " + outFile, ex);
        }
    }
}
