/**
 * 
 */
package isen.java2.interfaces.factory.impl;

import isen.java2.interfaces.bodypart.Arm;
import isen.java2.interfaces.bodypart.Head;
import isen.java2.interfaces.bodypart.Leg;
import isen.java2.interfaces.bodypart.Trunk;
import isen.java2.interfaces.exception.BodyException;
import isen.java2.interfaces.factory.SpeciesFactory;
import isen.java2.interfaces.species.Species;
import isen.java2.interfaces.species.impl.Human;

/**
 * @author Philippe Duval
 *
 */
public class HumanFactory implements SpeciesFactory {

	/**
	 * @author Philippe Duval
	 * this is a private static inner class. this means that it can only be referenced from within the HumanFactory class,
	 * and that we can reference its members without having to instanciate a HumanFactoryHolder object.
	 * this class will be loaded when we first try to access it, i.e. when we first call HumanFactory.getInstance()
	 */
	private static class HumanFactoryHolder {
		// this member will be valorized on loading, only once for everyone as it is static.
		private static final HumanFactory INSTANCE = new HumanFactory();
	}
	
	/**
	 * private constructor, ensuring that this class is never created from outside
	 */
	private HumanFactory() {
		super();
	}

	/**
	 * @return the unique factory instance
	 */
	public static HumanFactory getInstance() {
		return HumanFactoryHolder.INSTANCE;
	}
	
	/* (non-Javadoc)
	 * @see isen.java2.interfaces.factory.SpeciesFactory#createAnimal()
	 */
	@Override
	public Species createAnimal() throws BodyException {
		Human newBorn = new Human();
		newBorn.setTrunk(new Trunk());
		newBorn.setHead(new Head());
		newBorn.addLimb(new Leg());
		newBorn.addLimb(new Leg());
		newBorn.addLimb(new Arm());
		newBorn.addLimb(new Arm());
		newBorn.setName("John Doe");
		return newBorn;
	}

}
