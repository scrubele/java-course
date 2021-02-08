/**
 * 
 */
package isen.java2.interfaces.species;

/**
 * @author Philippe Duval
 * This is an interface. it cannot be instanciated and methods in it are allowed not to have an implementation,
 * without requiring an "abstract" keyword.
 * It still differs from an abstract class in the fact that an implementation can implement several interfaces, but 
 * a given class can extend only one ancestor.
 */
public interface Talkable {
	public void talk(String wannasay);
}
