package libraries;

import java.util.HashMap;
import java.util.Scanner;

import models.Appartement;
import models.Chambre;
import models.Local;
import models.Personne;

public class Forms {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Question question = new Question();

    public static HashMap<String, String> login()
    {
        HashMap<String, String> login = new HashMap<>();
        String nci = question.askNci();
        String mail = question.askEmail();
        login.put("nci", nci);
        login.put("mail", mail);
        return login;

    }

    public static Personne createAcompte()
    {
        String nci = question.askNci();
        String nomComplet = question.askString("Nom Complet");
        String mail = question.askEmail();
        String tel = question.askTel();
        String adresse = question.askString("adresse");
        Personne personne = new Personne(nci, nomComplet,tel, adresse, mail  );
        return personne;
    }

    public static Local createChambre(Boolean chambreApp)
    {
        int  prix = question.askInt("le prix : ", 0);
        int longueur = question.askInt("Dimensions : longueur", 0);
        int largeur = question.askInt("Dimensions : largeur", 0);
        HashMap<String, Integer> dimensions= new HashMap<>();
        dimensions.put("longueur", longueur);
        dimensions.put("largeur", largeur);
        if (chambreApp){
            return new Chambre( prix,dimensions );
        }
        int  taux = question.askInt("le taux : ", 0);
        String localisation = question.askString("Localisation");
        return new Chambre(localisation, prix, taux,dimensions );
    }

    public static Appartement createAppartement() {
        String localisation = question.askString("le prix : ");
        int prix = question.askInt("le prix ", 0);
        int taux = question.askInt("le taux ", 0);
        int pieces = question.askInt("le nombres de pieces", 0);
        Appartement appa = new Appartement(localisation, prix, taux, pieces);
        for (int i = 0; i < pieces; i++) {
            appa.addChambres(createChambre(true));
        }
        return appa;
    }

    public static int menuVisiteur() {
            System.out.println("\tBIENVENUE SUR LA PAGE D'ACCUEIL \t\t\n ");
            System.out.println("\t\t1- Se Connecter \t ");
            System.out.println("\t\t2- Creer un compte \t");
            System.out.println("\t\t3- Voir la liste des locaux disponibles\t ");
            System.out.println("\t\t4- Quiter \t \n");
            System.out.print("\t\tFaites un choix : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int menuClient() {
            System.out.println("\tBIENVENUE SUR LA PAGE D'ACCUEIL \t\t\n ");
            System.out.println("\t\t1- Voir la liste des locaux disponibles\t ");
            System.out.println("\t\t2- Lister les locaux par type \t");
            System.out.println("\t\t3- Voir details d'un local \t ");
            System.out.println("\t\t4- Faire une Reservation \t");
            System.out.println("\t\t5- Annuler une Reservation \t");
            System.out.println("\t\t6- Quiter \t \n");
            System.out.print("\t\tFaites un choix : ");
            return Integer.parseInt(scanner.nextLine());
        
    }

    public static int menuAdmin() {
        System.out.println("\tBIENVENUE SUR LA PAGE D'ADMINISTRATION \t\t\n ");
        System.out.println("\t\t1- Ajouter un local : une chambre\t ");
        System.out.println("\t\t2- Ajouter un local : un Appartement\t ");
        System.out.println("\t\t3- Lister les locaux par type \t");
        System.out.println("\t\t4- Lister les locaux reservés par un client \t");
        System.out.println("\t\t5- Voir la liste des locaux disponibles\t ");
        System.out.println("\t\t6- Voir details d'un local \t ");
        System.out.println("\t\t7- Quiter \t \n");
        System.out.print("\t\tFaites un choix : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int menuChoixLocal() {
        System.out.println("\tVeuillez choisir le type de local \t\t\n ");
        System.out.println("\t\t1- Chambre\t ");
        System.out.println("\t\t2- Appartement \t");
        System.out.print("\t\tFaites un choix : ");
        return Integer.parseInt(scanner.nextLine());  
}
}
