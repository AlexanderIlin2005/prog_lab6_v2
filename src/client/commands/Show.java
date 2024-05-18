package client.commands;

import client.TCPClient;
import common.requests.ShowRequest;
import client.utility.Console;

import java.io.IOException;

public class Show extends Command {
    private final Console console;
    private final  TCPClient tcpClient;

    public Show(Console console, TCPClient tcpClient) {
        super("show", "вывести все элементы коллекции");
        this.console = console;
        this.tcpClient = tcpClient;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) throws IOException {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        tcpClient.sendCmdRequest(new ShowRequest());
        return true;
    }
}
