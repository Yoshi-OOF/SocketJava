public class TraiteCommande {
    public static String traiter(String commande) {
        if (commande.startsWith("HELLO")) {
            return "Bienvenue sur le serveur Météo\nOK " + commande.split(" ")[1] + "\nEND";
        } else if (commande.startsWith("GET")) {
            return "OK nomDeStation température pression\nEND";
        }
        return "ERR COMMANDE INVALIDE\nEND";
    }

    // executerCommande
   /*  public static void main(String[] args) {
        System.out.println(traiter("HELLO Montélimar"));
        System.out.println(traiter("GET Montélimar"));
        System.out.println(traiter("GETALL"));
        System.out.println(traiter("PUT 25 1000"));
        System.out.println(traiter("BYE"));
        System.out.println(traiter("UNKNOWN"));
    } */
}