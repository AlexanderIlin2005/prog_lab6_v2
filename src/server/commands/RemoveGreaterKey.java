package server.commands;


import common.Responce;
import common.requests.CountByMetersAboveSeaLevelRequest;
import common.requests.RemoveGreaterKeyRequest;
import common.requests.Request;
import server.exceptions.CollectionIsEmptyException;
import server.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class RemoveGreaterKey extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveGreaterKey(Console console, CollectionManager collectionManager) {
        super("remove_greater_key", "удалить из коллекции все элементы, ключ которых превышает заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }



    public Responce applySp(RemoveGreaterKeyRequest request) throws IOException {
        Responce responce = new Responce();
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int key = request.getKey();
            this.collectionManager.removeGreaterKey(key);
            responce.addString("Элементы с id > заданного удалены.");

        } catch (CollectionIsEmptyException exception) {
            responce.addString("Коллекция пуста!");
        }
        return responce;
    }

    @Override
    public Responce apply(Request request) throws IOException {
        return applySp((RemoveGreaterKeyRequest) request);
    }
}

