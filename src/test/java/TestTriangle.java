import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.ShapeFactory;
import fr.uga.miage.m1.shapes.SimpleShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.geom.GeneralPath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestTriangle {

    @Mock
    private Graphics2D mockGraphics;
    @Test
    @DisplayName("Test accept triangle JSON representation with max value")
    void testAcceptTriangleJSONMaxValue(){
        int val = Integer.MAX_VALUE;
        SimpleShape t = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,val,val);
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
        SimpleShape t = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,val,val);
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
        SimpleShape t = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,val,val);
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
        SimpleShape t = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,val,val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        t.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>triangle</type><x>" + t.getX() + "</x><y>" + t.getY() + "</y></shape>";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test draw triangle")
    void testDraw() {
        // Arrange
        SimpleShape triangle = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,100,100);
        // Act
        triangle.draw(mockGraphics);

        // Assert
        // Vérifiez que setRenderingHint est appelé avec les bons arguments
        verify(mockGraphics, times(1)).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vérifiez que fill et draw de GeneralPath sont appelés avec les bons arguments
        verify(mockGraphics, times(1)).fill(any(GeneralPath.class));
        verify(mockGraphics, times(1)).draw(any(GeneralPath.class));

        // Vérifiez que setPaint et setColor sont appelés avec les bonnes couleurs
        verify(mockGraphics, times(1)).setPaint(any(GradientPaint.class));
        verify(mockGraphics, times(1)).setColor(Color.BLACK);

        // Vérifiez que setStroke est appelé avec le bon BasicStroke
        verify(mockGraphics, times(1)).setStroke(any(BasicStroke.class));
    }
}
