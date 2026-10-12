package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

// ÉTAPE 2 : client de calculatrice avec menu
public class Client {

    // Doit être IDENTIQUE à SERVER_IP dans Server.java
    private static final String SERVER_IP = "192.168.56.1";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté…");

            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(InetAddress.getByName(SERVER_IP), PORT), 5000);
            System.out.println("je suis un client connecté au serveur " + SERVER_IP + ":" + PORT);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);

            int choix;
            do {
                System.out.println("\n===== CALCULATRICE =====");
                System.out.println("1 - Addition");
                System.out.println("2 - Soustraction");
                System.out.println("3 - Multiplication");
                System.out.println("4 - Division");
                System.out.println("0 - Quitter");
                System.out.print("Votre choix : ");

                try {
                    choix = sc.nextInt();
                } catch (InputMismatchException e) {
                    sc.next();                          // vider la saisie invalide
                    System.out.println("Entrée invalide, tapez un nombre.");
                    choix = -1;
                    continue;
                }

                if (choix == 0) {
                    out.writeInt(0);
                    out.flush();
                    System.out.println(in.readUTF());   // "Au revoir !"
                } else if (choix >= 1 && choix <= 4) {
                    try {
                        System.out.print("Entrer x : ");
                        int x = sc.nextInt();
                        System.out.print("Entrer y : ");
                        int y = sc.nextInt();

                        out.writeInt(choix);            // opération
                        out.writeInt(x);                // opérandes
                        out.writeInt(y);
                        out.flush();

                        System.out.println("Résultat : " + in.readUTF());
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("Entrée invalide : entiers uniquement.");
                    }
                } else {
                    System.out.println("Choix invalide, réessayez.");
                }
            } while (choix != 0);

            sc.close();
            in.close();
            out.close();
            socket.close();
            System.out.println("Connexion fermée côté client.");

        } catch (IOException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}