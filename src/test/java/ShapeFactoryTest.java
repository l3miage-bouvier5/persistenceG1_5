import fr.uga.miage.m1.shapes.ShapeFactory;
import fr.uga.miage.m1.shapes.SimpleShape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeFactoryTest {

    @Test
    void testCreateSimpleShapeSquare() {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        SimpleShape square = shapeFactory.createSimpleShape(ShapeFactory.Shapes.SQUARE, 10, 20);
        assertNotNull(square);
        assertEquals("square", square.getType());
        assertEquals(-15, square.getX());
        assertEquals(-5, square.getY());
    }

    @Test
     void testCreateSimpleShapeTriangle() {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        SimpleShape triangle = shapeFactory.createSimpleShape(ShapeFactory.Shapes.TRIANGLE, 30, 40);
        assertNotNull(triangle);
        assertEquals("triangle", triangle.getType());
        assertEquals(5, triangle.getX());
        assertEquals(15, triangle.getY());
    }

    @Test
     void testCreateSimpleShapeCircle() {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        SimpleShape circle = shapeFactory.createSimpleShape(ShapeFactory.Shapes.CIRCLE, 50, 60);
        assertNotNull(circle);

        assertEquals("circle", circle.getType());
        assertEquals(25, circle.getX());
        assertEquals(35, circle.getY());
    }

    @Test
     void testCreateSimpleShapeCube() {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        SimpleShape cube = shapeFactory.createSimpleShape(ShapeFactory.Shapes.CUBE, 70, 80);
        assertNotNull(cube);
        assertEquals("cube", cube.getType());
        assertEquals(70, cube.getX());
        assertEquals(80, cube.getY());
    }


    @Test
     void testCreateSimpleShapeSquareWithString() {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        SimpleShape square = shapeFactory.createSimpleShape("square", 110, 120);
        assertNotNull(square);
        assertEquals("square", square.getType());
        assertEquals(85, square.getX());
        assertEquals(95, square.getY());
    }

    @Test
    void testCreateSimpleShapeUnknownShapeWithString() {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        SimpleShape unknown = shapeFactory.createSimpleShape("unknown", 130, 140);
        assertNull(unknown);
    }
}