import fr.uga.miage.m1.Shapes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class TestShapes {

    @Test
    void testEnumValues() {
        // Vérifier que les valeurs de l'énumération sont correctes
        assertEquals(3, Shapes.values().length); // Vérifiez qu'il y a trois formes

        assertTrue(containsShape(Shapes.SQUARE));
        assertTrue(containsShape(Shapes.TRIANGLE));
        assertTrue(containsShape(Shapes.CIRCLE));
    }

    private boolean containsShape(Shapes shape) {
        // Vérifie si l'énumération contient une certaine forme
        return shape == Shapes.SQUARE || shape == Shapes.TRIANGLE || shape == Shapes.CIRCLE;
    }
}