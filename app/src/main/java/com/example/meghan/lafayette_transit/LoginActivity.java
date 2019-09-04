package com.example.meghan.lafayette_transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meghan.mp4_drivelog.R;

public class LoginActivity extends AppCompatActivity {
    private Button mSubmit;
    private EditText mUserName, mPassword;
    // private RequestQueue requestQueue;
    // private DatabaseHelper db;
    //   private static final String URL = "http://10.0.2.2:80/lafayette_transit/users.php";

    public static TextView mReadData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = (EditText) findViewById(R.id.userName_field);
        mPassword = (EditText) findViewById(R.id.password_field);
        mSubmit = (Button) findViewById(R.id.connect);
        //db = new DatabaseHelper(this);
        // requestQueue = Volley.newRequestQueue(this);
        mSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, StartActivity.class);
                //  startActivity(i);

                String s1 = mUserName.getText().toString();
                String s2 = mPassword.getText().toString();

                if
                (s1.equals("employee1") && s2.equals("abc123")) {
                    Toast.makeText(getApplicationContext(), "Log in Successful",
                            Toast.LENGTH_SHORT).show();
                    startActivity(i);

                } else {
                    if (!s1.equals("employee1") || !s2.equals(" abc123")) {
                        Toast.makeText(getApplicationContext(), "Wrong User Name And Password", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }
}

