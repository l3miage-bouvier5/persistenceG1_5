package fr.uga.miage.m1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class GUIHelper {

    private GUIHelper() {
        /*
         * We don't need to do anything in the constructor*/
    }

    public static void showOnFrame(Client client) {
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        client.getFrame().addWindowListener(wa);
        client.getFrame().pack();
        client.getFrame().setVisible(true);
    }
}
