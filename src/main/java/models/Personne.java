package models;

public class Personne {

    private String nci;
    private String nomComplet;
    private String tel;
    private String adresse;
    private String email;
    private String role;

    public String getType(){ return "Personne";}
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public Personne(String nci, String nomComplet, String tel, String adresse, String email) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.tel = tel;
        this.adresse = adresse;
        this.email = email;
        this.role = "client";
    }

    public Personne(String nci, String nomComplet, String tel, String adresse, String email, String role) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.tel = tel;
        this.adresse = adresse;
        this.email = email;
        this.role = role;
    }

    public String afficherPersonne() {
        return "Nci : " + this.getNci() + 
            "\nNom Complet : " + this.getNomComplet() + 
            "\nTel : " + this.getTel() +
            "\nAdresse : " + this.getAdresse() +
            "\nEmail : " + this.getEmail() +
            "\nRole : " + this.getRole() + "\n";
   }
}
