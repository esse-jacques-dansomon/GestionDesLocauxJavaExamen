package models;
import java.util.HashMap;

public class Chambre extends Local {

    private HashMap<String, Integer> dimensions = new HashMap<>();

    public Chambre(String location, int prix, int taux, HashMap<String, Integer> dimensions) {
        super(location, prix, taux);
        this.dimensions = dimensions;
        this.type = "Chambre";
    }
    public Chambre( int prix, HashMap<String, Integer> dimensions) {
        super( prix);
        this.dimensions = dimensions;
        this.type = "Chambre";

    }
    public HashMap<String, Integer> getDimensions() {return dimensions;}

    public void setDimensions(HashMap<String, Integer> dimensions) {this.dimensions = dimensions;}

    @Override
    public String afficherLocal()
    {
        return super.afficherLocal() + 
                "\nDimensions : " + dimensions + 
                "\n";
    }
    
    
}
