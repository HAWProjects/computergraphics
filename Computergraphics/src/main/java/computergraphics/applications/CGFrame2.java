/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */

package main.java.computergraphics.applications;


import main.java.computergraphics.framework.AbstractCGFrame;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.BaumNode;
import main.java.computergraphics.scenegraph.ColorNode;
import main.java.computergraphics.scenegraph.CuboidNode;
import main.java.computergraphics.scenegraph.DuplicateNode;
import main.java.computergraphics.scenegraph.ForrestNode;
import main.java.computergraphics.scenegraph.HelicopterNode;
import main.java.computergraphics.scenegraph.RotationNode;
import main.java.computergraphics.scenegraph.ShaderNode;
import main.java.computergraphics.scenegraph.ShaderNode.ShaderType;
import main.java.computergraphics.scenegraph.TranslationNode;

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
	
	private static final double EPSILON = 0.2;
	private final int ROTORHEAD_ANGLE = 40;
	
	private final double X_SIZE_GROUND = 15;
	private final double Z_SIZE_GROUND = 20;
	
	private HelicopterNode heli;
	private TranslationNode translationNodeHelicopter;
	private Vector3 positionHeli;
	private RotationNode rotateNodeHeli;
	private Vector3 rotationHeli;
	private double heliAngle = 0;
	private double rotorheadAngle = 0.0;
	private int directionHeli = 1;
	
	/**
	 * Constructor.
	 */
	public CGFrame2(int timerInverval) {
		super(timerInverval);
		
		positionHeli = new Vector3(0.0, 0.9, 0.0);
		rotationHeli = new Vector3(0.0, 1.0, 0.0);
		// Shader node does the lighting computation
		ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
		getRoot().addChild(shaderNode);
		
		// Ground
		TranslationNode translationNodeBoden = new TranslationNode(new Vector3(0.0, -0.25, 0.0));
		shaderNode.addChild(translationNodeBoden);
		CuboidNode boden = new CuboidNode(X_SIZE_GROUND, 0.001, Z_SIZE_GROUND, new Vector3(0.8, 0.4, 0.1));
		ColorNode colorNodeGround = new ColorNode(0.0, 0.5, 0.2);
		translationNodeBoden.addChild(colorNodeGround);
		colorNodeGround.addChild(boden);
		
		// Duplicate Node
		DuplicateNode duplicateTreesNode = new DuplicateNode(5);
		shaderNode.addChild(duplicateTreesNode);
		
		// Single Tree
		TranslationNode translationNodeTrees = new TranslationNode(new Vector3(0.2, 0.0, 0.0));
		duplicateTreesNode.addChild(translationNodeTrees);
		BaumNode baum1 = new BaumNode();
		translationNodeTrees.addChild(baum1);
		
		// Forrest
		TranslationNode translationNodeForrest = new TranslationNode(new Vector3(0.0, 0.0, 0.0));
		shaderNode.addChild(translationNodeForrest);
		translationNodeForrest.addChild(new ForrestNode());
		
		// Helicopter
		translationNodeHelicopter = new TranslationNode(positionHeli);
		shaderNode.addChild(translationNodeHelicopter);
		rotateNodeHeli = new RotationNode(rotationHeli, heliAngle);
		translationNodeHelicopter.addChild(rotateNodeHeli);
		
		heli = new HelicopterNode();
		rotateNodeHeli.addChild(heli);
		
	}
	
	@Override
	protected void timerTick() {
		System.out.println("Tick");
		moveHeli();
		
	}
	
	private void moveHeli() {
		rotorheadAngle = rotorheadAngle + ROTORHEAD_ANGLE;
		this.heli.getRotorhead().setAngle(rotorheadAngle);
		
		double xPos = positionHeli.get(0) - 0.1 * directionHeli;
		double yPos = positionHeli.get(1);
		double zPos = positionHeli.get(2);
		if(xPos < X_SIZE_GROUND / 3 && (xPos > X_SIZE_GROUND / 3 * -1) && (Math.abs(heliAngle - 0.0) < EPSILON)) {
			positionHeli.copy(new Vector3(xPos, yPos, zPos));
			this.translationNodeHelicopter.setPosition(positionHeli);
		}
		else if(xPos < X_SIZE_GROUND / 3 && (xPos > X_SIZE_GROUND / 3 * -1) && (Math.abs(heliAngle - 180.0) < EPSILON)) {
			positionHeli.copy(new Vector3(xPos, yPos, zPos));
			this.translationNodeHelicopter.setPosition(positionHeli);
		}
		else if(heliAngle < 180.0 && xPos < 0.0) {
			heliAngle = heliAngle + 10;
			this.rotateNodeHeli.setAngle(heliAngle);
			directionHeli = -1;
		}
		else if(heliAngle > 0.0) {
			heliAngle = heliAngle - 10;
			this.rotateNodeHeli.setAngle(heliAngle);
			directionHeli = 1;
		}
		
	}
	
	public void keyPressed(int keyCode) {
		System.out.println("Key pressed: " + (char) keyCode);
	}
	
	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		// The timer ticks every 100 ms.
		new CGFrame2(100);
	}
}
