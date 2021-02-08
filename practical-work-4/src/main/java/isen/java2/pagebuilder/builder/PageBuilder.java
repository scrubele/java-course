package isen.java2.pagebuilder.builder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class PageBuilder {

    private Path root;

    private Path outputFile;

    private Path startFile;

    public PageBuilder(Path startFile, Path outputFile) {
        root = startFile.getParent();
        this.outputFile = outputFile;
    }

    public void build() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            writeFileContent(startFile.getFileName().toString(), writer);
            writer.flush();
        }

    }

    public void writeFileContent(String filename, Writer writer) throws IOException {
        System.err.println("reading " + filename + "...");
        try (BufferedReader fileReader = Files.newBufferedReader(root.resolve(filename))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                processLine(writer, line);
            }
        }
    }

    protected void processLine(Writer writer, String line) throws IOException {
        String inclusion = getFileToInclude(line);
        if (inclusion != null) {
            writeFileContent(inclusion, writer);
        } else {
            writer.write(line);
        }
    }

    protected String getFileToInclude(String line) {
        line = line.trim();
        if (line.startsWith("[[") || line.endsWith("]]")) {
            return line.substring(2, line.length() - 2);
        } else {
            return null;
        }
    }

}
