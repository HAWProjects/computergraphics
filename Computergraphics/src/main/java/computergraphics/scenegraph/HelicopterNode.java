/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.transformation.RotationNode;
import main.java.computergraphics.scenegraph.transformation.ScaleNode;
import main.java.computergraphics.scenegraph.transformation.TranslationNode;

/** Representation of an Helicopter
 * @author Robert
 *
 */
public class HelicopterNode extends GroupNode {
	/**
	 * the RotationNode of the Rotorhead
	 */
	private RotationNode rotateRotorhead;
	/**
	 * the RotationNode of the RotorheadBack
	 */
	private RotationNode rotateRotorheadBackZ;
	
	/**
	 * Constructor
	 */
	public HelicopterNode(){
		this(0.0,0.0,0.0);
	}
	
	/**
	 * Constructor
	 * @param xPosition
	 * @param yPosition
	 * @param zPosition
	 */
	public HelicopterNode(double xPosition, double yPosition, double zPosition){
		//Hull
		ColorNode colorHullSphere = new ColorNode(0.0, 0.2, 0.1);
		SphereNode hull = new SphereNode(0.20, 15);
		colorHullSphere.addChild(hull);
		addChild(colorHullSphere);
		
		TranslationNode translationNode = new TranslationNode(new Vector3(0.35, 0.065, 0.0));
		ColorNode colorHullCuboid = new ColorNode(1.0, 0.2, 0.1);
		colorHullCuboid.addChild(translationNode);
		translationNode.addChild(new CuboidNode(0.45, 0.16, 0.18));
		addChild(colorHullCuboid);
		
		TranslationNode translationNodeHullTop = new TranslationNode(new Vector3(0.15, 0.15, 0.0));
		translationNodeHullTop.addChild(new CuboidNode(0.15, 0.15, 0.15));
		addChild(colorHullCuboid);
		colorHullCuboid.addChild(translationNodeHullTop);
		//Skids Kuven
		TranslationNode translationNodeSkidsOne = new TranslationNode(new Vector3(0.0, -0.17, 0.09));
		translationNodeSkidsOne.addChild(new CuboidNode(0.5, 0.09, 0.09));
		addChild(translationNodeSkidsOne);
		//Skids
		TranslationNode translationNodeSkidsTwo = new TranslationNode(new Vector3(0.0, -0.17, -0.09));
		translationNodeSkidsTwo.addChild(new CuboidNode(0.5, 0.09, 0.09));
		addChild(translationNodeSkidsTwo);
		//Window
		TranslationNode translationNodeWindow = new TranslationNode(new Vector3(-0.07, 0.03, 0.0));
		ColorNode colorWindow = new ColorNode(0.0, 1.0, 1.0);
		translationNodeWindow.addChild(new SphereNode(0.15, 15));
		addChild(colorWindow);
		colorWindow.addChild(translationNodeWindow);
		//Rotorhead
		TranslationNode translationNodeRotorHead = new TranslationNode(new Vector3(0.15, 0.25, 0.0));
		rotateRotorhead = new RotationNode(new Vector3(0.0, 1.0, 0.0), 0);
		translationNodeRotorHead.addChild(rotateRotorhead);
		rotateRotorhead.addChild(new RotorHeadNode());
		addChild(translationNodeRotorHead);
		
		//RotorheadBack
		ScaleNode scaleRotorHeadBack = new ScaleNode(new Vector3(0.4, 0.4, 0.5));
		TranslationNode translationNodeRotorheadBack = new TranslationNode(new Vector3(1.25, 0.15, 0.2));
		RotationNode rotateRotorheadBack = new RotationNode(new Vector3(1.0, 0.0, 0.0), 90);
		rotateRotorheadBackZ = new RotationNode(new Vector3(0.0, 1.0, 0.0), 0);
		
		scaleRotorHeadBack.addChild(translationNodeRotorheadBack);
		translationNodeRotorheadBack.addChild(rotateRotorheadBack);
		rotateRotorheadBack.addChild(rotateRotorheadBackZ);
		rotateRotorheadBackZ.addChild(new RotorHeadNode());	
		addChild(scaleRotorHeadBack);
	}
	
	/**
	 * getter
	 * @return RotationNode Rotorhead
	 */
	public RotationNode getRotorhead(){
		return rotateRotorhead;
	}
	
	/**
	 * getter
	 * @return RotationNode RotorheadBack
	 */
	public RotationNode getRotorheadBack(){
		return rotateRotorheadBackZ;
	}
	

}
