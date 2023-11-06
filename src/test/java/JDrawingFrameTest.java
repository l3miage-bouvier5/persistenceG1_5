//import fr.uga.miage.m1.JDrawingFrame;
//import fr.uga.miage.m1.Shapes;
//import fr.uga.miage.m1.shapes.Triangle;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.awt.*;
//import java.awt.event.MouseEvent;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class JDrawingFrameTest {
//
//
//    @InjectMocks
//    private JDrawingFrame jDrawingFrame;
//
//    @Test
//    public void testMouseClicked() {
//
//        jDrawingFrame = mock(JDrawingFrame.class);
//
//        // Arrange
//        MouseEvent mockMouseEvent = mock(MouseEvent.class);
//
//
//        when(mockMouseEvent.getX()).thenReturn(100);
//        when(mockMouseEvent.getY()).thenReturn(100);
//        jDrawingFrame.setmSelected(Shapes.TRIANGLE);
//
//        // Act
//        jDrawingFrame.mouseClicked(mockMouseEvent);
//
//        // Assert
//
//        // verifier que la classe Triangle a bien été instanciée
//        verify(jDrawingFrame).getShapesVisible().add(any(Triangle.class));
//        // Vérifier que le triangle instancié a bien appeler draw
//        verify(jDrawingFrame).getShapesVisible().get(jDrawingFrame.getShapesVisible().size()-1).draw(any(Graphics2D.class));
//
//    }
//
//
//
//
//
//
//
//
//
//
//}
