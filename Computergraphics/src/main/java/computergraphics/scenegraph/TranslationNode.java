package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;

import org.omg.PortableServer.ServantRetentionPolicyOperations;

import com.jogamp.opengl.GL2;

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
