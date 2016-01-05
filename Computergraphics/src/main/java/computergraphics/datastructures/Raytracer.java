package main.java.computergraphics.datastructures;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import main.java.computergraphics.datastructures.Ray3D;
import main.java.computergraphics.framework.Camera;
import main.java.computergraphics.math.Vector3;
import main.java.computergraphics.scenegraph.Node;
import main.java.computergraphics.scenegraph.PlainNode;
import main.java.computergraphics.scenegraph.RootNode;
import main.java.computergraphics.scenegraph.SphereNode;

/**
 * Creates a raytraced image of the current scene.
 */
public class Raytracer {

	/**
	 * Reference to the current camera.
	 */
	private final Camera camera;
	private final Node rootNode;

	/**
	 * Constructor.
	 * 
	 * @param camera
	 *          Scene camera.
	 * @param rootNode
	 *          Root node of the scenegraph.
	 */
	public Raytracer(Camera camera, Node rootNode) {
		this.camera = camera;
		this.rootNode = rootNode;
	}

	/**
	 * Creates a raytraced image for the current view with the provided
	 * resolution. The opening angle in x-direction is grabbed from the camera,
	 * the opening angle in y-direction is computed accordingly.
	 * 
	 * @param resolutionX
	 *          X-Resolution of the created image.
	 * 
	 * @param resolutionX
	 *          Y-Resolution of the created image.
	 */
	public Image render(int resolutionX, int resolutionY) {
		BufferedImage image = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_RGB);

		Vector3 viewDirection = camera.getRef().subtract(camera.getEye()).getNormalized();
		Vector3 xDirection = viewDirection.cross(camera.getUp()).getNormalized();
		Vector3 yDirection = viewDirection.cross(xDirection).getNormalized();
		double openingAngleYScale = Math.sin(camera.getOpeningAngle() * Math.PI / 180.0);
		double openingAngleXScale = openingAngleYScale * (double) resolutionX / (double) resolutionY;

		for (int i = 0; i < resolutionX; i++) {
			double alpha = (double) i / (double) (resolutionX + 1) - 0.5;
			for (int j = 0; j < resolutionY; j++) {
				double beta = (double) j / (double) (resolutionY + 1) - 0.5;
				Vector3 rayDirection = viewDirection.add(xDirection.multiply(alpha * openingAngleXScale))
						.add(yDirection.multiply(beta * openingAngleYScale)).getNormalized();
				Ray3D ray = new Ray3D(camera.getEye(), rayDirection);

				Vector3 color = trace(ray, 0);

				// Adjust color boundaries
				for (int index = 0; index < 3; index++) {
					color.set(index, Math.max(0, Math.min(1, color.get(index))));
				}

				image.setRGB(i, j,
						new Color((int) (255 * color.get(0)), (int) (255 * color.get(1)), (int) (255 * color.get(2)))
								.getRGB());
			}
		}

		return image;
	}

	/**
	 * Compute a color from tracing the ray into the scene.
	 * 
	 * @param ray
	 *          Ray which needs to be traced.
	 * @param recursion
	 *          Current recursion depth. Initial recursion depth of the rays
	 *          through the image plane is 0. This parameter is used to abort the
	 *          recursion.
	 * 
	 * @return Color in RGB. All values are in [0,1];
	 */
	private Vector3 trace(Ray3D ray, int recursion) {
		List<IntersectionResult> objList = new LinkedList<>();
		// Your task
		for (int i = 0; i < rootNode.getNumberOfChildren(); i++) {
			Node currentNode = rootNode.getChildNode(i);
			if (currentNode instanceof SphereNode) {
				IntersectionResult intResult = ray.berechneSchnittKugel((SphereNode) currentNode);
				objList.add(intResult);
			} else if (currentNode instanceof PlainNode) {
				IntersectionResult intResult2 = ray.berechneSchnittEbene((PlainNode) currentNode);
				objList.add(intResult2);
			}
		}
		//Erstes Element, dass nicht Null ist finden
		IntersectionResult nearestObject = objList.get(0);
		boolean isAbgebrochen = false;
		for (IntersectionResult intResult : objList) {
			if (intResult != null) {
				nearestObject = intResult;
				isAbgebrochen = true;
				break;
			}
			
		}
		if(!isAbgebrochen){
			return new Vector3(0.0,0.0,0.0);
		}

		double abstandnearestObject = Double.POSITIVE_INFINITY;
		double abstandIntResult = Double.POSITIVE_INFINITY;
		for (IntersectionResult intResult : objList) {

			if (nearestObject != null) {
				abstandnearestObject = berechneAbstand(ray, nearestObject);
			}

			if (intResult != null) {
				abstandIntResult = berechneAbstand(ray, intResult);
			}

			if (abstandIntResult < abstandnearestObject) {
				nearestObject = intResult;
			}
		}

		return nearestObject.object.getColor();
	}

	private double berechneAbstand(Ray3D ray, IntersectionResult nearestObject) {
		Vector3 tempV = ray.getPoint().subtract(nearestObject.point);
		return Math.abs(Math.sqrt(Math.pow(tempV.get(0), 2) + Math.pow(tempV.get(1), 2) + Math.pow(tempV.get(2), 2)));
	}

}