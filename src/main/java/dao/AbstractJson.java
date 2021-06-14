package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class AbstractJson {

    /**
     *Fonction qui permet de lire un fichier json sous 
     *@param String path : chemein du fichier 
     *@return retoune un tableau d'objects json
     */
    public JSONArray jsonFile(String path) {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonFile = new JSONArray();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            jsonFile = (JSONArray) obj;
        } catch (IOException |ParseException e) {
            e.printStackTrace();
        }
        return jsonFile;

    }

    /**
     *Fonction qui permet d'ecrire un JSONArray dans un fichon json 
     *@param JSONArray : tableau de json contenant les objects a ecrire 
     *@param String path : le chemin de l'ecriture du fichier
     */
    public void writeJsonArray(String jsonArray, String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonArray);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
