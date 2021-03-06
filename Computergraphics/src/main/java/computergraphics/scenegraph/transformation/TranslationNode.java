/*
*Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph.transformation;

import com.jogamp.opengl.GL2;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.Node;

/**
 * Scene graph node which translate all its child nodes.
 * 
 * @author Robert Scheffel, Jennifer Momsen
 *
 */
public class TranslationNode extends Node {
	/**
	 * Scaling factors in x-, y- and z-direction.
	 */
	private final Vector3 trans = new Vector3(1, 1, 1);

	/**
	 * Constructor
	 */
	public TranslationNode(Vector3 trans) {
		 this.trans.copy(trans);
	}
	
	public void setPosition(Vector3 pos){
		this.trans.copy(pos);
	}

	@Override
	public void drawGl(GL2 gl) {
		// Remember current state of the render system
		gl.glPushMatrix();
		
		//Apply translation
		gl.glTranslatef((float)trans.get(0) , (float)trans.get(1), (float)trans.get(2));
		
		// Draw all children
	    for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
	      getChildNode(childIndex).drawGl(gl);
	    }

	    // Restore original state
	    gl.glPopMatrix();

	}

}
