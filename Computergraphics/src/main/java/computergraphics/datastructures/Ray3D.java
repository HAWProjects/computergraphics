package main.java.computergraphics.datastructures;

import main.java.computergraphics.math.Vector3;

/**
 * Representation of a ray in 3-space.
 * 
 * @author Philipp Jenke
 *
 */
public class Ray3D {

  /**
   * Starting point.
   */
  private Vector3 p = new Vector3();

  /**
   * Direction
   */
  private Vector3 r = new Vector3();

  /**
   * Constructor.
   */
  public Ray3D(Vector3 p, Vector3 r) {
    this.p.copy(p);
    this.r.copy(r);
  }

  @Override
  public String toString() {
    return p + " + lambda * " + r;
  }

  /**
   * Getter.
   */
  public Vector3 getDirection() {
    return r;
  }

  /**
   * Getter.
   */
  public Vector3 getPoint() {
    return p;
  }
  
  /**
   * Schnitt des Strahls mit einer Ebene
   */
  public IntersectionResult berechneSchnittEbene(){
	  //Hessesche Normalform + einsetzen:
	  //NormaleEbene*(AugpunktPS+Lambda*VStrahl)-NormaleEbene*PunktEbene
	  
	  //Nach Lambda auflösen, wenn kleiner 0 dann kein Schnitt return null
	  
	  //Lambda einfügen: AugpunktPS + Lambda * VS und berechnen == Schnittpunkt
	  
	  return null;
  }

}
