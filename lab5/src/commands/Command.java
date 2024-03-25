package commands;

import com.zetcode.project;
import exception.ArgumentException;
import manager.CollectionManager;
import models.*;
import org.w3c.dom.css.CSSStyleDeclaration;

import javax.naming.Name;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Commands {
    private static CollectionManager collectionManager;
    public Command(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
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

    private static FixedQueue<String> namesOfCommands = new FixedQueue<String>(11);
    /**
     * Команда 'help'. Добавляет новый элемент в коллекцию.
     */
    public static void help()
    {
        namesOfCommands.add("help");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add  : добавить новый элемент в коллекцию");
        System.out.println("update id :обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("add remove_by_id  : удалить элемент из коллекции по его id : добавить новый элемент в коллекцию");
        System.out.println("clear : очистить коллекцию");
        System.out.println("execute_script file_name : считать и" +
                "исполнить скрипт из указанного файла.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("add_if_max  : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("remove_lower : удалить из" +
                "коллекции все элементы, меньшие, чем заданный");
        System.out.println("history : вывести последние 11 команд (без их аргументов)");
        System.out.println("filter_contains_name name : вывести" +
                "элементы, значение поля name которых содержит заданную подстроку");
        System.out.println("print_ascending : вывести элементы коллекции в порядке возрастания");
        System.out.println(" print_descending : вывести элементы коллекции в порядке убывания");
    }
    /**
     * Команда 'exit'. Выполняет выход из программы
     */
    public static void exit()
    {
        System.exit(0);
        namesOfCommands.add("exit");
    }

    /**
     * Команда 'history'. Выводит последние 11 команд
     */
    public static void history()
    {
        namesOfCommands.print();

    }
    /**
     * Команда 'show'. Выводит все элементы коллекции
     */
    public static void show()
    {
        var col = collectionManager.getCollection();
        for (var tick: col)
        {
            System.out.println(tick.toString());

        }
        namesOfCommands.add("show");
    }
    public static int idForShowForUpdate;
    public static Ticket showforupdate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id;
        while (true) {
            try {

                int id1 = idForShowForUpdate; //!!!!!!!! тут вбивается id
                id = id1;
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        var col = collectionManager.getCollection();
        Ticket tickNow = null;

        for (var tick:col)
        {
            if (tick.getId() == id)
            {
                System.out.println(tick.toString());

            }
        }
        return tickNow;


    }

    /**
     * Команда 'add'. Добавляет новый элемент в коллекцию
     */
    public static void add() throws IOException, ArgumentException {
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

            } catch (NumberFormatException e) {
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
            } catch (NumberFormatException e) {
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
            } catch (NumberFormatException e) {
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


            } catch (NumberFormatException e) {
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

        collectionManager.addTicket(new Ticket(name, cor, price, discount, type, person));
        namesOfCommands.add("add");
        System.out.println("элемент добавлен");
    }
    /**
     * Команда 'update'. Обновляет значение коллекции, id которой равен заданному
     */
    public static void update() throws IOException, ArgumentException {
        var tick = showforupdate();

        if(tick == null) System.out.println("не существует ticket с таким id");
        else {
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

                } catch (NumberFormatException e) {
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
                } catch (NumberFormatException e) {
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
                } catch (NumberFormatException e) {
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


                } catch (NumberFormatException e) {
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

            tick.setName(name);
            tick.setCoordinates(cor);
            tick.setPrice(price);
            tick.setDiscount(discount);
            tick.setType(type);
            tick.setPerson(person);

            System.out.println("Изменения введены!");
        }
        namesOfCommands.add("update");
    }
    public static int IDForDelete; // id для удаления экземпляра
    /**
     * Команда 'remove_by_id'. Удаляет элемент из коллекции по его id
     */
    public static void remove_by_id() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        var tickets = collectionManager.getCollection();

        while (true) {
            if (IDForDelete > tickets.size() || IDForDelete < 0) {
                System.out.println();
                System.out.println("нет нужного id");
                System.out.println();
                System.out.println("Введите другой id");
                IDForDelete = Integer.parseInt( reader.readLine());


            } else {
                Iterator<Ticket> iterator = tickets.iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getId() == IDForDelete) {
                        iterator.remove(); // Удаляем элемент из коллекции
                        break;
                    }
                }

                // Обновляем id всех элементов, чьи id были больше удаленного элемента
                for (Ticket ticket : tickets) {
                    if (ticket.getId() > IDForDelete) {
                        ticket.setId(ticket.getId() - 1); // Уменьшаем id на 1
                    }
                }

                System.out.println();
                System.out.println("Удалили элемент по id");break;
            }
        }
        namesOfCommands.add("remove_by_id");
    }
    /**
     * Команда 'clear'. Oчищает коллекцию
     */
    public static void clear()
    {
        collectionManager.getCollection().clear();
        System.out.println("Очистили коллекцию");
        namesOfCommands.add("clear");
    }
    /**
     * Команда 'info'. Выводит информацию о коллекции
     */
    public static void info()
    {
        var col = collectionManager.getCollection();
        System.out.println();
        System.out.println("Элементов в коллекции: "  + col.size());
        for (var tick:col)
        {
            System.out.println("Дата инициализации: " + tick.getCreationDate());break;
        }
        System.out.println();
        System.out.println("Тип класса: "  + col.getClass().getName());
        namesOfCommands.add("info");


    }
    /**
     * Команда 'save'. Сохраняет коллекцию в файл
     */
    public static void save(String fileName) {
        Collection<Ticket> tickets = collectionManager.getCollection();
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Записываем заголовки столбцов
            writer.println("ID,Name,X,Y,CreationDate,Price,Discount,Type,Height,EyeColor,HairColor,Nationality,LocationX,LocationY,LocationName");

            // Записываем данные по каждому объекту Ticket
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    String ticketData = String.format(Locale.US,"%d,%s,%s,%s,%s,%.2f,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                            // Исправлено использование символа формата для Long (%d вместо %.2f)
                            ticket.getId(),
                            ticket.getName() != null ? ticket.getName() : "",
                            ticket.getCoordinates() != null ? ticket.getCoordinates().getX() : "",
                            ticket.getCoordinates() != null ? ticket.getCoordinates().getY() : "",
                            ticket.getCreationDate() != null ? ticket.getCreationDate() : "",
                            ticket.getPrice(),
                            ticket.getDiscount() != null ? ticket.getDiscount() : "",
                            ticket.getType() != null ? ticket.getType() : "",
                            ticket.getPerson() != null ? ticket.getPerson().getHeight() : "",
                            ticket.getPerson() != null && ticket.getPerson().getEyeColor() != null ? ticket.getPerson().getEyeColor().toString() : "",
                            ticket.getPerson() != null && ticket.getPerson().getHairColor() != null ? ticket.getPerson().getHairColor().toString() : "",
                            ticket.getPerson() != null && ticket.getPerson().getNationality() != null ? ticket.getPerson().getNationality().toString() : "",
                            ticket.getPerson() != null && ticket.getPerson().getLocation() != null ? String.valueOf(ticket.getPerson().getLocation().getX()) : "",
                            ticket.getPerson() != null && ticket.getPerson().getLocation() != null ? String.valueOf(ticket.getPerson().getLocation().getY()) : "",
                            ticket.getPerson() != null && ticket.getPerson().getLocation() != null && ticket.getPerson().getLocation().getName() != null ? ticket.getPerson().getLocation().getName() : "");
                    writer.println(ticketData);
                }
            }

            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        namesOfCommands.add("save");
    }
    /**
     * Команда 'add_if_max'. Добавляет новый элемент в коллекцию, если его цена превышает цену наибольшего элемента этой коллекции
     */
    public static void add_if_max() throws IOException, ArgumentException {
        //берём по прайсу
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

            } catch (NumberFormatException e) {
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
            } catch (NumberFormatException e) {
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
            } catch (NumberFormatException e) {
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


            } catch (NumberFormatException e) {
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

                    } catch (NumberFormatException | IOException e) {
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

        float maxPrice = -1;
        for(var t:collectionManager.getCollection())
        {
            if(t.getPrice() > maxPrice)
            {
                maxPrice = t.getPrice();
            }
        }

        while (true) {
            if (price > maxPrice) {
                collectionManager.addTicket(new Ticket(name, cor, price, discount, type, person));break;
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
        namesOfCommands.add("add_if_max");

    }
    /**
     * Команда 'remove_lower'. Удаляет элементы из коллекции меньшие чем цена нового элемента
     */
    public static void remove_lower() throws ArgumentException, IOException {
        // удаляем по price
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

            } catch (NumberFormatException e) {
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
            } catch (NumberFormatException e) {
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
            } catch (NumberFormatException e) {
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


            } catch (NumberFormatException e) {
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
        ArrayList<Integer> ids = new ArrayList<Integer>();
        Iterator<Ticket> it = collectionManager.getCollection().iterator();
        while(it.hasNext())
        {
            Ticket ticket = it.next();
            if(ticket.getPrice() < price)
            {
                ids.add(ticket.getId());
            }
        }

        collectionManager.addTicket(new Ticket(name,cor,price,discount,type,person));
        var tickets = collectionManager.getCollection();
        Iterator<Ticket> iterator = tickets.iterator();
        for (int i = 0; i < ids.size(); i++)
        {
            IDForDelete = ids.get(i);

            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                if (ticket.getId() == IDForDelete) {
                    iterator.remove(); // Удаляем элемент из коллекции
                    break;
                }
            }
        }

        System.out.println("Удалены элементы в которых price ниже нового price элемента");
        namesOfCommands.add("remove_lower");

    }
    public static String NameForFilter;
    /**
     * Команда 'filter_contains_name'. Выводит элементы, значение поля name, содержащие данную подстроку
     */
    public static void filter_contains_name()
    {
        var tickets = collectionManager.getCollection();
        for (var tick:tickets)
        {
            if(tick.getName().contains(NameForFilter))
            {
                System.out.println(tick.toString());

            }


        }
        System.out.println("Элементы выведены");
        namesOfCommands.add("filter_contains_name");
    }
    /**
     * Команда 'print_ascending'. Выводит элементы коллекции в порядке возрастания
     */
    public static void print_ascending() {
        for (var tick:collectionManager.getCollection())
        {
            System.out.println();
            System.out.println("ticket id: " + tick.getId());
            System.out.println("ticket name: " + tick.getName());

            var cor = tick.getCoordinates();
            System.out.println("coordinates x: " + cor.getX());
            System.out.println("coordinates y: " + cor.getY());

            System.out.println("ticket CreationDate: " + tick.getCreationDate());
            System.out.println("ticket price: " +tick.getPrice());
            System.out.println("ticket discount: " +tick.getDiscount());
            System.out.println("ticket type: " +tick.getType());

            var pers = tick.getPerson();
            if(pers != null) {
                System.out.println("person height: " + pers.getHeight());
                System.out.println("person eyecolor: " + pers.getEyeColor());
                System.out.println("person haircolor: " + pers.getHairColor());
                System.out.println("person nationality: " + pers.getNationality());

                var loc = pers.getLocation();
                System.out.println("location x: " + loc.getX());
                System.out.println("location y: " + loc.getY());
                System.out.println("location name: " + loc.getName());
            }
            else System.out.println("person is : " + pers);
        }
        namesOfCommands.add("print_ascending");
    }
    /**
     * Команда 'print_descending'. Выводит элементы коллекции в порядке убывания
     */
    public static void print_descending()
    {
        TreeSet<Ticket> ts = (TreeSet<Ticket>) collectionManager.getCollection();
        Iterator<Ticket> iterator = ts.descendingIterator();
        while (iterator.hasNext()) {
            var tick = iterator.next();
            System.out.println();
            System.out.println("ticket id: " + tick.getId());
            System.out.println("ticket name: " + tick.getName());

            var cor = tick.getCoordinates();
            System.out.println("coordinates x: " + cor.getX());
            System.out.println("coordinates y: " + cor.getY());

            System.out.println("ticket CreationDate: " + tick.getCreationDate());
            System.out.println("ticket price: " +tick.getPrice());
            System.out.println("ticket discount: " +tick.getDiscount());
            System.out.println("ticket type: " +tick.getType());

            var pers = tick.getPerson();
            if(pers != null) {
                System.out.println("person height: " + pers.getHeight());
                System.out.println("person eyecolor: " + pers.getEyeColor());
                System.out.println("person haircolor: " + pers.getHairColor());
                System.out.println("person nationality: " + pers.getNationality());

                var loc = pers.getLocation();
                System.out.println("location x: " + loc.getX());
                System.out.println("location y: " + loc.getY());
                System.out.println("location name: " + loc.getName());
            }
            else System.out.println("person is : " + pers);
        }
        namesOfCommands.add("print_descending");
    }
    /**
     * Команда 'execute_script'. считать и исполнить скрипт из указанного файла
     */
    public static void execute_script(String file_name) throws IOException, ArgumentException {
        InputStream inputStream = new FileInputStream(file_name);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String functionName = parts[0];
            String argument = parts.length > 1 ? parts[1] : null;

            // Вызываем соответствующую функцию в зависимости от имени
            switch (functionName) {
                case "info":
                    info();
                    break;
                case "show":
                    show();
                    break;
                case "add":
                    add();
                    break;
                case "update":
                    if(argument == null)
                    {
                        System.out.println("нет аргумента");
                    }
                    else idForShowForUpdate =Integer.parseInt(argument); update();

                    break;
                case "remove_by_id":
                    if (argument != null) {
                        try {
                            IDForDelete = Integer.parseInt(argument);
                            remove_by_id();
                        } catch (IOException e) {
                            System.out.println("неправильно введён аргумент");
                        }
                    } else {
                        System.out.println("Введите команду с аргументом");
                    }
                break;
                case "clear":
                    clear();
                    break;
                case "save":
                    save("C:\\Users\\сева\\Desktop\\ert1.txt");
                    break;
                case "add_if_max":
                    add_if_max();
                    break;
                case "remove_lower":
                    remove_lower();
                    break;
                case "filter_contains_name":
                    if (argument == null)
                    {
                        System.out.println("Аргумент = null");
                    }
                    else NameForFilter = argument; filter_contains_name();
                    break;
                case"print_ascending":
                    print_ascending();
                    break;
                case "print_descending":
                    print_descending();
                    break;
                case "history":
                    history();
                    break;

                    // Добавьте другие функции по мере необходимости
                default:
                    System.out.println("Неизвестная функция: " + functionName);
                    break;
            }
        }
        namesOfCommands.add("execute_script");
    }

}

