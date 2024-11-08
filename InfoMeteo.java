import java.util.*;

public class InfoMeteo {
    private InfoStation infoStation;

    public InfoMeteo() {
        infoStation = new InfoStation();
    }

    public String handleCommand(String commande, String nomClient) {
        String[] parts = commande.split(" ");
        String response = "";
        switch (parts[0].toUpperCase()) {
            case "HELLO":
                if (parts.length == 2) {
                    boolean success = infoStation.identifyClient(parts[1]);
                    if (success) {
                        response = "Bienvenue sur le serveur Météo\nOK " + parts[1] + "\nEND";
                    } else {
                        response = "ERR IDENTIFICATION INCORRECTE\nEND";
                    }
                } else {
                    response = "ERR SYNTAXE HELLO\nEND";
                }
                break;
            case "GET":
                if (parts.length == 2) {
                    Station station = infoStation.getStation(parts[1]);
                    if (station != null) {
                        response = "OK " + station.getLieu() + " " + station.getTemperature() + " " + station.getPression() + "\nEND";
                    } else {
                        response = "ERR STATION INCONNUE\nEND";
                    }
                } else {
                    response = "ERR SYNTAXE GET\nEND";
                }
                break;
            case "GETALL":
                List<Station> stations = infoStation.getAllStations();
                StringBuilder sb = new StringBuilder();
                for (Station s : stations) {
                    sb.append("OK ").append(s.getLieu()).append(" ").append(s.getTemperature()).append(" ").append(s.getPression()).append("\n");
                }
                sb.append("END");
                response = sb.toString();
                break;
            case "PUT":
                if (parts.length == 3) {
                    try {
                        float temp = Float.parseFloat(parts[1]);
                        float pression = Float.parseFloat(parts[2]);
                        boolean updated = infoStation.updateStation(nomClient, temp, pression);
                        if (updated) {
                            response = "Mise à jour acceptée\nEND";
                        } else {
                            response = "ERR STATION NON IDENTIFIEE\nEND";
                        }
                    } catch (NumberFormatException e) {
                        response = "ERR FORMAT NUMERIQUE\nEND";
                    }
                } else {
                    response = "ERR SYNTAXE PUT\nEND";
                }
                break;
            case "BYE":
                response = "Au revoir " + nomClient + "\nEND";
                break;
            default:
                response = "ERR COMMANDE INVALIDE\nEND";
        }
        return response;
    }
}
