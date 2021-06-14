import java.time.LocalDate;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import libraries.LocalDateSerializer;
import libraries.LocalDateDeserializer;
import libraries.LocalDateTimeDeserializer;
import libraries.LocalDateTimeSerializer;
import models.Reservation;


public class Main {
    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

    Gson gson = gsonBuilder.setPrettyPrinting().create();
        Reservation reservation = new Reservation( );
        System.out.println(reservation.toString());

        String reservationJson = gson.toJson(reservation);
        System.out.println(reservationJson);

        Reservation reservation1 = gson.fromJson(reservationJson, Reservation.class);
        System.out.println(reservation1.toString());


    }
}