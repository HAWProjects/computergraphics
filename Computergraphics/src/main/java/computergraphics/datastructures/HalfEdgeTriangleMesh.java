/**
* Praktikum WPCG, WS 2015
* Gruppe: Robert Scheffel (Robert.Scheffel@haw-hamburg.de),
* Jennifer Momsen (Jennifer.Momsen@haw-hamburg.de)
* Aufgabe: Aufgabenblatt 2, Aufgabe 1
*/
package main.java.computergraphics.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

		List<Vertex> tempVList = new ArrayList<>(); // vorbereitung um
													// STartvertex und nächste
													// Halbkante zu setzen
		tempVList.add(vList.get(vertexIndex1));
		tempVList.add(vList.get(vertexIndex2));
		tempVList.add(vList.get(vertexIndex3));

		List<HalfEdge> tempHeList = new ArrayList<>(); // vorbereitung um Startvertex und nächste Halbkante zu setzen
		for (int i = 0; i < 3; i++) {
			tempHeList.add(new HalfEdge());
		}

		for (int i = 0; i < 3; i++) {
			tempHeList.get(i).setStartVertex(tempVList.get(i));
			tempHeList.get(i).setNext(tempHeList.get((i + 1) % 3));
			hEList.add(tempHeList.get(i));
		}

		tf.setHalfEdge(tempHeList.get(0)); // halbkante setzen

		tFList.add(tf); // Facette zur Liste hinzufügen

		calculateOppositeHalfEdge(tf);

	}


	/**
	 * 
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
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#
	 * setTextureFilename(java.lang.String)
	 */
	@Override
	public void setTextureFilename(String filename) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.computergraphics.datastructures.ITriangleMesh#
	 * getTextureFilename()
	 */
	@Override
	public String getTextureFilename() {
		return null;
	}

}
