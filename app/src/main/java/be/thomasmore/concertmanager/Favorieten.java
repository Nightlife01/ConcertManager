package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Favorieten extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorieten);

        db = new DatabaseHelper(this);
        toonFavorieten();
    }


    private void toonFavorieten(){
        final List<Concert> favorieten = db.getConcerten();

        ArrayAdapter<Concert> adapter =
                new ArrayAdapter<Concert>(this,
                        android.R.layout.simple_list_item_1, favorieten);

        final ListView listFavorieten = (ListView) findViewById(R.id.listViewItems);
        listFavorieten.setAdapter(adapter);
    }


}
