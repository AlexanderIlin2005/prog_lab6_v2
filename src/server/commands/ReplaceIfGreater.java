package server.commands;

import common.Responce;
import common.requests.CountByMetersAboveSeaLevelRequest;
import common.requests.ReplaceIfGreaterRequest;
import common.requests.Request;
import server.exceptions.CollectionIsEmptyException;
import server.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;
import java.util.Objects;

public class ReplaceIfGreater extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public ReplaceIfGreater(Console console, CollectionManager collectionManager) {
        super("replace_if_greater", "заменить значение по ключу, если новое значение больше старого");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public Responce applySp(ReplaceIfGreaterRequest request) throws IOException {
        Responce responce = new Responce();
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int key = request.getKey();
            if (collectionManager.getByKey(key) != null){
                collectionManager.replaceIfGreater(key, request.getCity());
            }
            responce.addString("Город успешно добавлен.");

        } catch (CollectionIsEmptyException exception) {
            responce.addString("Коллекция пуста!");
        }
        return responce;
    }

    @Override
    public Responce apply(Request request) throws IOException {
        return applySp((ReplaceIfGreaterRequest) request);
    }
}
