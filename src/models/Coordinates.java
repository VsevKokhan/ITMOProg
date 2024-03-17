package models;

import exception.ArgumentException;
/**
 * класс координат
 */
public class Coordinates {
    private float x;//Значение поля должно быть больше -948
    private Long y;//Поле не может быть null

    public float getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public Coordinates(float x, Long y) throws ArgumentException {
        if(x <= -948 ) throw new ArgumentException("x must be more -948!");
        this.x = x;

        this.y = y;
        if(y == null ) throw new ArgumentException("y cant be null!");
    }


}
