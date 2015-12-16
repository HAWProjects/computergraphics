package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.computergraphics.kurven.BezierKurve;
import main.java.computergraphics.kurven.MonomKurve;
import main.java.computergraphics.math.Vector3;

public class KurvenTest {

	MonomKurve mK;
	BezierKurve bK;

	Vector3 p1;
	Vector3 p2;
	Vector3 p3;

	@Before
	public void setUp() throws Exception {
		mK = new MonomKurve();
		bK = new BezierKurve();

		p1 = new Vector3(0, 0, 0);
		p2 = new Vector3(0.2, 0.2, 0);
		p3 = new Vector3(0.2, 0.5, 0.2);
		bK.addKontrollpunkt(p1);
		bK.addKontrollpunkt(p2);
		bK.addKontrollpunkt(p3);
		mK.interpolieren(p1, p2, p3);
	}

	@Test
	public void testMonom() {
		assertEquals(p3, mK.getValue(1.0));
		assertEquals(p2, mK.getValue(0.5));
		assertEquals(p1, mK.getValue(0.0));
	}

	@Test
	public void testBezier() {
		assertTrue(bK.getKontrollpunkte().contains(p1));
		assertTrue(bK.getKontrollpunkte().contains(p3));
	}

}
