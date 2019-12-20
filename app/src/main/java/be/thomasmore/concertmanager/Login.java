package be.thomasmore.concertmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    EditText email, passwoord;
    Button loginButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        email = (EditText) findViewById(R.id.email);
        passwoord = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);

        final Intent intent = new Intent(this, MainActivity.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString();
                String s2 = passwoord.getText().toString();
                Boolean checkemailpass = db.emailPasswoord(s1, s2);
                if (checkemailpass == true) {
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fout emailadres of passwoord", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    public void registreer_onClick(View v)
    {
        Intent intent = new Intent(this, Registreer.class);
        startActivity(intent);
    }
}
