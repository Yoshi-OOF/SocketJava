import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InfoStation {
    private final Map<String, Station> stations;

    public InfoStation() {
        stations = new ConcurrentHashMap<>();
        stations.put("Montélimar", new Station("Montélimar", 34, 1015.3f));
        stations.put("Laval", new Station("Laval", 24, 1030));
        stations.put("Paris", new Station("Paris", 26, 1020));
        stations.put("Colmar", new Station("Colmar", 28, 980));
    }

    public boolean identifyClient(String nom) {
        return stations.containsKey(nom);
    }

    public Station getStation(String nom) {
        return stations.get(nom);
    }

    public List<Station> getAllStations() {
        return new ArrayList<>(stations.values());
    }

    public boolean updateStation(String nom, float temperature, float pression) {
        Station station = stations.get(nom);
        if (station != null) {
            synchronized (station) {
                station.setTemperature(temperature);
                station.setPression(pression);
            }
            return true;
        }
        return false;
    }

    public int infoLieu(String lieu) {
        int i = 0;

        for (Station s : stations.values()) {
            if (s.getLieu().equals(lieu)) {
                i++;
            }
        }

        if (i < stations.size()) {
            return i;
        } else {
            return -1;
        }
    }
}
