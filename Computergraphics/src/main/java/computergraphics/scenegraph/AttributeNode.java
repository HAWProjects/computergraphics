/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 1, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;

/**
 * @author Robert
 *
 */
public abstract class AttributeNode extends Node {
	
	private Vector3 color;
	
	/**
	 * Getter
	 * @return Vector3 color
	 */
	public Vector3 getColorVector(){
		return color;
	}
	
	/**
	 * setter
	 * @param Vector3 color
	 */
	public void setColor(Vector3 color){
		this.color = color;
	}

}
