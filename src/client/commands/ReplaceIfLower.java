package client.commands;

import client.TCPClient;
import client.exceptions.WrongAmountOfElementsException;
import common.requests.ReplaceIfLowerRequest;
import client.utility.Console;
import client.utility.Checker;

import java.io.IOException;

public class ReplaceIfLower extends Command{
    private final Console console;
    private final TCPClient tcpClient;

    public ReplaceIfLower(Console console, TCPClient tcpClient) {
        super("replace_if_lower", "заменить значение по ключу, если новое значение меньше старого");
        this.console = console;
        this.tcpClient = tcpClient;
    }

    @Override
    public boolean apply(String[] arguments) throws IOException {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            int key = Integer.parseInt(arguments[1]);

            tcpClient.sendCmdRequest(new ReplaceIfLowerRequest(key, Checker.checkCity(console, key)));

            this.console.println("Город успешно добавлен.");
            return true;

        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            console.printError("Ключ должен быть представлен числом!");
        } catch (Checker.CheckException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

