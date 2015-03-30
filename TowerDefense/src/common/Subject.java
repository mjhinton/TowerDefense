package common;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class donates methods to observable subject classes that make
 * it observable.
 * 
 * @author Michael Hinton
 */
public abstract class Subject {

	private List<IObserver> observers;

	public Subject() {
		observers = new ArrayList<IObserver>();
	}

	/**
	 * Adds an observer to the observers.
	 * 
	 * @param obs
	 *            observer to be added
	 */
	public void addObserver(IObserver obs) {
		observers.add(obs);
	}

	/**
	 * Removes an observer from the observers.
	 * 
	 * @param obs
	 *            observer to be removed
	 */
	public void removeObserver(IObserver obs) {
		observers.remove(obs);
	}

	/**
	 * Calls the update method of the observers.
	 * 
	 */
	protected void notifyObservers() {
		for (IObserver o : observers) {
			o.update();
		}
	}
}
