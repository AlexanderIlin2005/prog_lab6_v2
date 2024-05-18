package server.commands;

import common.Responce;
import common.requests.Request;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;
import java.time.LocalDateTime;

public class Info extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Info(Console console, CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public Responce apply(Request request) throws IOException {
        Responce responce = new Responce();

        LocalDateTime lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        responce.addString("Сведения о коллекции:");
        responce.addString(" Тип: " + collectionManager.collectionType());
        responce.addString(" Количество элементов: " + collectionManager.collectionSize());
        responce.addString(" Дата последнего сохранения: " + lastSaveTimeString);
        responce.addString(" Дата последней инициализации: " + lastInitTimeString);
        return responce;
    }
}
