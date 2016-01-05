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
public class PlainNode extends Node {

	/**
	 * Ebenenpunkte
	 */
	private Vector3 a;
	private Vector3 b;
	private Vector3 c;
	private Vector3 center;
	private Vector3 vectorNormale;

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
	}
	
	private Vector3 berechneCenter() {
		double x = (a.get(0)+b.get(0)+ c.get(0))/ 3;
		double y = (a.get(1)+b.get(1)+ c.get(1))/ 3;
		double z = (a.get(2)+b.get(2)+ c.get(2))/ 3;
		
		return new Vector3(x, y, z);
	}

	public Vector3 calculateNormalVector(){
		//Spannvektoren u und v
		Vector3 u = b.subtract(a);
		Vector3 v = c.subtract(a);
		
		return u.cross(v);
	}
	
	public Vector3 getVectorNormal(){
		System.out.println("Plain Normale" + this.vectorNormale);
		return this.vectorNormale;
	}
	
	public Vector3 getPoint(){
		return a;
	}
	@Override
	public void drawGl(GL2 gl) {
		GLU glu = new GLU();
		
	}
	

}
