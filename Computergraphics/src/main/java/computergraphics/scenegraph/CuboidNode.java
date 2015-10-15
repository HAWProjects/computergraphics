/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */
package main.java.computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

import main.java.computergraphics.math.Vector3;

/**
 * Representation of a cuboid with different dimensions in x-, y- and
 * z-direction.
 *
 * @author Philipp Jenke
 */
public class CuboidNode extends Node {

	/**
	 * Width of the cuboid (x-direction).
	 */
	private double width;
	/**
	 * Height of the cuboid (y-direction).
	 */
	private double height;

	/**
	 * Depth of the cuboid (z-direction).
	 */
	private double depth;
	
	/**
	 * Color of the Sphere
	 */
	private double colorR;
	private double colorG;
	private double colorB;

	/**
	 * Constructor.
	 */
	public CuboidNode(double width, double height, double depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.colorR = 0.85;
		this.colorG = 0.05;
		this.colorB = 0.05;
	}
	
	/**
	 * Constructor.
	 */
	public CuboidNode(double width, double height, double depth, Vector3 colorVector) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		this.colorR = colorVector.get(0);
		this.colorG = colorVector.get(1);
		this.colorB = colorVector.get(2);
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glColor3d(colorR, colorG, colorB);
		gl.glBegin(GL2.GL_QUADS);

		// left
		gl.glNormal3d(0, 0, -1);
		gl.glVertex3d(-width / 2.0, -height / 2.0, -depth / 2.0);
		gl.glVertex3d(width / 2.0, -height / 2.0, -depth / 2.0);
		gl.glVertex3d(width / 2.0, height / 2.0, -depth / 2.0);
		gl.glVertex3d(-width / 2.0, height / 2.0, -depth / 2.0);

		// front
		gl.glNormal3d(1, 0, 0);
		gl.glVertex3d(width / 2.0, -height / 2.0, -depth / 2.0);
		gl.glVertex3d(width / 2.0, -height / 2.0, depth / 2.0);
		gl.glVertex3d(width / 2.0, height / 2.0, depth / 2.0);
		gl.glVertex3d(width / 2.0, height / 2.0, -depth / 2.0);

		// back
		gl.glNormal3d(0, 0, 1);
		gl.glVertex3d(width / 2.0, -height / 2.0, depth / 2.0);
		gl.glVertex3d(-width / 2.0, -height / 2.0, depth / 2.0);
		gl.glVertex3d(-width / 2.0, height / 2.0, depth / 2.0);
		gl.glVertex3d(width / 2.0, height / 2.0, depth / 2.0);

		// right
		gl.glNormal3d(-1, 0, 0);
		gl.glVertex3d(-width / 2.0, -height / 2.0, depth / 2.0);
		gl.glVertex3d(-width / 2.0, -height / 2.0, -depth / 2.0);
		gl.glVertex3d(-width / 2.0, height / 2.0, -depth / 2.0);
		gl.glVertex3d(-width / 2.0, height / 2.0, depth / 2.0);

		// top
		gl.glNormal3d(0, 1, 0);
		gl.glVertex3d(-width / 2.0, height / 2.0, -depth / 2.0);
		gl.glVertex3d(width / 2.0, height / 2.0, -depth / 2.0);
		gl.glVertex3d(width / 2.0, height / 2.0, depth / 2.0);
		gl.glVertex3d(-width / 2.0, height / 2.0, depth / 2.0);
		
		
		// bottom
		gl.glNormal3d(0, -1, 0);
		gl.glVertex3d(-width / 2.0, -height / 2.0, depth / 2.0);
		gl.glVertex3d(width / 2.0, -height / 2.0, depth / 2.0);
		gl.glVertex3d(width / 2.0, -height / 2.0, -depth / 2.0);
		gl.glVertex3d(-width / 2.0, -height / 2.0, -depth / 2.0);

		gl.glEnd();

	}

}
