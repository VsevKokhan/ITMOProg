package commands;

import exception.ArgumentException;

import java.io.IOException;

public class Help extends Command {
    @Override
    public void apply(String arguments) throws ArgumentException, IOException {
        namesOfCommands.add(getName());
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

    public Help(String name, String description) {
        super(name, description);
    }
}
