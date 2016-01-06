package main.java.computergraphics.applications;

import main.java.computergraphics.datastructures.ImageViewer;
import main.java.computergraphics.datastructures.Raytracer;
import main.java.computergraphics.framework.Camera;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.GroupNode;
import main.java.computergraphics.scenegraph.PlainNode;
import main.java.computergraphics.scenegraph.SphereNode;

public class CGLicht {

	public static void main(String[] args) {

		GroupNode gpNode = new GroupNode();
		SphereNode sphere = new SphereNode(1.0, 10, new Vector3(0.0, 0.3, -1.5));
		PlainNode screen = new PlainNode(new Vector3(0.0, 0.0, 0.1), new Vector3(0.0, 0.0, 0.1), new Vector3(0.0,0.0,0.1));
		gpNode.addChild(sphere);
		gpNode.addChild(screen);
		Raytracer ray = new Raytracer(new Camera(), gpNode);
		new ImageViewer(ray.render(800, 600));
	}
}
