package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.computergraphics.datastructures.IntersectionResult;
import main.java.computergraphics.datastructures.Ray3D;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.PlainNode;
import main.java.computergraphics.scenegraph.SphereNode;

public class Ray3Dtest {
	
	private Ray3D ray;
	private Ray3D ray2;
	private PlainNode plain;
	private SphereNode sphere;
	
	@Before
	public void setUp() throws Exception {
		ray = new Ray3D(new Vector3(5.0, 1.0, 0.0), new Vector3(1.0, -3.0, 0.0));
		plain = new PlainNode(new Vector3(0.0, 0.0, 0.0), new Vector3(5.0, 0.0, 0.0), new Vector3(0.0, 0.0, 2.0));
		ray2 = new Ray3D(new Vector3(0.0,5.0,0.0),new Vector3(0.0,-1.0,0.0));
		sphere = new SphereNode(10.0, 20);
	}

	@Test
	public void testBerechneSchnittEbene() {
		Vector3 expectedResult = new Vector3(4.66666666, 2.0, 0.0);
		IntersectionResult actualResult = ray.berechneSchnittEbene(plain);
		
		assertEquals(expectedResult.get(0), actualResult.point.get(0), 0.00001);
		assertEquals(expectedResult.get(1), actualResult.point.get(1), 0.00001);
		assertEquals(expectedResult.get(2), actualResult.point.get(2), 0.00001);
	}
	
	@Test
	public void testBerechneSchnittKugel(){
		Vector3 expectedResult = new Vector3();
		IntersectionResult actualResult = ray2.berechneSchnittKugel(sphere);
	}
}
