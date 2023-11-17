package fr.uga.miage.m1.commands;

public interface Command {
    public void execute();

    public void undo();

    public boolean finished();

    public void setFinished(boolean finished);
}
