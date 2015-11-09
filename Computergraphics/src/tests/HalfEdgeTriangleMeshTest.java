package tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.computergraphics.datastructures.HalfEdge;
import main.java.computergraphics.datastructures.HalfEdgeTriangleMesh;
import main.java.computergraphics.datastructures.ObjIO;
import main.java.computergraphics.datastructures.Vertex;
import main.java.computergraphics.math.Vector3;

public class HalfEdgeTriangleMeshTest {
	
	private HalfEdgeTriangleMesh mesh;
	private String path = "meshes/cube.obj";
	private HalfEdgeTriangleMesh mesh2;
	
	@Before
	public void setUp() throws Exception {
		ObjIO obj = new ObjIO();
		mesh = new HalfEdgeTriangleMesh();
		obj.einlesen(path, mesh);
		mesh.computeTriangleNormals();
		
		mesh2 = new HalfEdgeTriangleMesh();
		
	}
	
	@Test
	public void testNumberOfVerticies() {
		assertEquals(mesh.getNumberOfVertices(), 8);
		mesh.clear();
		assertEquals(mesh.getNumberOfVertices(), 0);
	}
	
	@Test
	public void testNumberOfFaces() {
		assertEquals(mesh.getNumberOfTriangles(), 12);
		mesh.clear();
		assertEquals(mesh.getNumberOfTriangles(), 0);
	}
	
	@Test
	public void testAddVertex() {
		int count = 50;
		for(int i = 0; i < count; i++) {
			mesh2.addVertex(new Vertex(new Vector3(0.1, 0.1, 0.1)));
		}
		assertEquals(mesh2.getNumberOfVertices(), 50);
	}
	
	@Test
	public void testNormal() {
		assertEquals(new Vector3(0, 0, -1), mesh.getFacet(0).getNormal());
		assertEquals(new Vector3(0, 0, -1), mesh.getFacet(1).getNormal());
		assertEquals(new Vector3(1, 0, 0), mesh.getFacet(3).getNormal());
		assertEquals(new Vector3(0, 1, 0), mesh.getFacet(8).getNormal());
	}
	
	@Test
	public void testNextEdge() {
		for(int i = 0; i < mesh.getNumberOfTriangles(); i++) {			
			HalfEdge edgeOne = mesh.getFacet(i).getHalfEdge();
			HalfEdge edgeTwo = edgeOne.getNext().getNext().getNext();
			assertTrue(edgeOne == edgeTwo);
		}
	}
	
}
