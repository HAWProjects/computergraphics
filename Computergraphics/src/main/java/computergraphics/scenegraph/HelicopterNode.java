/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.transformation.RotationNode;
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
	 * Constructor
	 */
	public HelicopterNode(){
		this(0.0,0.0,0.0);
	}
	
	/**
	 * Construktor
	 * @param xPosition
	 * @param yPosition
	 * @param zPosition
	 */
	public HelicopterNode(double xPosition, double yPosition, double zPosition){
		//Hull
		SphereNode hull = new SphereNode(0.20, 15,0.0,0.7,0.5);
		addChild(hull);
		TranslationNode translationNode = new TranslationNode(new Vector3(0.35, 0.065, 0.0));
		translationNode.addChild(new CuboidNode(0.45, 0.16, 0.18));
		addChild(translationNode);
		TranslationNode translationNodeHullTop = new TranslationNode(new Vector3(0.15, 0.15, 0.0));
		translationNodeHullTop.addChild(new CuboidNode(0.15, 0.15, 0.15));
		addChild(translationNodeHullTop);
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
		translationNodeWindow.addChild(new SphereNode(0.15, 15, new Vector3(0.1, 0.2, 0.7)));
		addChild(translationNodeWindow);
		//Rotorhead
		TranslationNode translationNodeRotorHead = new TranslationNode(new Vector3(0.15, 0.25, 0.0));
		rotateRotorhead = new RotationNode(new Vector3(0.0, 1.0, 0.0), 0);
		translationNodeRotorHead.addChild(rotateRotorhead);
		rotateRotorhead.addChild(new RotorHeadNode());
		addChild(translationNodeRotorHead);
	}
	
	/**
	 * getter
	 * @return RotationNode Rotorhead
	 */
	public RotationNode getRotorhead(){
		return rotateRotorhead;
	}

}
