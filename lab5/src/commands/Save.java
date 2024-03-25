package commands;

import exception.ArgumentException;
import models.Ticket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

public class Save extends Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        Collection<Ticket> tickets = collectionManager.getCollection();
        try (PrintWriter writer = new PrintWriter(new FileWriter(arguments))) {
            // Записываем заголовки столбцов
            writer.println("ID,Name,X,Y,CreationDate,Price,Discount,Type,Height,EyeColor,HairColor,Nationality,LocationX,LocationY,LocationName");

            // Записываем данные по каждому объекту Ticket
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    String ticketData = String.format(Locale.US,"%d,%s,%s,%s,%s,%.2f,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                            // Исправлено использование символа формата для Long (%d вместо %.2f)
                            ticket.getId(),
                            ticket.getName() != null ? ticket.getName() : "",
                            ticket.getCoordinates() != null ? ticket.getCoordinates().getX() : "",
                            ticket.getCoordinates() != null ? ticket.getCoordinates().getY() : "",
                            ticket.getCreationDate() != null ? ticket.getCreationDate() : "",
                            ticket.getPrice(),
                            ticket.getDiscount() != null ? ticket.getDiscount() : "",
                            ticket.getType() != null ? ticket.getType() : "",
                            ticket.getPerson() != null ? ticket.getPerson().getHeight() : "",
                            ticket.getPerson() != null && ticket.getPerson().getEyeColor() != null ? ticket.getPerson().getEyeColor().toString() : "",
                            ticket.getPerson() != null && ticket.getPerson().getHairColor() != null ? ticket.getPerson().getHairColor().toString() : "",
                            ticket.getPerson() != null && ticket.getPerson().getNationality() != null ? ticket.getPerson().getNationality().toString() : "",
                            ticket.getPerson() != null && ticket.getPerson().getLocation() != null ? String.valueOf(ticket.getPerson().getLocation().getX()) : "",
                            ticket.getPerson() != null && ticket.getPerson().getLocation() != null ? String.valueOf(ticket.getPerson().getLocation().getY()) : "",
                            ticket.getPerson() != null && ticket.getPerson().getLocation() != null && ticket.getPerson().getLocation().getName() != null ? ticket.getPerson().getLocation().getName() : "");
                    writer.println(ticketData);
                }
            }

            System.out.println("Данные успешно записаны в файл " + arguments);
        } catch (IOException e) {
            e.printStackTrace();
        }
        namesOfCommands.add(getName());
    }

    public Save(String name, String description) {
        super(name, description);
    }
}
