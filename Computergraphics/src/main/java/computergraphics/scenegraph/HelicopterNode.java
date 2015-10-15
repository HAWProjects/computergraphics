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
	
	public HelicopterNode(){
		SphereNode rumpf = new SphereNode(0.20, 15);
		addChild(rumpf);
		
		TranslationNode translationNode = new TranslationNode(new Vector3(0.35, 0.015, 0.0));
		addChild(translationNode);
		translationNode.addChild(new CuboidNode(0.45, 0.16, 0.18));
		
		TranslationNode translationNodeSkidsOne = new TranslationNode(new Vector3(0.0, -0.17, 0.09));
		addChild(translationNodeSkidsOne);
		
		translationNodeSkidsOne.addChild(new CuboidNode(0.5, 0.09, 0.09));
		
		TranslationNode translationNodeSkidsTwo = new TranslationNode(new Vector3(0.0, -0.17, -0.09));
		addChild(translationNodeSkidsTwo);
		
		translationNodeSkidsTwo.addChild(new CuboidNode(0.5, 0.09, 0.09));
		
		
		TranslationNode translationNodeWindow = new TranslationNode(new Vector3(-0.07, 0.03, 0.0));
		addChild(translationNodeWindow);
		translationNodeWindow.addChild(new SphereNode(0.15, 15));
		
	}
	
	
	public HelicopterNode(double xPosition, double yPosition, double zPosition){
		
	}

}
