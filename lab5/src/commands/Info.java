package commands;

import exception.ArgumentException;

import java.io.IOException;

public class Info extends Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        var col = collectionManager.getCollection();
        System.out.println();
        System.out.println("Элементов в коллекции: "  + col.size());
        for (var tick:col)
        {
            System.out.println("Дата инициализации: " + tick.getCreationDate());break;
        }
        System.out.println();
        System.out.println("Тип класса: "  + col.getClass().getName());
        namesOfCommands.add(getName());
    }

    public Info(String name, String description) {
        super(name, description);
    }
}
