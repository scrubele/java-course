/**
 * 
 */
package isen.java2.pagebuilder.builder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Philippe Duval
 *
 *         This class represents our template parser. It will gather several
 *         files and assemble them, following a simple template inclusion
 *         system.
 */
public class PageBuilder {

	private Path startFile;
	private Path root;
	private Path outputFile;

	/**
	 * Constructor used to instantiate the class
	 * 
	 * @param startFile
	 *            the main (root) template file
	 * @param outputFile
	 *            the file that will be generated from the templates
	 */
	public PageBuilder(Path startFile, Path outputFile) {
		if (startFile == null || outputFile == null) {
			// this is an example of null parameter handling. this is verbose,
			// but ensures that you won't end up dealing with NPE in your code
			// this is still not ideal as it does not indicate clearly which
			// parameter is faulty
			throw new IllegalArgumentException("One of the parameters is null, please pass valid parameters !");
		}
		this.root = startFile.getParent();
		this.startFile = startFile;
		this.outputFile = outputFile;
	}

	/**
	 * Main method used to generate the output file from templates
	 */
	public void build() throws IOException {
		// we instantiate a buffered writer to allow to write files once at a
		// time into our target file. This deviates from the PW text, but is a
		// convenient enhancement to allow us to call platform independent code
		// later on.
		// we specify a charset that suits our needs. never forget to specify
		// the charset !
		try (BufferedWriter bw = Files.newBufferedWriter(this.outputFile, StandardCharsets.UTF_8);) {
			this.writeFileContent(this.startFile.toString(), bw);
		}
	}

	/**
	 * Method writing the content of the file parameter into a buffered writer.
	 * this method is convenient in that it allows to copy raw lines as-is, and
	 * process templates markup to include other files as well
	 * 
	 * @param filename
	 *            the file to copy into the final file
	 * @param writer
	 *            the writer that points to our final file
	 * @throws IOException
	 */
	public void writeFileContent(String filename, BufferedWriter writer) throws IOException {
		System.out.println("We will write the file " + filename);
		// create a bufferedReader from this, to allow us to use the readLine()
		// method !
		try (BufferedReader br = Files.newBufferedReader(root.resolve(filename), StandardCharsets.UTF_8);) {
			String line;
			while ((line = br.readLine()) != null) {
				// write the content of the buffered reader
				this.processLine(line, writer);
			}
		}
	}

	/**
	 * @param line
	 * @return
	 */
	public String getFileToInclude(String line) {
		line = line.trim(); // this is a null-UNsafe call (more to follow...) In
							// real life, either mark this method as private
							// (and have confidence in yourself for passing
							// non-null arguments !) or check for arguments
							// coherence
		if (line.startsWith("[[") && line.endsWith("]]")) {
			return line.substring(2, line.length() - 2); // here we don't pay
															// special attention
															// to the bounds of
															// the surbstring,
															// as we just
															// checked that the
															// String is at
															// least
															// 4 characters
															// long. In general,
															// beware !
		} else {
			return null;
		}
	}

	/**
	 * This method take a line, and check whether it's a new template or a
	 * regular line. If we have to deal with a new template, we delegate the
	 * process to the template parsing method, else we append the content to the
	 * target file
	 * 
	 * @param Line
	 *            the line to process
	 * @param writer
	 *            the writer pointing to the target file
	 * @throws IOException
	 *             if the writer encounters a problem
	 */
	public void processLine(String line, BufferedWriter writer) throws IOException {
		String tmpline = this.getFileToInclude(line);
		if (tmpline == null) {
			// First we write the line in the writer
			writer.write(line); // again not null-safe, but who can call us with
								// bad parameters ?
			// then we append the end of line characters. this is platform
			// independent, and only available in buffered writer ! this is the
			// limit of abstract classes
			writer.newLine();
			// here we ask java to write what we have in our buffer right on the
			// disk
			writer.flush();
		} else {
			// INCEPTION ! if the line is a template, we call the parent method
			// again. this is recursion, as simple as it gets !
			this.writeFileContent(root.resolve(tmpline).toString(), writer);
		}
	}
}
