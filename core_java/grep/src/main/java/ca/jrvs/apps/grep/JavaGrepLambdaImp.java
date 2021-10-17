package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.BasicConfigurator;


public class JavaGrepLambdaImp extends JavaGrepImp {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() throws IOException {
        for (File file : listFiles(rootPath)) {
            List<String> lines = readLines(file);
            writeToFile(lines);
        };
    }

    @Override
    public List<String> readLines(File inputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            return br.lines().filter(
                s -> containsPattern(s)
            ).collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Cannot read from " + inputFile.getAbsolutePath(), e);
            return null;
        }
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(outFile))) {
            Stream<String> lineStream = lines.stream();
            lineStream.forEach(line ->{
                try {
                    br.write(line);
                    br.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            br.close();
        } catch (IOException e) {
            logger.error("Cannot write " + outFile, e);
        }
    }
}
