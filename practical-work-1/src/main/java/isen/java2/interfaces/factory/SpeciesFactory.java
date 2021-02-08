/**
 * 
 */
package isen.java2.interfaces.factory;

import isen.java2.interfaces.exception.BodyException;
import isen.java2.interfaces.species.Species;

/**
 * @author Philippe Duval
 *
 */
public interface SpeciesFactory {
	/**
	 * This factory handles the hassle of creating a complete and accurate member of a given specie
	 * All the internal plumbing of the creation is taken care of by the factory, ensuring that no one has to remember 
	 * how many scales do a triceratops have before encountering a TooManyScalesException
	 * @return
	 * @throws BodyException
	 */
	public Species createAnimal() throws BodyException;
}
