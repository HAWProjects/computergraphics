/**
 * 
 */
package main.java.computergraphics.scenegraph;


import com.jogamp.opengl.GL2;

/**
 * @author Robert
 *		
 */
public class DuplicateNode extends Node {
	
	private int count;
	
	/**
	 * Constuctor
	 * 
	 * @param int
	 *            count
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
