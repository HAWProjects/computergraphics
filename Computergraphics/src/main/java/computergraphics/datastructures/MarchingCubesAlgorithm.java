package main.java.computergraphics.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.computergraphics.Constants;
import main.java.computergraphics.math.Vector3;

/**
 * @author abt434
 *
 */
public class MarchingCubesAlgorithm {

	private HalfEdgeTriangleMesh mesh;
	private double TAU = 0.0;
	private double RESOLUTION = 25.0;
	private double SIZE = 4/RESOLUTION;//Aufbau des WÃ¼rfels, Abstand zwischen den Punkten

	public MarchingCubesAlgorithm() {
		this.mesh = new HalfEdgeTriangleMesh();
	}
	
	public void createObject(ImplicitFunctionI implFunct) {
        double x = -2;
        double y = -2;
        double z = -2;

        while(y <= 2) {
            while (x <= 2) {
                while(z <= 2) {
                    List<Vector3> points = new ArrayList<>();

                    points.add(new Vector3(x, y, z));
                    points.add(new Vector3(x + SIZE, y, z));
                    points.add(new Vector3(x + SIZE, y + SIZE, z));
                    points.add(new Vector3(x, y + SIZE, z));
                    points.add(new Vector3(x, y, z+ SIZE));
                    points.add(new Vector3(x + SIZE, y, z+ SIZE));
                    points.add(new Vector3(x + SIZE, y + SIZE, z+ SIZE));
                    points.add(new Vector3(x, y + SIZE, z+ SIZE));

                    List<Double> values = implFunct.calculateValues(points);
                    createTriangle(points, values);
                    z += SIZE;
                }
                x += SIZE;
                z = -2;
            }
            y += SIZE;
            z = -2;
            x = -2;
        }
    }
	
	/**
	 * Dreieck in einem Würfel erzeugen
	 * @param points
	 * @param values
	 */
 private void createTriangle(List<Vector3> points, List<Double> values) {
		int caseIndex;
//		double t = 0.5; // Approximation des Eckpunktes fuer den Anfang
		List<Integer> bList = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			if (values.get(i) > TAU) {
				bList.add(1);
			} else {
				bList.add(0);
			}
		}
		caseIndex = bList.get(0) * 1 + bList.get(1) * 2 + bList.get(2) * 4 + bList.get(3) * 8 + bList.get(4) * 16
				+ bList.get(5) * 32 + bList.get(6) * 64 + bList.get(7) * 128;
		// CaseIndex benutzen fuer LookUp Tabelle: ab Index + 15

		int endIndex = (caseIndex + 1) * 15 - 1;
		List<Integer> edgeList = new ArrayList<>();
		for (int index = caseIndex * 15; index < endIndex; index++) {
			if (Constants.FACES[index] != -1) {
				edgeList.add(Constants.FACES[index]);
			}
		}
//		System.out.println("davor"+edgeList.size());
		while (!(edgeList.isEmpty())) {
			List<Integer>indexList = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				int[] indeces = getIndeces(edgeList.get(i));
				// Knoten hinzufuegen
				int v1 = indeces[0];
				int v2 = indeces[1];
				double t = (TAU - values.get(v1)) / (values.get(v2)-values.get(v1));
//				double t = 0.5;
				Vector3 p = (points.get(indeces[0]).multiply(1 - t)).add(points.get(indeces[1]).multiply(t));
				
				// Falls vertex mit gleicher position schon da? -> Verwenden, sonst neuen bauen
				Vertex v = new Vertex(p);
				v.setColor(new Vector3(1.0,0.0,0.0));
				if(mesh.getvList().contains(v)){
					int index = mesh.getvList().indexOf(v);
					indexList.add(index);
				}else{
					int index = mesh.addVertex(v);
					indexList.add(index);
				}
			}
			// knoten hinzufuegen plus dreieck
			mesh.addTriangle(indexList.get(0), indexList.get(1), indexList.get(2));
			edgeList.remove(0);
			edgeList.remove(0);
			edgeList.remove(0);
		}

	}

	// Punkte zu der Kante holen
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

	public HalfEdgeTriangleMesh getMesh() {
		return mesh;
	}
}
