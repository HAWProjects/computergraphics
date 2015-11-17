package tests;

import java.util.ArrayList;
import java.util.List;

import main.java.computergraphics.datastructures.Vertex;
import main.java.computergraphics.math.Vector3;

public class Test {

	public static void main(String[] args) {
		List<Vertex> vList = new ArrayList<>();
		vList.add(new Vertex(new Vector3()));
		vList.add(new Vertex(new Vector3()));
		vList.add(new Vertex(new Vector3()));
		System.out.println(vList.size());
		
		List<Vertex> tempvList = new ArrayList<>();
		tempvList.add(new Vertex(new Vector3()));
		tempvList.add(new Vertex(new Vector3()));
		System.out.println(tempvList.size());
		
		vList = tempvList;
		
		System.out.println(vList.size());

	}

}
