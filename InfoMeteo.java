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
                        response = "Bienvenue sur le serveur Météo\n" + ErrorCode.OK + " " + parts[1] + "\nEND";
                    } else {
                        response = ErrorCode.ERR_IDENTIFICATION_INCORRECTE + "\nEND";
                    }
                } else {
                    response = ErrorCode.ERR_SYNTAXE_HELLO + "\nEND";
                }
                break;
            case "GET":
                if (parts.length == 2) {
                    Station station = infoStation.getStation(parts[1]);
                    if (station != null) {
                        response = ErrorCode.OK + " " + station.getLieu() + " " + station.getTemperature() + " " + station.getPression() + "\nEND";
                    } else {
                        response = ErrorCode.ERR_STATION_INCONNUE + "\nEND";
                    }
                } else {
                    response = ErrorCode.ERR_SYNTAXE_GET + "\nEND";
                }
                break;
            case "GETALL":
                List<Station> stations = infoStation.getAllStations();
                StringBuilder sb = new StringBuilder();
                for (Station s : stations) {
                    sb.append(ErrorCode.OK).append(" ").append(s.getLieu()).append(" ").append(s.getTemperature()).append(" ").append(s.getPression()).append("\n");
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
                            response = ErrorCode.ERR_STATION_NON_IDENTIFIEE + "\nEND";
                        }
                    } catch (NumberFormatException e) {
                        response = ErrorCode.ERR_FORMAT_NUMERIQUE + "\nEND";
                    }
                } else {
                    response = ErrorCode.ERR_SYNTAXE_PUT + "\nEND";
                }
                break;
            case "BYE":
                response = "Au revoir " + nomClient + "\nEND";
                break;
            default:
                response = ErrorCode.ERR_COMMANDE_INVALIDE + "\nEND";
        }
        return response;
    }
}
