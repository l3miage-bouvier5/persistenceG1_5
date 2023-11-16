package fr.uga.miage.m1.commands;

public interface Command {
    public void execute();

    public void undo();
}
