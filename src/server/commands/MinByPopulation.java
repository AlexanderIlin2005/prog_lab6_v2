package server.commands;

import common.Responce;
import common.requests.Request;
import server.exceptions.CollectionIsEmptyException;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class MinByPopulation extends Command {

    private final Console console;
    private final CollectionManager collectionManager;

    public MinByPopulation(Console console, CollectionManager collectionManager) {
        super("min_by_population", "вывести город с наименьшим населением");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public Responce apply(Request request) throws IOException {
        Responce responce = new Responce();
        if (collectionManager.collectionSize() == 0) try {
            throw new CollectionIsEmptyException();
        } catch (CollectionIsEmptyException e) {
            responce.addString("Коллекция пуста!");
            return responce;
        }
        responce.addString(String.valueOf(collectionManager.minByPopulation()));
        return responce;
    }
}
