public enum ErrorCode {
    OK("OK"),
    ERR_IDENTIFICATION_INCORRECTE("ERR IDENTIFICATION INCORRECTE"),
    ERR_SYNTAXE_HELLO("ERR SYNTAXE HELLO"),
    ERR_STATION_INCONNUE("ERR STATION INCONNUE"),
    ERR_SYNTAXE_GET("ERR SYNTAXE GET"),
    ERR_STATION_NON_IDENTIFIEE("ERR STATION NON IDENTIFIEE"),
    ERR_FORMAT_NUMERIQUE("ERR FORMAT NUMERIQUE"),
    ERR_SYNTAXE_PUT("ERR SYNTAXE PUT"),
    ERR_COMMANDE_INVALIDE("ERR COMMANDE INVALIDE");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
