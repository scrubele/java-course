package isen.java2.interfaces.bodypart;

/**
 * @author Philippe Duval
 *
 */
public class ArmChild extends Arm {

	public ArmChild() {
		// Here we make use of the constructor of the parent class, which takes a parameter.
		// Notice that we don't have to give our current constructor a parameter, as we know for shure that
		// an arm's join is always an elbow.
		// This means that you don't have to mimic the parent's constructor when implementing the child's constructor.
		super();
	}

}