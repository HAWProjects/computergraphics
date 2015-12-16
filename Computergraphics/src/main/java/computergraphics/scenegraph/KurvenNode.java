package main.java.computergraphics.scenegraph;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

import main.java.computergraphics.kurven.Kurve;
import main.java.computergraphics.math.Vector3;

public class KurvenNode extends Node {
	
	private Kurve kurve;
	private List<Vector3> punkte;
	private double ANZAHL_TEILSTUECKE = 20;
	private double tPos;
	
	
	
	public KurvenNode(Kurve kurve){
		this.kurve = kurve;
		this.punkte = new ArrayList<>();
		this.tPos = 0.75;
		calculateValues();
	}
	
	private void calculateValues(){
		double t = 0.0; 
		punkte.add(kurve.getValue(t));
		for(int i = 0; i < (ANZAHL_TEILSTUECKE-1); i++){

			t += 1/ANZAHL_TEILSTUECKE;
			punkte.add(kurve.getValue(t));
		}
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3d(1.0, 0.0, 0.0);
		for(int i = 0; i < punkte.size() -1 ; i++){
			Vector3 v1 = punkte.get(i);
			Vector3 v2 = punkte.get(i+1);
			gl.glVertex3d(v1.get(0), v1.get(1), v1.get(2));
			gl.glVertex3d(v2.get(0), v2.get(1), v2.get(2));
		}
		
		Vector3 v1 = punkte.get((int)ANZAHL_TEILSTUECKE -2);
		Vector3 v2 = punkte.get((int)ANZAHL_TEILSTUECKE -1);
		
		gl.glVertex3d(v1.get(0), v1.get(1), v1.get(2));
		gl.glVertex3d(v2.get(0), v2.get(1), v2.get(2));
		
		gl.glColor3d(0.0, 0.0, 1.0);
		Vector3 tan1 = kurve.getValue(tPos);
		Vector3 tan2 = tan1.add(kurve.berechneTangente(tPos));
		gl.glVertex3d(tan1.get(0), tan1.get(1), tan1.get(2));
		gl.glVertex3d(tan2.get(0), tan2.get(1), tan2.get(2));
	
		gl.glEnd();
	}

}
