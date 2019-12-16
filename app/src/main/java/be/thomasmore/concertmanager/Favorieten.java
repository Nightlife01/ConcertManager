package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Favorieten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorieten);

        toonFavorieten();
    }


    private void toonFavorieten(){
        List<Concert> concerten = new ArrayList<Concert>();
        concerten.add(new Concert(1, "Elton John - Platinum Tickets"));
        concerten.add(new Concert(5, "James Arthur"));

        ArrayAdapter<Concert> adapter =
                new ArrayAdapter<Concert>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, concerten);

        final ListView listConcerten = (ListView) findViewById(R.id.listViewItems);
        listConcerten.setAdapter(adapter);
    }
}
