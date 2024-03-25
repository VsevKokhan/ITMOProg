package commands;

import exception.ArgumentException;

import java.io.IOException;

public class Show extends  Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        var col = collectionManager.getCollection();
        for (var tick: col)
        {
            System.out.println(tick.toString());

        }
        namesOfCommands.add(getName());

    }

    public Show(String name, String description) {
        super(name, description);
    }
}
