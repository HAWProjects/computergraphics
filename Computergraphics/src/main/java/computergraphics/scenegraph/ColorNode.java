/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import main.java.computergraphics.math.Vector3;


/**
 * @author Robert
 *
 */
public class ColorNode extends Node {
	
	
	private double r;
	private double g;
	private double b;
	
	
	
	/**
	 * Constructor
	 * @param double r
	 * @param double g
	 * @param double b
	 */
	public ColorNode(double r, double g, double b) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
	}




	/* (non-Javadoc)
	 * @see main.java.computergraphics.scenegraph.Node#drawGl(com.jogamp.opengl.GL2)
	 */
	@Override
	public void drawGl(GL2 gl) {

		
		// Draw all children
	    for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
	      getChildNode(childIndex).getColor().copy(new Vector3(r, g, b));
	    }

		
	}
	
}
