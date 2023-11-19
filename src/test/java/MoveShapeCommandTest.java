import fr.uga.miage.m1.JDrawingFrame;
import fr.uga.miage.m1.commands.MoveShapeCommand;
import fr.uga.miage.m1.shapes.SimpleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoveShapeCommandTest {

    @Mock
    private MoveShapeCommand moveShapeCommand;
    @Mock
    private JDrawingFrame frameMock;

    @Mock
    private SimpleShape shapeMock;

    @BeforeEach
     void setUp() {
        frameMock = mock(JDrawingFrame.class);
        shapeMock = mock(SimpleShape.class);

        when(shapeMock.getType()).thenReturn("single");

        moveShapeCommand = new MoveShapeCommand(0, 0, shapeMock, frameMock);
    }

    @Test
     void testExecute() {
        moveShapeCommand.setDiff(10, 20);
        moveShapeCommand.execute();

        verify(frameMock).moveShape(shapeMock,10, 20);
    }


    @Test
     void testFinished() {
        assertFalse(moveShapeCommand.finished());

        moveShapeCommand.setFinished(true);

        assertTrue(moveShapeCommand.finished());
    }
}

