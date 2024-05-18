package server.commands;

import common.Responce;
import common.requests.CountByMetersAboveSeaLevelRequest;
import common.requests.ReplaceIfLowerRequest;
import common.requests.Request;
import server.commands.Command;
import server.exceptions.CollectionIsEmptyException;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class ReplaceIfLower extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public ReplaceIfLower(Console console, CollectionManager collectionManager) {
        super("replace_if_lower", "заменить значение по ключу, если новое значение больше старого");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public Responce applySp(ReplaceIfLowerRequest request) throws IOException {
        Responce responce = new Responce();
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int key = request.getKey();
            if (collectionManager.getByKey(key) != null){
                collectionManager.replaceIfLower(key, request.getCity());
            }
            responce.addString("Город успешно добавлен.");

        } catch (CollectionIsEmptyException exception) {
            responce.addString("Коллекция пуста!");
        }
        return responce;
    }

    @Override
    public Responce apply(Request request) throws IOException {
        return applySp((ReplaceIfLowerRequest) request);
    }
}
