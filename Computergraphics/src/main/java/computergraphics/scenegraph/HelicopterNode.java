/**
 * 
 */
package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;

/**
 * @author Robert
 *
 */
public class HelicopterNode extends GroupNode {
	
	private RotationNode rotateRotorhead;
	
	public HelicopterNode(){
		//Hull
		SphereNode hull = new SphereNode(0.20, 15,0.0,0.7,0.5);
		addChild(hull);
		
		
		TranslationNode translationNode = new TranslationNode(new Vector3(0.35, 0.065, 0.0));
		addChild(translationNode);
		translationNode.addChild(new CuboidNode(0.45, 0.16, 0.18));
		
		
		TranslationNode translationNodeHullTop = new TranslationNode(new Vector3(0.15, 0.15, 0.0));
		addChild(translationNodeHullTop);
		
		translationNodeHullTop.addChild(new CuboidNode(0.15, 0.15, 0.15));
		
		//Skids Kuven
		TranslationNode translationNodeSkidsOne = new TranslationNode(new Vector3(0.0, -0.17, 0.09));
		addChild(translationNodeSkidsOne);
		
		translationNodeSkidsOne.addChild(new CuboidNode(0.5, 0.09, 0.09));
		
		//Skids
		TranslationNode translationNodeSkidsTwo = new TranslationNode(new Vector3(0.0, -0.17, -0.09));
		addChild(translationNodeSkidsTwo);
		
		translationNodeSkidsTwo.addChild(new CuboidNode(0.5, 0.09, 0.09));
		
		//Window
		TranslationNode translationNodeWindow = new TranslationNode(new Vector3(-0.07, 0.03, 0.0));
		addChild(translationNodeWindow);
		translationNodeWindow.addChild(new SphereNode(0.15, 15, new Vector3(0.1, 0.2, 0.7)));
		
		//Rotorhead
		TranslationNode translationNodeRotorHead = new TranslationNode(new Vector3(0.15, 0.25, 0.0));
		addChild(translationNodeRotorHead);
		
		rotateRotorhead = new RotationNode(new Vector3(0.0, 1.0, 0.0), 0);
		translationNodeRotorHead.addChild(rotateRotorhead);
	
		rotateRotorhead.addChild(new RotorHeadNode());
	}
	
	
	public HelicopterNode(double xPosition, double yPosition, double zPosition){
		
	}
	
	public RotationNode getRotorhead(){
		return rotateRotorhead;
	}

}
