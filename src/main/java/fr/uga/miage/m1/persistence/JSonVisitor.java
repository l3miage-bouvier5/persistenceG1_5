package fr.uga.miage.m1.persistence;

import fr.uga.miage.m1.shapes.Circle;
import fr.uga.miage.m1.shapes.Square;
import fr.uga.miage.m1.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {
    private static final String X = "        \"x\": ";
    private static final String Y = "        \"y\": ";

    private String representation = null;


    public JSonVisitor() {
        /*
         * We don't need to do anything in the constructor*/
    }

    @Override
    public void visit(Circle circle) {
        this.representation = "{\n" +
                "        \"type\": \"circle\",\n" +
                X + circle.getX() + ",\n" +
                Y + circle.getY() + "\n" +
                "}";
    }

    @Override
    public void visit(Square square) {
        this.representation = "{\n" +
                "        \"type\": \"square\",\n" +
                X + square.getX() + ",\n" +
                Y + square.getY() + "\n" +
                "}";
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation = "{\n" +

                "        \"type\": \"triangle\",\n" +
                X + triangle.getX() + ",\n" +
                Y + triangle.getY() + "\n" +

                "}";
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
