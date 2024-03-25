package commands;

import exception.ArgumentException;
import manager.CollectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Add extends Command {
    /**
     * Команда 'add'. Добавляет новый элемент в коллекцию
     */
    public Add(String name, String description) {
        super(name, description);
    }

    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        collectionManager.addTicket(OperationTicket());
        namesOfCommands.add(getName());
    }
}
