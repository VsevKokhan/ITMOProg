package commands;

import exception.ArgumentException;
import models.Ticket;

import java.io.IOException;

public class Update extends Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        int id;
        while (true) {
            try {

                id = Integer.parseInt(arguments);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        var col = collectionManager.getCollection();
        Ticket tickUp = null;
        for (var tick:col)
        {
            if (tick.getId() == id)
            {
                System.out.println(tick.toString());
                tickUp = tick;

            }
        }
        Ticket t = OperationTicket();
        tickUp.setName(t.getName());
        tickUp.setCoordinates(t.getCoordinates());
        tickUp.setPrice(t.getPrice());
        tickUp.setDiscount(t.getDiscount());
        tickUp.setType(t.getType().toString());
        tickUp.setPerson(t.getPerson());

        System.out.println("Изменения введены!");
        namesOfCommands.add(getName());

    }

    public Update(String name, String description) {
        super(name, description);
    }
}
