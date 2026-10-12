package serverPackage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
    try {
        // Étape 1 : créer le ServerSocket sur le port 1234
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente la connexion d'un client ");

        // Étape 2 : accept() bloque jusqu'à la connexion d'un client
        Socket socket = serverSocket.accept();
        System.out.println("un client est connecté");

        // Dernière étape : fermer les sockets
        socket.close();
        serverSocket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
