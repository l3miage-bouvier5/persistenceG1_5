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
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestSquare {
    @Mock
    private Graphics2D mockGraphics;

    @Test
    @DisplayName("Test accept circle JSON representation with max value")
    void testAcceptSquareJSONMaxValue(){
        int val = Integer.MAX_VALUE;
        SimpleShape s = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.SQUARE,val,val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        s.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"square\",\n" + "\"x\": " + s.getX() + ",\n" + "\"y\": " + s.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle JSON representation with min value")
    void testAcceptSquareJSONMinValue(){
        int val = Integer.MIN_VALUE;
        SimpleShape s = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.SQUARE,val,val);
        JSonVisitor jsonVisitor = new JSonVisitor();
        s.accept(jsonVisitor);
        String res = jsonVisitor.getRepresentation();

        String expected = "{\n" + "\"type\": \"square\",\n" + "\"x\": " + s.getX() + ",\n" + "\"y\": " + s.getY() + "\n" + "}";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle XML representation with max value")
    void testAcceptSquareXMLMaxValue(){
        int val = Integer.MAX_VALUE;
        SimpleShape s = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.SQUARE,val,val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        s.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>square</type><x>" + s.getX() + "</x><y>" + s.getY() + "</y></shape>";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test accept circle XML representation with min value")
    void testAcceptSquareXMLMinValue(){
        int val = Integer.MIN_VALUE;
        SimpleShape s = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.SQUARE,val,val);
        XMLVisitor xmlVisitor = new XMLVisitor();
        s.accept(xmlVisitor);
        String res = xmlVisitor.getRepresentation();

        String expected = "<shape><type>square</type><x>" + s.getX() + "</x><y>" + s.getY() + "</y></shape>";
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("Test draw square")
    void testDraw() {
        // Arrange
        SimpleShape square = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.SQUARE,100,100);

        // Act
        square.draw(mockGraphics);

        // Assert
        // Vérifiez que setRenderingHint est appelé avec les bons arguments
        verify(mockGraphics, times(1)).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vérifiez que fill et draw de Rectangle2D.Double sont appelés avec les bons arguments
        verify(mockGraphics, times(1)).fill(new Rectangle2D.Double(75, 75, 50, 50));
        verify(mockGraphics, times(1)).draw(new Rectangle2D.Double(75, 75, 50, 50));

        // Vérifiez que setPaint et setColor sont appelés avec les bonnes couleurs
        verify(mockGraphics, times(1)).setPaint(any(GradientPaint.class));
        verify(mockGraphics, times(1)).setColor(Color.BLACK);

        // Vérifiez que setStroke est appelé avec le bon BasicStroke
        verify(mockGraphics, times(1)).setStroke(any(BasicStroke.class));
    }
}
