package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté…");

            // Connexion au serveur (localhost, puis essayer une adresse IP, ex. "192.168.1.10")
            Socket socket = new Socket("localhost", 1234);
            System.out.println("je suis un client connecté");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);

            int x;
            do {
                System.out.print("Entrer un entier (0 pour arrêter) : ");
                x = sc.nextInt();               // saisie clavier
                out.writeInt(x);                // envoi au serveur
                out.flush();
                int resultat = in.readInt();    // réception du résultat
                System.out.println("Résultat (" + x + " * 5) = " + resultat);
            } while (x != 0);

            // Dernière étape : fermer les flux et le socket
            sc.close();
            in.close();
            out.close();
            socket.close();
            System.out.println("Connexion fermée côté client.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}