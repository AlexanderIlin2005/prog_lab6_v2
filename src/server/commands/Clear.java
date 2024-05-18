package server.commands;

import common.Responce;
import common.requests.ClearRequest;
import common.requests.Request;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class Clear extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public Responce apply(Request request) throws IOException {

        Responce responce = new Responce();

        collectionManager.clearCollection();
        responce.addString("Коллекция очищена!");
        return responce;
    }
}
