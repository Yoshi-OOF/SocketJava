import java.io.*;
import java.net.*;

public class ServeurMeteo {
    public static void main(String[] args) {
        int port = 1080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur Météo en écoute sur le port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion acceptée de " + clientSocket.getInetAddress());
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private InfoMeteo infoMeteo;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.infoMeteo = new InfoMeteo();
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            String commande;
            String nomClient = null;
            while ((commande = in.readLine()) != null) {
                System.out.println("Client [" + clientSocket.getInetAddress() + "] : " + commande);
                if (commande.startsWith("HELLO")) {
                    String[] parts = commande.split(" ");
                    if (parts.length == 2) {
                        nomClient = parts[1];
                    }
                }
                synchronized (infoMeteo) {
                    String reponse = infoMeteo.handleCommand(commande, nomClient);
                    out.println(reponse);
                }
                if (commande.equalsIgnoreCase("BYE")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur avec le client [" + clientSocket.getInetAddress() + "] : " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connexion fermée avec " + clientSocket.getInetAddress());
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
