/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
* 
*/

package main.java.computergraphics.applications;


import main.java.computergraphics.Constants;
import main.java.computergraphics.framework.AbstractCGFrame;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.ColorNode;
import main.java.computergraphics.scenegraph.CuboidNode;
import main.java.computergraphics.scenegraph.ForrestNode;
import main.java.computergraphics.scenegraph.HelicopterNode;
import main.java.computergraphics.scenegraph.ShaderNode;
import main.java.computergraphics.scenegraph.ShaderNode.ShaderType;
import main.java.computergraphics.scenegraph.transformation.RotationNode;
import main.java.computergraphics.scenegraph.transformation.TranslationNode;
import main.java.computergraphics.units.RotorheadSpeed;

/**
 * Application for the third exercise
 * 
 * @author Robert Scheffel, Jennifer Momsen
 */
public class CGFrame2 extends AbstractCGFrame {
	
	/**
	 * Instanzvariablen und Konstanten
	 */
	private static final long serialVersionUID = 4257130065274995543L;
	
	
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
	
	/*
	 * A method, which moves the helicopter back and forth over the ground
	 */
	private void moveHeli() {
		//calculate new angle of rotorhead
		rotorheadAngle = rotorheadAngle + ROTORHEAD_ANGLE;
		heli.getRotorhead().setAngle(rotorheadAngle);
		
		double xPos = positionHeli.get(0) - 0.1 * directionHeli;
		double yPos = positionHeli.get(1);
		double zPos = positionHeli.get(2);
		//calculate the new heli position in over the ground
		if(xPos < X_SIZE_GROUND / 3 && (xPos > X_SIZE_GROUND / 3 * -1) && (Math.abs(heliAngle - 0.0) < Constants.EPSILON)) {
			positionHeli.copy(new Vector3(xPos, yPos, zPos));
			translationNodeHelicopter.setPosition(positionHeli);
		}
		else if(xPos < X_SIZE_GROUND / 3 && (xPos > X_SIZE_GROUND / 3 * -1) && (Math.abs(heliAngle - 180.0) < Constants.EPSILON)) {
			positionHeli.copy(new Vector3(xPos, yPos, zPos));
			translationNodeHelicopter.setPosition(positionHeli);
		}
		//turning heli, when at the end of ground
		else if(heliAngle < 180.0 && xPos < 0.0) {
			heliAngle = heliAngle + 10;
			this.rotateNodeHeli.setAngle(heliAngle);
			directionHeli = -1;
		}
		else if(heliAngle > 0.0) {
			heliAngle = heliAngle - 10;
			rotateNodeHeli.setAngle(heliAngle);
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
