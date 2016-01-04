package main.java.computergraphics.datastructures;

import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.Node;

/**
 * Representation of the intersection result.
 */
public class IntersectionResult {

  /**
   * The intersection happens at this point.
   */
  public Vector3 point;

  /**
   * Normal at the given point.
   */
  public Vector3 normal;

  /**
   * Intersected object
   */
  public Node object;
  
  public IntersectionResult(Vector3 point, Node object){
	  this.point = point;
	  this.object = object;
  }
}
