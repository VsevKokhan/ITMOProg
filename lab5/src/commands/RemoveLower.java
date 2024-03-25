package commands;

import exception.ArgumentException;
import models.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class RemoveLower extends Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Ticket tick = OperationTicket();
        var price = tick.getPrice();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        Iterator<Ticket> it = collectionManager.getCollection().iterator();
        while(it.hasNext())
        {
            Ticket ticket = it.next();
            if(ticket.getPrice() < price)
            {
                ids.add(ticket.getId());
            }
        }

        collectionManager.addTicket(new Ticket(tick.getName(),tick.getCoordinates(),price,tick.getDiscount(),tick.getType().toString(),tick.getPerson()));
        var tickets = collectionManager.getCollection();
        Iterator<Ticket> iterator = tickets.iterator();
        for (int i = 0; i < ids.size(); i++)
        {
            int id = ids.get(i);

            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                if (ticket.getId() == id) {
                    iterator.remove(); // Удаляем элемент из коллекции
                    break;
                }
            }
        }

        System.out.println("Удалены элементы в которых price ниже нового price элемента");
        namesOfCommands.add(getName());

    }

    public RemoveLower(String name, String description) {
        super(name, description);
    }
}
