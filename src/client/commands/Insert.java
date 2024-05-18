package client.commands;
import client.TCPClient;
import client.exceptions.WrongAmountOfElementsException;
import common.requests.InsertRequest;
import client.utility.Checker;
import client.utility.Console;

import java.io.IOException;

public class Insert extends Command{
    private final Console console;
    private final TCPClient tcpClient;

    public Insert(Console console, TCPClient tcpClient) {
        super("insert  null", "добавить новый элемент с заданным ключом");
        this.console = console;
        this.tcpClient = tcpClient;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            int key = Integer.parseInt(arguments[1]);

            if (key == 0) {
                console.printError("id должен быть больше 0!");
            } else {
                tcpClient.sendCmdRequest(new InsertRequest(key, Checker.checkCity(console, key)));
            }
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
