/**
 * 
 */
package main.java.computergraphics.scenegraph;


import java.util.Random;

import main.java.computergraphics.math.Vector3;

/**
 * @author Robert
 * 		
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
	 * @param int
	 *            treeCount
	 */
	public ForrestNode(int treeCount) {
		Random rand = new Random();
		for(int i = 0; i < treeCount; i++) {
			
			double xPos = rand.nextInt(6) * (rand.nextDouble() + 0.15);
			double yPos = 0.0;
			double zPos = rand.nextInt(11) * (rand.nextDouble() + 0.1);
			
			if(rand.nextInt(10) < 5) {
				xPos = xPos * -1;
			}
			if(rand.nextInt(10) < 5) {
				zPos = zPos * -1;
			}
			
			if(xPos > 10) {
				xPos = 10;
			}
			
			if(zPos > 15) {
				zPos = 15;
			}
			
			TranslationNode translateTree = new TranslationNode(new Vector3(xPos, yPos, zPos));		
			addChild(translateTree);
			BaumNode tree = new BaumNode();
			translateTree.addChild(tree);
		}
	}
	
}
