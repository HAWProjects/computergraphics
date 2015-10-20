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
	
	private double colorR;
	private double colorG;
	private double colorB;
	
	/**
	 * Getter
	 * @return Vector3 color
	 */
	public double getColorR(){
		return colorR;
	}
	
	/**
	 * setter
	 * @param Vector3 color
	 */
	public void setColorR(double colorR){
		this.colorR = colorR;
	}
	public void setColorG(double colorG){
		this.colorG = colorG;
	}
	public void setColorB(double colorB){
		this.colorB = colorB;
	}

}
