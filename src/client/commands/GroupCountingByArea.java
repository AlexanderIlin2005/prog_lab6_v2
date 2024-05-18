package client.commands;

import client.TCPClient;
import client.exceptions.WrongAmountOfElementsException;
import common.requests.GroupCountingByAreaRequest;
import client.utility.Console;

import java.io.IOException;

public class GroupCountingByArea extends Command{

    private final Console console;
    private final TCPClient tcpClient;

    public GroupCountingByArea(Console console, TCPClient tcpClient) {
        super("group_counting_by_area",
                "сгруппировать элементы коллекции по значению поля аrеа, вывести количество элементов в каждой группе");
        this.console = console;
        this.tcpClient = tcpClient;
    }
    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            tcpClient.sendCmdRequest(new GroupCountingByAreaRequest());
            //if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            //collectionManager.groupCountingByArea(console);
            return true;

        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
