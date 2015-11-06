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
import main.java.computergraphics.datastructures.TriangleFacet;
import main.java.computergraphics.datastructures.Vertex;
import main.java.computergraphics.math.Vector3;

/**
 * @author abt434
 *
 */
public class CubeMeshNode extends Node {

	private int size;

	private HalfEdgeTriangleMesh mesh;

	private GL2 gl;

	private int idDisplayList;

	/**
	 * @param size
	 */
	public CubeMeshNode(int size) {
		this.size = size;
		mesh = buildCube();
	}

	private void initDisplaylist() {
		idDisplayList = gl.glGenLists(1);
		gl.glNewList(1, gl.GL_COMPILE);
		renderCube();
		gl.glEndList();

	}

	private HalfEdgeTriangleMesh buildCube() {

		HalfEdgeTriangleMesh mesh = new HalfEdgeTriangleMesh();
		Vertex v1 = new Vertex(new Vector3(0, 0, 0));
		Vertex v2 = new Vertex(new Vector3(0, 1, 0));
		Vertex v3 = new Vertex(new Vector3(1, 0, 0));
		Vertex v4 = new Vertex(new Vector3(1, 1, 0));

		Vertex v5 = new Vertex(new Vector3(0, 0, 1));
		Vertex v6 = new Vertex(new Vector3(0, 1, 1));
		Vertex v7 = new Vertex(new Vector3(1, 0, 1));
		Vertex v8 = new Vertex(new Vector3(1, 1, 1));

		mesh.addVertex(v1);
		mesh.addVertex(v2);
		mesh.addVertex(v3);
		mesh.addVertex(v4);
		mesh.addVertex(v5);
		mesh.addVertex(v6);
		mesh.addVertex(v7);
		mesh.addVertex(v8);

		mesh.addTriangle(0, 2, 1); //front
		mesh.addTriangle(2, 3, 1);
		mesh.addTriangle(1, 3, 5); //oben
		mesh.addTriangle(3, 7, 5);
		mesh.addTriangle(2, 6, 3); // seite rechts
		mesh.addTriangle(6, 7, 3);
		mesh.addTriangle(1, 5, 0); // seite links
		mesh.addTriangle(0, 5, 4);
		mesh.addTriangle(0, 2, 4); // unten
		mesh.addTriangle(4, 2, 6);
		mesh.addTriangle(6, 7, 5); // hinten

		mesh.computeTriangleNormals();

		return mesh;
	}

	@Override
	public void drawGl(GL2 gl) {
		if (idDisplayList == 0) {
			this.gl = gl;
			initDisplaylist();
		} else {
			gl.glCallList(idDisplayList);
		}
	}

	private void renderCube() {
		gl.glBegin(GL.GL_TRIANGLES);
		for (int i = 0; i < mesh.getNumberOfTriangles(); i++) {
			TriangleFacet facet = mesh.getFacet(i);
			gl.glNormal3d(facet.getNormal().get(0), facet.getNormal().get(1), facet.getNormal().get(2));
			Vector3 vec1 = mesh.getFacet(i).getHalfEdge().getStartVertex().getPosition();
			Vector3 vec2 = mesh.getFacet(i).getHalfEdge().getNext().getStartVertex().getPosition();
			Vector3 vec3 = mesh.getFacet(i).getHalfEdge().getNext().getNext().getStartVertex().getPosition();

			gl.glVertex3d(vec1.get(0), vec1.get(1), vec1.get(2));
			gl.glVertex3d(vec2.get(0), vec2.get(1), vec2.get(2));
			gl.glVertex3d(vec3.get(0), vec3.get(1), vec3.get(2));
		}
		gl.glEnd();
	}

}
