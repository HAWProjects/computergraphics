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
public class PlainNode extends Node {

	/**
	 * Ebenenpunkte
	 */
	private Vector3 a;
	private Vector3 b;
	private Vector3 c;
	private Vector3 center;
	private Vector3 vectorNormale;
	private Vector3 color;
	private Vector3 u ;
	private Vector3 v ;

	/**
	 * Constructor.
	 */
	public PlainNode(Vector3 a, Vector3 b, Vector3 c) {
		this.a = a;
		this.b = b;
		this.c = c;
		center = berechneCenter();
		vectorNormale = calculateNormalVector();
		vectorNormale.normalize();
		color = new Vector3(1.0, 0.0, 0.0);
	}

	private Vector3 berechneCenter() {
		double x = (a.get(0) + b.get(0) + c.get(0)) / 3;
		double y = (a.get(1) + b.get(1) + c.get(1)) / 3;
		double z = (a.get(2) + b.get(2) + c.get(2)) / 3;

		return new Vector3(x, y, z);
	}

	public Vector3 calculateNormalVector() {
		// Spannvektoren u und v
		Vector3 u = b.subtract(a);
		Vector3 v = c.subtract(a);

		return u.cross(v);
	}

	public Vector3 getVectorNormal() {
		return this.vectorNormale;
	}

	public Vector3 getPoint() {
		return a;
	}

	public IntersectionResult berechneSchnitt(Ray3D ray) {
		// Hessesche Normalform + einsetzen:
		// Nach Lambda auflösen, wenn kleiner 0 dann kein Schnitt return null
		Vector3 pEbene = this.getPoint();
		Vector3 ebeneNormale = this.getVectorNormal();
		double lambda = 0.0;
		double tempNEPE = ebeneNormale.multiply(pEbene);
		double tempNEPS = ebeneNormale.multiply(ray.getPoint());
		double tempNEVS = ebeneNormale.multiply(ray.getDirection());
		lambda = (tempNEPE - tempNEPS) / tempNEVS;
		if (lambda < 0) {
			return null;
		}

		// Lambda einfügen: AugpunktPS + Lambda * VS und berechnen ==
		// Schnittpunkt
		Vector3 point = ray.getPoint().add(ray.getDirection().multiply(lambda));
		return new IntersectionResult(point, this, ebeneNormale);
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_QUADS);
		gl.glPushMatrix();
		gl.glColor3d(color.get(0), color.get(1), color.get(2));

		gl.glNormal3d(vectorNormale.get(0), vectorNormale.get(1),vectorNormale.get(2));
		//Spannvektoren
		gl.glVertex3d(u.get(0), u.get(1), u.get(2));
		gl.glVertex3d(v.get(0), v.get(1), v.get(2));
		gl.glVertex3d(-1 * u.get(0), -1 * u.get(1), -1 * u.get(2));
		gl.glVertex3d(-1 * v.get(0), -1 * v.get(1), -1 * v.get(2));
		
		gl.glEnd();
		gl.glPopMatrix();
		
		gl.glEnd();

	}

}
