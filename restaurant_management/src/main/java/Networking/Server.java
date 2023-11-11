package Networking;
import Restaurant.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public HashMap<String, String> resownMap;

public RestaurantManager manager;

    Server() throws Exception {
        List<restaurant>restaurants=IO.addres();
        System.out.println("restaurants");
        List<food>menu=IO.addmenu();
        System.out.println("menu");
        manager=new RestaurantManager(menu,restaurants);
        System.out.println("manager");
        userMap = IO.getclient();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
            System.out.println("manager gone2");
        new ReadThreadServer(userMap,manager,socketWrapper);
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }
}
