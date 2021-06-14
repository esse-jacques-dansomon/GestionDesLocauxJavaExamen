package models;
public abstract class Local {
    protected String ref;
    protected String localisation;
    protected int prix;
    protected int taux;
    protected String type;

    public String getLocalisation() {
        return this.localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }


    protected static final int FORMAT = 4;
    protected static int nombreLocaux = 0;

    protected Local(String localisation, int prix, int taux) {
        this.ref = generateNumero();
        this.localisation = localisation;
        this.prix = prix;
        this.taux = taux;
    }
    protected Local( int prix) {
        this.ref = generateNumero();
        this.prix = prix;
    }
    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public static int getNombreLocaux() {
        return nombreLocaux;
    }

    public static void setNombreLocaux(int nombreLocaux) {
        Local.nombreLocaux = nombreLocaux;
    }

    private String generateNumero() {
        String nombreZero = "";
        String nombreLocauxString = String.valueOf(nombreLocaux++);
        for (int i = 1; i <= (Local.FORMAT - nombreLocauxString.length()); i++) {
            nombreZero += "0";
        }
        return "Ref" + nombreZero + nombreLocaux;
    }

    public String afficherLocal()
    {
        return "Type du Local : " + type + 
                "\nRef : " + ref + 
                "\nPrix : " + prix + 
                "`\nTaux : " + taux+
                "\nLocalisation : " + localisation;
    }


}
