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
import main.java.computergraphics.math.Vector3;


/**
 * @author Robert
 * @date 09.11.2015
 */
public class CowNode extends Node {
	
	/**
	 * mesh
	 */
	private HalfEdgeTriangleMesh mesh;
	
	/**
	 * idDisplayList
	 */
	private int idDisplayList;
	
	public CowNode(){
		this("meshes/cow.obj");
	}
	
	/**
	 * Constructor
	 * @param path
	 */
	public CowNode(String path){
		ObjIO obj = new ObjIO();
		mesh = new HalfEdgeTriangleMesh();
		obj.einlesen(path, mesh);
		mesh.computeTriangleNormals();
	}
	

	
	/**
	 * initialize the Displaylist
	 * @param gl
	 */
	private void initDisplaylist(GL2 gl) {
		idDisplayList = gl.glGenLists(1);
		gl.glNewList(1, GL2.GL_COMPILE);
		buildMesh(gl);
		gl.glEndList();

	}
	
	
	
	/**
	 * method build the mesh for displaylist
	 * @param gl
	 */
	private void buildMesh(GL2 gl) {
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
		
	

	/* (non-Javadoc)
	 * @see main.java.computergraphics.scenegraph.Node#drawGl(com.jogamp.opengl.GL2)
	 */
	@Override
	public void drawGl(GL2 gl) {
		if (idDisplayList == 0) {
			initDisplaylist(gl);
		} else {
			gl.glCallList(idDisplayList);
		}
	}
	
	

	/**
	 * @return
	 */
	public HalfEdgeTriangleMesh getMesh() {
		return mesh;
	}
	

	public void setIdDisplaylist(int id){
		idDisplayList = id;
	}
}
