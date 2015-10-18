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
public class TreeNode extends GroupNode {
	/**
	 * Constructoor
	 */
	public TreeNode() {
		addChild(new CuboidNode(0.05, 0.5, 0.05, new Vector3(0.8, 0.5, 0.4)));

		TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.35, 0.0));
		addChild(translationNode);
		
		ColorNode colorNode = new ColorNode(0.5, 1.0, 0.01);
		translationNode.addChild(colorNode);
		
		colorNode.addChild(new SphereNode(0.20, 15, new Vector3(0.1, 0.8, 0.2)));
	}
	
	/**
	 * Constructor
	 * @param int size
	 */
	public TreeNode(int size) {
		addChild(new CuboidNode(size/10, size, size/10));

		TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.35, 0.0));
		addChild(translationNode);
		
		ColorNode colorNode = new ColorNode(0.0, 1.0, 0.01);
		
		translationNode.addChild(colorNode);
		colorNode.addChild(new SphereNode(size/2, size));
	}
}
