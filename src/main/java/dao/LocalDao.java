package dao;

import models.Local;
import models.Appartement;
import models.Chambre;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LocalDao extends AbstractJson implements IDao<Local> {

    private final String PATH = "src\\main\\java\\data\\locaux.json";

    @SuppressWarnings("unchecked")
    public String create(Local obj) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String local = gson.toJson(obj);
        JSONArray locauxList = this.jsonFile(this.PATH);
        locauxList.add(local);
        this.writeJsonArray(locauxList.toJSONString(), this.PATH);
        return obj.getRef();
    }

    @Override
    public boolean update(Local obj) {
        return false;
    }
    @Override
    public boolean delete(int id){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    public boolean delete(String ref) {
        JSONArray locauxList = this.jsonFile(this.PATH);
        for (Object o : locauxList) {
            JSONObject local = (JSONObject) o;
            if (ref.equalsIgnoreCase(local.get("ref").toString()) ) {
                locauxList.remove(local);
                this.writeJsonArray(locauxList.toJSONString(), this.PATH);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Local> findAll() {
        ArrayList<Local> allLocaux = new ArrayList<>();
        JSONArray locauxList = this.jsonFile(this.PATH);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        for (Object o : locauxList) {
            JSONObject local = (JSONObject) o;
            if (local.containsKey("chambres")) {
                JSONArray chambres =(JSONArray) local.get("chambres");
                String localisation =local.get("localisation").toString();
                int prix = local.get("prix").hashCode();
                int taux =  local.get("taux").hashCode();
                int pieces = local.get("pieces").hashCode();
                Appartement appartement = new Appartement(localisation, prix ,taux , pieces);
                for (Object chambreApp : chambres) {
                    Chambre chambreAppartement = gson.fromJson(chambreApp.toString(), Chambre.class);
                    appartement.addChambres(chambreAppartement);
                }
                allLocaux.add(appartement);
            }
            else
            {
                Chambre chambre = gson.fromJson(local.toString(), Chambre.class);
                allLocaux.add(chambre);
            } 
            }
        return allLocaux;
    }

    @Override
    public Local findOne(int id) {
        return null;
    }

    @Override
    public Local findByRef(String ref) {
        for (Local local : findAll())
        {
            if(local.getRef().equalsIgnoreCase(ref))
            {
                return local;
            }
        }
        return null;
    }
}
