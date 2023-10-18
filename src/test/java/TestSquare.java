import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSquare {
    @Test
    @DisplayName("Test accept circle JSON representation with max value")
    void testAcceptCircleJSONMaxValue(){
        int val = Integer.MAX_VALUE;
        Square s = new Square(val, val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        s.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"square\",\n" + "\"x\": " + s.getX() + ",\n" + "\"y\": " + s.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle JSON representation with min value")
    void testAcceptCircleJSONMinValue(){
        int val = Integer.MIN_VALUE;
        Square s = new Square(val, val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        s.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"square\",\n" + "\"x\": " + s.getX() + ",\n" + "\"y\": " + s.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle XML representation with max value")
    void testAcceptCircleXMLMaxValue(){
        int val = Integer.MAX_VALUE;
        Square s = new Square(val, val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        s.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>square</type><x>" + s.getX() + "</x><y>" + s.getY() + "</y></shape>";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle XML representation with min value")
    void testAcceptCircleXMLMinValue(){
        int val = Integer.MIN_VALUE;
        Square s = new Square(val, val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        s.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>square</type><x>" + s.getX() + "</x><y>" + s.getY() + "</y></shape>";
        assertEquals(expected, res);
    }
}
