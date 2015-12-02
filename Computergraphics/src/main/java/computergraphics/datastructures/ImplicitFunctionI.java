package main.java.computergraphics.datastructures;

import java.util.List;

import main.java.computergraphics.math.Vector3;
/*
 * Interface welches die Impliziten Funktionen ausrechnet
 */
public interface ImplicitFunctionI {

	List<Double> calculateValues(List<Vector3>points);
}
