package isen.java2.interfaces.bodypart;

/**
 * @author Philippe Duval
 *
 */
public abstract class PrintableBodyPart {

	@Override
	public String toString() {
		return "Part:" + this.getClass().getSimpleName();
	}

}
