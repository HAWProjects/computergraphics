/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import main.java.computergraphics.math.Vector3;

/**
 * @author Robert Scheffel, Jennifer Momsen
 *		
 */
public class CylinderNode extends Node {
	
	/**
	 * Cylinder radius.
	 */
	private double radius;
	/**
	 * Cylinder height.
	 */
	private double height;
	
	/**
	 * Resolution (in one dimension) of the mesh.
	 */
	private int resolution;
	
	/**
	 * Color of the Cylinder
	 */
	private double colorR;
	private double colorG;
	private double colorB;
	
	/**
	 * Constructor
	 */
	public CylinderNode() {
		this(1.0, 1.0,10);
	}
	
	/**
	 * Constructor
	 */
	public CylinderNode(double radius, double height, int resolution) {
		this(radius, height,resolution, new Vector3(0.1, 0.1, 0.1));
	}
	
	/**
	 * Constructor
	 */
	public CylinderNode(double radius, double height, int resolution, Vector3 colorVeector) {
		this.radius = radius;
		this.height = height;
		this.resolution = resolution;
		this.colorR = colorVeector.get(0);
		this.colorG = colorVeector.get(1);
		this.colorB = colorVeector.get(2);
	}
	
	@Override
	public void drawGl(GL2 gl) {
		gl.glColor3d(colorR, colorG, colorB);
		
		GLU glu = new GLU();
		GLUquadric obj = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(obj, GLU.GLU_FILL);
		glu.gluQuadricNormals(obj, GLU.GLU_SMOOTH);
		glu.gluQuadricOrientation(obj, GLU.GLU_OUTSIDE);
		final int slices = resolution;
		final int stacks = resolution;
		glu.gluCylinder(obj, radius, radius, height, slices, stacks);
		
	}
	
}
