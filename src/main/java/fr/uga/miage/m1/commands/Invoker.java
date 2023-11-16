package fr.uga.miage.m1.commands;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    private final List<Command> commands = new ArrayList<>();

    private final List<Command> history = new ArrayList<>();

    public Invoker() {

    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void execute() {
        Command c = this.commands.get(this.commands.size() - 1);
        c.execute();
        this.history.add(c);
    }

    public void undo(){
        Command c = this.history.get(this.commands.size() - 1);
        c.undo();
        this.commands.add(c);
    }


}
