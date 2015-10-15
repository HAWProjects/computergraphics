/**
 * 
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
		// Remember current state of the render system
		gl.glPushMatrix();
		
		//Apply Color
//		gl.glBegin(GL2.GL_3D_COLOR);
//		gl.glClearColor(0.1f, 0.5f, 0.8f, 0.7f);
//		gl.glColor3d(r, g, b);
//		gl.glEnd();
		
		
		
		// Draw all children
	    for (int childIndex = 0; childIndex < getNumberOfChildren(); childIndex++) {
	      getChildNode(childIndex).drawGl(gl);
	      getChildNode(childIndex).getColor().copy(new Vector3(r, g, b));
	    }

	    // Restore original state
	    gl.glPopMatrix();
		
	}
	
}
