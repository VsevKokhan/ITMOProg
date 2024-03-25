package commands;

import exception.ArgumentException;

import java.io.IOException;

public class Clear extends  Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        collectionManager.getCollection().clear();
        System.out.println("Очистили коллекцию");
        namesOfCommands.add(getName());
    }

    public Clear(String name, String description) {
        super(name, description);
    }
}
