package models;
import java.time.LocalDate;

public class Reservation {
    private int id;
    private LocalDate date;
    private Boolean etat;
    private int duree;
    private String nciClient;
    private String refLocal;
    private static int nombreDeReserrvation = 0 ;

    public Reservation( LocalDate date, Boolean etat, int duree, String nciClient, String refLocal) {
        this.id =++ nombreDeReserrvation;
        this.date = date;
        this.etat = etat;
        this.duree = duree;
        this.nciClient = nciClient;
        this.refLocal = refLocal;
    }
    public String getRefLocal() {
        return refLocal;
    }
    public void setRefLocal(String refLocal) {
        this.refLocal = refLocal;
    }
    public String getNciClient() {
        return nciClient;
    }
    public void setNciClient(String nciClient) {
        this.nciClient = nciClient;
    }
    public int getId() {
        return id;
    }
    public int getDuree() {
        return duree;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDuree (int duree) {
        this.duree = duree;
    }
    public Boolean getEtat() {
        return etat;
    }
    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
 
    public String afficherReservation() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", etat='" + getEtat() + "'" +
            ", duree='" + getDuree() + "'" +
            ", nciClient='" + getNciClient() + "'" +
            ", refLocal='" + getRefLocal() + "'" +
            "}";
    }
    @Override
    public String toString(){
        return "Id: " + this.getId() + 
        "\nDate: " + this.getDate() + 
        "\nEtat: " + this.getEtat() +
        "\nDuree: " + this.getDuree() +
        "\nRef du Local: " + this.getRefLocal() +
        "\nNci du client: " + this.getNciClient() +
        "\nFin reservation: " + this.getDate().plusDays(this.getDuree())
        ;
    }
}
