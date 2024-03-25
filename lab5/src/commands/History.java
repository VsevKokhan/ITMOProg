package commands;

import exception.ArgumentException;

import java.io.IOException;

public class History extends Command{

    public History(String name, String description) {
        super(name, description);
    }

    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        namesOfCommands.print();
    }
}
