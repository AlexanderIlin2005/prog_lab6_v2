package server;

import java.io.IOException;
import java.util.Scanner;

import BaseModel.City;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.DumpManager;
import server.utility.Console;
import server.utility.Runner;
import server.utility.StandardConsole;
import server.commands.*;

import javax.xml.bind.JAXBException;

import static java.lang.System.exit;


public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        Scanner sc = new Scanner(System.in);
        StandardConsole console = new StandardConsole();

        /*
        if (args.length == 0){
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }

         */

        DumpManager dumpManager = new DumpManager(console, "data.xml"); //args[0]
        CollectionManager collectionManager = new CollectionManager(dumpManager, console);

        City.updateNextId(collectionManager);
        collectionManager.validateAll(console);

        server.managers.CommandManager commandManager = new CommandManager() {{
            register("clear", new Clear(console, collectionManager));
            register("count_by_meters_above_sea_level", new CountByMetersAboveSeaLevel(console, collectionManager));
            register("exit", new Exit(console));
            register("group_counting_by_area", new GroupCountingByArea(console, collectionManager));
            register("info", new Info(console, collectionManager));
            register("insert", new Insert(console, collectionManager));
            register("min_by_population", new MinByPopulation(console, collectionManager));
            register("remove_greater_key", new RemoveGreaterKey(console, collectionManager));
            register("remove_key", new RemoveKey(console, collectionManager));
            register("replace_if_greater", new ReplaceIfGreater(console, collectionManager));
            register("replace_if_lower", new ReplaceIfLower(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("update_id", new UpdateId(console, collectionManager));
        }};

        TCPServer server = new TCPServer(commandManager);

        Thread serverThread = new Thread(() -> {
            try {
                server.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        Thread inputThread = new Thread(() -> {
            while (true) {
                String input = sc.nextLine();
                if (input.equals("exit")) {
                    System.out.println("exit");
                    collectionManager.saveCollection("data.xml", console);
                    exit(0);
                } else if (input.startsWith("save")) {
                    String[] parts = input.split(" ");
                    if (parts.length == 2) {
                        String filePath = parts[1];
                        collectionManager.saveCollection(filePath, console);
                    } else {
                        System.out.println("Введите save path/to/xml/file'");
                    }
                }
            }
        });
        inputThread.start();


    }


}