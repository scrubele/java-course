/**
 * 
 */
package isen.java2.nio.sorter;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Philippe Duval
 *
 *         This class implements a file sorter, which takes the content of a
 *         directory, sorts it according to the file extensions, and move all
 *         the exiting content to another archive directory
 */
public class FileSorter {

	// This sample code is there to demonstrate the use of final static keywords
	// to declare constants
	// this type of code is often used to help IDE autocomplete and make the
	// code more readable and less error prone
	private static final String ANIMALS_DIR = "animal";
	private static final String ARCHIVE_DIR = "archive";
	private static final String BY_EXTENSION_DIR = "byExt";

	Path root;
	Path archive;
	Path byExtension;
	Path animals;

	/**
	 * Empty constructor used solely for testing purposes
	 */
	public FileSorter() {
		super();
	}

	/**
	 * Main constructor. We deal with all the directory creations here, not in
	 * the setters. Thus, when the constructor returns, we are sure that the
	 * object is usable
	 * 
	 * @param root
	 *            the root directory containing the files to sort
	 * @throws IOException
	 */
	public FileSorter(String root) throws IOException {
		// This is a simple example of nullsafe processing : check that your
		// public interface is called with legit arguments
		if (root == null) {
			// this exception has many advantages : it is runtime, thus the
			// caller does not have to explicitly deal with it (no useless
			// try/catch to implement), it is meaningful just by it's name (far
			// more than inadvertently throwing NPE), and with a simple message,
			// it can tell exactly what argument is bad and why.
			throw new IllegalArgumentException("You have to provide a root directory to process !");
		}
		this.root = Paths.get(root); // without nullsafe checking, we could
										// throw a NPE just from there. The
										// caller would have to decompile our
										// class, and analyze why our code
										// throws a NPE, which can involve
										// several subsequent classes and become
										// really painful
		this.animals = this.root.resolve(FileSorter.ANIMALS_DIR);
		this.archive = this.root.resolve(FileSorter.ARCHIVE_DIR);
		if (Files.notExists(this.archive)) {
			this.prepareDirectory("", this.archive);
		}
		this.byExtension = this.root.resolve(FileSorter.BY_EXTENSION_DIR);
		if (Files.notExists(this.byExtension)) {
			this.prepareDirectory("", this.byExtension);
		}
	}

	/**
	 * This method prepares a directory : it creates the path to write on the
	 * drive, and then writes it actually
	 * 
	 * @param newDir
	 *            the name of the new directory to create
	 * @param base
	 *            the path to the base root where to create the new directory
	 * @return the path to the newly created directory
	 * @throws IOException
	 */
	public Path prepareDirectory(String newDir, Path base) throws IOException {
		// this part creates a model inside the JVM. At this point, no operation
		// has been done on the filesystem
		Path newPath = base.resolve(newDir);
		if (Files.notExists(newPath, LinkOption.NOFOLLOW_LINKS)) { // Sample of
																	// LinkOption,
																	// here to
																	// specify
																	// to the
																	// method
																	// not to
																	// follow
																	// symlinks
			// here we actually manipulate the underlying filesystem, by
			// creating the expeccted directory. this is where things can go
			// wrong.
			Files.createDirectory(newPath);
		}
		return newPath;
	}

	/**
	 * This method grabs the extension of a filename. several implementation are
	 * possible, including relying on regex matching (tedious) or string
	 * splitting (a little verbose but explicit). The following implementation
	 * rely on the lastIndexOf() method available on String objects, which is
	 * expressive, but dig it's logic into a ternary operator which renders the
	 * code quite complex to understand. This is the perfect example of what NOT
	 * to do. when you write code, keep it understandable. Always remember that
	 * the compiler does a lot of optimization on your code, and that you don't
	 * have to optimize it upfront by code structures. keep optimization at the
	 * used methods level (prefer a method that deals with primitive types than
	 * high level types if the two are available, for example) but don't try to
	 * outsmart the compiler by telling it how to optimize your imperative
	 * programming. It will do it better than you anyways
	 * 
	 * @param entry
	 *            the path to the file to grab the extension from
	 * @return the extension (without the dot)
	 */
	public String getExtension(Path entry) {
		// this is exactly why ternary operators are evil. can you understand
		// the following code ?
		String extension = entry.getFileName().toString();
		return extension.lastIndexOf('.') > 0 ? extension.substring(extension.lastIndexOf('.') + 1, extension.length())
				: null;
	}

	/**
	 * This method is a simple boilerplate code to simplify readability
	 * 
	 * @param entry
	 *            the path to the file to copy
	 * @param directory
	 *            the directory to copy to
	 * @throws IOException
	 */
	public void copyFile(Path entry, Path directory) throws IOException {
		Files.copy(entry, directory.resolve(entry.getFileName()));
	}

	/**
	 * Again, a simple boilerplate method to enhance readability of our code
	 * 
	 * @param entry
	 *            the path to the file to archive
	 * @throws IOException
	 */
	public void moveFileToArchive(Path entry) throws IOException {
		Files.move(entry, archive.resolve(entry.getFileName()), StandardCopyOption.ATOMIC_MOVE);
		// again, standardCopyOption example, here to ensure that the move is
		// done completely or not at all
	}

	/**
	 * This is a simple setter for testing convenience note that we don't deal
	 * here with all the checks on the filesystem. handling of underlying
	 * resources should be done in the constructor, to ensure that if we
	 * construct an object, the object is complete and able to run. dealing
	 * with, for example, filesystem issues in a setter can leave the user with
	 * an half-created, unusable object, and a lot of debug in perspective
	 * 
	 * @param path
	 *            the path to the archive to set
	 */
	public void setArchive(Path path) {
		this.archive = path;
	}

	/**
	 * This is the main processing method. Using convenient helper methods, we
	 * can keep this code simple and readable. The comments are pretty useless
	 * to understand the code, don't you think ?
	 * 
	 * @return the number of files sorted
	 * @throws IOException
	 */
	public Integer sortFiles() throws IOException {
		Integer filesProcessed = 0;
		// read the animals path
		DirectoryStream<Path> filesToSort = Files.newDirectoryStream(this.animals);
		// for each file :
		for (Path file : filesToSort) {
			// get filename, deduce extension
			String extension = this.getExtension(file);
			// create matching folder under the byExtension
			Path storeByExtension = this.prepareDirectory(extension, this.byExtension);
			// copy the entry inside the byExtension
			this.copyFile(file, storeByExtension);
			// move to archive
			this.moveFileToArchive(file);
		}
		return filesProcessed;
	}
}
