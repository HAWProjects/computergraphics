package main.java.computergraphics.datastructures;

import java.util.ArrayList;
import java.util.List;

import main.java.computergraphics.math.Vector3;

public class SphereImplicitFunction implements ImplicitFunctionI {
	private double _radius;

	public SphereImplicitFunction(double radius) {
		_radius = radius;
	}
	@Override
	public List<Double> calculateValues(List<Vector3> points) {
		List<Double>resultList = new ArrayList<>();
		for(int i = 0; i<points.size();i++){
			double x = points.get(i).get(0);
			double y = points.get(i).get(1);
			double z = points.get(i).get(2);
			
			resultList.add(x*x+y*y+z*z-this._radius*this._radius);
		}
		return resultList;
	}

}
