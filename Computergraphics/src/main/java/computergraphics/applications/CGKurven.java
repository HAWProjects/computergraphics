/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */

package main.java.computergraphics.applications;


import com.jogamp.opengl.GL2;

import main.java.computergraphics.framework.AbstractCGFrame;
import main.java.computergraphics.kurven.BezierKurve;
import main.java.computergraphics.kurven.Kurve;
import main.java.computergraphics.kurven.MonomKurve;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.ColorNode;
import main.java.computergraphics.scenegraph.CowNode;
import main.java.computergraphics.scenegraph.CuboidNode;
import main.java.computergraphics.scenegraph.KurvenNode;
import main.java.computergraphics.scenegraph.ObjNode;
import main.java.computergraphics.scenegraph.ShaderNode;
import main.java.computergraphics.scenegraph.ShaderNode.ShaderType;
import main.java.computergraphics.scenegraph.SphereNode;
import main.java.computergraphics.scenegraph.TransformationNode;
import main.java.computergraphics.scenegraph.transformation.TranslationNode;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 */
public class CGKurven extends AbstractCGFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;
	
	CowNode cow;
	ObjNode objNode;
	
	/**
	 * Constructor.
	 */
	public CGKurven(int timerInverval) {
		super(timerInverval);
		
		// Shader node does the lighting computation
		ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
		getRoot().addChild(shaderNode);

		Vector3 p1 = new Vector3(0, 0, 0);
		Vector3 p2 = new Vector3(0.2, 0.2, 0);
		Vector3 p3 = new Vector3(0.2, 0.5, 0.2);
		
		SphereNode sphere1 = new SphereNode(0.01, 20);
		SphereNode sphere2 = new SphereNode(0.01, 20);
		SphereNode sphere3 = new SphereNode(0.01, 20);
		
		TranslationNode tN1 = new TranslationNode(p1);
		TranslationNode tN2 = new TranslationNode(p2);
		TranslationNode tN3 = new TranslationNode(p3);
			
		getRoot().addChild(tN1);
		getRoot().addChild(tN2);
		getRoot().addChild(tN3);
		tN1.addChild(sphere1);
		tN2.addChild(sphere2);
		tN3.addChild(sphere3);

		
		Kurve kurve = new MonomKurve();
		Kurve kurve2 = new BezierKurve();
		kurve2.addKontrollpunkt(p1);
		kurve2.addKontrollpunkt(p2);
		kurve2.addKontrollpunkt(p3);
		
		kurve.interpolieren(p1, p2, p3);
		KurvenNode kN = new KurvenNode(kurve);
//		KurvenNode bN = new KurvenNode(kurve2);
		
		ColorNode cN = new ColorNode(0.0, 1.0, 0.0);
		shaderNode.addChild(cN);
		cN.addChild(kN);
	
	}
	
	/*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		System.out.println("Tick");
	}
	
	public void keyPressed(int keyCode) {

	}
	
	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		// The timer ticks every 1000 ms.
		new CGKurven(1000);
	}
}
