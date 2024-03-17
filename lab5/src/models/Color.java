package models;
/**
 * Класс цветов
 */
public enum Color {
    GREEN,
    WHITE,
    BROWN,
    ORANGE;
    public static boolean check(String type) {
        for (Color color : Color.values()) {
            if (color.name().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
