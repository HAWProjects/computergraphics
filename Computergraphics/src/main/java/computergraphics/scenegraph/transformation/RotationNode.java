/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 2
*/
package main.java.computergraphics.scenegraph.transformation;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.Node;

import com.jogamp.opengl.GL2;

/**
 * Scene graph node which rotates all its child nodes.
 * 
 * @author Robert Scheffel, Jennifer Momsen
 *
 */

public class RotationNode extends Node {

	/**
	 * Rotational axis.
	 */
	private Vector3 axis = new Vector3(1, 1, 1);
	
	/**
	 * Angle
	 */
	private double angle;


	
	
	/**Constructor
	 * @param axis
	 * @param angle
	 */
	public RotationNode(Vector3 axis, double angle) {
		super();
		this.axis.copy(axis);
		this.angle = angle;
	}
	
	public void setAxis(Vector3 axis){
		this.axis.copy(axis);
	}
	
	public void setAngle(double angle){
		this.angle = angle;
	}




	/* (non-Javadoc)
	 * @see main.java.computergraphics.scenegraph.Node#drawGl(com.jogamp.opengl.GL2)
	 */
	@Override
	public void drawGl(GL2 gl) {
		// Remember current state of the render system
		gl.glPushMatrix();
		
		//Apply rotation
		gl.glRotated(angle, axis.get(0), axis.get(1), axis.get(2));
		
		// Draw all children
	    for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
	      getChildNode(childIndex).drawGl(gl);
	    }

	    // Restore original state
	    gl.glPopMatrix();

	}

}
