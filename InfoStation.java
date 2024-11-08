import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

public class InfoStation {
    private final Map<String, Station> stations;

    public InfoStation() {
        stations = new ConcurrentHashMap<>();
        loadStationsFromFile("stations.txt");
    }

    private void loadStationsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String lieu = parts[0];
                    float temperature = Float.parseFloat(parts[1]);
                    float pression = Float.parseFloat(parts[2]);
                    stations.put(lieu, new Station(lieu, temperature, pression));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
