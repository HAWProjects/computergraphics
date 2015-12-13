package main.java.computergraphics.kurven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.java.computergraphics.math.Vector3;

public abstract class Kurve {
	

	/**
	 * Key ist der Kontrollpunkt und Value die entsprechende Basisfunktion
	 * kontrollpunkte
	 */
	Map<Vector3,Double> kontrollpunkte = new HashMap<>();
	
	
	/**
	 * TODO
	 * @param kontrollpunkt
	 */
	public void addKonrtollpunkt(Vector3 kontrollpunkt, Double basisFunktion){
		// basisFunktion zwischen 0 & 1
		if(basisFunktion > 1){basisFunktion = 1.0;}
		if(basisFunktion < 0){basisFunktion = 0.0;}
		
		kontrollpunkte.put(kontrollpunkt, basisFunktion);		
	}
	
	/**
	 * TODO
	 * @param kontrollpunkt
	 */
	public void rentferneKontrollpunkt(Vector3 kontrollpunkt){
		kontrollpunkte.remove(kontrollpunkt);
	}
	
	public List<Vector3> getKontrollpunkte(){
		List<Vector3> list = new ArrayList<>();
		Set<Vector3> set = kontrollpunkte.keySet();
		list.addAll(set);
		return list;
	}
	
	
	/**
	 * liefert eine Liste von 
	 * @return list
	 */
	public List<Vector3> berechneKurven(){
		List<Vector3> list = new ArrayList<>();
		
		for(Map.Entry<Vector3, Double> entry: kontrollpunkte.entrySet()){
			Vector3 key = entry.getKey();
			double value = entry.getValue();		
			list.add(key.multiply(value));
		}
		
		return list;
	}
	
	/**
	 * Kurvengerad
	 * @return
	 */
	public int bestimmeKurvengerad(){
		return kontrollpunkte.size() -1;
	}
	
	public void parametrisierung(double t){
		
	}
	
	/**
	 * Liefert den Kurvenwert an der Position p
	 * @param p
	 * @return
	 */
	public abstract Vector3 getValue(double p);
	
	
}
