package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

public class ConcertDetail extends AppCompatActivity {
    private Concert concert;
    private DatabaseHelper db = new DatabaseHelper(this);
    boolean favorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_detail);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String naam =  bundle.getString("naam");

        checkFavorite(id);
        getConcert(id);
    }

    private void getConcert(String id){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                concert = jsonHelper.getConcert(result);
                Log.d("concert", ""+concert);

                showConcert(concert);

            }
        });
        httpReader.execute("https://app.ticketmaster.com/discovery/v2/events/"+id+".json?classificationName=music&countryCode=BE&apikey=ULfwtsW3mXLAZ9euNL3aEFVoIbtGpAeE&size=20");
    }

    private void showConcert(Concert concert){
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(concert.getNaam());

        ImageView image = (ImageView) findViewById(R.id.image);
        new ImageLoadTask(concert.getImage(), image).execute();

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(concert.getDate());

        TextView genreView = (TextView) findViewById(R.id.genre);

        String genres = concert.getGenres();
        genreView.setText(genres);

        Button favoriteButton = (Button) findViewById(R.id.favorite);
        if(favorite){ favoriteButton.setText("Verwijder uit favorieten"); }
        else{ favoriteButton.setText("Voeg toe aan favorieten"); }

    }

    public void onClick(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(concert.getUrl()));
        startActivity(browserIntent);
    }

    public void favorite(View v){
        Log.e("Favorite", ""+concert);
        db.insertFavorite(concert);
    }

    public void checkFavorite(String id){
        favorite = db.checkConcert(id);
    }
}
