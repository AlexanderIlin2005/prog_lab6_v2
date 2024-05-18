package client;

import client.managers.CommandManager;
import client.utility.Console;
import client.utility.Interrogator;
import client.utility.Runner;
import client.utility.StandardConsole;
import client.commands.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        while (true){
            try {
                Scanner sc = new Scanner(System.in);

                TCPClient tcpClient = new TCPClient();
                tcpClient.connect();


                Interrogator.setUserScanner(new Scanner(System.in));
                Console console = new StandardConsole();

                CommandManager commandManager = new CommandManager() {{
                    register("clear", new Clear(console, tcpClient));
                    register("count_by_meters_above_sea_level", new CountByMetersAboveSeaLevel(console, tcpClient));
                    register("exit", new Exit(console));
                    register("group_counting_by_area", new GroupCountingByArea(console, tcpClient));
                    register("help", new Help(console, this));
                    register("info", new Info(console, tcpClient));
                    register("insert", new Insert(console, tcpClient));
                    register("min_by_population", new MinByPopulation(console, tcpClient));
                    register("remove_greater_key", new RemoveGreaterKey(console, tcpClient));
                    register("remove_key", new RemoveKey(console, tcpClient));
                    register("replace_if_greater", new ReplaceIfGreater(console, tcpClient));
                    register("replace_if_lower", new ReplaceIfLower(console, tcpClient));
                    register("show", new Show(console, tcpClient));
                    register("update_id", new UpdateId(console, tcpClient));
                    register("execute_script", new ExecuteScript(console));
                }};

                new Runner(console, commandManager).interactiveMode();
            } catch (RuntimeException e){
                System.out.println("");
            }
        }


    }
}