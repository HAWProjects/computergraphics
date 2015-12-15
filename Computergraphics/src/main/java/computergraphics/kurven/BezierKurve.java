package main.java.computergraphics.kurven;

import main.java.computergraphics.math.MathHelpers;
import main.java.computergraphics.math.Vector3;

public class BezierKurve extends Kurve {

	@Override
	public Vector3 getValue(double t) {
		Vector3 ergebnis = new Vector3();
		int n = getKontrollpunkte().size();
		for(int i = 0; i < n; i++){
			Vector3 temp = getKontrollpunkte().get(i);
			temp = temp.multiply(MathHelpers.over(n, i) * Math.pow(t, i) * Math.pow(1.0 - t, n-1));
			ergebnis = ergebnis.add(temp);
		}
		
		return ergebnis;
	}

	@Override
	public Vector3 berechneTangente(double t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void interpolieren(Vector3 p1, Vector3 p2, Vector3 p3) {
		// TODO Auto-generated method stub		
	}
	
	





}
