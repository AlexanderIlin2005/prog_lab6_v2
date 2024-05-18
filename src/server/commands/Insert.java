package server.commands;
import common.Responce;
import common.requests.InsertRequest;
import common.requests.Request;
import server.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;
import client.utility.Checker;
import server.utility.Console;

import java.io.IOException;

public class Insert extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Insert(Console console, CollectionManager collectionManager) {
        super("insert  null", "добавить новый элемент с заданным ключом");
        this.console = console;
        this.collectionManager = collectionManager;
        System.out.println("insert");
    }



    public Responce applySp(InsertRequest request) throws IOException {
        Responce responce = new Responce();

        int key = request.getKey();

        if (collectionManager.getByKey(key) != null){
            responce.addString("Город с таким id уже есть. Возможно, стоит использовать команду update_id");
        } else {
            this.collectionManager.insert(key, request.getCity());
            responce.addString("Город успешно добавлен.");
        }

        return responce;
    }

    @Override
    public Responce apply(Request request) throws IOException {
        Responce responce = applySp((InsertRequest) request);
        return responce;
    }
}
