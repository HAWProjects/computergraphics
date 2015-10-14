/**
 * 
 */
package main.java.computergraphics.scenegraph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/**
 * @author abq184
 *
 */
public class CylinderNode extends Node{
	
	private double radius;
	private double height;
	
	/**
	 * Constructor
	 */
	public CylinderNode(double radius, double height){
		this.radius = radius;
		this.height = height;
	}
	
	@Override
	public void drawGl(GL2 gl) {
		
		
		GLU glu = new GLU();
		glu.createGLU(gl);
		
		GLUquadric obj = glu.gluNewQuadric();
		
		glu.gluCylinder(obj, 0.2, 0.2, 0.75, 20, 10);
		
	}

}
