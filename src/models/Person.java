package models;

import exception.ArgumentException;
/**
 * Класс людей
 */
public class Person {

    public float getHeight() {
        return height;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }
    private float height;//Значение поля должно быть больше 0
    private Color eyeColor;//Поле может быть null
    private Color hairColor;//Поле не может быть null
    private Country nationality;//Поле может быть null
    private Location location;//Поле не может быть null

    public Person(float height, String _eyeColor, String _hairColor, String _nationality, Location location) throws ArgumentException {
        if(height < 0) throw new ArgumentException("height must be more 0!");
        this.height = height;
        if (_eyeColor != null ) {

            switch (_eyeColor) {
                case "ORANGE":
                    eyeColor = Color.ORANGE;
                    break;
                case "BROWN":
                    eyeColor = Color.BROWN;
                    break;
                case "GREEN":
                    eyeColor = Color.GREEN;
                    break;
                case "WHITE":
                    eyeColor = Color.WHITE;
                    break;
            }
        }
        switch(_hairColor)
        {
            case "BROWN":hairColor = Color.BROWN;break;
            case "GREEN":hairColor = Color.GREEN;break;
            case "WHITE":hairColor = Color.WHITE;break;
            case "ORANGE":hairColor = Color.ORANGE;break;
        }
        if(hairColor == null) throw new ArgumentException("haircolor  cant be null!");

        if(_nationality != null) {
            switch (_nationality) {
                case "UNITED_KINGDOM":
                    nationality = Country.UNITED_KINGDOM;
                    break;
                case "USA":
                    nationality = Country.USA;
                    break;
                case "SPAIN":
                    nationality = Country.SPAIN;
                    break;
                case "THAILAND":
                    nationality = Country.THAILAND;
                    break;
            }
        }
        this.location = location;
        if(this.location == null) throw new ArgumentException("location cant be null!");
    }


}

