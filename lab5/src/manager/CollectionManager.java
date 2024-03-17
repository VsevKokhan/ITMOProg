package manager;

import models.Ticket;

import java.util.Collection;
import java.util.TreeSet;
/**
 * класс распоряжается коллекцией
 */
public class CollectionManager {
    private final Collection<Ticket> collection;
    private int nextId;

    public CollectionManager() {
        this.collection = new TreeSet<Ticket>();
        this.nextId = 1; // Начинаем с 1
    }

    public Collection<Ticket> getCollection() {
        return collection;
    }

    public int getNextId() {
        return nextId;
    }

    public void addTicket(Ticket ticket) {
        ticket.setId(nextId++); // Присваиваем текущее значение nextId, а затем увеличиваем его на 1
        collection.add(ticket);
    }


}
