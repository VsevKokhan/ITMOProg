package com.zetcode;



import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.TreeSet;
import java.util.concurrent.Callable;

import commands.Command;
import exception.ArgumentException;
import manager.CollectionManager;
import models.*;
//"C:\\Users\\сева\\Desktop\\ert1.txt"
public class project {

    public static void main(String[] args) throws IOException, ArgumentException {

        var fileName = "FileRead.csv";
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);



        String linefr;

        CollectionManager collectionManager = new CollectionManager();

        while ((linefr = br.readLine()) != null) {
            String[] nextLine = linefr.split(",");
            collectionManager.addTicket(new Ticket(nextLine[0], new Coordinates(Float.parseFloat(nextLine[1]),Long.parseLong(nextLine[2])),Float.parseFloat(nextLine[3]),Long.parseLong(nextLine[4]),nextLine[5],new Person(Float.parseFloat(nextLine[6]),nextLine[7],nextLine[8],nextLine[9],new Location(Long.parseLong(nextLine[10]),Double.parseDouble(nextLine[11]), nextLine[12]))));
        }
        Command com = new Command(collectionManager);

        while(true)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            var line = reader.readLine();
            String[] parts = line.split(" ");
            String functionName = parts[0];
            String argument = parts.length > 1 ? parts[1] : null;
            switch (functionName) {
                case "info":
                    Command.info();
                    break;
                case "show":
                    Command.show();
                    break;
                case "add":
                    Command.add();
                    break;
                case "update":
                    if(argument == null)
                    {
                        System.out.println("нет аргумента");
                    }
                    else Command.idForShowForUpdate =Integer.parseInt(argument); Command.update();

                    break;
                case "remove_by_id":
                    if (argument != null) {
                        try {
                            Command.IDForDelete = Integer.parseInt(argument);
                            Command.remove_by_id();
                        } catch (IOException e) {
                            System.out.println("неправильно введён аргумент");
                        }
                    } else {
                        System.out.println("Введите команду с аргументом");
                    }
                    break;
                case "clear":
                    Command.clear();
                    break;
                case "save":
                    Command.save("SaveFile.csv");
                    break;
                case "add_if_max":
                    Command.add_if_max();
                    break;
                case "remove_lower":
                    Command.remove_lower();
                    break;
                case "filter_contains_name":
                    if (argument == null)
                    {
                        System.out.println("Аргумент = null");
                    }
                    else {Command.NameForFilter = argument; Command.filter_contains_name();};
                    break;
                case"print_ascending":
                    Command.print_ascending();
                    break;
                case "print_descending":
                    Command.print_descending();
                    break;
                case "history":
                    Command.history();
                    break;
                case "help":
                    Command.help();
                    break;
                case "exit":
                    Command.exit();
                    break;
                case "execute_script":
                    if(argument == null) {
                    System.out.println("не указан файл");
                    }
                    else {
                        Command.execute_script(argument);
                    }
                    break;


                // Добавьте другие функции по мере необходимости
                default:
                    System.out.println("Неизвестная функция: " + functionName);
                    break;
            }


        }



//C:\Users\сева\Desktop\pop.txt








    }
}
