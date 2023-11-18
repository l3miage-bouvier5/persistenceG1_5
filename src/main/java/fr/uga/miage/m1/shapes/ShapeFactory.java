package fr.uga.miage.m1.shapes;

public class ShapeFactory {

    public enum Shapes {

        SQUARE, TRIANGLE, CIRCLE, CUBE
    }
    private static ShapeFactory singleton;

    private ShapeFactory(){}

    public static ShapeFactory getInstance(){
        if (singleton == null) {
            singleton = new ShapeFactory();
        }
        return singleton;
    }


    public SimpleShape createSimpleShape(Shapes shape, int x, int y){
        SimpleShape s;
        switch (shape){
            case TRIANGLE:
                s = new Triangle(x, y);
                break;
            case CIRCLE:
                s = new Circle(x, y);
                break;
            case SQUARE:
                s = new Square(x, y);
                break;
                case CUBE:
                s = new Cube(x, y);
                    break;

            default:
                s = null;
        }
        return s;
    }

    public SimpleShape createSimpleShape(String shape, int x, int y){
        SimpleShape s = null;

            if(shape.equals("triangle")){
                s = new Triangle(x, y);
            }
            if( shape.equals("circle")){

                s = new Circle(x, y);
            }
            if(shape.equals("square")) {

                s = new Square(x, y);
            }
            if(shape.equals("cube")) {

                s = new Cube(x, y);
            }

        return s;
    }
}
