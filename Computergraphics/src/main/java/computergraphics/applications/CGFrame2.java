/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */

package main.java.computergraphics.applications;

import main.java.computergraphics.framework.AbstractCGFrame;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.CuboidNode;
import main.java.computergraphics.scenegraph.RotationNode;
import main.java.computergraphics.scenegraph.ScaleNode;
import main.java.computergraphics.scenegraph.ShaderNode;
import main.java.computergraphics.scenegraph.ShaderNode.ShaderType;
import main.java.computergraphics.scenegraph.SingleTriangleNode;
import main.java.computergraphics.scenegraph.SphereNode;
import main.java.computergraphics.scenegraph.TranslationNode;
import main.java.computergraphics.scenegraphObjects.Tree;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 */
public class CGFrame2 extends AbstractCGFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257130065274995543L;

	/**
	 * Constructor.
	 */
	public CGFrame2(int timerInverval) {
		super(timerInverval);

		// Shader node does the lighting computation
		ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
		getRoot().addChild(shaderNode);

		// translation
		TranslationNode translationNodeBoden = new TranslationNode(new Vector3(0.0, -0.25, 0.0));
		shaderNode.addChild(translationNodeBoden);
		// Boden
		CuboidNode boden = new CuboidNode(10, 0.001, 15);
		translationNodeBoden.addChild(boden);

		// CuboidNode
		CuboidNode cuboidNode = new CuboidNode(0.05, 0.5, 0.05);
		shaderNode.addChild(cuboidNode);

		// translation
		TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.35, 0.0));
		shaderNode.addChild(translationNode);

		// SphereNode
		SphereNode sphereNode = new SphereNode(0.20, 15);
		translationNode.addChild(sphereNode);

		// //translation
		// TranslationNode translationNode = new TranslationNode(new
		// Vector3(-1.0,-1.0,0.0));
		// shaderNode.addChild(translationNode);
		//
		// // ScaleNode double size
		// ScaleNode scaleNodeDoubleSize = new ScaleNode(new Vector3(2.0, 2.0,
		// 2.0));
		// shaderNode.addChild(scaleNodeDoubleSize);
		//
		// // RotationNode
		// RotationNode rotationNode = new RotationNode(new Vector3(0.0, 0.0,
		// 1.0), 90);
		// shaderNode.addChild(rotationNode);

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
		System.out.println("Key pressed: " + (char) keyCode);
	}

	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		// The timer ticks every 1000 ms.
		new CGFrame2(1000);
	}
}
