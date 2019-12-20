package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Agenda extends AppCompatActivity {

    private List<String> sortering = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        toonSorteren();
        toonConcerten();
    }

    private void toonSorteren(){
        sortering.add("Populariteit");
        sortering.add("Datum");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, sortering);

        final Spinner spinnerSort = (Spinner) findViewById(R.id.sortering);
        spinnerSort.setAdapter(adapter);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                toonConcerten();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                toonConcerten();
            }

        });
    }

    private void toonConcerten(){
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

        Spinner spinnerSort = (Spinner) findViewById(R.id.sortering);
        String sort = (String)spinnerSort.getSelectedItem();

        if (sort.equals("Datum"))
        {
            httpReader.execute("https://app.ticketmaster.com/discovery/v2/events.json?classificationName=music&countryCode=BE&sort=date,asc&apikey=ULfwtsW3mXLAZ9euNL3aEFVoIbtGpAeE&size=20");
        }
        else{
            httpReader.execute("https://app.ticketmaster.com/discovery/v2/events.json?classificationName=music&countryCode=BE&apikey=ULfwtsW3mXLAZ9euNL3aEFVoIbtGpAeE&size=20");
        }

        }
}
