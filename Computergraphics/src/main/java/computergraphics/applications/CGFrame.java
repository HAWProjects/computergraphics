/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */

package main.java.computergraphics.applications;

import main.java.computergraphics.framework.AbstractCGFrame;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.ColorNode;
import main.java.computergraphics.scenegraph.CowNode;
import main.java.computergraphics.scenegraph.CubeMeshNode;
import main.java.computergraphics.scenegraph.CuboidNode;
import main.java.computergraphics.scenegraph.CylinderNode;
import main.java.computergraphics.scenegraph.ObjNode;
import main.java.computergraphics.scenegraph.ShaderNode;
import main.java.computergraphics.scenegraph.ShaderNode.ShaderType;
import main.java.computergraphics.scenegraph.TreeCylinderNode;
import main.java.computergraphics.scenegraph.transformation.RotationNode;
import main.java.computergraphics.scenegraph.transformation.ScaleNode;
import main.java.computergraphics.scenegraph.transformation.TranslationNode;
import main.java.computergraphics.scenegraph.SingleTriangleNode;
import main.java.computergraphics.scenegraph.SphereNode;

/**
 * Application for the first exercise.
 * 
 * @author Philipp Jenke
 */
public class CGFrame extends AbstractCGFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 4257130065274995543L;

  /**
   * Constructor.
   */
  public CGFrame(int timerInverval) {
    super(timerInverval);

    // Shader node does the lighting computation
    ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
    getRoot().addChild(shaderNode);
    
//    //translation 
//    TranslationNode translationNode = new TranslationNode(new Vector3(-1.0,-1.0,0.0));
//    shaderNode.addChild(translationNode);
//    
//    // ScaleNode double size
//    ScaleNode scaleNodeDoubleSize = new ScaleNode(new Vector3(2.0, 2.0, 2.0));
//    shaderNode.addChild(scaleNodeDoubleSize);
//
//    // RotationNode
//    RotationNode rotationNode = new RotationNode(new Vector3(0.0, 0.0, 1.0), 90);
//    shaderNode.addChild(rotationNode);
//
//    
//    // Simple triangle
//    SingleTriangleNode triangleNode = new SingleTriangleNode();
//    scaleNodeDoubleSize.addChild(triangleNode);
//
//    // Sphere
//    SphereNode sphereNode = new SphereNode(0.25, 20);
//    translationNode.addChild(sphereNode);
//    
//    //CuboidNode
//    CuboidNode cubeNode = new CuboidNode(0.2, 1.0, 0.5);
//    ColorNode colorNode = new ColorNode(0.0, 0.2, 1.0);
//    rotationNode.addChild(colorNode);
//    colorNode.addChild(cubeNode);
//    
//    //Cylinder
//    TranslationNode translationNodeCylinder = new TranslationNode(new Vector3(-1.0,-0.03,0.0));
//    translationNodeCylinder.addChild(new CylinderNode(0.2, 0.8, 20));
//    shaderNode.addChild(translationNodeCylinder);
    
    //Cube
    ColorNode colorNode = new ColorNode(0.8, 0.1, 0.8);
    shaderNode.addChild(colorNode);
    
    CubeMeshNode cmn = new CubeMeshNode(2);
    colorNode.addChild(cmn);
    
//    ObjNode objNode = new ObjNode("meshes/cow.obj");
//    colorNode.addChild(objNode);
    
//    CowNode cow = new CowNode();
//    colorNode.addChild(cow);
    
//    ObjNode objNodeMan = new ObjNode("meshes/baseman_shoes.obj");
//    colorNode.addChild(objNodeMan);
    
    
    
  }

  /*
   * (nicht-Javadoc)
   * 
   * @see computergrafik.framework.ComputergrafikFrame#timerTick()
   */
  @Override
  protected void timerTick() {
    System.out.println("Tick");
  }

  public void keyPressed(int keyCode) {
    System.out.println("Key pressed: " + (char) keyCode);
  }

  /**
   * Program entry point.
   */
  public static void main(String[] args) {
    // The timer ticks every 1000 ms.
    new CGFrame(1000);
  }
}
