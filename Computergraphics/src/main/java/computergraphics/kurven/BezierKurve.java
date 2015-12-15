package main.java.computergraphics.kurven;

import main.java.computergraphics.math.MathHelpers;
import main.java.computergraphics.math.Vector3;

public class BezierKurve extends Kurve {

	@Override
	public double berechneBasisFunktion(Vector3 kontrollpunkt) {
		// TODO Auto-generated method stub
		return 0;
	}

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





}
