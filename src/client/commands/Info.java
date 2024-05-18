package client.commands;

import client.TCPClient;
import client.utility.Console;
import common.requests.InfoRequest;

import java.io.IOException;

public class Info extends Command {
    private final Console console;
    private final TCPClient tcpClient;

    public Info(Console console, TCPClient tcpClient) {
        super("info", "вывести информацию о коллекции");
        this.console = console;
        this.tcpClient = tcpClient;
    }

    @Override
    public boolean apply(String[] arguments) throws IOException {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        tcpClient.sendCmdRequest(new InfoRequest());


        return true;
    }
}
