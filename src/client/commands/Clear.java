package client.commands;

//import managers.CollectionManager;

import client.TCPClient;
import common.requests.ClearRequest;
import client.utility.Console;

import java.io.IOException;

public class Clear extends Command {
    private final Console console;
    private final TCPClient tcpClient;
    //private final CollectionManager collectionManager;

    public Clear(Console console, TCPClient tcpClient) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.tcpClient = tcpClient;
        //this.collectionManager = collectionManager;
    }


    @Override
    public boolean apply(String[] arguments) throws IOException {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }


        //collectionManager.clearCollection();
        tcpClient.sendCmdRequest(new ClearRequest());
        console.println("Коллекция очищена!");
        return true;
    }
}
