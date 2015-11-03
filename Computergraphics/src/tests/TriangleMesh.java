package tests;

import static org.junit.Assert.*;
import main.java.computergraphics.datastructures.HalfEdgeTriangleMesh;
import main.java.computergraphics.datastructures.Vertex;
import main.java.computergraphics.math.Vector3;

import org.junit.Test;

public class TriangleMesh {
	
	HalfEdgeTriangleMesh mesh = new HalfEdgeTriangleMesh();
	Vertex v1 = new Vertex(new Vector3(0.0, 0.1, 0.2));
	Vertex v2 = new Vertex(new Vector3(1.0, 0.5, 0.2));
	Vertex v3 = new Vertex(new Vector3(5.0, 0.1, 0.2));
	Vertex v4 = new Vertex(new Vector3(7.0, 0.1, 0.2));
	Vertex v5 = new Vertex(new Vector3(0.8, 2.1, 0.2));
	Vertex v6 = new Vertex(new Vector3(0.0, 9.1, 4.2));

	@Test
	public void testAddVertex() {
		mesh.addVertex(v1);
		mesh.addVertex(v2);
		mesh.addVertex(v3);
		mesh.addVertex(v4);
		
		assertTrue(4 == mesh.getNumberOfVertices());
	}
	
	@Test
	public void testClear(){
		mesh.addVertex(v1);
		mesh.addVertex(v2);
		mesh.addVertex(v3);
		mesh.addVertex(v4);
		mesh.addTriangle(1, 2, 3);
		mesh.clear();
		assertTrue(0 == mesh.getNumberOfVertices() && 0 == mesh.getNumberOfTriangles());
	}

	@Test
	public void testAddTriangleCountOfTriangle(){
		mesh.addVertex(v1);
		mesh.addVertex(v2);
		mesh.addVertex(v3);
		mesh.addVertex(v4);
		mesh.addVertex(v5);
		mesh.addVertex(v6);
		
		mesh.addTriangle(1, 2, 3);
		mesh.addTriangle(0,5,2);
		
		assertTrue(2 == mesh.getNumberOfTriangles());		
	}
	

}
