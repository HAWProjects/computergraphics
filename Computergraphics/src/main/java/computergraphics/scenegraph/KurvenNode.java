package main.java.computergraphics.scenegraph;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

import main.java.computergraphics.kurven.Kurve;
import main.java.computergraphics.math.Vector3;

public class KurvenNode extends Node {
	
	Kurve kurve;
	List<Vector3> punkte;
	private int ANZAHL_TEILSTUECKE = 20;
	
	
	public KurvenNode(Kurve kurve){
		this.kurve = kurve;
		this.punkte = new ArrayList<>();
		calculateValues();
	}
	
	private void calculateValues(){
		double t = 0; 
		punkte.add(kurve.getValue(t));
		for(int i = 0; i < (ANZAHL_TEILSTUECKE-1); i++){
			t += 1/ANZAHL_TEILSTUECKE;
			punkte.add(kurve.getValue(t));
		}
	}

	@Override
	public void drawGl(GL2 gl) {
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(1, 0, 0);
		for(int i = 0; i < punkte.size() -1 ; i++){
			Vector3 v1 = punkte.get(i);
			Vector3 v2 = punkte.get(i+1);
			gl.glVertex3d(v1.get(0), v1.get(1), v1.get(2));
			gl.glVertex3d(v2.get(0), v2.get(1), v2.get(2));
		}
		
		Vector3 v1 = punkte.get(ANZAHL_TEILSTUECKE -2);
		Vector3 v2 = punkte.get(ANZAHL_TEILSTUECKE -1);
		
		gl.glVertex3d(v1.get(0), v1.get(1), v1.get(2));
		gl.glVertex3d(v2.get(0), v2.get(1), v2.get(2));
	
		gl.glEnd();
	}

}
