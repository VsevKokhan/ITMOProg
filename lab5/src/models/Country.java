package models;
/**
 * Класс Стран
 */
public enum Country {
    UNITED_KINGDOM,
    USA,
    SPAIN,
    THAILAND;
    public static boolean check(String type) {
        for (Country c : Country.values()) {
            if (c.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
