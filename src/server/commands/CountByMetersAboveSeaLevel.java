package server.commands;

import common.Responce;
import common.requests.CountByMetersAboveSeaLevelRequest;
import common.requests.InsertRequest;
import common.requests.Request;
import server.exceptions.CollectionIsEmptyException;
import server.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class CountByMetersAboveSeaLevel extends Command {

    private final Console console;
    private final CollectionManager collectionManager;

    public CountByMetersAboveSeaLevel(Console console, CollectionManager collectionManager) {
        super("count_by_meters_above_sea_level",
                " вывести количество элементов, значение поля metersAboveSeaLevel которых равно заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }





    public Responce applySp(CountByMetersAboveSeaLevelRequest request) throws IOException {
        Responce responce = new Responce();
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int metersAboveSeaLevel = request.getMeters();
            long value = collectionManager.countByMetersAboveSeaLevel(console, metersAboveSeaLevel);
            responce.addString(String.valueOf(value));

        } catch (CollectionIsEmptyException exception) {
            responce.addString("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            responce.addString("Параметр <высота над уровнем моря> должен быть представлен числом!");
        }
        return responce;

    }

    @Override
    public Responce apply(Request request) throws IOException {
        return applySp((CountByMetersAboveSeaLevelRequest) request);
    }
}
