package commands;

package commands;

import exception.ArgumentException;
import manager.CollectionManager;
import models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Абстрактная команда с именем и описанием
 */
public abstract class Command implements Describable, Executable {
    private final String name;
    private final String description;
    public static CollectionManager collectionManager;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    static class FixedQueue<T> {
        private LinkedList<T> queue = new LinkedList<>();
        private final int maxSize;

        public FixedQueue(int maxSize) {
            this.maxSize = maxSize;
        }

        public void add(T element) {
            queue.add(element);
            if (queue.size() > maxSize) {
                queue.removeFirst();
            }
        }

        public void print() {
            for (T element : queue) {
                System.out.println(element);
            }
        }
    }

    protected static FixedQueue<String> namesOfCommands = new FixedQueue<String>(11);

    /**
     * @return Название и использование команды.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Описание команды.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) && Objects.equals(description, command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    public Ticket OperationTicket() throws ArgumentException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        while (true) {
            System.out.println("Введите имя");
            try {
                name = reader.readLine();
                if (name == null || name.trim().isEmpty() ) {
                    System.out.println("Ошибка: name cant be null or empy");
                }

                else break; // Если ввод корректный, выходим из цикла

            } catch (NumberFormatException | IOException e) {
                System.out.println("Ошибка: name cant be null or empy");
            }
        }

        // Ввод координат x
        float xCor;
        while (true) {
            System.out.println("Введите координату х для локации");
            try {
                boolean a1 = true;
                xCor = Float.parseFloat(reader.readLine());
                if (xCor <= -948) {
                    System.out.println("Ошибка: Координата x должна быть больше -948.");
                    a1 = false;
                }
                if(a1)
                {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
            }
        }

        // Ввод координат y
        Long yCor;
        while (true) {
            System.out.println("Введите координату у для локации");
            try {
                yCor = Long.parseLong(reader.readLine());
                if (yCor == null) {
                    System.out.println("Ошибка: Координата y не может быть null.");
                }
                break; // Если ввод корректный, выходим из цикла
            } catch (NumberFormatException | IOException e) {
                System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
            }
        }

        var cor = new Coordinates(xCor, yCor);

        // Генерация даты создания
        LocalDateTime creationDate = LocalDateTime.now();

        // Ввод цены
        float price;
        while (true) {
            boolean a = true;
            System.out.println("Введите цену");
            try {
                price = Float.parseFloat(reader.readLine());
                if (price <= 0) {
                    System.out.println("Ошибка: Цена должна быть больше 0."); a = false;
                }
                if(a)
                {
                    break; // Если ввод корректный, выходим из цикла
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
            }
        }

        // Ввод скидки
        Long discount = null;
        while (true) {
            System.out.println("Введите скидку");
            try {

                boolean a1 = true;
                String discountStr = reader.readLine();
                if (discountStr.trim().isEmpty() || discountStr == null) {
                    break;

                }
                if (!discountStr.trim().isEmpty() || discountStr != null) {
                    discount = Long.parseLong(discountStr);

                }

                if (discount <= 0 || discount > 100) {
                    System.out.println("Ошибка: Скидка должна быть больше 0 и не больше 100.");
                    a1 = false;
                }

                if(a1 == true)
                {
                    break; // конец работы
                }


            } catch (NumberFormatException | IOException e) {
                System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
            }
        }

        // Ввод типа билета
        String type = null;
        while (true) {
            System.out.println("Введите тип билета: VIP, USUAL, BUDGETARY, CHEAP");
            type = reader.readLine().toUpperCase();
            if(TicketType.check(type) == true)
                break; // Если ввод корректный, выходим из цикла
            else {
                System.out.println("Ошибка: Некорректный тип билета. Повторите ввод.");
            }
        }

        Person person = null;

        while (true) {
            System.out.println("Желаете добавить информацию о человеке? (да/нет)");

            String ans = reader.readLine().toUpperCase();
            if (ans.equals("YES")) {
                // Ввод роста человека
                float height;
                while (true) {
                    System.out.println("Введите рост человека");
                    try {
                        boolean a = true;
                        height = Float.parseFloat(reader.readLine());
                        if (height <= 0) {
                            System.out.println("Ошибка: Рост должен быть больше 0.");
                            a = false;
                        }
                        if(a) break; // Если ввод корректный, выходим из цикла

                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
                    }

                }
                // Ввод цвет глаз
                String eye = null;
                while (true) {
                    System.out.println("Введите цвет глаз: GREEN, WHITE, BROWN, ORANGE");
                    eye = reader.readLine().toUpperCase();
                    if(Color.check(eye) == true)
                        break; // Если ввод корректный, выходим из цикла
                    else if (eye == null || eye.trim().isEmpty()) {
                        eye = null;
                        break;

                    } else {
                        System.out.println("Ошибка: Некорректный цвет глаз. Повторите ввод.");
                    }
                }
                // Ввод цвет волос
                String hair = null;
                while (true) {
                    System.out.println("Введите цвет волос: GREEN, WHITE, BROWN, ORANGE");
                    hair = reader.readLine().toUpperCase();
                    if(Color.check(hair) == true)
                        break; // Если ввод корректный, выходим из цикла
                    else {
                        System.out.println("Ошибка: Некорректный цвет волос. Повторите ввод.");
                    }
                }
                // Ввод национальность
                String nationality = null;
                while (true) {
                    System.out.println("Введите национальность: USA, UNITED_KINGDOM, SPAIN, THAILAND");
                    nationality = reader.readLine().toUpperCase();
                    if(Country.check(nationality) == true)
                        break; // Если ввод корректный, выходим из цикла
                    else if (nationality == null || nationality.trim().isEmpty()) {
                        nationality = null;
                        break;

                    } else {
                        System.out.println("Ошибка: Некорректная национальность. Повторите ввод.");
                    }
                }
                Location loc = null;
                // Ввод локации x
                Long xLoc;
                while (true) {
                    System.out.println("Введите локацию(х) человека");
                    try {
                        xLoc = Long.parseLong(reader.readLine());
                        if (xLoc == null) {
                            System.out.println("Ошибка: Координата x локации не может быть null.");
                        }
                        break; // Если ввод корректный, выходим из цикла
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
                    }
                }
                // Ввод локации y
                Double yLoc;
                while (true) {
                    System.out.println("Введите локацию(у) человека");
                    try {
                        yLoc = Double.parseDouble(reader.readLine());
                        if (yLoc == null) {
                            System.out.println("Ошибка: Координата y локации не может быть null.");
                        }
                        break; // Если ввод корректный, выходим из цикла
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Некорректный формат числа. Повторите ввод.");
                    }
                }
                // Ввод названия локации
                String nameLoc = null;
                while (true) {
                    System.out.println("Введите локацию (название) человека");
                    nameLoc = reader.readLine();
                    if (nameLoc.length() > 370 && !nameLoc.trim().isEmpty() && nameLoc != null) {
                        System.out.println("Ошибка: Длина названия локации не может быть больше 370. Повторите ввод.");
                    } else if (nameLoc.trim().isEmpty() || nameLoc == null) {
                        nameLoc = null;
                        break;
                    }
                    else {
                        break; // Если ввод корректный, выходим из цикла
                    }
                }
                loc = new Location(xLoc, yLoc, nameLoc);
                person = new Person(height,eye,hair,nationality,loc);
                break;

            } else if (ans.equals("NO")) {
                break;

            }
            else System.out.println("Некорректный ввод ответа, повторите");
        }
        return (new Ticket(name, cor, price, discount, type, person));
    }

}

