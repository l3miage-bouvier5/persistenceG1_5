package fr.uga.miage.m1.shapes;

import fr.uga.miage.m1.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup implements SimpleShape{
    List<SimpleShape> shapes = new ArrayList<>();
    

    public void draw(Graphics2D g2){
        for(SimpleShape shape : shapes){
            shape.draw(g2);
        }
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void accept(Visitor visitor) {
        //Rien
    }

    @Override
    public String getType() {
        return "group";
    }

    @Override
    public boolean contains(int x, int y) {

        boolean res = false;
        for (SimpleShape shape : shapes){
            if(shape.contains(x,y)){
                res = true;
                break;
            }
        }
        return res;
    }

    public void addShape(SimpleShape shape){
        shapes.add(shape);
    }

    public void removeShape(SimpleShape shape){
        shapes.remove(shape);
    }

    public List<SimpleShape> getShapes(){
        return shapes;
    }

    public void move(int x, int y){
        for(SimpleShape shape : shapes){
            shape.move(x, y);
        }
    }

    public void goTo(int x, int y) {
        for (SimpleShape shape : shapes) {
            shape.goTo(x, y);
        }
    }

    @Override
    public void setSelected(boolean selected) {
       // rien
    }

    @Override
    public boolean isSelected() {
        return false;
    }
}
