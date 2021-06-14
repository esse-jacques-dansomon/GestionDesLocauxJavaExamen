package models;
import java.util.ArrayList;

public class Appartement extends Local 
{

    private ArrayList<Local> chambres = new ArrayList<>();
    private int pieces = 3 ;

    public ArrayList<Local> getChambres() {
        return this.chambres;
    }

    public void addChambres(Local chambre) {
        this.chambres.add(chambre);
    }

    public int getPieces() {
        return this.pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public Appartement(String localisation, int prix, int taux, int pieces) {
        super(localisation, prix, taux);
        this.pieces = pieces;
        this.type = "Appartment";


    }

    public Appartement(String localisation, int prix, int taux) {
        super(localisation, prix, taux);
        this.type = "Appartement";
    }
    
    @Override
    public String afficherLocal()
    {
        
        String chaine = super.afficherLocal() + 
            "\nPieces : " + this.getPieces() + 
            "\n\nLes chambre des l'appartements " + 
            "\n\n\t";
        for(Local chambre : this.chambres)
        {
             chaine +=  chambre.afficherLocal()+ "\t";
        }
        return chaine;

    }

    
}
