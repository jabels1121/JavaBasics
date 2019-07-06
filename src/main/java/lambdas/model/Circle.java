package lambdas.model;

public class Circle extends Elipsis implements Shape {

    public Circle() {
        System.out.println("Creating circle");
    }

    @Override
    public double calcSquare() {
        return 1;
    }
}
