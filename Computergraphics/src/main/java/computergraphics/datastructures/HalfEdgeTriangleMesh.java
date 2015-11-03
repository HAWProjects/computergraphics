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

/**
 * @author Robert Scheffel, Jennifer Momsen
 *
 */
public class HalfEdgeTriangleMesh implements ITriangleMesh {

	List<Vertex> vList;
	List<HalfEdge> hEList;
	List<TriangleFacet> tFList;

	/**
	 * Constructor
	 */
	public HalfEdgeTriangleMesh() {
		this.vList = new ArrayList<>();
		this.hEList = new ArrayList<>();
		this.tFList = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.java.computergraphics.datastructures.ITriangleMesh#addTriangle(int,
	 * int, int)
	 */
	@Override
	public void addTriangle(int vertexIndex1, int vertexIndex2, int vertexIndex3) {
		
		
		List<Vertex> tempVList = new ArrayList<>();
		tempVList.add(vList.get(vertexIndex1));
		tempVList.add(vList.get(vertexIndex2));
		tempVList.add(vList.get(vertexIndex3));
		
		List<HalfEdge> tempHeList = new ArrayList<>();
		for(int i = 0 ; i< 3;i++){
			tempHeList.add(new HalfEdge());
		}
		
		if (!tFList.isEmpty() ) {
			
			for (TriangleFacet facet : tFList) {
				
				HalfEdge halfEdge = facet.getHalfEdge();
				Vertex v1 = halfEdge.getStartVertex();
				Vertex v2 = halfEdge.getNext().getStartVertex();
				
				if(tempVList.contains(v1)&& tempVList.contains(v2)){
					TriangleFacet tF = new TriangleFacet();
					
					HalfEdge tempHalfEdge1 = new HalfEdge();
					HalfEdge tempHalfEdge2 = new HalfEdge();
					tempHalfEdge2.setStartVertex(tempVList.get(2));
					tempHalfEdge1.setNext(tempHalfEdge2);
					tempHalfEdge2.setNext(halfEdge);
					
					hEList.add(tempHalfEdge1);
					hEList.add(tempHalfEdge2);
					v2.setHalfEgde(tempHalfEdge1);
					tF.setHalfEdge(tempHalfEdge1);
					tFList.add(tF);
				}
			}
			
		} else {
			for (int i = 0; i < 3; i++) {
				tempHeList.get(i).setStartVertex(tempVList.get(i));
				tempHeList.get(i).setNext(tempHeList.get((i+1) % 3));
				hEList.add(tempHeList.get(i)); 
			}
			
		}

		calculateOppositeHalfEdge();
	}



	/**
	 * 
	 */
	private void calculateOppositeHalfEdge() {
		for (Iterator<HalfEdge> itHalfEdge = hEList.iterator(); itHalfEdge.hasNext();) {
			HalfEdge hE = itHalfEdge.next();
			Vertex vTarget = hE.getNext().getStartVertex();
			hE.setOpposite(vTarget.getHalfEdge());
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

}
