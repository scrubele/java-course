package isen.java2.pagebuilder.builder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PageBuilderTestCase {

    private PageBuilder pageBuilder;
    private String rootDir = "tmp/buildertest/";

    @Before
    public void initTests() throws Exception {
        // Creating the test directory
        Path rootDirPath = Paths.get(rootDir);
        Files.createDirectories(rootDirPath);
        // Adding test files
        InputStream testFileInputStream = this.getClass().getClassLoader().getResourceAsStream("file.txt");
        Files.copy(testFileInputStream, rootDirPath.resolve("file.txt"));
        InputStream inclusionFileInputStream = this.getClass().getClassLoader().getResourceAsStream("inclusion.txt");
        Files.copy(inclusionFileInputStream, rootDirPath.resolve("inclusion.txt"));

        pageBuilder = new PageBuilder(rootDirPath.resolve("file.txt"), rootDirPath.resolve("output.txt"));
    }

    @After
    public void deleteTestFiles() throws Exception {
        Files.delete(Paths.get(rootDir, "file.txt"));
        Files.delete(Paths.get(rootDir, "inclusion.txt"));
        if (Files.exists(Paths.get(rootDir, "output.txt"))) {
            Files.delete(Paths.get(rootDir, "output.txt"));
        }
    }

    @Test
    public void shouldGetNull() {
        // GIVEN
        String line = "Test line";
        // WHEN
        String fileToInclude = pageBuilder.getFileToInclude(line);
        // THEN
        assertThat(fileToInclude).isNull();
    }

    @Test
    public void shouldGetFileToInclude() {
        // GIVEN
        String line = "[[filetoinclude.txt]]";
        // WHEN
        String fileToInclude = pageBuilder.getFileToInclude(line);
        // THEN
        assertThat(fileToInclude).isNotNull();
        assertThat(fileToInclude).isEqualTo("filetoinclude.txt");
    }

    @Test
    public void shouldWriteContentInWriter() throws Exception {
        // GIVEN
        String fileName = "inclusion.txt";
        Writer writer = Files.newBufferedWriter(Paths.get(rootDir, "output.txt"));
        // WHEN
        pageBuilder.writeFileContent(fileName, writer);
        writer.close();
        // THEN
        List<String> lines = Files.readAllLines(Paths.get(rootDir, "output.txt"));
        System.out.println(lines);
        assertThat(lines).hasSize(1);
        assertThat(lines).containsOnly("Inclusion Line");
    }

    @Test
    public void shouldWriteLineInWriter() throws Exception {
        // GIVEN
        String line = "my test line";
        Writer writer = Files.newBufferedWriter(Paths.get(rootDir, "output.txt"));
        // WHEN
        pageBuilder.processLine(writer, line);
        writer.close();
        // THEN
        List<String> lines = Files.readAllLines(Paths.get(rootDir, "output.txt"));
        assertThat(lines).hasSize(1);
        assertThat(lines).containsOnly("my test line");
    }

    @Test
    public void shouldWriteFileContentInWriter() throws Exception {
        // GIVEN
        String line = "[[inclusion.txt]]";
        Writer writer = Files.newBufferedWriter(Paths.get(rootDir, "output.txt"));
        // WHEN
        pageBuilder.processLine(writer, line);
        writer.close();
        // THEN
        List<String> lines = Files.readAllLines(Paths.get(rootDir, "output.txt"));
        assertThat(lines).hasSize(1);
        assertThat(lines).containsOnly("Inclusion Line");
    }

    @Test
    public void shouldBuildFinalFile() throws Exception {
        //		fail("test is not implemented yet");
        // check if two lines exists
        // compare two lines
        //

    }
}
