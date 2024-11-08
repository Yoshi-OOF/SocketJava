public class Station {
    private final String lieu;
    private float temperature;
    private float pression;

    public Station(String lieu, float temperature, float pression) {
        this.lieu = lieu;
        this.temperature = temperature;
        this.pression = pression;
    }

    public String getLieu() {
        return this.lieu;
    }

    public synchronized float getTemperature() {
        return this.temperature;
    }

    public synchronized float getPression() {
        return this.pression;
    }

    public synchronized void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public synchronized void setPression(float pression) {
        this.pression = pression;
    }
}
