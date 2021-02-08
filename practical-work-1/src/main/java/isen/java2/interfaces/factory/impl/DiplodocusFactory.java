/**
 * 
 */
package isen.java2.interfaces.factory.impl;

import isen.java2.interfaces.bodypart.Head;
import isen.java2.interfaces.bodypart.Leg;
import isen.java2.interfaces.bodypart.Trunk;
import isen.java2.interfaces.exception.BodyException;
import isen.java2.interfaces.factory.SpeciesFactory;
import isen.java2.interfaces.species.Species;
import isen.java2.interfaces.species.impl.Diplodocus;

/**
 * @author Philippe Duval
 *
 */
public class DiplodocusFactory implements SpeciesFactory {

	/**
	 * @author Philippe Duval
	 *
	 */
	private static class DiplodocusFactoryHolder {
		// this member will be valorized on loading, only once for everyone as
		// it is static.
		private static final DiplodocusFactory INSTANCE = new DiplodocusFactory();
	}

	/**
	 * 
	 */
	private DiplodocusFactory() {
		super();

	}

	/**
	 * @return the instance of the factory
	 */
	public static DiplodocusFactory getInstance() {
		return DiplodocusFactoryHolder.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isen.java2.interfaces.factory.SpeciesFactory#createAnimal()
	 */
	@Override
	public Species createAnimal() throws BodyException {
		Diplodocus newBorn = new Diplodocus();
		newBorn.setTrunk(new Trunk());
		newBorn.setHead(new Head());
		newBorn.addLimb(new Leg());
		newBorn.addLimb(new Leg());
		newBorn.addLimb(new Leg());
		newBorn.addLimb(new Leg());
		// " A diplodocus has no name..."
		return newBorn;
	}

}
