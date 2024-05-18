package server.commands;

import common.Responce;
import common.requests.Request;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class Show extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Responce apply(Request request) throws IOException {
        Responce responce = new Responce();
        String res = collectionManager.toString();
        responce.addString(res != null ? res : "Коллекция пуста!" );
        return responce;
    }
}
