package main.java.computergraphics.datastructures;

import java.util.ArrayList;
import java.util.List;

import main.java.computergraphics.math.Vector3;

public class TorsoImplicitFunction implements ImplicitFunctionI {
	private double _innerRadius;
	private double _outerRadius;
	
	public TorsoImplicitFunction(double innerRadius, double outerRadius) {
		_innerRadius = innerRadius;
		_outerRadius = outerRadius;
	}
	
	@Override
	public List<Double> calculateValues(List<Vector3> points) {
		List<Double> resultList = new ArrayList<>();
		for(int i = 0; i<points.size();i++){
			double x = points.get(i).get(0);
			double y = points.get(i).get(1);
			double z = points.get(i).get(2);
			
			
			resultList.add((Math.pow((x*x+y*y+z*z+_outerRadius*_outerRadius-_innerRadius*_innerRadius),2))-4*_outerRadius*_outerRadius*(x*x+y*y));
		}
		return resultList;
	}

}
