package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import libraries.LocalDateDeserializer;
import libraries.LocalDateSerializer;
import libraries.LocalDateTimeDeserializer;
import libraries.LocalDateTimeSerializer;
import models.Reservation;


public class ReservationDao extends AbstractJson implements IDao<Reservation> {

    private final String PATH = "src\\main\\java\\data\\reservations.json";
    private  GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

    Gson gson = gsonBuilder.setPrettyPrinting().create();

    private Type personeAL = new TypeToken<ArrayList<Reservation>>(){}.getType();
    public ReservationDao(){
    }

    /**
     *permet d'ajouter une reservation
     *@return void
     */
    @Override
    @SuppressWarnings("unchecked")
    public String create(Reservation obj) {
        JSONObject reservationDetails = new JSONObject();
        reservationDetails.put("id", obj.getId());
        reservationDetails.put("date", obj.getDate());
        reservationDetails.put("etat", obj.getEtat());
        reservationDetails.put("duree", obj.getDuree());
        reservationDetails.put("nciClient", obj.getNciClient());
        reservationDetails.put("refLocal", obj.getRefLocal());
        JSONArray reservationList = this.jsonFile(this.PATH);
        reservationList.add(reservationDetails);
        this.writeJsonArray(reservationList.toJSONString(), this.PATH);
        return (String)reservationDetails.get("id");

    }

    @Override
    public boolean update(Reservation obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(int id) {
        JSONArray reservationList = this.jsonFile(this.PATH);
        for (Object o : reservationList) {
            JSONObject reservation = (JSONObject) o;
            if (id == reservation.get("id").hashCode()) {
                reservationList.remove(reservation);
                this.writeJsonArray(reservationList.toJSONString(), this.PATH);
                return true;
            }
        }
        return false;
    }

   @Override
    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> allReservations = new ArrayList<>();
        File fileName = new File(this.PATH);
        try {
            allReservations = gson.fromJson(new FileReader(fileName), personeAL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allReservations;

    }
    public ArrayList<Reservation> findAllByClient(String nci ){
        ArrayList<Reservation> allReservations =this.findAll();
        ArrayList<Reservation> allReservationsByClient = new ArrayList<>();
        for (Reservation reservation : allReservations){
            if (reservation.getNciClient().equalsIgnoreCase(nci)) 
            {
                allReservationsByClient.add(reservation);
            }
        }
        return allReservationsByClient;

    }
    @Override
    public Reservation findOne(int id) {
        ArrayList<Reservation> allReservations =this.findAll();
        for (Reservation reservation : allReservations){
            if(reservation.getId() == id ){
                return reservation;
            }
        }
        return null;
    }

    public Reservation findByRef(String ref) {
        ArrayList<Reservation> allReservations =this.findAll();
        for (Reservation reservation : allReservations){
            if(reservation.getRefLocal().equalsIgnoreCase(ref) ){
                return reservation;
            }
        }
        return null;    }


}
