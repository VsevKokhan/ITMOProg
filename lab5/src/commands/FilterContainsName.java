package commands;

import exception.ArgumentException;

import java.io.IOException;

public class FilterContainsName extends Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        var tickets = collectionManager.getCollection();
        for (var tick:tickets)
        {
            if(tick.getName().contains(arguments))
            {
                System.out.println(tick.toString());

            }

        }
        System.out.println("Элементы выведены");
        namesOfCommands.add(getName());
    }

    public FilterContainsName(String name, String description) {
        super(name, description);
    }
}
