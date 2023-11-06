package fr.uga.miage.m1.shapes;

public class ShapeFactory {
    private static ShapeFactory singleton;

    private ShapeFactory(){}

    public static ShapeFactory getInstance(){
        if (singleton == null) {
            singleton = new ShapeFactory();
        }
        return singleton;
    }
    public SimpleShape createTriangle(int x, int y){
        return new Triangle(x, y);
    }

    public SimpleShape createSquare(int x, int y){
        return new Square(x, y);
    }

    public SimpleShape createCircle(int x, int y){
        return new Circle(x, y);
    }
}
