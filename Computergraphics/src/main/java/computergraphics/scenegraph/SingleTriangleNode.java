/**
 * Prof. Philipp Jenke
 * Hochschule für Angewandte Wissenschaften (HAW), Hamburg
 * 
 * Base framework for "WP Computergrafik".
 */
package main.java.computergraphics.scenegraph;

import com.jogamp.opengl.GL2;

/**
 * This node draws a simple triangle.
 * 
 * @author Philipp Jenke
 */
public class SingleTriangleNode extends AttributeNode {

  @Override
  public void drawGl(GL2 gl) {
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glNormal3f(0, 0, 1);
    // um die Farbe des Dreiecks zu aendern muessen die rgb Werte geaendert werden.
    // Eine Aenderung gilt bis zur naechsten Aenderung
    gl.glColor3d(1.0, 1.0, 1.0); 
    gl.glVertex3f(-0.5f, -0.5f, 0);
    gl.glNormal3f(0, 0, 1);
    gl.glColor3d(0.0, 1.0, 0.0);
    gl.glVertex3f(0.5f, -0.5f, 0);
    gl.glNormal3f(0, 0, 1);
    gl.glColor3d(0.0, 0.5, 1.0);
    gl.glVertex3f(0, 0.5f, 0);
    gl.glEnd();
  }
}
