/**
 * 
 */
package isen.java2.interfaces.app;

import isen.java2.interfaces.species.Talkable;

/**
 * @author Philippe Duval
 * Hint: you can place this class in any package, as long as it is semantically coherent. For instance,
 * it's not a good idea to put Robot in the species.impl package, as it does not extends species, 
 * BUT as it implements Talkable, it should belong there, doesn't it ? Maybe it's time for a little package refactoring...
 */
public class Robot implements Talkable {

	String name;

	/**
	 * @param name
	 *            the name to be given to the robot
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isen.java2.interfaces.species.Talkable#talk(java.lang.String)
	 */
	@Override
	public void talk(String wannasay) {
		String robotsays = wannasay.toUpperCase().replaceAll("[.,!? \\-]", "-");

		System.out.println(this.name + " says: " + robotsays);
	}

}
