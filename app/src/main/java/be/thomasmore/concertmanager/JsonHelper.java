package be.thomasmore.concertmanager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public List<Concert> getConcerten(String jsonTekst) {
        List<Concert> concerten = new ArrayList<Concert>();

        try {
            JSONObject JSONObjectEvenement = new JSONObject(jsonTekst);
            JSONObject jsonObjectEmbedded =  JSONObjectEvenement.getJSONObject("_embedded");

            JSONArray jsonArrayConcerten =  jsonObjectEmbedded.getJSONArray("events");


            for (int i = 0; i < jsonArrayConcerten.length();i++)
            {
                JSONObject JSONObjectConcert = jsonArrayConcerten.getJSONObject(i);
                Concert concert = new Concert();

                concert.setNaam(JSONObjectConcert.getString("name"));
                concert.setId(JSONObjectConcert.getString("id"));
                concerten.add(concert);
            }



        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return concerten;
    }

    public Concert getConcert(String jsonTekst) {
        Concert concert = new Concert();

        try {
            JSONObject JSONObjectConcert = new JSONObject(jsonTekst);

            JSONArray JSONArrayImages = JSONObjectConcert.getJSONArray("images");
            JSONObject JSONObjectImage = JSONArrayImages.getJSONObject(0);

            JSONObject JSONObjectDates = JSONObjectConcert.getJSONObject("dates");
            JSONObject JSONObjectDate = JSONObjectDates.getJSONObject("start");

            JSONArray jsonArrayGenres = JSONObjectConcert.getJSONArray("classifications");

            String genres = "";
            String genre0 = "";
            for (int i = 0; i < jsonArrayGenres.length(); i++)
            {
                JSONObject jsonObjectGenre = jsonArrayGenres.getJSONObject(i);
                JSONObject jsonObjectGenreName = jsonObjectGenre.getJSONObject("genre");
                JSONObject jsonObjectSubGenreName = jsonObjectGenre.getJSONObject("subGenre");

                String genre = jsonObjectGenreName.getString("name");
                String subGenre = jsonObjectSubGenreName.getString("name");

                if(genre.equals(genre0)){
                }
                else{
                    genre0 = genre;
                    genres += genre;
                    genres += ", ";
                }
                genres+=subGenre;
                genres+=", ";
            }

            concert.setNaam(JSONObjectConcert.getString("name"));
            concert.setId(JSONObjectConcert.getString("id"));
            concert.setImage(JSONObjectImage.getString("url"));
            concert.setDate(JSONObjectDate.getString("localDate"));
            concert.setGenres(genres);
            concert.setUrl(JSONObjectConcert.getString("url"));


        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return concert;
    }

}

