package main.java.computergraphics.applications;

import com.jogamp.newt.event.KeyEvent;

import main.java.computergraphics.datastructures.HalfEdgeTriangleMesh;
import main.java.computergraphics.datastructures.MarchingCubesAlgorithm;
import main.java.computergraphics.datastructures.SphereImplicitFunction;
import main.java.computergraphics.datastructures.TorsoImplicitFunction;
import main.java.computergraphics.framework.AbstractCGFrame;
import main.java.computergraphics.scenegraph.ColorNode;
import main.java.computergraphics.scenegraph.CubeMeshNode;
import main.java.computergraphics.scenegraph.ObjNode;
import main.java.computergraphics.scenegraph.ShaderNode;

public class Praktikum4MarchingCubes extends AbstractCGFrame{
	private HalfEdgeTriangleMesh mesh;
    private ObjNode meshNode;
    
    
	public Praktikum4MarchingCubes(int timerInterval) {
		super(timerInterval);
		MarchingCubesAlgorithm march = new MarchingCubesAlgorithm();
//		march.createObject(new SphereImplicitFunction(1.0));
		march.createObject(new TorsoImplicitFunction(0.5, 1.0));
		ShaderNode shader = new ShaderNode(ShaderNode.ShaderType.PHONG);
		ColorNode color = new ColorNode(1.0, 0.0, 0.0);
//		mesh.computeAllNormals();
		mesh = march.getMesh();
		getRoot().addChild(shader);
		
		meshNode = new ObjNode(mesh);
		shader.addChild(color);
		color.addChild(meshNode);
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
	
	public void keyPressed(int keyCode) {
		if(KeyEvent.VK_S == keyCode) {
			System.out.println("Key pressed: " + (char) keyCode);			
			mesh.getMesh().laplacianSmoothing(0.5);
			mesh.getMesh().computeAllNormals();
		}
		
		if(KeyEvent.VK_D == keyCode){
			System.out.println("Key pressed: " + (char) keyCode);
			mesh.getMesh().calculateWarp();
			mesh.getMesh().computeAllNormals();
		}
	}
	
	/**
	 * Program entry point.
	 */
	public static void main(String[] args) {
		// The timer ticks every 1000 ms.
		new Praktikum4MarchingCubes(1000);
	}

}
