package fr.uga.miage.m1.shapes;

import edu.uga.singleshape.CubePanel;
import fr.uga.miage.m1.persistence.Visitor;

import java.awt.*;

class Cube implements SimpleShape{

    private SimpleShape group;
    private int mX;
    private int mY;

    private boolean isSelected = false;

    private int size = 50;

    public Cube(int mX,int mY){
        this.mX = mX;
        this.mY = mY;
    }


    @Override
    public void draw(Graphics2D g2) {


        CubePanel c = new CubePanel(size, mX, mY);

        c.paintComponent(g2);
        if (isSelected) {
            int borderSize = 1;
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(borderSize));


            int minX = this.mX - this.size / 2;
            int minY = this.mY - this.size / 2;
            int width = this.size + borderSize;
            int height = this.size + borderSize;


            g2.drawRect(minX, minY, width, height);
        }
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
    public void setGroup(SimpleShape group) {
        this.group = group;
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
        int minX = this.mX - this.size/2;
        int minY = this.mY - this.size/2;
        int maxX = this.mX + this.size/2;
        int maxY = this.mY + this.size/2;

        return (x >= minX && x <= maxX && y >= minY && y <= maxY);
    }


    public void move(int diffX, int diffY) {
        this.mX += diffX;
        this.mY += diffY;
    }

    @Override
    public void goTo(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    @Override
    public SimpleShape getGroup() {
        return group;
    }
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }


}
