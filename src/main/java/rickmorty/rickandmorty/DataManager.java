/**
 * Created by Denys Durbalov
 * Date of creation: 5/30/24
 * Project name: RickAndMorty
 * email: den.dyrbalov25@gmail.com or s28680@pjwstk.edu.pl
 */

package rickmorty.rickandmorty;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rickmorty.rickandmorty.model.CharacterModel;
import rickmorty.rickandmorty.model.LocationModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

public class DataManager {

    private static final String API_URL = "https://rickandmortyapi.com/api/character";

    public static void fetchAndSaveData() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String nextUrl = API_URL;

            do {
                JsonObject jsonResponse = fetchJsonFromUrl(nextUrl);
                if (jsonResponse != null) {
                    JsonArray results = jsonResponse.getAsJsonArray("results");
                    for (JsonElement result : results) {
                        saveCharacterFromJson(result.getAsJsonObject(), session);
                    }
                    nextUrl = getNextPageUrl(jsonResponse);
                } else {
                    break;
                }
            } while (nextUrl != null);

            transaction.commit();
        } catch (IOException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static JsonObject fetchJsonFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            return new Gson().fromJson(reader, JsonObject.class);
        } else {
            System.out.println("Failed to fetch data. Response code: " + conn.getResponseCode());
            return null;
        }
    }

    private static void saveCharacterFromJson(JsonObject characterJson, Session session) {
        CharacterModel characterModel = parseCharacterFromJson(characterJson);
        session.saveOrUpdate(characterModel.getOrigin());
        session.saveOrUpdate(characterModel.getLocationModel());
        session.saveOrUpdate(characterModel);
    }

    private static CharacterModel parseCharacterFromJson(JsonObject characterJson) {
        CharacterModel characterModel = new CharacterModel();
        characterModel.setId(characterJson.get("id").getAsInt());
        characterModel.setName(characterJson.get("name").getAsString());
        characterModel.setStatus(characterJson.get("status").getAsString());
        characterModel.setSpecies(characterJson.get("species").getAsString());
        characterModel.setType(characterJson.get("type").getAsString());
        characterModel.setGender(characterJson.get("gender").getAsString());
        characterModel.setImageUrl(characterJson.get("image").getAsString());
        characterModel.setUrl(characterJson.get("url").getAsString());
        characterModel.setCreated(new Timestamp(new Date().getTime()));

        characterModel.setOrigin(parseLocationFromJson(characterJson.getAsJsonObject("origin")));
        characterModel.setLocationModel(parseLocationFromJson(characterJson.getAsJsonObject("location")));

        return characterModel;
    }

    private static LocationModel parseLocationFromJson(JsonObject locationJson) {
        LocationModel locationModel = new LocationModel();
        locationModel.setName(locationJson.get("name").getAsString());
        locationModel.setUrl(locationJson.get("url").getAsString());
        return locationModel;
    }

    private static String getNextPageUrl(JsonObject jsonResponse) {
        JsonObject info = jsonResponse.getAsJsonObject("info");
        JsonElement nextElement = info.get("next");
        return nextElement.isJsonNull() ? null : nextElement.getAsString();
    }
}
