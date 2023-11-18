import fr.uga.miage.m1.JDrawingFrame;
import fr.uga.miage.m1.commands.AddShapeCommand;
import fr.uga.miage.m1.shapes.SimpleShape;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class AddShapeCommandTest {

    @Mock
    private AddShapeCommand addShapeCommand;
    @Mock
    private JDrawingFrame frameMock;

    @Mock
    private SimpleShape shapeMock;

    @BeforeEach
     void setUp() {
        frameMock = mock(JDrawingFrame.class);
        shapeMock = mock(SimpleShape.class);

        addShapeCommand = new AddShapeCommand(frameMock, shapeMock);
    }

    @Test
     void testExecute() {
        addShapeCommand.execute();

        verify(frameMock).addShape(shapeMock);
    }

    @Test
     void testUndo() {
        addShapeCommand.undo();

        verify(frameMock).removeShape(shapeMock);
    }

    @Test
     void testFinished() {
        assertTrue(addShapeCommand.finished());

        addShapeCommand.setFinished(false);

        assertFalse(addShapeCommand.finished());
    }
}

