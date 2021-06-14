package com.dansomon;

import java.time.LocalDate;
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
    private static final Service  service = new Service();

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
                                        service.flushEcran();
                                        break;
                                    case 2:
                                        System.out.println("Nouveau Appartement ajoutée \nRecf : " +service.addAppartement( Forms.createAppartement())) ;
                                        service.flushEcran();
                                        break;
                                    case 3:
                                        System.out.println("Entrer type de local : \nCmabre \nAppartement");
                                        service.listerLocauxParType(scanner.nextLine());
                                        service.flushEcran();
                                        break;
                                    case 4:
                                        service.listerCLients();
                                        String nci = service.getQuestion().askNci();
                                        if (!service.listerReservationByClient(nci).isEmpty())
                                        {
                                            for (Local local: service.listerReservationByClient(nci) )
                                            {
                                                local.afficher();
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Le client n'as pas de reservation ou n'exisite pas");
                                        }

                                        service.flushEcran();
                                        break;
                                    case 5:
                                        service.afficherLocauxDisponibles();
                                        service.flushEcran();
                                        break;
                                    case 6:
                                        String ref = service.getQuestion().askString("Ref : ");
                                        service.detailLocal(ref);
                                        service.flushEcran();
                                        break;
                                    case 7:
                                        service.flushEcran();
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
                                        service.afficherLocauxDisponibles();
                                        service.flushEcran();
                                        break;
                                    case 2:
                                        System.out.println("Entrer type de local : \nCmabre \nAppartement");
                                        service.listerLocauxParType(scanner.nextLine());
                                        service.flushEcran();
                                        break;
                                    case 3:
                                        String ref = service.getQuestion().askString("Ref : ");
                                        service.detailLocal(ref);
                                        service.flushEcran();
                                        break;
                                    case 4:
                                        ref = service.getQuestion().askString("Ref du local");
                                        if (service.getLocalDao().findByRef(ref) != null)
                                        {
                                            if(!service.isReserve(ref))
                                            {
                                                int duree = service.getQuestion().askInt("La duree", 1);
                                                Reservation reservation = new Reservation(LocalDate.now(), true, duree,
                                                        user.getNci(), ref);
                                                service.addReservation(reservation);
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("local n'existe pas ");
                                        }
                                        service.flushEcran();
                                        break;
                                    case 5:
                                        service.afficherReservationByClient(user.getNci());
                                        int id = service.getQuestion().askInt("Id de la reservation :", 0);
                                        if (service.getReservationDao().findOne(id) != null) {
                                            service.annulerReservation(service.getReservationDao().findOne(id));
                                        }
                                        else
                                        {
                                            System.out.println("Vous n'a pas de de reservations");
                                        }
                                        service.flushEcran();
                                        break;
                                    case 6:
                                        service.flushEcran();
                                        break;
                                    default:
                                }
                            } while (choixCLient != 6);
                        }
                    } else {
                        System.out.println("erreur de login ou password");
                    }

                    service.flushEcran();
                    break;
                case 2:
                    user = Forms.createAcompte();
                    System.out.println("Votre nci " + service.addUser(user));
                    service.flushEcran();
                    break;
                case 3:
                    service.afficherLocauxDisponibles();
                    break;
                case 4:
                    String ref = service.getQuestion().askString("Ref du local");
                    if (service.getLocalDao().findByRef(ref) != null) {
                        if (!service.isReserve(ref)) {
                            int duree = service.getQuestion().askInt("La duree", 1);
                            user = Forms.createAcompte();
                            service.addUser(user);
                            Reservation reservation = new Reservation(LocalDate.now(), true, duree, user.getNci(), ref);
                            service.addReservation(reservation);
                        } else {
                            System.out.println("local deja reservé ");
                        }
                    } else {
                        System.out.println("local n'existe pas ");
                    }
                    service.flushEcran();
                    break;
                case 5:
                    System.out.println("Bye");
                    service.flushEcran();
                    break;
                default:
                    System.out.println("Erreur d'entier");

            }
        } while (choix != 5);
    }
}
