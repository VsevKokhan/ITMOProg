package commands;

import exception.ArgumentException;

import java.io.IOException;

public class Exit extends Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        System.exit(0);
        namesOfCommands.add(getName());
    }

    public Exit(String name, String description) {
        super(name, description);
    }
}
