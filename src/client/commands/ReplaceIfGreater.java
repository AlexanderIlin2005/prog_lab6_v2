package client.commands;

import client.exceptions.WrongAmountOfElementsException;
import client.utility.Checker;
import client.TCPClient;
import client.utility.Console;
import common.requests.ReplaceIfGreaterRequest;

import java.io.IOException;

public class ReplaceIfGreater extends Command{
    private final Console console;
    private final TCPClient tcpClient;

    public ReplaceIfGreater(Console console, TCPClient tcpClient) {
        super("replace_if_greater", "заменить значение по ключу, если новое значение больше старого");
        this.console = console;
        this.tcpClient = tcpClient;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            int key = Integer.parseInt(arguments[1]);

            tcpClient.sendCmdRequest(new ReplaceIfGreaterRequest(key, Checker.checkCity(console, key)));

            return true;

        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            console.printError("Ключ должен быть представлен числом!");
        } catch (Checker.CheckException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
