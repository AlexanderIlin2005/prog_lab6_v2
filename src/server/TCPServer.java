package server;

import common.Responce;
import common.requests.Request;
import server.commands.Command;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.DumpManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.utility.Console;

public class TCPServer {

    private static final Logger logger = Logger.getLogger(TCPServer.class.getName());
    private static FileHandler fileHandler;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private CommandManager commandManager;
    private BufferedReader reader;

    public TCPServer(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void connect() throws IOException {
        String host;
        int port;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                //logger.log(Level.INFO, "Введите порт сервера:");
                System.out.println("Введите порт сервера:");
                port = Integer.valueOf(sc.nextLine());
                serverSocket = new ServerSocket(port);
                break;

            } catch (IOException e) {
                logger.log(Level.SEVERE, "Неверный формат ввода", e);
            }
        }
        logger.log(Level.INFO, "Запущен сервер на порту " + port);

        // Создание FileHandler для записи логов в файл
        try {
            fileHandler = new FileHandler("src/server/log/server.log", true);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при создании FileHandler", e);
        }

        waitForConnection();
    }

    private void waitForConnection() throws IOException {
        clientSocket = serverSocket.accept();
        logger.log(Level.INFO, "Получено новое подключение: " + clientSocket.getInetAddress() + " " + clientSocket.getPort());
        listenCommands();
    }

    private void listenCommands() throws IOException {
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

        try {
            while (true) {
                Request request = (Request) in.readObject();
                String cmdName = request.getName();
                logger.log(Level.INFO, "Получен новый запрос: " + cmdName);
                Command command = commandManager.getCommands().get(cmdName);
                Responce response = command.apply(request);
                out.writeObject(response);
                out.flush();
                logger.log(Level.INFO, "Отправлен ответ: " + response.getContent());
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            Responce response = new Responce();
            response.addString("Неверный формат введенных команд. Вы отключены от сервера. Используйте корректный клиент");
            out.writeObject(response);
            out.flush();
            logger.log(Level.INFO, "Закончена связь с текущим пользователем");
            // Новое подключение:
            waitForConnection();
        }
    }
}