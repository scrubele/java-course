package isen.java2.interfaces.app;

import isen.java2.interfaces.bodypart.Arm;
import isen.java2.interfaces.bodypart.Head;
import isen.java2.interfaces.bodypart.Leg;
import isen.java2.interfaces.bodypart.PrintableBodyPart;
import isen.java2.interfaces.exception.BodyException;
import isen.java2.interfaces.factory.impl.DiplodocusFactory;
import isen.java2.interfaces.factory.impl.HumanFactory;
import isen.java2.interfaces.species.Species;
import isen.java2.interfaces.species.Talkable;
import isen.java2.interfaces.species.impl.Diplodocus;
import isen.java2.interfaces.species.impl.Human;

/**
 * @author a501341 This Class is the program's entrypoint. in the main method,
 *         we will script the interactions with our classes to check that they
 *         do what we want.
 */
public class Application {

	public static void main(String[] args) {
		// let's instanciate an object based on our Head class definition. this
		// object can be of type PrintableBodyPart,
		// because Head extends PrintableBodyPart, thus an instance of Head has
		// at least all the attributes and functions
		// needed to be a instance of PrintableBodyPart
		PrintableBodyPart myHead = new Head();
		System.out.println(myHead);
		// The same goes for our instance of Leg
		PrintableBodyPart myLeg = new Leg();
		System.out.println(myLeg);
		// And for our instance of Human, which is a child of Species.
		Species myHuman = new Human();
		System.out.println(myHuman);

		// Now let's construct our human
		try {
			myHuman.addLimb(new Arm());
			System.out.println(myHuman);

			myHuman.addLimb(new Arm());
			System.out.println(myHuman);

			myHuman.addLimb(new Leg());
			System.out.println(myHuman);

			myHuman.addLimb(new Leg());
			System.out.println(myHuman);
			// So far so good, but let's try a little Frankensteinyness here...
			myHuman.addLimb(new Arm());

		} catch (BodyException e) {
			System.out.println("ooops... a Human can't have three arms !");
			e.printStackTrace();
		}

		// Let's try a new (kind of...) Species child.
		Species myDino = new Diplodocus();
		System.out.println(myDino);
		try {
			myDino.addLimb(new Leg());
			System.out.println(myDino);

			myDino.addLimb(new Leg());
			System.out.println(myDino);

			myDino.addLimb(new Arm());
			System.out.println(myDino);
		} catch (BodyException e) {
			System.out.println("Oups, silly, a Diplodocus doesn't have arms !");
			e.printStackTrace();
		}

		// Now it's time for a little drama
		Human luke = new Human();
		luke.setName("luke");

		Robot vader = new Robot();
		vader.setName("vader");

		// Here we call a static method belonging to the class Application. Note
		// that we don't have to instanciate
		// an object of the Application class to be able to access this method.
		// Convenient, isn't it ?
		// this is really helpful when the method that you want to write doesn't
		// have to carry state information,
		// and is a pure function. It helps reducing memory usage.
		playStarWarsScene(luke, vader);

		// finally, let's create a new human using our newly created factory :
		HumanFactory hf = HumanFactory.getInstance();
		try {
			Human completeHuman = (Human) hf.createAnimal();
			// Notice how it is far more convenient to have our human created
			// automatically by the factory
			// rather than to have to instanciate the multiple part ourselves
			// and to deal with the complexity of how
			// a human is created
			System.out.println(completeHuman);
		} catch (BodyException e) {
			// this is still not perfect. we would prefer not to have to deal
			// with bodyExceptions, as the factory can
			// surely fix these better than us...
			e.printStackTrace();
		}

		DiplodocusFactory df = DiplodocusFactory.getInstance();
		try {
			// Here is a trick that allows us to chain calls to rename our Diplodocus at instanciation time
			// it relies on the function Diplodocus.setName returning the object rather than void, 
			// and the cast below being encapsulated using parentheses, thus allowing us
			// to call the method belonging to the Diplodocus class. 
			Diplodocus eddy = ((Diplodocus) df.createAnimal()).setName("Eddy");
			System.out.println(eddy);
		} catch (BodyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * plays a little piece of historical dialogue between two famous characters
	 * 
	 * @param luke
	 *            the Talkable playing the role of luke
	 * @param vader
	 *            the Talkable playing the role of the bad guy (what's his name
	 *            already ?)
	 */
	private static void playStarWarsScene(Talkable luke, Talkable vader) {
		vader.talk(
				"There is no escape. Don't make me destroy you. You do not yet realize your importance. You have only begun to discover your power. Join me and I will complete your training. With our combined strength, we can end this destructive conflict and bring order to the galaxy.");
		luke.talk("I'll never join you!");
		vader.talk("If you only knew the power of the dark side. Obi-Wan never told you what happened to your father.");
		luke.talk("He told me enough! It was you who killed him.");
		vader.talk("No. I am your father.");
		luke.talk("No. No. That's not true! That's impossible!");
		vader.talk("Search your feelings. You know it to be true.");
		luke.talk("No! No! No!");
		vader.talk(
				"Luke. You can destroy the Emperor. He has foreseen this. It is your destiny. Join me, and together we can rule the galaxy as father and son. Come with me. It is the only way.");
	}

}
