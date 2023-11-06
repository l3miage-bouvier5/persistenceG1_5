import fr.uga.miage.m1.commands.Invoker;
import fr.uga.miage.m1.commands.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvokerTest {

    @Mock
    private Command mockCommand;

    @InjectMocks
    private Invoker invoker;

    @Test
    void testExecute() {
        // Arrange (no need to arrange anything in this case)

        // Act
        invoker.execute();

        // Assert
        // Verify that the execute() method of the Command is called
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void testSetCommand() {
        // Arrange
        Command newMockCommand = mock(Command.class);

        // Act
        invoker.setCommand(newMockCommand);
        invoker.execute();

        // Assert
        // Verify that the execute() method of the new Command is called
        verify(newMockCommand, times(1)).execute();
        // Verify that the previous mockCommand's execute() method is not called
        verify(mockCommand, never()).execute();
    }
}
