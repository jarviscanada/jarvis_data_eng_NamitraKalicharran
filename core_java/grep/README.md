# Java Grep App
# Introduction
This Java Grep App is made to replicate the Linux `grep` command, searching files in a directory and matching strings to within the files. 
There are two different implementations, one using BufferedReader one using Java's Stream API.
This project incorporates Maven, Streams, Regex and Lambda Functions and is unit tested using JUnit. Lastly, the application containerized using Docker.

# Quick Start
The following variables are required to run the program:
* `regex_pattern`: the regex pattern to be searched
* `src_dir`: the root directory path
* `outfile`: the output file name

The application can be run in two different ways:

1. Using the Jar file:
```bash
mvn clean package
java -jar target/grep-1.0-SNAPSHOT.jar ${regex_pattern} ${src_dir} ./out/${outfile}
```
2. Using the Docker image:
```bash
docker pull namitrakali/grep
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out namitrakali/grep ${regex_pattern} ${src_dir} /out/${outfile}
```
Once the program is run, view the file created using:
```bash
cat out/$outfile
```

# Implementation

## Pseudocode
```java
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue
The application outputs an OutOfMemoryError exception if the file size is larger than the heap of the JVM. The List data structure can easily get extremely large if a file contains too many lines to process. A solution would be to use Stream APIs, rather than Lists, to process file data. Streams are lazily evaluated so that it minimizes the amount of data being placed on your machine's memory.

# Test
The app was tested by inputting sample data into the arguments of the program. This sample data was input manually, using multiple different regex strings, root directory paths and out filenames. By comparing it with the Linux `grep` command, any issues with correctness could be determined and fixed accordingly.

# Deployment
A Docker image was created and uploaded to the Docker Hub for easy access. This can be viewed on publicly on `https://hub.docker.com/r/namitrakali/grep` or using the command `docker pull namitrakali/grep`.

# Improvement
* Fix the performance issue by implementing more memory-efficient methods.
* Allow user to perform multiple functionalities on the given files. For example, replacing text (like the Linux command `sed`) or searching filenames (like the Linux command `find`).
* Output more specific details of the search, such as the line number and the timestamp of the search.