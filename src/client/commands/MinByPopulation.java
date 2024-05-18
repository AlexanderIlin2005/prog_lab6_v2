package client.commands;

import client.TCPClient;
import common.requests.MinByPopulationRequest;
import client.utility.Console;

import java.io.IOException;

public class MinByPopulation extends Command{

    private final Console console;
    private final TCPClient tcpClient;

    public MinByPopulation(Console console, TCPClient tcpClient) {
        super("min_by_population", "вывести город с наименьшим населением");
        this.console = console;
        this.tcpClient = tcpClient;
    }
    @Override
    public boolean apply(String[] arguments) throws IOException {
        tcpClient.sendCmdRequest(new MinByPopulationRequest());
        return true;
    }
}
