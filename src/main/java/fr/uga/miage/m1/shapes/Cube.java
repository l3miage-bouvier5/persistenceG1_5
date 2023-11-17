package fr.uga.miage.m1.shapes;

import edu.uga.singleshape.CubePanel;
import fr.uga.miage.m1.persistence.Visitor;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Cube implements SimpleShape{

    private int mX;
    private int mY;

    public Cube(int mX,int mY){
        this.mX = mX;
        this.mY = mY;
    }


    @Override
    public void draw(Graphics2D g2) {


        CubePanel c = new CubePanel(50, mX, mY);

        c.paintComponent(g2);
    }

    @Override
    public int getX() {
        return mX;
    }

    @Override
    public int getY() {
        return mY;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getType() {
        return "cube";
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }


    public void move(int diffX, int diffY) {
        this.mX += diffX;
        this.mY += diffY;
    }

    @Override
    public void goTo(int x, int y) {

    }
}
