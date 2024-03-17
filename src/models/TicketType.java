package models;
/**
 * Класс типов билета
 */
public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;
    public static boolean check(String type) {
        for (TicketType ticketType : TicketType.values()) {
            if (ticketType.name().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
