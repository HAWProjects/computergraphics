/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.transformation.TranslationNode;
/**
 * 
 * @author Robert Scheffel, Jennifer Momsen
 *
 */
public class TreeCuboidNode extends TreeNode{
	/**
	 * Constructor
	 */
	public TreeCuboidNode() {
		
		ColorNode colorTrunk = new ColorNode(0.8, 0.5, 0.4);
		addChild(colorTrunk);
		colorTrunk.addChild(new CuboidNode(0.05, 0.5, 0.05));
		
		ColorNode colorTreeTop = new ColorNode(0.1, 0.8, 0.2);
		TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.35, 0.0));
		addChild(translationNode);
		translationNode.addChild(colorTreeTop);
				
		colorTreeTop.addChild(new SphereNode(0.20, 15));
	}
}
