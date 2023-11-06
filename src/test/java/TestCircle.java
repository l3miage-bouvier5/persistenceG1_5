import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.Circle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestCircle {

    @Mock
    private Graphics2D mockGraphics;

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

    @Test
    @DisplayName("Test accept circle JSON representation with zero value")
    void testAcceptCircleJSONZeroValue(){
        int val = 0;
        Circle c = new Circle(val, val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        c.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"circle\",\n" + "\"x\": " + c.getX() + ",\n" + "\"y\": " + c.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle XML representation with zero value")
    void testAcceptCircleXMLZeroValue(){
        int val = 0;
        Circle c = new Circle(val, val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        c.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>circle</type><x>" + c.getX() + "</x><y>" + c.getY() + "</y></shape>";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test draw circle")
    void testDraw() {
        // Arrange
        Circle circle = new Circle(100, 100);

        // Act
        circle.draw(mockGraphics);

        // Assert
        // Vérifiez que setRenderingHint est appelé avec les bons arguments
        verify(mockGraphics, times(1)).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vérifiez que fill et draw de Ellipse2D.Double sont appelés avec les bons arguments
        verify(mockGraphics, times(1)).fill(new Ellipse2D.Double(75, 75, 50, 50));
        verify(mockGraphics, times(1)).draw(new Ellipse2D.Double(75, 75, 50, 50));

        // Vérifiez que setPaint et setColor sont appelés avec les bonnes couleurs
        verify(mockGraphics, times(1)).setPaint(any(GradientPaint.class));
        verify(mockGraphics, times(1)).setColor(Color.BLACK);

        // Vérifiez que setStroke est appelé avec le bon BasicStroke
        verify(mockGraphics, times(1)).setStroke(any(BasicStroke.class));
    }
}
