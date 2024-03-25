package commands;

import exception.ArgumentException;
import models.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddIfMax extends  Command{
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        float maxPrice = -1;
        for(var t:collectionManager.getCollection())
        {
            if(t.getPrice() > maxPrice)
            {
                maxPrice = t.getPrice();
            }
        }

        var tick = OperationTicket();
        var price = tick.getPrice();
        while (true) {
            if (price > maxPrice) {
                collectionManager.addTicket(new Ticket(tick.getName(), tick.getCoordinates(), price, tick.getDiscount(), tick.getType().toString(), tick.getPerson()));break;
            } else {
                System.out.println("Введите повторно цену, цена должна быть больше максимальной цены во всей коллекции");
                while (true) {
                    boolean a = true;
                    System.out.println("Введите цену");
                    try {
                        price = Float.parseFloat(reader.readLine());
                        if (price <= 0) {
                            System.out.println("Ошибка: Цена должна быть больше 0.");
                            a = false;
                        }
                        if (a) {
                            break; // Если ввод корректный, выходим из цикла
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
                    }
                }

            }
        }
        namesOfCommands.add(getName());

    }

    public AddIfMax(String name, String description) {
        super(name, description);
    }
}
