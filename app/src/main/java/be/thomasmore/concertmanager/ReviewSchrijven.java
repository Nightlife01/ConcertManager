package be.thomasmore.concertmanager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ReviewSchrijven extends AppCompatActivity {

    private DatabaseHelper db = new DatabaseHelper(this);
    private Concert concert;

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_schrijven);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addListenerOnRatingBar();
        addListenerOnButton();

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String naam =  bundle.getString("naam");

        getConcert(id);
    }

    private void getConcert(String id)
    {
        concert = db.getConcert(id);
        showConcert(concert);
    }

    private void showConcert(Concert concert)
    {
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(concert.getNaam());

        ImageView image = (ImageView) findViewById(R.id.image);
        new ImageLoadTask(concert.getImage(), image).execute();

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(concert.getDate());

        TextView genreView = (TextView) findViewById(R.id.genre);

        String genres = concert.getGenres();
        genreView.setText(genres);
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);


        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(ReviewSchrijven.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }

}
