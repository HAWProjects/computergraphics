package main.java.computergraphics.kurven;

import java.util.List;

import main.java.computergraphics.math.Vector3;

public class MonomKurve extends Kurve {

	@Override
	public Vector3 getValue(double t) {
		Vector3 ergebnis = new Vector3(0,0,0);
		for(int i = 0 ; i < this.getKontrollpunkte().size(); i++){
			ergebnis = ergebnis.add(getKontrollpunkte().get(i).multiply(Math.pow(t, i)));
		}
		return ergebnis;
	}

	@Override
	public Vector3 berechneTangente(double t) {
		Vector3 vec = new Vector3();
		List<Vector3> kontrollpunkte = getKontrollpunkte();
		//int n = kontrollpunkte.size()-1;//Hochzahl, Grad der Kurve
		for(int i = 0; i < kontrollpunkte.size(); i++){
			Vector3 temp = new Vector3();
			temp = kontrollpunkte.get(i).multiply(i*Math.pow(t, i-1));
			vec = vec.add(temp);
			
		}
		return vec;
	}

	public void interpolieren(Vector3 p1, Vector3 p2, Vector3 p3){
		Vector3 c0 = new Vector3();
		Vector3 c1 = new Vector3();
		Vector3 c2 = new Vector3();
		
		c0 = p1;
		
		c1 = p2.multiply(4.0);
		c1 = c1.subtract(p1.multiply(3.0));
		c1 = c1.subtract(p3);
		
		c2 = p3.subtract(p1);
		c2 = c2.subtract(c1);
		
		this.addKontrollpunkt(c0);
		this.addKontrollpunkt(c1);
		this.addKontrollpunkt(c2);		
	}



}
