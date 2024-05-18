package client.commands;

import java.io.IOException;

public interface Executable {
    boolean apply(String[] arguments) throws IOException;
}
