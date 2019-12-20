package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Agenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        toonConcerten();
    }

    private void toonConcerten(){
       /* List<Concert> concerten = new ArrayList<Concert>();
        concerten.add(new Concert(1, "Elton John - Platinum Tickets"));
        concerten.add(new Concert(2, "Werchter Boutique pop-up"));
        concerten.add(new Concert(3, "Werchter Boutique 2020"));
        concerten.add(new Concert(4, "Céline Dion - Platinum Tickets"));
        concerten.add(new Concert(5, "James Arthur"));
        concerten.add(new Concert(6, "Alter Bridge"));

        ArrayAdapter<Concert> adapter =
                new ArrayAdapter<Concert>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, concerten);

        final ListView listConcerten = (ListView) findViewById(R.id.listViewItems);
        listConcerten.setAdapter(adapter);

        */


            HttpReader httpReader = new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    JsonHelper jsonHelper = new JsonHelper();
                    final List<Concert> concerten = jsonHelper.getConcerten(result);

                    ArrayAdapter<Concert> adapter =
                            new ArrayAdapter<Concert>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, concerten);

                    final ListView listConcerten = (ListView) findViewById(R.id.listViewItems);
                    listConcerten.setAdapter(adapter);

                    listConcerten.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parentView,
                                                        View childView, int position, long id) {
                                    //toon(competities.get(position).getNaam());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("naam", concerten.get(position).getNaam());
                                    bundle.putString("id", concerten.get(position).getId());

                                    Intent intent = new Intent(getApplicationContext(), ConcertDetail.class);
                                    intent.putExtras(bundle);

                                    startActivity(intent);

                                }
                            });

                }
            });
            httpReader.execute("https://app.ticketmaster.com/discovery/v2/events.json?classificationName=music&countryCode=BE&apikey=ULfwtsW3mXLAZ9euNL3aEFVoIbtGpAeE&size=20");
        }
}
