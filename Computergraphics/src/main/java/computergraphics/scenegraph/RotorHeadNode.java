/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.transformation.RotationNode;

/** Representation of an Rotorhead
 *
 */
public class RotorHeadNode extends GroupNode {
	/**
	 * Constructor
	 */
	public RotorHeadNode(){
		
		CuboidNode rotorbladeOne = new CuboidNode(0.8, 0.02, 0.12);
		ColorNode colorRotor = new ColorNode(0.0,0.0,0.0);
		colorRotor.addChild(rotorbladeOne);
		addChild(colorRotor);
		
		RotationNode rotate = new RotationNode(new Vector3(0.0, 1.0, 0.0), 90);
		addChild(rotate);
		
		CuboidNode rotorbladeTwo = new CuboidNode(0.8, 0.02, 0.12);
		rotate.addChild(rotorbladeTwo);
	}

}
