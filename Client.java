import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serveur = "localhost";
        int port = 1080;
        try (Socket socket = new Socket(serveur, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez le nom de la station: ");
            String nomStation = scanner.nextLine();
            out.println("HELLO " + nomStation);
            String reponse;
            while ((reponse = in.readLine()) != null) {
                System.out.println("Serveur: " + reponse);
                if (reponse.equals("END")) {
                    break;
                }
            }

            System.out.print("Entrez une commande (par exemple, GET Colmar): ");
            String commande = scanner.nextLine();
            out.println(commande);
            while ((reponse = in.readLine()) != null) {
                System.out.println("Serveur: " + reponse);
                if (reponse.equals("END")) {
                    break;
                }
            }

            out.println("BYE");
            while ((reponse = in.readLine()) != null) {
                System.out.println("Serveur: " + reponse);
                if (reponse.equals("END")) {
                    break;
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Erreur du client: " + e.getMessage());
        }
    }
}
