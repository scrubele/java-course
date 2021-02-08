package isen.java2.interfaces.bodypart;

/**
 * @author Philippe Duval
 *
 */
public abstract class Limb extends PrintableBodyPart {

	String JoinName;

	/**
	 * @param joinName
	 */
	public Limb(String joinName) {
		JoinName = joinName;
	}

	/**
	 * @return the joinName
	 */
	public String getJoinName() {
		return JoinName;
	}

	/**
	 * @param joinName
	 *            the joinName to set
	 */
	public void setJoinName(String joinName) {
		JoinName = joinName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", Join:" + this.getJoinName();
	}

}
