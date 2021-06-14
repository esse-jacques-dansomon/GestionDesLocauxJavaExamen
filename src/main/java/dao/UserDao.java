package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.Personne;

public class UserDao extends AbstractJson implements IDao<Personne>   {

    private final String PATH = "src\\main\\java\\data\\users.json";
    private Gson  gson = new GsonBuilder().create();
    private Type personeAL = new TypeToken<ArrayList<Personne>>(){}.getType();

    public String getPATH() {
        return this.PATH;
    }

    public UserDao() {
    }
    
    @SuppressWarnings("unchecked")
    public String create(Personne obj) {
        JSONObject personneDetails = new JSONObject();
        personneDetails.put("nci", obj.getNci());
        personneDetails.put("nomComplet", obj.getNomComplet());
        personneDetails.put("tel", obj.getTel());
        personneDetails.put("adresse", obj.getAdresse());
        personneDetails.put("email", obj.getEmail());

        // RECUPERATION DES USERS DANS UN TABLEAU JSON
        JSONArray userList = this.jsonFile(this.PATH);

        // AJOUTER OBJ TABLEAU
        userList.add(personneDetails);

        // REECRIRE DANS LE FICHIER LE MODE ECRITURE 
        this.writeJsonArray(userList.toJSONString(), this.PATH);
        return personneDetails.get("nci").toString();
    }


    public boolean update(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public boolean delete(int id) {
        JSONArray userList = this.jsonFile(this.PATH);
        for (Object o : userList) {
            JSONObject user = (JSONObject) o;
            int nci = user.get("nci").hashCode();
            if (id == nci) {
                userList.remove(user);
                this.writeJsonArray(userList.toJSONString(), this.PATH);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Personne> findAll() {
        ArrayList<Personne> allUsers = new ArrayList<>();
        File fileName = new File(this.PATH);
        try {
            allUsers = gson.fromJson(new FileReader(fileName), personeAL);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public Personne findNci(String id) {
        ArrayList<Personne> users = this.findAll();
        for (Personne user : users) {
            if (id.equals(user.getNci())) {
                return user;
            }
        }
        return null;
    }

    public Personne findByRef(String ref) {
        ArrayList<Personne> users = this.findAll();
        for (Personne user : users) {
            if (ref.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Personne findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}