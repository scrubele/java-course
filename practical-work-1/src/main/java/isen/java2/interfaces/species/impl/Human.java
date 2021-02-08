/**
 * 
 */
package isen.java2.interfaces.species.impl;

import isen.java2.interfaces.bodypart.Arm;
import isen.java2.interfaces.bodypart.Head;
import isen.java2.interfaces.bodypart.Leg;
import isen.java2.interfaces.bodypart.Limb;
import isen.java2.interfaces.bodypart.Trunk;
import isen.java2.interfaces.exception.BodyException;
import isen.java2.interfaces.exception.TooManyArmsException;
import isen.java2.interfaces.exception.TooManyLegsException;
import isen.java2.interfaces.species.Family;
import isen.java2.interfaces.species.Species;
import isen.java2.interfaces.species.Talkable;

/**
 * @author Philippe Duval
 * This class is an example of both inheritance and composition : a human is a member of species, and has the ability to talk
 */
public class Human extends Species implements Talkable {

	String name;
	Leg leftLeg;
	Leg rightLeg;
	Arm leftArm;
	Arm rightArm;
	Head head;
	Trunk trunk;

	/**
	 * @param aName
	 *            the name to be given to the human
	 */
	public void setName(String aName) {
		this.name = aName;
	}

	/**
	 * @param head
	 *            the head to be given to the human
	 */
	public void setHead(Head head) {
		this.head = head;
	}

	/**
	 * @param trunk
	 *            the trunk used to construct the human
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
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see isen.java2.interfaces.species.Species#addLimb(isen.java2.interfaces.
	 * bodypart.Limb)
	 */
	@Override
	public void addLimb(Limb aLimb) throws BodyException {
		// this implementation is verbose. It is kept simple because we only accept legs as limbs, but it could turn 
		// kite complicated if we had to deal with fantasmagoric species. There is a design pattern called Visitor
		// that could help reduce the verbosity if we had to deal with too much cases.
		if (aLimb instanceof Arm) {
			this.addArm((Arm) aLimb);
		} else if (aLimb instanceof Leg) {
			this.addLeg((Leg) aLimb);
		} else {
			throw new BodyException();
		}
	}

	/**
	 * adds an arm to the human
	 * 
	 * @param anArm
	 *            the arm to be added
	 * @throws TooManyArmsException
	 *             when trying to add more than two arms
	 */
	private void addArm(Arm anArm) throws TooManyArmsException {
		if (null == leftArm) {
			leftArm = anArm;
		} else if (null == rightArm) {
			rightArm = anArm;
		} else {
			throw new TooManyArmsException();
		}
	}

	/**
	 * adds a leg to the human
	 * 
	 * @param aLeg
	 *            the leg to be added
	 * @throws TooManyLegsException
	 *             when trying to add more than two legs
	 */
	public void addLeg(Leg aLeg) throws TooManyLegsException {
		if (null == leftLeg) {
			leftLeg = aLeg;
		} else if (null == rightLeg) {
			rightLeg = aLeg;
		} else {
			throw new TooManyLegsException();
		}
	}

	@Override
	public void talk(String wannasay) {
		System.out.println(this.name + " says: " + wannasay);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Human [name=" + name + ", leftLeg=" + leftLeg + ", rightLeg=" + rightLeg + ", leftArm=" + leftArm
				+ ", rightArm=" + rightArm + ", head=" + head + ", trunk=" + trunk + "]";
	}
}
