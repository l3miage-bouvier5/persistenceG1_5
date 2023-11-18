import fr.uga.miage.m1.persistence.JSonVisitor;
import fr.uga.miage.m1.persistence.XMLVisitor;
import fr.uga.miage.m1.shapes.ShapeFactory;
import fr.uga.miage.m1.shapes.SimpleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class CubeTest {


    private SimpleShape simpleShape;
    @BeforeEach
    void setUp() {
        simpleShape = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE, 10, 10);
    }

        @Test
        @DisplayName("Test accept circle JSON representation with max value")
        void testAcceptCubeJSONMaxValue(){
            int val = Integer.MAX_VALUE;
            SimpleShape c = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE,val,val);
            JSonVisitor jsonVisitor = new JSonVisitor();
            c.accept(jsonVisitor);
            String res = jsonVisitor.getRepresentation();

            String expected = "{\n" + "\"type\": \"cube\",\n" + "\"x\": " + c.getX() + ",\n" + "\"y\": " + c.getY() + "\n" + "}";
            assertEquals(expected, res);
        }

        @Test
        @DisplayName("Test accept circle JSON representation with min value")
        void testAcceptCubeJSONMinValue(){
            int val = Integer.MIN_VALUE;
            SimpleShape c = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE,val,val);
            JSonVisitor jsonVisitor = new JSonVisitor();
            c.accept(jsonVisitor);
            String res = jsonVisitor.getRepresentation();

            String expected = "{\n" + "\"type\": \"cube\",\n" + "\"x\": " + c.getX() + ",\n" + "\"y\": " + c.getY() + "\n" + "}";
            assertEquals(expected, res);
        }

        @Test
        @DisplayName("Test accept circle XML representation with max value")
        void testAcceptCubeXMLMaxValue(){
            int val = Integer.MAX_VALUE;
            SimpleShape c = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE,val,val);
            XMLVisitor xmlVisitor = new XMLVisitor();
            c.accept(xmlVisitor);
            String res = xmlVisitor.getRepresentation();

            String expected = "<shape><type>cube</type><x>" + c.getX() + "</x><y>" + c.getY() + "</y></shape>";
            assertEquals(expected, res);
        }

        @Test
        @DisplayName("Test accept circle XML representation with min value")
        void testAcceptCubeXMLMinValue(){
            int val = Integer.MIN_VALUE;
            SimpleShape c = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE,val,val);
            XMLVisitor xmlVisitor = new XMLVisitor();
            c.accept(xmlVisitor);
            String res = xmlVisitor.getRepresentation();

            String expected = "<shape><type>cube</type><x>" + c.getX() + "</x><y>" + c.getY() + "</y></shape>";
            assertEquals(expected, res);
        }

        @Test
        @DisplayName("Test accept cube JSON representation with zero value")
        void testAcceptCubeJSONZeroValue(){
            int val = 0;
            SimpleShape c = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE,val,val);
            JSonVisitor jsonVisitor = new JSonVisitor();
            c.accept(jsonVisitor);
            String res = jsonVisitor.getRepresentation();

            String expected = "{\n" + "\"type\": \"cube\",\n" + "\"x\": " + c.getX() + ",\n" + "\"y\": " + c.getY() + "\n" + "}";
            assertEquals(expected, res);
        }

        @Test
        @DisplayName("Test accept cube XML representation with zero value")
        void testAcceptCubeXMLZeroValue(){
            int val = 0;
            SimpleShape c = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE,val,val);
            XMLVisitor xmlVisitor = new XMLVisitor();
            c.accept(xmlVisitor);
            String res = xmlVisitor.getRepresentation();

            String expected = "<shape><type>cube</type><x>" + c.getX() + "</x><y>" + c.getY() + "</y></shape>";
            assertEquals(expected, res);
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
        simpleShape.setSelected(true);

        assertTrue(simpleShape.isSelected());
    }

    @Test
     void testIsNotSelected() {
        simpleShape.setSelected(false);

        assertFalse(simpleShape.isSelected());
    }

    @Test
     void testContains() {
        SimpleShape cube = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE, 50, 60);
        assertTrue(cube.contains(50, 60));
        assertTrue(cube.contains(25, 35));
        assertFalse(cube.contains(10, 10));
    }

    @Test
     void testMove() {
        SimpleShape cube = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE, 50, 60);

        cube.move(10, 20);

        assertEquals(60, cube.getX());
        assertEquals(80, cube.getY());
    }

    @Test
     void testGoTo() {
        SimpleShape cube = ShapeFactory.getInstance().createSimpleShape(ShapeFactory.Shapes.CUBE, 50, 60);

        cube.goTo(30, 40);

        assertEquals(30, cube.getX());
        assertEquals(40, cube.getY());
    }

}

