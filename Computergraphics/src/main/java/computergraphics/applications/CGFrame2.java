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
	
	private static final int ROTORHEADANGLE = 40;
	
	private TranslationNode translationNodeHelicopter;
	private RotationNode rotateNodeHeli;
	HelicopterNode heli;
	private double xPos = 1;
	private double zPos = 1;
	private double rotorheadAngle = 1;
	private double heliAngle = 10;
	
	private double xSizeGround = 10;
	private double zSizeGround = 15;
	
	/**
	 * Constructor.
	 */
	public CGFrame2(int timerInverval) {
		super(timerInverval);
		
		// Shader node does the lighting computation
		ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
		getRoot().addChild(shaderNode);
		
		// Ground
		TranslationNode translationNodeBoden = new TranslationNode(new Vector3(0.0, -0.25, 0.0));
		shaderNode.addChild(translationNodeBoden);
		CuboidNode boden = new CuboidNode(xSizeGround, 0.001, zSizeGround,new Vector3(0.8, 0.4, 0.1));
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
		
		
		//Forrest
		TranslationNode translationNodeForrest = new TranslationNode(new Vector3(0.0, 0.0, 0.0));
		shaderNode.addChild(translationNodeForrest);
		translationNodeForrest.addChild(new ForrestNode());
		
		//Helicopter
		translationNodeHelicopter = new TranslationNode(new Vector3(2.85, 0.85, 0.0));
		shaderNode.addChild(translationNodeHelicopter);
		rotateNodeHeli = new RotationNode(new Vector3(0.0, 1.0, 0.0), 0);
		translationNodeHelicopter.addChild(rotateNodeHeli);
		heli = new HelicopterNode();
		rotateNodeHeli.addChild(heli);
		
		
	}
	
	@Override
	protected void timerTick() {
		System.out.println("Tick");
		xPos = xPos - 0.1;
		zPos = zPos - 0.1;
		rotorheadAngle = rotorheadAngle + ROTORHEADANGLE;
		this.heli.getRotorhead().setAngle(rotorheadAngle);
		
		heliAngle = heliAngle + 10;
		this.translationNodeHelicopter.setPosition(new Vector3(xPos, 1, 0));

//		this.rotateNodeHeli.setAngle(heliAngle);;
		
		
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
