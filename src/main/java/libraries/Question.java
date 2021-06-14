package libraries;

import java.time.LocalDate;
import java.util.Scanner;


public class Question {

    public Validator validator = new Validator();
    public static final Scanner scanner = new Scanner(System.in);

    public Integer askInt(String name, long min ){
        String entier;
        int entier2;
        do
        {
            System.out.print("Entrer "+ name + " : ");
            entier = Forms.scanner.nextLine();
            entier2 = Integer.parseInt(entier);
            if(entier2 < min){
                flushEcran();
                System.out.println("Valeur incoorecte ");
            }
        } while (entier2 < min);
        return  entier2;
    }
    public String askNci(){
        String nci;
        do
        {
            System.out.print("Entrer votre nci : format '1 102 2017 4545454' : ");
            nci = Forms.scanner.nextLine();
            if (!Validator.isValidNci(nci)) {
                flushEcran();
                System.out.println("Erreur de format : Veuillez reessayer ");
            }
        } while (!Validator.isValidNci(nci));
        return nci ;
    }


    public String askString(String nomString)
    {
        String nomComplet = null;
        do {
            System.out.print("Entrer votre " + nomString + " : ");
            nomComplet = Forms.scanner.nextLine();
            if (!Validator.isValidNom(nomComplet)) {
                flushEcran();
                System.out.println("Erreur de format : Veuillez reessayer ");
            }
        } while (!Validator.isValidNom(nomComplet));
        return nomComplet;
    }


    public String askEmail()
    {
        String email;
        do {
            System.out.print("Entrer votre Email : ");
            email = Forms.scanner.nextLine();
            if (!Validator.isValidMail(email)) {
                flushEcran();
                System.out.println("Erreur de de mail, Veuillez reessayer ");
            }

        } while (!Validator.isValidMail(email));
        return email;
    }


    public LocalDate askDate()
    {
        String date = null;
        do {
            System.out.print("Entrer une date format'yyyy-MM-dd ' : ");
            date = Forms.scanner.nextLine();
            if (Validator.isValideDate(date) == null) {
                flushEcran();
                System.out.println("Format date invalid ");
            }
        } while (Validator.isValideDate(date) == null);
        return Validator.isValideDate(date);
    }


    public String askTel()
    {
        String tel;
        do
        {
            System.out.print("Entrer tel senegalaise: ");
            tel = Forms.scanner.nextLine();
            if (!Validator.isValidTel(tel)) {
                flushEcran();
                System.out.println("Numero incorrecte ");
            }
        }
        while (!Validator.isValidTel(tel));
        return  tel;
    }

    public void flushEcran() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
