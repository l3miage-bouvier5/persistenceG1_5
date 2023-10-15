package fr.uga.miage.m1.commands;

import fr.uga.miage.m1.JDrawingFrame;


public class UndoCommand implements Command {

    private JDrawingFrame frame;
    public UndoCommand(JDrawingFrame frame) {
        this.frame = frame;
    }
    public void execute() {
        frame.undo();
    }
}

