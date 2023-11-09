import fr.uga.miage.m1.shapes.ShapeFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class TestShapes {

    @Test
    void testEnumValues() {
        // Vérifier que les valeurs de l'énumération sont correctes
        assertEquals(3, ShapeFactory.Shapes.values().length); // Vérifiez qu'il y a trois formes

        assertTrue(containsShape(ShapeFactory.Shapes.SQUARE));
        assertTrue(containsShape(ShapeFactory.Shapes.TRIANGLE));
        assertTrue(containsShape(ShapeFactory.Shapes.CIRCLE));
    }

    private boolean containsShape(ShapeFactory.Shapes shape) {
        // Vérifie si l'énumération contient une certaine forme
        return shape == ShapeFactory.Shapes.SQUARE || shape == ShapeFactory.Shapes.TRIANGLE || shape == ShapeFactory.Shapes.CIRCLE;
    }
}