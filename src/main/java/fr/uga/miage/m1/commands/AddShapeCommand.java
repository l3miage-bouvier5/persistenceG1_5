package fr.uga.miage.m1.commands;

import fr.uga.miage.m1.JDrawingFrame;
import fr.uga.miage.m1.shapes.SimpleShape;

public class AddShapeCommand implements Command{

    private JDrawingFrame frame;

    private SimpleShape shape;

    private boolean finished;

    public AddShapeCommand(JDrawingFrame frame, SimpleShape shape){
        this.frame = frame;
        this.shape = shape;
        this.finished = true;
    }
    

    @Override
    public void execute() {
        this.frame.addShape(this.shape);
    }

    @Override
    public void undo() {
        this.frame.removeShape(this.shape);
    }

    @Override
    public boolean finished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
