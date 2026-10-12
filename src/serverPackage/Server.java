package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            // Étape 1 : créer le ServerSocket sur le port 1234 (garde ton port de l'Activité 1-1)
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Je suis un serveur en attente la connexion d'un client ");

            // Étape 2 : attendre la connexion d'un client
            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté");

            // Flux pour lire / écrire des entiers (gère bien les négatifs)
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            int x;
            do {
                x = in.readInt();                       // réception de x
                System.out.println("Reçu du client : " + x);
                int resultat = x * 5;                   // calcul
                out.writeInt(resultat);                 // envoi du résultat
                out.flush();
                System.out.println("Résultat envoyé : " + resultat);
            } while (x != 0);

            // Dernière étape : fermer les flux et les sockets
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("Connexion fermée côté serveur.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}