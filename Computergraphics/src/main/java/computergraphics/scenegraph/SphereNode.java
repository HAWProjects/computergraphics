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

import main.java.computergraphics.datastructures.IntersectionResult;
import main.java.computergraphics.datastructures.Ray3D;
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

	private Vector3 centre = new Vector3(0.0, 0.0, 0.0);

	private Vector3 color;

	/**
	 * Constructor.
	 */
	public SphereNode(double radius, int resolution, Vector3 centre) {
		this.radius = radius;
		this.resolution = resolution;
		this.centre.copy(centre);
		color = new Vector3(1.0, 0.0, 0.0);
	}

	public IntersectionResult berechneSchnitt(Ray3D ray) {

		double pSVS = 2 * (ray.getPoint().multiply(ray.getDirection()));
		double mKVS = 2 * (centre.multiply(ray.getDirection()));
		double vS = ray.getDirection().multiply(ray.getDirection());
		double pS = ray.getPoint().multiply(ray.getPoint());
		double pSMK = 2 * (ray.getPoint().multiply(centre));
		double mK = centre.multiply(centre);
		double radius = this.radius * this.radius;

		double pe = (pSVS - mKVS) / vS;
		double q = (pS - pSMK + mK - radius) / vS;

		double lambda1 = -pe / 2 + Math.sqrt((Math.pow(pe, 2) / 4) - q);
		double lambda2 = -pe / 2 - Math.sqrt((Math.pow(pe, 2) / 4) - q);

		if (lambda1 > 0 && lambda2 > 0) {

			Vector3 schnittpunkt = ray.getPoint().add(ray.getDirection().multiply(Math.min(lambda1, lambda2)));
			Vector3 normale = schnittpunkt.subtract(centre);
			normale.normalize();
			return new IntersectionResult(schnittpunkt, this, normale);
		}
		return null;
	}

	@Override
	public void drawGl(GL2 gl) {
		GLU glu = new GLU();
		gl.glColor3d(color.get(0), color.get(1), color.get(2));
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
		glu.gluQuadricNormals(earth, GLU.GLU_SMOOTH);
		glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
		final int slices = resolution;
		final int stacks = resolution;
		glu.gluSphere(earth, radius, slices, stacks);
	}

	public Vector3 getCentre() {
		return this.centre;
	}

	public double getRadius() {
		return radius;
	}

	public Vector3 getColor() {
		return color;
	}

}
