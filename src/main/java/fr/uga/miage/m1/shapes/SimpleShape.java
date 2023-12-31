package fr.uga.miage.m1.shapes;

import fr.uga.miage.m1.persistence.Visitable;
import fr.uga.miage.m1.persistence.Visitor;

import java.awt.Graphics2D;

/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public interface SimpleShape extends Visitable {

    /**
     * Method to draw the shape of the extension.
     * @param g2 The graphics object used for painting.
     */
    void draw(Graphics2D g2);

    int getX();

    int getY();

    @Override
    void accept(Visitor visitor);

    public String getType();

    /**
     * Renvoie True si la forme contient les coordonn�es envoy�es en param�tre
     * @param x : Coordonn�e x � checker
     * @param y : Coordonn�e y � checker
     * */
    public boolean contains(int x, int y);

    /**
     * Permet de modifier les coordonn�es X et Y de la shape
     *
     * */
    public void move(int diffX, int diffY);

    public void goTo(int x, int y);

    public void setSelected(boolean selected);

    public boolean isSelected();

    public void setGroup(SimpleShape group);

    public SimpleShape getGroup();
}
