package server.commands;

import common.Responce;
import common.requests.Request;
import server.utility.Console;

import java.io.IOException;

public class Exit extends Command {
    private final Console console;

    public Exit(Console console) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.console = console;
    }


    public boolean applySp(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        console.println("Завершение выполнения...");
        return true;
    }
    public Responce apply(Request request) throws IOException {
        return null;
    }
}
