package models;

import exception.ArgumentException;
/**
 * Класс локации
 */
public class Location {
    private final Long x;//Поле не может быть null
    private Double y;//Поле не может быть null
    private String name;//Длина строки не должна быть больше 370, Поле может быть nul

    public Long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public Location(Long x, Double y, String name) throws ArgumentException {
            if (x == null) throw new ArgumentException("X can't be null!");
            this.x = x;

        if(y == null ) throw new ArgumentException("Y can't be null!");
        this.y = y;

        if(name != null && name.length() > 370 ) System.out.println("name can't be more 370 symbols!");
        this.name = name;
    }


}
