package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class ConcertDetail extends AppCompatActivity {
    private Concert concert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_detail);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String naam =  bundle.getString("naam");

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
    }
}
