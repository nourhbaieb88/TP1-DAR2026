package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

// ÉTAPE 2 : serveur de calculatrice (addition, soustraction, multiplication, division)
public class Server {

    // Doit être IDENTIQUE dans Client.java
    private static final String SERVER_IP = "192.168.56.1";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            InetAddress adresse = InetAddress.getByName(SERVER_IP);
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(adresse, PORT));

            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            System.out.println("Écoute sur " + adresse.getHostAddress() + ":" + PORT);

            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté : "
                    + socket.getInetAddress().getHostAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println("En attente d'un choix du client...");
                int choix = in.readInt();               // opération choisie

                if (choix == 0) {                       // quitter
                    out.writeUTF("Au revoir !");
                    out.flush();
                    break;
                }

                int x = in.readInt();                   // premier opérande
                int y = in.readInt();                   // second opérande
                System.out.println("Reçu : choix=" + choix + ", x=" + x + ", y=" + y);

                String resultat;
                switch (choix) {
                    case 1:
                        resultat = x + " + " + y + " = " + ((long) x + y);
                        break;
                    case 2:
                        resultat = x + " - " + y + " = " + ((long) x - y);
                        break;
                    case 3:
                        resultat = x + " * " + y + " = " + ((long) x * y);
                        break;
                    case 4:
                        if (y == 0) {
                            resultat = "Erreur : division par zéro impossible";
                        } else {
                            resultat = x + " / " + y + " = " + ((double) x / y);
                        }
                        break;
                    default:
                        resultat = "Choix invalide";
                }

                out.writeUTF(resultat);                 // envoi du résultat
                out.flush();
                System.out.println("Résultat envoyé : " + resultat);
            }

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