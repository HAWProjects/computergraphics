/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 2, Aufgabe 1
*/
package main.java.computergraphics.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import main.java.computergraphics.math.Vector3;

/**
 * @author Robert Scheffel, Jennifer Momsen
 * 
 */
public class HalfEdgeTriangleMesh implements ITriangleMesh {

	private List<Vertex> vList;
	private List<HalfEdge> hEList;
	private List<TriangleFacet> tFList;

	/**
	 * Constructor
	 */
	public HalfEdgeTriangleMesh() {
		this.vList = new ArrayList<>();
		this.hEList = new ArrayList<>();
		this.tFList = new ArrayList<>();
	}

	@Override
	public void addTriangle(int vertexIndex1, int vertexIndex2, int vertexIndex3) {
		TriangleFacet tf = new TriangleFacet();

		List<Vertex> tempVList = new ArrayList<>(); // vorbereitung um Startvertex und n�chste Halbkante zu setzen
		tempVList.add(vList.get(vertexIndex1));
		tempVList.add(vList.get(vertexIndex2));
		tempVList.add(vList.get(vertexIndex3));

		List<HalfEdge> tempHeList = new ArrayList<>(); // vorbereitung um Startvertex und n�chste Halbkante zu setzen
		for (int i = 0; i < 3; i++) {
			tempHeList.add(new HalfEdge());
		}

		for (int i = 0; i < 3; i++) {
			tempHeList.get(i).setStartVertex(tempVList.get(i));
			tempVList.get(i).setHalfEgde(tempHeList.get(i));
			tempHeList.get(i).setFacet(tf);
			tempHeList.get(i).setNext(tempHeList.get((i + 1) % 3));
			hEList.add(tempHeList.get(i));
		}

		tf.setHalfEdge(tempHeList.get(0)); // halbkante setzen

		tFList.add(tf); // Facette zur Liste hinzuf�gen
		
		
		calculateOppositeHalfEdge(tf);
		
	}



	/**
	 * calculates the opposite HalfEdge and adds it to the Halfedge
	 */
	private void calculateOppositeHalfEdge(TriangleFacet tf) {
		HalfEdge he = tf.getHalfEdge();
		List<HalfEdge> tempHEList = new ArrayList<>();
		tempHEList.add(he);
		tempHEList.add(he.getNext());
		tempHEList.add(he.getNext().getNext());
		for (HalfEdge halfEdge : tempHEList) {
			for (Iterator<HalfEdge> itHalfEdge = hEList.iterator(); itHalfEdge.hasNext();) {
				HalfEdge hE = itHalfEdge.next();

				Vertex startVertex = halfEdge.getStartVertex();
				Vertex endVertex = halfEdge.getNext().getStartVertex();

				Vertex startVertexOpposite = hE.getStartVertex();
				Vertex endVertexOpposite = hE.getNext().getStartVertex();

				if (startVertex.equals(endVertexOpposite) && endVertex.equals(startVertexOpposite)) {
					halfEdge.setOpposite(hE);
					hE.setOpposite(halfEdge);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.computergraphics.datastructures.ITriangleMesh#addVertex(main.
	 * java.computergraphics.datastructures.Vertex)
	 */
	@Override
	public int addVertex(Vertex v) {
		vList.add(v);
		return vList.indexOf(v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#
	 * getNumberOfTriangles()
	 */
	@Override
	public int getNumberOfTriangles() {
		return tFList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#
	 * getNumberOfVertices()
	 */
	@Override
	public int getNumberOfVertices() {
		return vList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.computergraphics.datastructures.ITriangleMesh#getVertex(int)
	 */
	@Override
	public Vertex getVertex(int index) {
		return vList.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.computergraphics.datastructures.ITriangleMesh#getFacet(int)
	 */
	@Override
	public TriangleFacet getFacet(int facetIndex) {
		return tFList.get(facetIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#clear()
	 */
	@Override
	public void clear() {
		tFList.clear();
		vList.clear();
		hEList.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#
	 * computeTriangleNormals()
	 */
	@Override
	public void computeTriangleNormals() {
		for (ListIterator<TriangleFacet> itTF = tFList.listIterator(); itTF.hasNext();) {
			TriangleFacet tempFacet = itTF.next();
			// berechne Normale
			Vector3 v1, v2, v3;
			v1 = tempFacet.getHalfEdge().getStartVertex().getPosition();
			v2 = tempFacet.getHalfEdge().getNext().getStartVertex().getPosition();
			v3 = tempFacet.getHalfEdge().getNext().getNext().getStartVertex().getPosition();

			// kreuzprodukt
			Vector3 vNormal = (v2.subtract(v1)).cross((v3.subtract(v1)));
			// normieren
			vNormal = vNormal.getNormalized();
			// setze normale
			tempFacet.setNormal(vNormal);
		}

	}

	/**
	 * compute the normal for all Vertices
	 */
	public void computeVertexNormals() {
		for (ListIterator<Vertex> itVertex = vList.listIterator(); itVertex.hasNext();) {
			Vertex v = itVertex.next();

			ArrayList<TriangleFacet> tempTriangleList = new ArrayList<>();
			for (ListIterator<TriangleFacet> itTriangle = tFList.listIterator(); itTriangle.hasNext();) {
				TriangleFacet triangle = itTriangle.next();

				LinkedList<Vertex> tempVertexList = new LinkedList<>();
				tempVertexList.add(triangle.getHalfEdge().getStartVertex());
				tempVertexList.add(triangle.getHalfEdge().getNext().getStartVertex());
				tempVertexList.add(triangle.getHalfEdge().getNext().getNext().getStartVertex());

				if (tempVertexList.contains(v)) {
					tempTriangleList.add(triangle);
				}
			}

			Vector3 result = new Vector3();
			for (TriangleFacet tempFacet : tempTriangleList) {
				result = result.add(tempFacet.getNormal());
			}
			result = result.getNormalized();
			v.setNormal(result);
		}
	}
	


	/**
	 * method laplacianSmoothing
	 */
	public void laplacianSmoothing(double alpha) {
		Map<Vertex, Vector3> mapNewPos = new HashMap<>();
		// alle vertex durchlaufen
		System.out.println("Verteces:" + vList.size());
		for (Iterator<Vertex> itVl = vList.iterator(); itVl.hasNext();) {
			Vertex v = itVl.next();

			Set<Vertex> neighbourVertexSet = new HashSet<>();
			// alle Nachbar-Vertex finden 

			for (Iterator<TriangleFacet> itTriangle = tFList.iterator(); itTriangle.hasNext();) { // alle Dreiecke durchlaufen um alle zu finden die angrenzen
				TriangleFacet triangle = itTriangle.next();
				Set<Vertex> tempVertexSet = new HashSet<>();
				tempVertexSet.add(triangle.getHalfEdge().getStartVertex());
				tempVertexSet.add(triangle.getHalfEdge().getNext().getStartVertex());
				tempVertexSet.add(triangle.getHalfEdge().getNext().getNext().getStartVertex());

				if (tempVertexSet.contains(v)) { // wenn tempSet vertex enth�lt dann Dreieck was angrenzt
					neighbourVertexSet.addAll(tempVertexSet);
				}
			}

			if (!neighbourVertexSet.isEmpty()) {
				neighbourVertexSet.remove(v); // nur die Nachbarn
			}
			// berechne die schwerpunkte der Nachbarn
			Vector3 schwerpunkt = new Vector3();
			for (Iterator<Vertex> itNeighbour = neighbourVertexSet.iterator(); itNeighbour.hasNext();) {

				schwerpunkt = schwerpunkt.add(itNeighbour.next().getPosition());
			}

			System.err.println("Nachbarn: " + neighbourVertexSet.size());
			double pos = 1.0 / neighbourVertexSet.size();
			schwerpunkt = schwerpunkt.multiply((pos));

			// neue Position fuer den Vertex speichern
			mapNewPos.put(v, schwerpunkt);
		}

		// neue Positionen setzen
	
		for (Vertex v : mapNewPos.keySet()) {
			Vector3 oldPos = v.getPosition().multiply(alpha);
			Vector3 newPos = mapNewPos.get(v).multiply(alpha);
			oldPos = oldPos.add(newPos);
			v.getPosition().copy(oldPos);
		}
	}

	public void laplace() {
		double alpha = 0.5;
		Map<Vertex, Vector3> newPositions = new HashMap<>();
		// Alle c(i) berechnen
		for (Vertex v : this.vList) {
			HalfEdge start = v.getHalfEdge();
			Vector3 newPos = new Vector3(0, 0, 0);
			double n = 0.0;
			HalfEdge nextOp = start;
			// Summe aus allen Nachbarn
			do {

				newPos = newPos.add(nextOp.getNext().getStartVertex().getPosition());

				nextOp = nextOp.getOpposite().getNext();
				++n;
			} while (!(nextOp.equals(start)));
			// Summe multipliziert mit der Anzahl der Nachbarn.
			double factor = 1.0 / n;
			newPositions.put(v, newPos.multiply(factor));
		}
		// Punkte verschieben
		for (Vertex v : newPositions.keySet()) {
			Vector3 newValue = v.getPosition().multiply(alpha);
			Vector3 ciAlpha = newPositions.get(v).multiply(alpha);
			newValue = newValue.add(ciAlpha);
			v.getPosition().copy(newValue);
		}
	}

	/**
	 * TODO:
	 */

	public void calculateWarp() {
//		this.computeAllNormals();
	

		double kMax = Double.MIN_VALUE;
		double kMin = Double.MAX_VALUE;
		Map<Vertex, Double> bendings = new HashMap<>();

		for (Vertex v : vList) {
			double grad = 0.0;
			double areas = 0.0;
			HalfEdge tempEdge = v.getHalfEdge();
			double i = 0.0;
			HalfEdge next = tempEdge.getOpposite().getNext();

			while (!(next.equals(tempEdge))) {
				grad += Math.acos((v.getNormal()
						.multiply(next.getFacet().getNormal())));
				areas += next.getFacet().getArea();
				next = next.getOpposite().getNext();
				++i;
			}

			grad = grad / i;
			double bending = grad / areas;

			bendings.put(v, bending);

			kMin = Math.min(kMin, bending);
			kMax = Math.max(kMax, bending);
		}

		for (Vertex v : bendings.keySet()) {
			Vector3 value = new Vector3(0, 1, 0);
			value = value.multiply((bendings.get(v) - kMin) / (kMax - kMin));
			v.setColor(value);
		}

	}

	/**
	 * returns a Set of all neighbortriangels of the Vertex
	 */
	private Set<TriangleFacet> getNeighbors(Vertex v) {
		Set<TriangleFacet> resultSet = new HashSet<>();
		for (TriangleFacet tf : tFList) {
			Set<Vertex> checkSet = new HashSet<>();
			checkSet.add(tf.getHalfEdge().getStartVertex());
			checkSet.add(tf.getHalfEdge().getNext().getStartVertex());
			checkSet.add(tf.getHalfEdge().getNext().getNext().getStartVertex());

			if (checkSet.contains(v)) {
				resultSet.add(tf);
			}
		}
		return resultSet;
	}
	
	public void computeAllNormals(){
		this.computeTriangleNormals();
		this.computeVertexNormals();
	}

	/*
	 * (non-Javadoc)
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#
	 * setTextureFilename(java.lang.String)
	 */
	@Override
	public void setTextureFilename(String filename) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#
	 * getTextureFilename()
	 */
	@Override
	public String getTextureFilename() {
		return null;
	}
	


}
