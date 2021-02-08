/**
 * 
 */
package isen.java2.pagebuilder.app;

import java.io.IOException;
import java.nio.file.Paths;

import isen.java2.pagebuilder.builder.PageBuilder;

/**
 * @author Philippe Duval
 *
 */
public class Application {

	public static void main(String[] args) {
		
		// We create a new PageBuilder Object, using our parameterized constructor, to set the directories
		PageBuilder pb = new PageBuilder(Paths.get("c:/isen/java2/nio/pagebuilder/index.html"),
				Paths.get("c:/isen/java2/nio/pagebuilder/output.html"));
		try {
			// All the magic of our class happens here. As you can see, all our methods, except this one and getFileToInclude, could be (and should be) private
			pb.build();
		} catch (IOException e) {
			System.out.println("too bad ! this should not happen !");
			e.printStackTrace();
		}

		// this is boilerplate testing code to check if our method does what we expect when parsing the template
		PageBuilder builder = new PageBuilder(Paths.get("foo"), Paths.get("bar"));
		System.out.println(builder.getFileToInclude("test line")); // prints
																	// null
		System.out.println(builder.getFileToInclude("  [[path/to/file]]  ")); // prints
																				// path/to/file

	}

}
