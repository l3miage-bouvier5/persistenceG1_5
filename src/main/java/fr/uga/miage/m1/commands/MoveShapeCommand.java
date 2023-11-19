package fr.uga.miage.m1.commands;

import fr.uga.miage.m1.JDrawingFrame;
import fr.uga.miage.m1.shapes.ShapeGroup;
import fr.uga.miage.m1.shapes.SimpleShape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MoveShapeCommand implements Command{

    private Map<SimpleShape, Point> initialPositions = new HashMap<>();
    private int startX;
    private int startY;

    private SimpleShape shape;

    private int diffX;
    private int diffY;

    private boolean finished;

    private JDrawingFrame frame;

    public MoveShapeCommand(int startX, int startY, SimpleShape shape, JDrawingFrame frame){
        this.startX = startX;
        this.startY = startY;
        this.shape = shape;
        this.finished = false;
        this.frame = frame;

        if (shape.getType().equals("group")) {
            for (SimpleShape s : ((ShapeGroup) shape).getShapes()) {
                initialPositions.put(s, new Point(s.getX(), s.getY()));
            }
        }
    }
    @Override
    public void execute() {
        shape.move(diffX, diffY);
    }

    @Override
    public void undo() {
        if(shape.getType().equals("group")){
            for(SimpleShape s : ((ShapeGroup) shape).getShapes()){
                Point initialPosition = initialPositions.get(s);
                if (initialPosition != null) {
                    s.goTo(initialPosition.x, initialPosition.y);
                }
            }
        }else {
            shape.goTo(startX,startY);
        }
        frame.paintComponents(frame.getGraphics());
    }

    @Override
    public boolean finished() {
        return finished;
    }

    public void setDiff(int x, int y){
        this.diffX = x;
        this.diffY = y;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
