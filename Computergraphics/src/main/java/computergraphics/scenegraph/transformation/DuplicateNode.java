/*Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph.transformation;


import com.jogamp.opengl.GL2;

import main.java.computergraphics.scenegraph.Node;

/**
 *Scenegraphnode, which duplicate all its children
 *
 *		
 */
public class DuplicateNode extends Node {
	//count how much the object needs to duplicate
	private int count;
	
	/**
	 * Constuctor
	 * 
	 * @param int count
	 *            
	 */
	public DuplicateNode(int count) {
	  super();
	  this.count = count;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.computergraphics.scenegraph.Node#drawGl(com.jogamp.opengl.GL2)
	 */
	@Override
	public void drawGl(GL2 gl) {
		// Remember current state of the render system
		gl.glPushMatrix();
		
		// Draw all children
		for(int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
			for(int i = 0; i < count; i++) {
				getChildNode(childIndex).drawGl(gl);
			}
		}
		// Restore original state
		gl.glPopMatrix();
		
	}
	
}
