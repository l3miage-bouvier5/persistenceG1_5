package fr.uga.miage.m1.persistence;

import fr.uga.miage.m1.shapes.SimpleShape;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {
    private static final String X = "\"x\": ";
    private static final String Y = "\"y\": ";

    private String representation = null;


    public JSonVisitor() {
        /*
         * We don't need to do anything in the constructor*/
    }

    @Override
    public void visit(SimpleShape shape) {
        this.representation = "{\n" + "\"type\": \""+shape.getType()+"\",\n" + X + shape.getX() + ",\n" + Y + shape.getY() + "\n" + "}";
    }

    public String getRepresentation() {
        return this.representation;
    }
}
