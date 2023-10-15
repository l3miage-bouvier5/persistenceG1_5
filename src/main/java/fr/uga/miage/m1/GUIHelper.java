package fr.uga.miage.m1;

import fr.uga.miage.m1.commands.Command;
import fr.uga.miage.m1.commands.Invoker;
import fr.uga.miage.m1.commands.UndoCommand;

import java.awt.event.*;
import javax.swing.*;

/**
 *  @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class GUIHelper {

    private GUIHelper() {
        /*
         * We don't need to do anything in the constructor*/
    }

    public static void showOnFrame(String frameName) {


        JDrawingFrame frame = new JDrawingFrame(frameName);
        Command command = new UndoCommand(frame);
        Invoker invoker = new Invoker(command);

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        frame.getRootPane().getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoker.execute();
            }
        });


        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.pack();
        frame.setVisible(true);
    }
}
