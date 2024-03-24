package models;

import exception.ArgumentException;

import java.time.LocalDateTime;

/**
 * Класс билетов
 */
public class Ticket  implements Comparable<Ticket>{
    private Integer id = 0;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;//Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates;//Поле не может быть null
    private LocalDateTime creationDate;//Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price;//Значение поля должно быть больше 0
    private Long discount;//Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private TicketType type;//Поле не может быть null
    private Person person;//Поле может быть null
    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public void setType(String type) {
        switch(type)
        {
            case "CHEAP":this.type = TicketType.CHEAP;break;
            case "VIP":this.type = TicketType.VIP;break;
            case "USUAL":this.type = TicketType.USUAL;break;
            case "BUDGETARY":this.type = TicketType.BUDGETARY;break;
        }
        try {
            if (this.type == null) throw new ArgumentException("type can't be null!");
        }
        catch (ArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void setPerson(Person person) {
        this.person = person;
    }




    public Ticket(String name, Coordinates coordinates, float price, Long discount, String _type, Person person) throws ArgumentException {

        try {
            if (name == null || name == "") throw new ArgumentException("name can't be empty or null!");
            this.name = name;
        }
        catch (ArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        try {
            if (coordinates == null) throw new ArgumentException("coordinates can't be null!");
            this.coordinates = coordinates;
        }
        catch (ArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        creationDate = java.time.LocalDateTime.now();

        try {
            if (price < 0) throw new ArgumentException("price can't be less 0!");
            this.price = price;
        }
        catch (ArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        try {
            if (discount!= null &&(discount > 100 || discount <= 0))
                throw new ArgumentException("discount must be less 100 and more 0");
            this.discount = discount;
        }
        catch (ArgumentException e)
        {
            System.out.println(e.getMessage());
        }


        switch(_type)
        {
            case "CHEAP":type = TicketType.CHEAP;break;
            case "VIP":type = TicketType.VIP;break;
            case "USUAL":type = TicketType.USUAL;break;
            case "BUDGETARY":type = TicketType.BUDGETARY;break;
        }
        try {
            if (type == null) throw new ArgumentException("type can't be null!");
        }
        catch (ArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        this.person = person;
    }
    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getPrice() {
        return price;
    }

    public Long getDiscount() {
        return discount;
    }

    public TicketType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    @Override
    public int compareTo(Ticket other) {
        return id.compareTo(other.getId());
    }
    @Override
    public String toString()
    {
        String ans = "";
        ans += "id: " + ((Integer)getId()).toString() + "\n";
        ans += "Name: " +getName().toString() + "\n";

        var cor = getCoordinates();
        ans+= "coordinates X: " +((Float)cor.getX()).toString()+ "\n";
        ans+= "coordinates Y: " + cor.getY().toString()+ "\n";

        ans+= "Date: " +getCreationDate().toString()+ "\n";
        ans += "Price: " +((Float)getPrice()).toString()+ "\n";
        ans += "Discount: " + (getDiscount() == null ? "" : getDiscount().toString())+ "\n";
        ans += "TicketType: " +getType().toString() + "\n";

        var pers = getPerson();
        if(pers != null) {
            ans += "Person height: " +((Float)pers.getHeight()).toString()+ "\n";
            ans += "Person EyeColor: " +(pers.getEyeColor() == null ? "" : pers.getEyeColor().toString())+ "\n";
            ans += "Person HairColor: " +pers.getHairColor().toString()+ "\n";
            ans+=  "Person Nationality: " +(pers.getNationality() == null ? "" : pers.getNationality().toString()) +  "\n";

            var loc = pers.getLocation();
            ans += "location x: " + loc.getX().toString()+ "\n";
            ans += "location y: " + loc.getY().toString()+ "\n";
            ans += "location name: " + (loc.getName() == null ? "" : loc.getName()) +"\n";
        }
        else ans += "person is : null";
        return ans;
    }


}
