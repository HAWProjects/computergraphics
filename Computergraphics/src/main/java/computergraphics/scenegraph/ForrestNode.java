/**
 * Praktikum WPCG, WS 2015
 * Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
 * Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
 * Aufgabe: Aufgabenblatt 1, Aufgabe 3
 */
package main.java.computergraphics.scenegraph;

import java.util.Random;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.transformation.TranslationNode;

/**
 * Representation of a Forrest, which consists of TreeNodes. The Param treeCount
 * defines count of the trees. A Forrest has 50 trees by default.
 * 
 * @author Robert
 */
public class ForrestNode extends GroupNode {

	/**
	 * Construktor
	 */
	public ForrestNode() {
		this(50);
	}

	/**
	 * Constructor
	 * 
	 * @param int treeCount the count, how many trees should be in the forrest
	 * 
	 */
	public ForrestNode(int treeCount) {
		Random rand = new Random();
		for (int i = 0; i < treeCount; i++) {
			TreeNode tree;

			// put a tree at a random position
			double xPos = rand.nextInt(6) * (rand.nextDouble() + 0.15);
			double yPos = 0.0;
			double zPos = rand.nextInt(11) * (rand.nextDouble() + 0.1);

			if (rand.nextInt(10) < 5) {
				xPos = xPos * -1;
			}

			if (rand.nextInt(10) < 5) {
				zPos = zPos * -1;
			}

			if (xPos > 10) {
				xPos = 9;
			}

			if (zPos > 15) {
				zPos = 14;
			}

			if (rand.nextInt(10) < 5) {
				tree = new TreeCuboidNode();
			} else {
				tree = new TreeCylinderNode();
			}
			// add the new tree to the forrest
			TranslationNode translateTree = new TranslationNode(new Vector3(xPos, yPos, zPos));
			addChild(translateTree);

			translateTree.addChild((Node) tree);
		}
	}

}
