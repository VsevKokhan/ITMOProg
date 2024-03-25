package commands;

import exception.ArgumentException;
import models.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class RemoveById extends  Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        var tickets = collectionManager.getCollection();
        int id = Integer.parseInt(arguments);

        while (true) {
            if (id > tickets.size() || id < 0) {
                System.out.println();
                System.out.println("нет нужного id");
                System.out.println();
                System.out.println("Введите другой id");
                id = Integer.parseInt( reader.readLine());


            } else {
                Iterator<Ticket> iterator = tickets.iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getId() == id) {
                        iterator.remove(); // Удаляем элемент из коллекции
                        break;
                    }
                }

                // Обновляем id всех элементов, чьи id были больше удаленного элемента
                for (Ticket ticket : tickets) {
                    if (ticket.getId() > id) {
                        ticket.setId(ticket.getId() - 1); // Уменьшаем id на 1
                    }
                }

                System.out.println();
                System.out.println("Удалили элемент по id");break;
            }
        }
        namesOfCommands.add(getName());
    }

    public RemoveById(String name, String description) {
        super(name, description);
    }
}
