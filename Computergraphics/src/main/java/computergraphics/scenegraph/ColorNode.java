/**
 * 
 */
package main.java.computergraphics.scenegraph;

import com.jogamp.opengl.GL2;


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
		// Remember current state of the render system
		gl.glPushMatrix();
		
		//Apply Color
		gl.glColor3d(r, g, b);
		
		
		// Draw all children
	    for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
	      getChildNode(childIndex).drawGl(gl);
	    }

	    // Restore original state
	    gl.glPopMatrix();
		
	}
	
}
