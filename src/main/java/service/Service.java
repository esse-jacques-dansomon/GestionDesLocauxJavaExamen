package service;

import java.util.ArrayList;
import java.util.HashMap;

import dao.LocalDao;
import dao.ReservationDao;
import dao.UserDao;
import libraries.Question;
import models.Local;
import models.Personne;
import models.Reservation;

public class Service {
    private LocalDao localDao = new LocalDao();
    private UserDao userDao = new UserDao();
    private ReservationDao reservationDao = new ReservationDao();
    private Question question = new Question();

    public LocalDao getLocalDao() {
        return this.localDao;
    }

    public void setLocalDao(LocalDao localDao) {
        this.localDao = localDao;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ReservationDao getReservationDao() {
        return this.reservationDao;
    }

    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Personne seConnecter(HashMap<String, String> login) {
        return userDao.findNci(login.get("nci"));
    }

    public String addUser(Personne user) {
        return userDao.create(user);
    }

    public void listerLocauxDisponible() {
        ArrayList<Local> locaux = localDao.findAll();
        ArrayList<Reservation> reservations = reservationDao.findAll();
        for (Local local : locaux) {
            boolean find = false;
            for (Reservation reservation : reservations) {
                if (local.getRef().equalsIgnoreCase(reservation.getRefLocal())) {
                    find = true;
                }
            }
            if (!find) {
                System.out.println(local.afficherLocal());
            }
        }
    }
    
    public String addChambre(Local chambre)
    {
        return localDao.create(chambre);
    }

    public String addAppartement(Local appartement)
    {
        return localDao.create(appartement);
    }

    public void listerLocauxParType(String type) 
    {
        ArrayList<Local> locaux = localDao.findAll();
        System.out.println("Les locaux de type : " + type + " sont : \n");
        for (Local local : locaux) {
            if (local.getType().equalsIgnoreCase(type)) {
                System.out.println(local.afficherLocal());
            }
        }
    }
    
    public ArrayList<Local> listerReservationByClient(String nci) 
    {
        ArrayList<Local> locauxClients = new ArrayList<>();
        for(Reservation r:reservationDao.findAll())
        {
            if (r.getNciClient().equalsIgnoreCase(nci)) {
                for (Local local : localDao.findAll()) {
                    if (local.getRef().equalsIgnoreCase(r.getRefLocal())) {
                        locauxClients.add(local);
                    }
                }
            }
        }
        return locauxClients;
    }

    public void listerCLients()
    {
        int i = 1;
        for (Personne p : userDao.findAll()) {
            if (p.getRole().equalsIgnoreCase("client")) {
                System.out.println("Client no :" + i);
                System.out.println(" Nci:" + p.getNci());
                System.out.println(" Nci:" + p.getNomComplet() + "\n");
            }
        }
    }

    public void detailLocal(String ref)
    {
        Local local = localDao.findByRef(ref);
        if (local != null) {
            System.out.println(local);
        } else {
            System.out.println("Erreur de refence");
        }
    }

    public boolean isReserve (String ref)
    {

        if(reservationDao.findByRef(ref) != null && reservationDao.findByRef(ref).getEtat() ) 
        {
            return true;
        }
        else
        {
            return false;            
        }

    }
}