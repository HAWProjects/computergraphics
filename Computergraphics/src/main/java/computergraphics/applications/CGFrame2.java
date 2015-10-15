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
import main.java.computergraphics.scenegraph.HelicopterNode;
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
	
	private TranslationNode translationNodeHelicopter;
	private double testPos = 1;
	
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
		ColorNode colorNodeGround = new ColorNode(0.0, 0.5, 0.2);
		colorNodeGround.addChild(boden);
		
		translationNodeBoden.addChild(colorNodeGround);
		
		
		// Duplicate Node
		DuplicateNode duplicateTreesNode = new DuplicateNode(5);
		shaderNode.addChild(duplicateTreesNode);
		// translation trees
		TranslationNode translationNodeTrees = new TranslationNode(new Vector3(0.2, 0.0, 0.0));
		duplicateTreesNode.addChild(translationNodeTrees);
		// 
		BaumNode baum1 = new BaumNode();
		translationNodeTrees.addChild(baum1);
		
		
		//Helicopter
		translationNodeHelicopter = new TranslationNode(new Vector3(0.0, 0.85, 0.0));
		shaderNode.addChild(translationNodeHelicopter);
		
		HelicopterNode heli = new HelicopterNode();
		translationNodeHelicopter.addChild(heli);
		
		
	}
	
	@Override
	protected void timerTick() {
		System.out.println("Tick");
		testPos = testPos -0.1;
		this.translationNodeHelicopter.setPosition(new Vector3(testPos, 1, 0));
		
	}
	
	public void keyPressed(int keyCode) {
		System.out.println("Key pressed: " + (char) keyCode);
	}
	
	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		// The timer ticks every 1000 ms.
		new CGFrame2(100);
	}
}
