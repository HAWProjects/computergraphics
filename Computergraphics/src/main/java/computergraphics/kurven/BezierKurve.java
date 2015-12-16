package main.java.computergraphics.kurven;

import java.util.List;

import main.java.computergraphics.math.MathHelpers;
import main.java.computergraphics.math.Vector3;

public class BezierKurve extends Kurve {

	@Override
	public Vector3 getValue(double t) {
		Vector3 ergebnis = new Vector3();
		int size = getKontrollpunkte().size();
		for(int i = 0; i < size; i++){
			Vector3 temp = getKontrollpunkte().get(i);
			temp = temp.multiply(MathHelpers.over(size, i) * Math.pow(t, i) * Math.pow(1.0 - t, size-1));
			ergebnis = ergebnis.add(temp);
		}
		
		return ergebnis;
	}

	@Override
	public Vector3 berechneTangente(double t) {
		Vector3 vec = new Vector3();
		int size = getKontrollpunkte().size();
		List<Vector3> kontrollpunkte = getKontrollpunkte();
		for(int i = 0; i<(size-1); i++){
			//Q berechnen: n*(P1-P0)
			Vector3 q = kontrollpunkte.get(i+1).subtract(kontrollpunkte.get(i));
			//TODO:Tangentenberechnung
			//Vector3 temp = ?;
			vec = vec.add(temp);
		}
		return null;
	}

	@Override
	public void interpolieren(Vector3 p1, Vector3 p2, Vector3 p3) {
		// TODO Auto-generated method stub		
	}
}
