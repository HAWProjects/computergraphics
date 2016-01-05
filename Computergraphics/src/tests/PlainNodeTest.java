package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.computergraphics.datastructures.Ray3D;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.PlainNode;

public class PlainNodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBerechneSchnittNegativ() {
		PlainNode pn = new PlainNode(new Vector3(), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		Ray3D ray = new Ray3D(new Vector3(0, 0, 1), new Vector3(1, 0, 0));
		assertNull(pn.berechneSchnitt(ray));
	}

	@Test
	public void testBerechneSchnittPositiv() {
		PlainNode pn = new PlainNode(new Vector3(), new Vector3(1, 0, 0), new Vector3(0, 1, 0));
		Ray3D ray = new Ray3D(new Vector3(0, 0, 1), new Vector3(0, 0, -1));
		Vector3 expected = new Vector3();
		assertEquals(expected, pn.berechneSchnitt(ray).point);
	}

}
