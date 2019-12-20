package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReviewToevoegen extends AppCompatActivity {

    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_toevoegen);

        toonReviewableConcert();
    }


    private void toonReviewableConcert(){
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.e("Datum",""+currentDate);
        final List<Concert> reviewables = db.getReviewableConcerts(currentDate);

        ArrayAdapter<Concert> adapter =
                new ArrayAdapter<Concert>(this,
                        android.R.layout.simple_list_item_1, reviewables);

        final ListView listReviewables = (ListView) findViewById(R.id.listViewItems);
        listReviewables.setAdapter(adapter);

        listReviewables.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parentView,
                                            View childView, int position, long id) {

                        Bundle bundle = new Bundle();
                        bundle.putString("naam", reviewables.get(position).getNaam());
                        bundle.putString("id", reviewables.get(position).getId());

                        Intent intent = new Intent(getApplicationContext(), ReviewSchrijven.class);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
    }

}
