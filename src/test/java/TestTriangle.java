import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTriangle {

    @Test
    @DisplayName("Test accept triangle JSON representation with max value")
    void testAcceptTriangleJSONMaxValue(){
        int val = Integer.MAX_VALUE;
        Triangle t = new Triangle(val, val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        t.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"triangle\",\n" + "\"x\": " + t.getX() + ",\n" + "\"y\": " + t.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept triangle JSON representation with min value")
    void testAcceptTriangleJSONMinValue(){
        int val = Integer.MIN_VALUE;
        Triangle t = new Triangle(val, val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        t.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"triangle\",\n" + "\"x\": " + t.getX() + ",\n" + "\"y\": " + t.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept triangle XML representation with max value")
    void testAcceptTriangleXMLMaxValue(){
        int val = Integer.MAX_VALUE;
        Triangle t = new Triangle(val, val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        t.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>triangle</type><x>" + t.getX() + "</x><y>" + t.getY() + "</y></shape>";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept triangle XML representation with min value")
    void testAcceptTriangleXMLMinValue(){
        int val = Integer.MIN_VALUE;
        Triangle t = new Triangle(val, val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        t.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>triangle</type><x>" + t.getX() + "</x><y>" + t.getY() + "</y></shape>";
        assertEquals(expected, res);
    }
}
