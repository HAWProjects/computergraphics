/**
 * 
 */
package main.java.computergraphics.units;


/**
 * @author Robert
 *
 */
public enum RotorheadSpeed {
	SPEED(10.0);
	
	private double value;

	private RotorheadSpeed(double value) {
		this.value = value;
	}

	public double getFactor() {
		return value;
	}

}
