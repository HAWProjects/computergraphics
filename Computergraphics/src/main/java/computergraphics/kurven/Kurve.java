package main.java.computergraphics.kurven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.computergraphics.math.Vector3;

public abstract class Kurve {
	

	/**
	 * Key ist der Kontrollpunkt und Value die entsprechende Basisfunktion
	 * kontrollpunkte
	 */
	private Map<Vector3,Double> kontrollpunkteMap = new HashMap<>();
	
	private List<Vector3> kontrollpunkte = new ArrayList<>();
	
	
	/**
	 * TODO
	 * @param kontrollpunkt
	 */
	public void addKonrtollpunkt(Vector3 kontrollpunkt){
		kontrollpunkte.add(kontrollpunkt);		
	}
	
	/**
	 * TODO
	 * @param kontrollpunkt
	 */
	public void entferneKontrollpunkt(Vector3 kontrollpunkt){
		kontrollpunkte.remove(kontrollpunkt);
	}
	
	public List<Vector3> getKontrollpunkte(){

		return kontrollpunkte;
	}
	
	
	/**
	 * liefert eine Liste von 
	 * @return list
	 */
	public List<Vector3> berechneKurven(){
		
		List<Vector3> list = new ArrayList<>();		
		for(Map.Entry<Vector3, Double> entry: kontrollpunkteMap.entrySet()){
			Vector3 key = entry.getKey();
			double value = entry.getValue();		
			list.add(key.multiply(value));
		}
		
		return list;
	}
	
	/**
	 * liefert die Basisfunktion zu dem Kontrollpunkt
	 * @param kontrollpunkt
	 * @return
	 */
	public Double getBasisfunktion(Vector3 kontrollpunkt){
		
		return kontrollpunkteMap.get(kontrollpunkt);
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
	
	
	/**
	 * speichert die Sasisfunktionen zu den Kontrollpunkten in einer Map
	 */
	public abstract void aktualisiereKontrollpunktmap();
	
	
	
	
}
