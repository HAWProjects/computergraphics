package main.java.computergraphics.datastructures;

import main.java.computergraphics.math.Vector3;

public class Leuchtquelle {
	private Vector3 pos;
	private Vector3 color;
	
	public Leuchtquelle(Vector3 pos){
		this.pos = pos;
		this.color = new Vector3(0.0, 0.0, 0.0);
	}

	public Vector3 getPos() {
		return pos;
	}
	
	public Vector3 getColor(){
		return color;
	}
}
