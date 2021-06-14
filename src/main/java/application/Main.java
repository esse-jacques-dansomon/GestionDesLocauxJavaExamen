package application;

 
import java.util.HashMap;
import java.util.Scanner;

import libraries.Forms;
import models.Personne;
import models.Reservation;
import models.Local;
import service.Service;

public class Main 
{
    public static final Scanner scanner = new Scanner(System.in);
    private static Service service = new Service();

    public static void main(String[] args) {
        int choix;
        do {
            choix = Forms.menuVisiteur();
            Personne user;
            switch (choix) {
                case 1:
                    HashMap<String, String> login = Forms.login();
                    user = service.seConnecter(login);
                    if (user != null) {
                        System.out.println(user.afficherPersonne());
                        if (user.getRole().equalsIgnoreCase("admin")) {
                            int choixAdmin;
                            do {
                                choixAdmin = Forms.menuAdmin();
                                switch (choixAdmin) {
                                    case 1:
                                        System.out.println("Nouvelle chambre ajoutée \nRecf : " +service.addChambre( Forms.createChambre(false))) ;
                                        break;
                                    case 2:
                                        System.out.println("Nouveau Appartement ajoutée \nRecf : " +service.addAppartement( Forms.createAppartement())) ;
                                        break;
                                    case 3:
                                        System.out.println("Entrer type de local : \nCmabre \nAppartement");
                                        service.listerLocauxParType(scanner.nextLine());
                                        break;
                                    case 4:
                                        service.listerCLients();
                                        String nci = service.getQuestion().askNci();
                                        if (!service.listerReservationByClient(nci).isEmpty())
                                        {
                                            for (Local local: service.listerReservationByClient(nci) )
                                            {
                                                local.afficherLocal();
                                            }
                                        }
                                        else 
                                        {
                                            System.out.println("Le client n'as pas de reservation ou n'exisite pas");
                                        }
                                        break;
                                    case 5:
                                        service.listerLocauxDisponible();
                                        break;
                                    case 6:
                                        String ref = service.getQuestion().askString("Ref : ");
                                        service.detailLocal(ref);
                                        break;
                                    case 7:
                                        break;
                                    default:
                                }
                            } while (choixAdmin != 7);
                        } else if (user.getRole().equalsIgnoreCase("client")) {
                            int choixCLient = Forms.menuClient();
                            do {
                                choixCLient = Forms.menuClient();
                                switch (choixCLient) {
                                    case 1:
                                        service.listerLocauxDisponible();
                                        break;
                                    case 2:
                                        System.out.println("Entrer type de local : \nCmabre \nAppartement");
                                        service.listerLocauxParType(scanner.nextLine());
                                        break;
                                    case 3:
                                        String ref = service.getQuestion().askString("Ref : ");
                                        service.detailLocal(ref);
                                        break;
                                    case 4:
                                        ref = service.getQuestion().askString("Ref du local");
                                        if (service.getLocalDao().findByRef(ref) != null)
                                        {
                                            if(!service.isReserve(ref))
                                            {
                                                Reservation reservation  = new Reservation();
                                                service.addReservation(reservation);
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("local n'existe pas ");
                                        }
                                        break;
                                    case 5:
                                        break;
                                    case 6:
                                        break;
                                    default:
                                }
                            } while (choixCLient != 6);
                        }
                    } else {
                        System.out.println("erreur de login ou password");
                    }

                    break;
                case 2:
                    user = Forms.createAcompte();
                    System.out.println("Votre nci " + service.addUser(user));
                    break;
                case 3:
                    service.listerLocauxDisponible();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Erreur d'entier");
                    break;
            }
        } while (choix != 4);
    }
}