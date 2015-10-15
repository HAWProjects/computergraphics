package main.java.computergraphics.scenegraph;

import main.java.computergraphics.math.Vector3;

public class BaumNode extends GroupNode {
	public BaumNode() {
		addChild(new CuboidNode(0.05, 0.5, 0.05));

		TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.35, 0.0));
		addChild(translationNode);
		
		ColorNode colorNode = new ColorNode(0.5, 1.0, 0.01);
		translationNode.addChild(colorNode);
		
		colorNode.addChild(new SphereNode(0.20, 15));
	}
	
	public BaumNode(int size) {
		addChild(new CuboidNode(size/10, size, size/10));

		TranslationNode translationNode = new TranslationNode(new Vector3(0.0, 0.35, 0.0));
		addChild(translationNode);
		
		ColorNode colorNode = new ColorNode(0.0, 1.0, 0.01);
		
		translationNode.addChild(colorNode);
		colorNode.addChild(new SphereNode(size/2, size));
	}
}
