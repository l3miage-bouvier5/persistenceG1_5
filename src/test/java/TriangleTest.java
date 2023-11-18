import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.ShapeFactory;
import fr.uga.miage.m1.shapes.SimpleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TriangleTest {

    private SimpleShape simpleShape;
    @BeforeEach
    void setUp() {
        simpleShape = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE, 10, 10);
    }
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

        SimpleShape triangle = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,100,100);
        triangle.draw(mockGraphics);

        verify(mockGraphics, times(1)).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        verify(mockGraphics, times(1)).fill(any(Polygon.class));
        verify(mockGraphics, times(1)).draw(any(Polygon.class));

        verify(mockGraphics, times(1)).setPaint(any(GradientPaint.class));
        verify(mockGraphics, times(1)).setColor(Color.BLACK);

        verify(mockGraphics, times(1)).setStroke(any(BasicStroke.class));
    }


    @Test
    void testDrawSelected() {
        SimpleShape triangle = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,100,100);

        triangle.setSelected(true);

        // Act
        triangle.draw(mockGraphics);

        // Assert
        verify(mockGraphics).setColor(Color.GREEN);
        verify(mockGraphics, times(2)).setStroke(any(BasicStroke.class));
        verify(mockGraphics).drawPolygon(any(Polygon.class));
    }
    @Test
    @DisplayName("Test move triangle")
    void testMove(){
        int baseX = 50;
        int baseY = 50;

        int moveX = 100;
        int moveY = 100;
        SimpleShape triangle = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,baseX,baseY);

        triangle.move(moveX,moveY);

        assertEquals(triangle.getX(),baseX - 25 + moveX);
        assertEquals(triangle.getY(),baseY - 25 + moveY );
    }

    @Test
    @DisplayName("Test goTo with maxValue")
    void testGoTo(){
        int baseX = 50;
        int baseY = 50;
        int moveX = 100;
        int moveY = 100;
        SimpleShape triangle = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.TRIANGLE,baseX,baseY);


        triangle.goTo(moveX,moveY);

        assertEquals(triangle.getY(), moveY);
        assertEquals(triangle.getX(), moveX);
    }
    @Test
     void testSetSelected() {

        assertFalse(simpleShape.isSelected());

        simpleShape.setSelected(true);


        assertTrue(simpleShape.isSelected());
    }

    @Test
     void testSetSelectedFalse() {
        simpleShape.setSelected(true);


        simpleShape.setSelected(false);

        assertFalse(simpleShape.isSelected());
    }

    @Test
     void testIsSelected() {
        // Arrange
        simpleShape.setSelected(true);

        assertTrue(simpleShape.isSelected());
    }
    @Test
     void testIsNotSelected() {
        // Arrange
        simpleShape.setSelected(false);

        assertFalse(simpleShape.isSelected());
    }
}
