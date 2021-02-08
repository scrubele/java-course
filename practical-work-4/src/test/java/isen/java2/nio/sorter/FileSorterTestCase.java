package isen.java2.nio.sorter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

//import main.java.isen.java2.nio.sorter.FileSorter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileSorterTestCase {

	private FileSorter fileSorter;
	private final String rootDir = "tmp/niotest/";

	@Before
	public void initTests() throws Exception {
		// Creating the test directory
		Path rootDirPath = Paths.get(rootDir);
		Files.createDirectories(rootDirPath);
		this.initAnimalsDirectory(rootDirPath);

		fileSorter = new FileSorter(rootDir);
	}

	@After
	public void purgeTestDirectory() throws Exception {
		this.purgeDirectory(Paths.get(rootDir));
	}

	@Test
	public void shouldGetExtension() {
		// GIVEN
		Path fileName = Paths.get("test.png");
		// WHEN
		String extension = fileSorter.getExtension(fileName);
		// THEN
		assertThat(extension).isEqualTo(".png");
	}

	@Test
	public void shouldPrepareDirectoryAndReturnExistingPath() throws Exception {
		// GIVEN
		Path mainDir = Paths.get(rootDir);
		String newDirName = "animals";
		// WHEN
		Path newDirPath = fileSorter.prepareDirectory(newDirName, mainDir);
		// THEN
		assertThat(Files.exists(newDirPath)).isTrue();
		assertThat(Files.isDirectory(newDirPath)).isTrue();
		assertThat(newDirPath.getFileName().toString()).isEqualTo("animals");
	}

	@Test
	public void shouldPrepareDirectoryAndReturnNewPath() throws Exception {
		// GIVEN
		Path mainDir = Paths.get(rootDir);
		String newDirName = "testDir";
		// WHEN
		Path newDirPath = fileSorter.prepareDirectory(newDirName, mainDir);
		// THEN
		assertThat(Files.exists(newDirPath)).isTrue();
		assertThat(Files.isDirectory(newDirPath)).isTrue();
		assertThat(newDirPath.getFileName().toString()).isEqualTo("testDir");
	}

	@Test
	public void shouldCopyFile() throws Exception {
		// GIVEN
		Path targetDir = Paths.get(rootDir);
		Path entry = Paths.get(rootDir, "animals/test.png");
		// WHEN
		fileSorter.copyFile(entry, targetDir);
		// THEN
		assertThat(Files.exists(Paths.get(rootDir, "test.png"))).isTrue();
		assertThat(Files.exists(entry)).isTrue();
	}

	@Test
	public void shouldMoveFileToArchive() throws Exception {
		// GIVEN
		Path entry = Paths.get(rootDir, "animals/test.png");
		// WHEN
		fileSorter.moveFileToArchive(entry);
		// THEN
		assertThat(Files.exists(Paths.get(rootDir, "archive/test.png"))).isTrue();
		assertThat(Files.notExists(entry)).isTrue();
	}

	@Test
	public void shouldSortFiles() throws Exception {
		int count = fileSorter.sortFiles();
		System.out.println(count);

		// stream data
		assertThat(Files.exists(Paths.get(rootDir, "byext/.png"))).isTrue();
		assertThat(Files.list(Paths.get(rootDir, "byext/.png")).count()).isEqualTo(2);
		assertThat(Files.exists(Paths.get(rootDir, "byext/.gif"))).isTrue();
		assertThat(Files.list(Paths.get(rootDir, "byext/.gif")).count()).isEqualTo(2);
		assertThat(Files.exists(Paths.get(rootDir, "byext/.html"))).isTrue();
		assertThat(Files.list(Paths.get(rootDir, "byext/.html")).count()).isEqualTo(1);

		assertThat(Files.exists(Paths.get(rootDir, "archive/test.png"))).isTrue();
		assertThat(Files.exists(Paths.get(rootDir, "archive/test2.png"))).isTrue();
		assertThat(Files.exists(Paths.get(rootDir, "archive/test.gif"))).isTrue();
		assertThat(Files.exists(Paths.get(rootDir, "archive/test2.gif"))).isTrue();
		assertThat(Files.exists(Paths.get(rootDir, "archive/test.html"))).isTrue();


	}

	private void initAnimalsDirectory(Path rootDir) throws Exception {
		// Create animals directory if does not exist
		Path animalsPath = rootDir.resolve("animals");
		Files.createDirectory(animalsPath);

		// Create some files in animals directory
		Files.createFile(animalsPath.resolve("test.png"));
		Files.createFile(animalsPath.resolve("test2.png"));
		Files.createFile(animalsPath.resolve("test.gif"));
		Files.createFile(animalsPath.resolve("test2.gif"));
		Files.createFile(animalsPath.resolve("test.html"));
	}

	private void purgeDirectory(Path dir) throws Exception {
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}

		});
	}
}
