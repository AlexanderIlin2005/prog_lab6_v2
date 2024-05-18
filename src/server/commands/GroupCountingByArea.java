package server.commands;

import common.Responce;
import common.requests.Request;
import server.exceptions.CollectionIsEmptyException;
import server.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;
import server.utility.Console;

import java.io.IOException;

public class GroupCountingByArea extends Command {

    private final Console console;
    private final CollectionManager collectionManager;

    public GroupCountingByArea(Console console, CollectionManager collectionManager) {
        super("group_counting_by_area",
                "сгруппировать элементы коллекции по значению поля аrеа, вывести количество элементов в каждой группе");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public Responce apply(Request request) {
        Responce responce = new Responce();
        try {

            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            responce = collectionManager.groupCountingByArea(responce);

        } catch (CollectionIsEmptyException e) {
            responce.addString("Коллекция пуста!");

        }
        return responce;
    }


}
