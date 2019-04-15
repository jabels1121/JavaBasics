package lambdas.model;

import java.util.List;

public interface Shape {

    double calcSquare();

    static double calcShapeSquare(List<Shape> shapeList) {
        double squareSum = 0;
        for (Shape s : shapeList) {
            squareSum = squareSum + s.calcSquare();
        }
        return squareSum;
    }

}
