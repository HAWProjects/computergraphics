package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.computergraphics.datastructures.Ray3D;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.SphereNode;

public class SphereNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBerechneSchnittNegativ() {
		SphereNode sn = new SphereNode(.5, 10, new Vector3());
		Ray3D ray = new Ray3D(new Vector3(0, 0, 1), new Vector3(1, 0, 0));
		assertNull(sn.berechneSchnitt(ray));
	}

	@Test
	public void testBerechneSchnittPositiv() {
		SphereNode sn = new SphereNode(.5, 10, new Vector3());
		Ray3D ray = new Ray3D(new Vector3(0, 0, 1), new Vector3(0, 0, -1));
		Vector3 expected = new Vector3(0, 0, .5);
		Vector3 expectedNormal = new Vector3(0, 0, 1);
		assertEquals(expected, sn.berechneSchnitt(ray).point);
		assertEquals(expectedNormal, sn.berechneSchnitt(ray).normal);
	}

}
