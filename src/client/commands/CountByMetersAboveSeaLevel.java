package client.commands;

import client.TCPClient;
import client.utility.Console;
import client.exceptions.WrongAmountOfElementsException;
import common.requests.CountByMetersAboveSeaLevelRequest;

import java.io.IOException;


public class CountByMetersAboveSeaLevel extends Command{

    private final Console console;
    private final TCPClient tcpClient;

    public CountByMetersAboveSeaLevel(Console console, TCPClient tcpClient) {
        super("count_by_meters_above_sea_level",
                " вывести количество элементов, значение поля metersAboveSeaLevel которых равно заданному");
        this.console = console;
        this.tcpClient = tcpClient;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            //if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int metersAboveSeaLevel = Integer.parseInt(arguments[1]);
            tcpClient.sendCmdRequest(new CountByMetersAboveSeaLevelRequest(metersAboveSeaLevel));
            //console.println(collectionManager.countByMetersAboveSeaLevel(console, metersAboveSeaLevel));
            return true;

        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            console.printError("Параметр <высота над уровнем моря> должен быть представлен числом!");
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
