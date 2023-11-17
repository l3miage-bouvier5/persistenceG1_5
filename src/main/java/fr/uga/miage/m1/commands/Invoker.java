package fr.uga.miage.m1.commands;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    private final List<Command> commands = new ArrayList<>();

    private final List<Command> history = new ArrayList<>();


    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void execute() {
        if(!this.commands.isEmpty()) {
            Command c = this.commands.get(this.commands.size() - 1);
            c.execute();
            if(c.finished()) {
                this.commands.remove(c);
                this.history.add(c);
            }
        }
    }


    public void undo(){
        if(!this.history.isEmpty()){
            Command c = this.history.get(this.history.size() - 1);
            c.undo();
            this.history.remove(c);
            this.commands.add(c);
        }
    }

    public Command getCommand(){
        return this.commands.get(this.commands.size() - 1);
    }



}
