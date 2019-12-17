package be.thomasmore.concertmanager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registreer extends AppCompatActivity {
DatabaseHelper db;
    EditText email,passwoord, confirmpasswoord;
    Button buttonRegistreer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db= new DatabaseHelper(this);
        email=(EditText)findViewById(R.id.email) ;
        passwoord=(EditText)findViewById(R.id.password) ;
        confirmpasswoord=(EditText)findViewById(R.id.confirmPassword) ;
        buttonRegistreer=(Button)findViewById(R.id.registreer);

        buttonRegistreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1= email.getText().toString();
                String s2=passwoord.getText().toString();
                String s3=confirmpasswoord.getText().toString();
                if (s1.equals("")|| s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Velden zijn leeg",Toast.LENGTH_SHORT).show();

                }
                else {
                    if (s2.equals(s3)){
                        boolean checkemail=db.CheckEmail(s1);
                        if (checkemail==true)
                        {
                            boolean insert= db.insert(s1,s2);
                            if (insert==true)
                            {
                                Toast.makeText(getApplicationContext(),"Registreren is gelukt",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email bestaat Al",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Passwoord klopt niet" +
                                "",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }

}
