/**
 * 
 */
package isen.java2.interfaces.species.impl;

import isen.java2.interfaces.bodypart.Head;
import isen.java2.interfaces.bodypart.Leg;
import isen.java2.interfaces.bodypart.Limb;
import isen.java2.interfaces.bodypart.Trunk;
import isen.java2.interfaces.exception.BodyException;
import isen.java2.interfaces.exception.TooManyLegsException;
import isen.java2.interfaces.species.Family;
import isen.java2.interfaces.species.Species;

/**
 * @author Philippe Duval This is a simple example of inheritance
 */
public class Diplodocus extends Species {

	String name;
	Leg leftFrontLeg;
	Leg rightFrontLeg;
	Leg leftBackLeg;
	Leg rightBackLeg;
	Head head;
	Trunk trunk;

	/**
	 * @param name
	 *            the name to be given to the diplodocus
	 * @return the diplodocus object, so that we can chain this call at creation
	 */
	public Diplodocus setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param head
	 *            the head to be given to the diplodocus
	 */
	public void setHead(Head head) {
		this.head = head;
	}

	/**
	 * @param trunk
	 *            the trunk used to construct the diplodocus
	 */
	public void setTrunk(Trunk trunk) {
		this.trunk = trunk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isen.java2.interfaces.species.Species#getFamily()
	 */
	@Override
	public Family getFamily() {
		return Family.MAMMAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isen.java2.interfaces.species.Species#isExtinct()
	 */
	@Override
	public Boolean isExtinct() {
		return Boolean.TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isen.java2.interfaces.species.Species#addLimb(isen.java2.interfaces.
	 * bodypart.Limb)
	 */
	@Override
	public void addLimb(Limb aLimb) throws BodyException {
		// here we use some tricks to determine if we are indeed trying to add a
		// leg to our dino,
		// and not some sort of crasy limb.
		if (aLimb instanceof Leg) { // the keyword instaceof allows to check if
									// an object is an instance of a class. it
									// returns a boolean
			// here we use another trick, called type casting : we cast our limb
			// in a different type, which is required by the function that we
			// use.
			// the syntax goes as this : newvar = (Destination Type) oldvar
			// if the cast is safe (meaning the destination type is a supertype
			// of the source, e.g. an ancestor) everything is fine
			// if, to the contrary, the destination type is not a supertype of
			// the source, the cast can raise a ClassCastException
			// Here, despite Leg not being a supertype of Limb, everything is
			// safe because of the previous test in the if clause, so we can
			// proceed
			this.addLeg((Leg) aLimb);
		} else {
			throw new BodyException();
		}
	}

	/**
	 * adds a leg to the diplodocus
	 * 
	 * @param aLeg
	 *            the leg to be added
	 * @throws TooManyLegsException
	 *             when trying to add more than four legs
	 */
	private void addLeg(Leg aLeg) throws TooManyLegsException {
		if (null == leftFrontLeg) {
			leftFrontLeg = aLeg;
		} else if (null == rightFrontLeg) {
			rightFrontLeg = aLeg;
		} else if (null == leftBackLeg) {
			leftBackLeg = aLeg;
		} else if (null == rightBackLeg) {
			rightBackLeg = aLeg;
		} else {
			throw new TooManyLegsException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Diplodocus [name=" + name + ", leftFrontLeg=" + leftFrontLeg + ", rightFrontLeg=" + rightFrontLeg
				+ ", leftBackLeg=" + leftBackLeg + ", rightBackLeg=" + rightBackLeg + ", head=" + head + ", trunk="
				+ trunk + "]";
	}

}
