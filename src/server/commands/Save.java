package server.commands;

import common.Responce;
import common.requests.Request;
import server.managers.CollectionManager;
import server.utility.Console;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Save extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Save(Console console, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.console = console;
        this.collectionManager = collectionManager;
    }


    public boolean applySp(String[] arguments)  {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
        }
        String filename = arguments[1];

        collectionManager.saveCollection(filename, console);

        return true;
    }

    @Override
    public Responce apply(Request request) throws IOException {
        return new Responce();
    }
}
