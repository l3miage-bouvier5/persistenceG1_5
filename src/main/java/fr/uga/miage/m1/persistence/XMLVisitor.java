package fr.uga.miage.m1.persistence;

import fr.uga.miage.m1.shapes.SimpleShape;

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
    public void visit(SimpleShape shape) {
        this.representation = SHAPE + "<type>"+shape.getType()+"</type>" + X + shape.getX() + CLOSING_X + Y + shape.getY() + CLOSING_Y + CLOSING_SHAPE;
    }

    public String getRepresentation() {
        return this.representation;
    }
}
