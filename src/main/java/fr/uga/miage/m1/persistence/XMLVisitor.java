package fr.uga.miage.m1.persistence;

import fr.uga.miage.m1.shapes.Circle;
import fr.uga.miage.m1.shapes.Square;
import fr.uga.miage.m1.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {
    private static final String X ="<x>";
    private static final String Y = "<y>";
    private static final String SHAPE = "<shape>";
    private static final String CLOSING_SHAPE = "</shape>";
    private static final String CLOSING_X = "</x>";
    private static final String CLOSING_Y = "</y>";
    private String representation = null;

    public XMLVisitor() {
        /*
         * We don't need to do anything in the constructor*/
    }

    @Override
    public void visit(Circle circle) {
        this.representation = SHAPE +
                "<type>circle</type>" +
                X + circle.getX() + CLOSING_X +
                Y + circle.getY() + CLOSING_Y +
                CLOSING_SHAPE;
    }

    @Override
    public void visit(Square square) {
        this.representation = SHAPE +
                "<type>square</type>" +
                X + square.getX() + CLOSING_X +
                Y + square.getY() + CLOSING_Y +
                CLOSING_SHAPE;
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation = SHAPE +
                "<type>triangle</type>" +
                X + triangle.getX() + CLOSING_X +
                Y + triangle.getY() + CLOSING_Y +
                CLOSING_SHAPE;
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
