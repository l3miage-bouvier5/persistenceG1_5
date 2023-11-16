package fr.uga.miage.m1.commands;

import fr.uga.miage.m1.JDrawingFrame;
import fr.uga.miage.m1.shapes.SimpleShape;

public class AddShapeCommand implements Command{

    private JDrawingFrame frame;

    private SimpleShape shape;

    public AddShapeCommand(JDrawingFrame frame, SimpleShape shape){
        this.frame = frame;
        this.shape = shape;
    }
    

    @Override
    public void execute() {
        this.frame.addShape(this.shape);
    }

    @Override
    public void undo() {
        this.frame.removeShape(this.shape);
    }
}
