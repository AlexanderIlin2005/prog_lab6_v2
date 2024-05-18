package client.commands;

import BaseModel.City;
import client.TCPClient;
import client.exceptions.WrongAmountOfElementsException;
import client.utility.Checker;
import common.requests.UpdateIdRequest;
import client.utility.Console;

import java.io.IOException;
import java.util.Objects;

public class UpdateId extends Command{
    private final Console console;
    private final TCPClient tcpClient;

    public UpdateId(Console console, TCPClient tcpClient) {
        super("update id", "обновить значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.tcpClient = tcpClient;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            //if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int key = Integer.parseInt(arguments[1]);
            City city = Objects.requireNonNull(Checker.checkCity(console, key));
            tcpClient.sendCmdRequest(new UpdateIdRequest(key, city));
            this.console.println("Город успешно добавлен.");
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
