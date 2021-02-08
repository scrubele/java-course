/**
 * 
 */
package isen.java2.interfaces.species;

import isen.java2.interfaces.bodypart.Limb;
import isen.java2.interfaces.exception.BodyException;

/**
 * @author Philippe Duval
 * This is our first abstract class. It cannot be instanciated. 
 * Notice that the methods are abstract too, and thus do not have an implementation
 */
public abstract class Species {

	public abstract Family getFamily();
	
	public abstract Boolean isExtinct();
	
	public abstract void addLimb(Limb aLimb) throws BodyException;
	
}
