/*Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.units;


/**
 * @author Robert
 *
 */
public enum RotorheadSpeed {
	SPEED(10.0);
	
	private double value;
	
	/*
	 * Constructor
	 */
	private RotorheadSpeed(double value) {
		this.value = value;
	}
	/*
	 * Getter.
	 */
	public double getFactor() {
		return value;
	}

}
