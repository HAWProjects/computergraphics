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
/**
 * 
 * @author Robert Scheffel, Jennifer Momsen
 *
 */
public class TreeCylinderNode extends TreeNode {
	/**
	 * Constructor
	 */
	public TreeCylinderNode() {
		ColorNode colorTrunk = new ColorNode(0.6,0.5,0.5);
		RotationNode rotate = new RotationNode(new Vector3(1.0, 0.0, 0.0), 90);
		TranslationNode translationNodeTree = new TranslationNode(new Vector3(0.0, 0.3, 0.0));
		addChild(colorTrunk);
		colorTrunk.addChild(translationNodeTree);
		translationNodeTree.addChild(rotate);
		rotate.addChild(new CylinderNode(0.05, 0.5, 12));

		TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.35, 0.0));
		ColorNode colorTreeTop = new ColorNode(0.6,0.5, 0.0);
		addChild(translationNode);
		translationNode.addChild(colorTreeTop);				
		colorTreeTop.addChild(new SphereNode(0.20, 15, new Vector3(0, 0, 0)));
	}
}
