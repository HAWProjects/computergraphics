package main.java.computergraphics.applications;

import com.jogamp.newt.event.KeyEvent;

import main.java.computergraphics.datastructures.HalfEdgeTriangleMesh;
import main.java.computergraphics.datastructures.MarchingCubesAlgorithm;
import main.java.computergraphics.datastructures.SphereImplicitFunction;
import main.java.computergraphics.framework.AbstractCGFrame;
import main.java.computergraphics.scenegraph.CubeMeshNode;
import main.java.computergraphics.scenegraph.ShaderNode;

public class MarchingCubes extends AbstractCGFrame{
	private HalfEdgeTriangleMesh meshTest;
    private CubeMeshNode meshNode;
    
	public MarchingCubes(int timerInterval) {
		super(timerInterval);
		 
	}


	/*
	 * (nicht-Javadoc)
	 * 
	 * @see computergrafik.framework.ComputergrafikFrame#timerTick()
	 */
	@Override
	protected void timerTick() {
		System.out.println("Tick");
	}
	
//	public void keyPressed(int keyCode) {
//		if(KeyEvent.VK_S == keyCode) {
//			System.out.println("Key pressed: " + (char) keyCode);			
//			meshTest.getMesh().laplacianSmoothing(0.5);
//			meshTest.getMesh().computeAllNormals();
//		}
//		
//		if(KeyEvent.VK_D == keyCode){
//			System.out.println("Key pressed: " + (char) keyCode);
//			meshTest.getMesh().calculateWarp();
//			meshTest.getMesh().computeAllNormals();
//		}
//	}
	
	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		// The timer ticks every 1000 ms.
		new MarchingCubes(1000);
	}

}
