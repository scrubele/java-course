/**
 * 
 */
package isen.java2.nio.app;

import java.io.IOException;
import java.nio.file.Paths;

import isen.java2.nio.sorter.FileSorter;

/**
 * @author Philippe Duval
 *
 */
public class Application {

	/**
	 * Example of runtime exception used to signal that something should not happen 
	 */
	private Application() {
		throw new IllegalStateException("this class should not be instanciated");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// New directory
		FileSorter sorter = new FileSorter();
		try {
			sorter.prepareDirectory("testDirectory", Paths.get("c:/isen/java2/nio"));
		} catch (IOException e) {
			// We should not be there
			e.printStackTrace();
		}

		// Nothing happens
		try {
			sorter.prepareDirectory("testDirectory", Paths.get("c:/isen/java2/nio"));
		} catch (IOException e) {
			// Here neither
			e.printStackTrace();
		}

		System.out.println(sorter.getExtension(Paths.get("c:/isen/java2/nio/test.png")));
		System.out.println(sorter.getExtension(Paths.get("c:/isen/java2/nio/test")));

		try {
			sorter.copyFile(Paths.get("c:/isen/java2/nio/animals/adelie-penguin.html"), Paths.get("c:/isen/java2/nio"));
		} catch (IOException e) {
			// you could also face : NoSuchFileException if the source does not
			// exist or is not a file, or if the target does not exist
			// if you try to pass a file as a second argument, it will be
			// treated as a directory, and thus you will encounter a
			// NoSuchFileException, as the directory does not exist !
			e.printStackTrace();
		}
		
		FileSorter archiveSorter = new FileSorter();
		archiveSorter.setArchive(Paths.get("c:/isen/java2/nio/archive"));
		try {
			System.out.println("let's archive !");
			archiveSorter.moveFileToArchive(Paths.get("c:/isen/java2/nio/adelie-penguin.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileSorter definitiveSorter = new FileSorter("c:/isen/java2/nio/");
			definitiveSorter.sortFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
