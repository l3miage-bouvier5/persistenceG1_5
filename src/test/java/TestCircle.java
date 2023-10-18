import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.Circle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestCircle {

    @Test
    @DisplayName("Test accept circle JSON representation with max value")
    void testAcceptCircleJSONMaxValue(){
        int val = Integer.MAX_VALUE;
        Circle c = new Circle(val, val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        c.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"circle\",\n" + "\"x\": " + c.getX() + ",\n" + "\"y\": " + c.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle JSON representation with min value")
    void testAcceptCircleJSONMinValue(){
        int val = Integer.MIN_VALUE;
        Circle c = new Circle(val, val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        c.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"circle\",\n" + "\"x\": " + c.getX() + ",\n" + "\"y\": " + c.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle XML representation with max value")
    void testAcceptCircleXMLMaxValue(){
        int val = Integer.MAX_VALUE;
        Circle c = new Circle(val, val);
        XMLVisitor xmlVisitor = new XMLVisitor();   
        c.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>circle</type><x>" + c.getX() + "</x><y>" + c.getY() + "</y></shape>";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle XML representation with min value")
    void testAcceptCircleXMLMinValue(){
        int val = Integer.MIN_VALUE;
        Circle c = new Circle(val, val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        c.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>circle</type><x>" + c.getX() + "</x><y>" + c.getY() + "</y></shape>";
        assertEquals(expected, res);
    }
}
