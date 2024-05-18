package server.commands;

import BaseModel.City;
import common.Responce;
import common.requests.CountByMetersAboveSeaLevelRequest;
import common.requests.RemoveKeyRequest;
import common.requests.Request;
import server.exceptions.CollectionIsEmptyException;
import server.exceptions.NotFoundException;
import server.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class RemoveKey extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveKey(Console console, CollectionManager collectionManager) {
        super("remove_key", "удалить элемент по ключу");
        this.console = console;
        this.collectionManager = collectionManager;
    }



    public Responce applySp(RemoveKeyRequest request) throws IOException {
        Responce responce = new Responce();
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int key = request.getKey();
            City cityToRemove = collectionManager.getByKey(key);
            if (cityToRemove == null) throw new NotFoundException();

            collectionManager.removeFromCollectionByKey(key);
            responce.addString("Элемент успешно удален.");


        } catch (CollectionIsEmptyException exception) {
            responce.addString("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            responce.addString("Ключ должен быть представлен числом!");
        } catch (NotFoundException exception) {
            responce.addString("Города с таким ключом в коллекции нет!");
        }
        return responce;
    }

    @Override
    public Responce apply(Request request) throws IOException {
        return applySp((RemoveKeyRequest) request);
    }
}
