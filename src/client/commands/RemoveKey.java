package client.commands;

import client.TCPClient;
import client.exceptions.WrongAmountOfElementsException;
import common.requests.RemoveKeyRequest;
import client.utility.Console;

import java.io.IOException;

public class RemoveKey extends Command{
    private final Console console;
    private final TCPClient tcpClient;

    public RemoveKey(Console console, TCPClient tcpClient) {
        super("remove_key", "удалить элемент по ключу");
        this.console = console;
        this.tcpClient = tcpClient;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            int key = Integer.parseInt(arguments[1]);
            tcpClient.sendCmdRequest(new RemoveKeyRequest(key));
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
