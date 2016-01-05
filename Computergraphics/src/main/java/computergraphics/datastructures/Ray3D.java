package main.java.computergraphics.datastructures;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.PlainNode;
import main.java.computergraphics.scenegraph.SphereNode;

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
    System.out.println("Punkt des Strahls "+p);
    System.out.println("Richtung des Strahls "+r);
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
  public IntersectionResult berechneSchnittEbene(PlainNode plainNode){
	  //Hessesche Normalform + einsetzen:
	  //Nach Lambda auflösen, wenn kleiner 0 dann kein Schnitt return null
	  Vector3 pEbene = plainNode.getPoint();
	  Vector3 ebeneNormale = plainNode.getVectorNormal();
	  double lambda = 0.0;
	  double tempNEPE = ebeneNormale.multiply(pEbene);
	  System.out.println("NEPE" + tempNEPE);
	  double tempNEPS = ebeneNormale.multiply(p);
	  System.out.println("NEPS"+ tempNEPS);
	  double tempNEVS = ebeneNormale.multiply(r);
	  System.out.println("NEVS" + tempNEVS);
	  lambda = (tempNEPE - tempNEPS)/tempNEVS;
	  System.out.println("Lamda" + lambda);
	  if(lambda < 0){
		  return null;
	  }
	  
	  //Lambda einfügen: AugpunktPS + Lambda * VS und berechnen == Schnittpunkt
	  Vector3 point = p.add(r.multiply(lambda));
	  System.out.println("Schnittpunkt" + point);
	  return new IntersectionResult(point, plainNode);
  }
  
  public IntersectionResult berechneSchnittKugel(SphereNode sphereNode){
	  double pSVS = 2*(p.multiply(r));
	  double mKVS = 2*(sphereNode.getCentre().multiply(r));
	  double vS = r.multiply(r);
	  double pS = p.multiply(p);
	  double pSMK = 2*(p.multiply(sphereNode.getCentre()));
	  double mK = sphereNode.getCentre().multiply(sphereNode.getCentre());
	  double radius = sphereNode.getRadius().multiply(sphereNode.getRadius());
	  
	  double pe = (pSVS-mKVS)/vS;
	  double q = (pS - pSMK + mK - radius)/vS;
	  
	  double lambda1 = -pe/2 + Math.sqrt((Math.pow(pe, 2))/4 - q);
	  double lambda2 = -pe/2 - Math.sqrt((Math.pow(pe, 2))/4 - q);
	  
	  if(lambda1 < 0 && lambda2 < 0){
		  return null;
	  }
	  
	  Vector3 schnittpunkt = p.add(r.multiply(Math.min(lambda1, lambda2)));
	  return new IntersectionResult(schnittpunkt, sphereNode);
  }
}
