package server.commands;

import common.Responce;
import common.requests.Request;

import java.io.IOException;

public interface Executable {
    Responce apply(Request request) throws IOException;
}
