/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */
package main.java.computergraphics.scenegraph;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import main.java.computergraphics.math.Vector3;

/**
 * Geometry of a simple sphere.
 * 
 * @author Philipp Jenke
 */
public class SphereNode extends AttributeNode {

	/**
	 * Sphere radius.
	 */
	private double radius;

	/**
	 * Resolution (in one dimension) of the mesh.
	 */
	private int resolution;
	
	/**
	 * Color of the Sphere
	 */
	private Vector3 color;


	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution) {
		this.radius = radius;
		this.resolution = resolution;
		this.color.set(0, 0.75); 
		this.color.set(1,0.25); 
		this.color.set(2,0.25);
	}
	
	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution, double colorR, double colorG,double colorB) {
		this.radius = radius;
		this.resolution = resolution;
		this.color.set(0, colorR); 
		this.color.set(1,colorG); 
		this.color.set(2,colorB);
	}
	
	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution,Vector3 colorVector) {
		this.radius = radius;
		this.resolution = resolution;
		this.color.copy(colorVector); 
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glColor3d(color.get(0),color.get(1),color.get(2));
		GLU glu = new GLU();
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
		glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
		final int slices = resolution;
		final int stacks = resolution;
		glu.gluSphere(earth, radius, slices, stacks);
	}

}
