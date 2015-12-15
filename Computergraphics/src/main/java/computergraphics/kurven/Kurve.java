package main.java.computergraphics.kurven;

import java.util.ArrayList;
import java.util.List;

import main.java.computergraphics.math.Vector3;

public abstract class Kurve {
	

	/**
	 * Key ist der Kontrollpunkt und Value die entsprechende Basisfunktion
	 * kontrollpunkte
	 */
	
	private List<Vector3> kontrollpunkte;
	
	public Kurve(){
		kontrollpunkte = new ArrayList<>();
	}
	
	
	/**
	 * TODO
	 * @param kontrollpunkt
	 */
	public void addKontrollpunkt(Vector3 kontrollpunkt){
		kontrollpunkte.add(kontrollpunkt);		
	}
	
	/**
	 * TODO
	 * @param kontrollpunkt
	 */
	public void entferneKontrollpunkt(Vector3 kontrollpunkt){
		kontrollpunkte.remove(kontrollpunkt);
	}
	
	/**
	 * @return
	 */
	public List<Vector3> getKontrollpunkte(){

		return kontrollpunkte;
	}
	
	/**
	 * @param kontrollpunkteList
	 */
	public void addKontrollpunkte(List<Vector3> kontrollpunkteList){
		kontrollpunkte.addAll(kontrollpunkteList);
	}
	
	
	/**
	 * liefert eine Liste von 
	 * @return list
	 */
	public List<Vector3> berechneKurven(){
		
		List<Vector3> list = new ArrayList<>();		
	
		
		return list;
	}
	

	
	/**
	 * Kurvengerad
	 * @return
	 */
	public int bestimmeKurvengerad(){
		return kontrollpunkte.size() -1;
	}
	
	/**
	 * Liefert den Kurvenwert an der Position t
	 * @param p
	 * @return
	 */
	public abstract Vector3 getValue(double t);
	
	
	/**
	 * gibt die Tangente an der Position t zur�ck
	 * @param kontrollpunkt
	 * @return
	 */
	public abstract Vector3 berechneTangente(double t);
	
	/**
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public abstract void interpolieren(Vector3 p1, Vector3 p2, Vector3 p3);	
	
}
