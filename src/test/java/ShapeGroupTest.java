import fr.uga.miage.m1.shapes.ShapeGroup;
import fr.uga.miage.m1.shapes.SimpleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ShapeGroupTest {

    private ShapeGroup shapeGroup;
    @Mock
    private List<SimpleShape> shapesMock;

    @BeforeEach
    void setUp() {
        shapeGroup = new ShapeGroup();
        shapesMock = new ArrayList<>();
    }

    @Test
    void testDraw() {
        SimpleShape shape1 = mock(SimpleShape.class);
        SimpleShape shape2 = mock(SimpleShape.class);
        shapesMock.add(shape1);
        shapesMock.add(shape2);

        shapeGroup.addShape(shape1);
        shapeGroup.addShape(shape2);

        Graphics2D g2Mock = mock(Graphics2D.class);
        shapeGroup.draw(g2Mock);

        for (SimpleShape shape : shapesMock) {
            verify(shape).draw(g2Mock);
        }
    }

    @Test
    void testGetX() {
        assertEquals(0, shapeGroup.getX());
    }

    @Test
    void testGetY() {
        assertEquals(0, shapeGroup.getY());
    }



    @Test
     void testGetType() {
        assertEquals("group", shapeGroup.getType());
    }

    @Test
     void testContainsTrue() {
        SimpleShape shape = mock(SimpleShape.class);
        shapesMock.add(shape);
        shapeGroup.addShape(shape);

        when(shape.contains(1, 1)).thenReturn(true);

        assertTrue(shapeGroup.contains(1, 1));
    }

    @Test
     void testContainsFalse() {
        SimpleShape shape = mock(SimpleShape.class);
        shapesMock.add(shape);
        shapeGroup.addShape(shape);

        when(shape.contains(1, 1)).thenReturn(false);

        assertFalse(shapeGroup.contains(1, 1));
    }
    

    @Test
     void testMove() {
        SimpleShape shape1 = mock(SimpleShape.class);
        SimpleShape shape2 = mock(SimpleShape.class);
        shapesMock.add(shape1);
        shapesMock.add(shape2);

        shapeGroup.addShape(shape1);
        shapeGroup.addShape(shape2);

        shapeGroup.move(1, 2);

        for (SimpleShape shape : shapesMock) {
            verify(shape).move(1, 2);
        }
    }

    @Test
     void testGoTo() {
        SimpleShape shape1 = mock(SimpleShape.class);
        SimpleShape shape2 = mock(SimpleShape.class);
        shapesMock.add(shape1);
        shapesMock.add(shape2);

        shapeGroup.addShape(shape1);
        shapeGroup.addShape(shape2);

        shapeGroup.goTo(3, 4);

        for (SimpleShape shape : shapesMock) {
            verify(shape).goTo(3, 4);
        }
    }


    @Test
     void testIsSelected() {
        assertFalse(shapeGroup.isSelected());
    }
}
