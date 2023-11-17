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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TestCube {


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

    }

