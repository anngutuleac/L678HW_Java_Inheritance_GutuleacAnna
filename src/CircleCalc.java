class CircleCalc {
    public static void main(String args[]) {
        double radius = 4;
        Circle circle = new Circle(radius);
        System.out.println("Площадь круга с радиусом " + radius + " равна: " + circle.area());
        System.out.println("Ширина круга с радиусом " + radius + " равна: " + circle.width);
        System.out.println("Высота круга с радиусом " + radius + " равна: " + circle.height);
    }
}

abstract class TwoDShape {
    double width, height;
    public TwoDShape(double width, double height) throws IllegalArgumentException {
        if (width < 0) { throw new IllegalArgumentException("Ширина должна быть неотрицательной!"); }
        if (height < 0) { throw new IllegalArgumentException("Высота должна быть неотрицательной!"); }
        this.width = width;
        this.height = height;
    }
}
class Circle extends TwoDShape {
    double radius;
    double pi = 3.14;

    public Circle(double radius) throws IllegalArgumentException {
        super(radius * 2, radius * 2);
        this.radius = radius;
    }

    double area() {
        return pi * radius * radius;
    }
}
