package client.commands;


import client.TCPClient;
import client.exceptions.WrongAmountOfElementsException;
import common.requests.RemoveGreaterKeyRequest;
import client.utility.Console;

import java.io.IOException;

public class RemoveGreaterKey extends Command{
    private final Console console;
    private final TCPClient tcpClient;

    public RemoveGreaterKey(Console console, TCPClient tcpClient) {
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный");
        this.console = console;
        this.tcpClient = tcpClient;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            int key = Integer.parseInt(arguments[1]);
            tcpClient.sendCmdRequest(new RemoveGreaterKeyRequest(key));
            this.console.println("Элементы с id > заданного удалены.");
            return true;

        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            console.printError("Ключ должен быть представлен числом!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

