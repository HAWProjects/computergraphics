package main.java.computergraphics.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.computergraphics.Constants;
import main.java.computergraphics.math.Vector3;

public class MarchingCubesAlgorithm {

	private HalfEdgeTriangleMesh mesh;

	public MarchingCubesAlgorithm() {
		this.mesh = new HalfEdgeTriangleMesh();
	}

	private void createTriangle(List<Vector3> points, List<Double> values) {
		int caseIndex;
		double t = 0.5; //Approximation des Eckpunktes f�r den Anfang
		List<Integer> bList = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			if (values.get(i) > 0) {
				bList.add(1);
			} else {
				bList.add(0);
			}
		}
		caseIndex = bList.get(0) * 1 + bList.get(1) * 2 + bList.get(2) * 4 + bList.get(3) * 8 + bList.get(4) * 16
				+ bList.get(5) * 32 + bList.get(6) * 64 + bList.get(7) * 128;
		//CaseIndex benutzen f�r LookUp Tabelle: ab Index + 15

		int endIndex = (caseIndex + 1) * 15 - 1;
		List<Integer> edgeList = new ArrayList<>();
		for (int index = caseIndex * 15; index < endIndex; index++) {
			if (Constants.FACES[index] != -1) {
				edgeList.add(Constants.FACES[index]);
			}
		}
		Map<Integer, Vector3> tempMap = new HashMap();
		int triangleCount = edgeList.size()/3;
		edgeList.forEach((edge) -> {
			while (!(edgeList.isEmpty())) {
				for (int i = 0; i < 3; i++) {
					int[] p = getIndeces(edgeList.get(i));

					Vector3 pos = (points.get(p[0]).multiply(1 - t)).add(points.get(p[1]).multiply(t));
					tempMap.put(edgeList.get(i), pos);
				}
				edgeList.remove(0);
				edgeList.remove(1);
				edgeList.remove(2);
			}
		});
		//Knoten geben lassen
//		mesh.addTriangle(vertexIndex1, vertexIndex2, vertexIndex3);



	}

	private int[] getIndeces(int edge) {
		int points[] = new int[2];
		switch (edge) {
		case 0:
			points[0] = 0;
			points[1] = 1;
			break;
		case 1:
			points[0] = 1;
			points[1] = 2;
			break;
		case 2:
			points[0] = 3;
			points[1] = 2;
			break;
		case 3:
			points[0] = 0;
			points[1] = 3;
			break;
		case 4:
			points[0] = 4;
			points[1] = 5;
			break;
		case 5:
			points[0] = 5;
			points[1] = 6;
			break;
		case 6:
			points[0] = 7;
			points[1] = 6;
			break;
		case 7:
			points[0] = 4;
			points[1] = 7;
			break;
		case 8:
			points[0] = 0;
			points[1] = 4;
			break;
		case 9:
			points[0] = 1;
			points[1] = 5;
			break;
		case 10:
			points[0] = 3;
			points[1] = 7;
			break;
		case 11:
			points[0] = 2;
			points[1] = 6;
			break;
		}
		return points;
	}
}