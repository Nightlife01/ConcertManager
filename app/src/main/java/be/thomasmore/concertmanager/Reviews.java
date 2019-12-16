package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        toonReviews();
    }

    private void toonReviews(){
        List<Concert> concerten = new ArrayList<Concert>();
        concerten.add(new Concert(8, "Werchter Boutique 2019"));
        concerten.add(new Concert(19, "Rock Werchter 2019"));

        ArrayAdapter<Concert> adapter =
                new ArrayAdapter<Concert>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, concerten);

        final ListView listConcerten = (ListView) findViewById(R.id.listViewItems);
        listConcerten.setAdapter(adapter);
    }
}
