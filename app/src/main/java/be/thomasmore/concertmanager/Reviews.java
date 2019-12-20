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

public class Reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        toonReviews();
    }

    private void toonReviews(){
        final List<Concert> reviews = new ArrayList<Concert>();

        ArrayAdapter<Concert> adapter =
                new ArrayAdapter<Concert>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, reviews);

        final ListView listConcerten = (ListView) findViewById(R.id.listViewItems);
        listConcerten.setAdapter(adapter);

        listConcerten.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parentView,
                                            View childView, int position, long id) {

                        Bundle bundle = new Bundle();
                        bundle.putString("naam", reviews.get(position).getNaam());
                        bundle.putString("id", reviews.get(position).getId());

                        Intent intent = new Intent(getApplicationContext(), ReviewSchrijven.class);
                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                });
    }
}
