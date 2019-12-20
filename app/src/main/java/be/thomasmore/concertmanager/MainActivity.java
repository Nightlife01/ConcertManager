package be.thomasmore.concertmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_info:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_concerten:
                Intent intent2 = new Intent(getApplicationContext(), Agenda.class);
                startActivity(intent2);
                return true;
            case R.id.menu_favorieten:
                Intent intent3 = new Intent(getApplicationContext(), Favorieten.class);
                startActivity(intent3);
                return true;
            case R.id.menu_reviews:
                Intent intent4 = new Intent(getApplicationContext(), Reviews.class);
                startActivity(intent4);
                return true;
            case R.id.menu_kaart:
                Intent intent5 = new Intent(getApplicationContext(), Kaart.class);
                startActivity(intent5);
                return true;
            case R.id.menu_toevoegen:
                Intent intent6 = new Intent(getApplicationContext(), ReviewToevoegen.class);
                startActivity(intent6);
                return true;
            case R.id.menu_login:
                Intent intent7 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent7);
                return true;
            default:
                return false;
        }
    }
}
