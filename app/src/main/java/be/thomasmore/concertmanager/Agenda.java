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
        List<Concert> concerten = new ArrayList<Concert>();
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
    }


}
