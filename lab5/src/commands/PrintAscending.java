package commands;

import exception.ArgumentException;
import models.Ticket;

import java.io.IOException;
import java.util.Comparator;
import java.lang.Long;
public class PrintAscending extends Command{
    class PrintPriceComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return ((Float)(o1.getPrice())).compareTo((Float)o2.getPrice());
        }
    }
    class PrintCoordinatesComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            Float x1 = o1.getCoordinates().getX();
            Float y1 = o1.getCoordinates().getY().floatValue();
            Float Co1 = (float)(Math.sqrt(x1 * x1 + y1 * y1));

            Float x2 = o2.getCoordinates().getX();
            Float y2 = o2.getCoordinates().getY().floatValue();
            Float Co2 = (float)(Math.sqrt(x1 * x1 + y1 * y1));

            return Co1.compareTo(Co2);
        }
    }
    class PrintCoordinatesAndPriceComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            Float x1 = o1.getCoordinates().getX();
            Float y1 = o1.getCoordinates().getY().floatValue();
            Float Co1 = (float)(Math.sqrt(x1 * x1 + y1 * y1));

            Float x2 = o2.getCoordinates().getX();
            Float y2 = o2.getCoordinates().getY().floatValue();
            Float Co2 = (float)(Math.sqrt(x1 * x1 + y1 * y1));

            Float P1 = o1.getPrice();
            Float P2 = o2.getPrice();

            return ((Float)(Co1 + P1)).compareTo(Co2 + P2);
        }
    }


    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        var collection = collectionManager.getCollection();




    }

    public PrintAscending(String name, String description) {
        super(name, description);
    }
}
