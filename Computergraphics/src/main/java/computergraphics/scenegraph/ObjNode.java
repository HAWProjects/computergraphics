/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 2, Aufgabe 3
*/
package main.java.computergraphics.scenegraph;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

import main.java.computergraphics.datastructures.HalfEdgeTriangleMesh;
import main.java.computergraphics.datastructures.ObjIO;
import main.java.computergraphics.datastructures.TriangleFacet;
import main.java.computergraphics.datastructures.Vertex;
import main.java.computergraphics.math.Vector3;

/**
 * @author abt434
 *
 */
public class ObjNode extends Node {

	private HalfEdgeTriangleMesh mesh;

	public ObjNode(String path) {
		ObjIO obj = new ObjIO();
		mesh = new HalfEdgeTriangleMesh();
		obj.einlesen(path, mesh);
		mesh.computeAllNormals();
	}

	/* (non-Javadoc)
	 * @see main.java.computergraphics.scenegraph.Node#drawGl(com.jogamp.opengl.GL2)
	 */
	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL.GL_TRIANGLES);

		
		for (int i = 0; i < mesh.getNumberOfTriangles(); i++) {
			TriangleFacet temp = this.mesh.getFacet(i);
//			gl.glNormal3d(temp.getNormal().get(0), temp.getNormal().get(1), temp.getNormal().get(2));
			Vertex v1 = mesh.getFacet(i).getHalfEdge().getStartVertex();
			Vertex v2 = mesh.getFacet(i).getHalfEdge().getNext().getStartVertex();
			Vertex v3 = mesh.getFacet(i).getHalfEdge().getNext().getNext().getStartVertex();
			double r = v1.getColor().get(0);
			double g = v2.getColor().get(1);
			double b = v3.getColor().get(2);
			gl.glColor3d(r, g, b);
		
			Vector3 vec1 = v1.getPosition();
			Vector3 vec2 = v2.getPosition();
			Vector3 vec3 = v3.getPosition();
			
			//Vertex normnale
			gl.glNormal3d(v1.getNormal().get(0), v1.getNormal().get(1), v1.getNormal().get(2));
			gl.glVertex3d(vec1.get(0), vec1.get(1), vec1.get(2));
			gl.glNormal3d(v2.getNormal().get(0), v2.getNormal().get(1), v2.getNormal().get(2));
			gl.glVertex3d(vec2.get(0), vec2.get(1), vec2.get(2));
			gl.glNormal3d(v3.getNormal().get(0), v3.getNormal().get(1), v3.getNormal().get(2));
			gl.glVertex3d(vec3.get(0), vec3.get(1), vec3.get(2));
	}

		gl.glEnd();
	}
	
	public HalfEdgeTriangleMesh getMesh(){
		return mesh;
	}
}


