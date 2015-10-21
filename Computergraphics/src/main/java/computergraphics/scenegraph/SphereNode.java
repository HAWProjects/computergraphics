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
public class SphereNode extends Node {

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
	private double colorR;
	private double colorG;
	private double colorB;


	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution) {
		this(radius,resolution,0.75,0.25,0.25);
	}
	
	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution, double colorR, double colorG,double colorB) {
		this.radius = radius;
		this.resolution = resolution;
		this.colorR = colorR;
		this.colorG = colorG;
		this.colorB = colorB;
	}
	
	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution,Vector3 colorVector) {
		this.radius = radius;
		this.resolution = resolution;
		this.colorR = colorVector.get(0);
		this.colorG = colorVector.get(1);
		this.colorB = colorVector.get(2);
		
	}

	@Override
	public void drawGl(GL2 gl) {
//		gl.glColor3d(colorR,colorG,colorB);
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
