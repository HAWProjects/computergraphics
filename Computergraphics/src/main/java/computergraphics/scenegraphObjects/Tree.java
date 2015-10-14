package main.java.computergraphics.scenegraphObjects;

import main.java.computergraphics.scenegraph.CuboidNode;
import main.java.computergraphics.scenegraph.RootNode;
import main.java.computergraphics.scenegraph.ShaderNode;
import main.java.computergraphics.scenegraph.ShaderNode.ShaderType;
import main.java.computergraphics.scenegraph.SphereNode;

/**
 * This class generates a Scenegraph that draws a tree
 * @author Jennifer Momsen, Robert Scheffel
 *
 */
public class Tree {
	private RootNode root;

	
	/**
	 * Constructor
	 */
	public Tree(){
		this.root = new RootNode();
		
		 // Shader node does the lighting computation
	    ShaderNode shaderNode = new ShaderNode(ShaderType.PHONG);
	    root.addChild(shaderNode);
	    
	    
	    //CuboidNode
	    CuboidNode cuboidNode = new CuboidNode(1.0, 2.0, 1.0);
	    shaderNode.addChild(cuboidNode);
	    
	    //SphereNode
	    SphereNode sphereNode = new SphereNode(2.0, 20);
	    cuboidNode.addChild(sphereNode);
	
	}
	
	
}
