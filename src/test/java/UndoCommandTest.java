import fr.uga.miage.m1.commands.UndoCommand;
import fr.uga.miage.m1.JDrawingFrame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UndoCommandTest {

    @Mock
    private JDrawingFrame mockFrame;

    @InjectMocks
    private UndoCommand undoCommand;

    @Test
    void testExecute() {
        // Arrange (no need to arrange anything in this case)

        // Act
        undoCommand.execute();

        // Assert
        // Verify that the undo() method of the JDrawingFrame is called
        verify(mockFrame, times(1)).undo();
    }
}
