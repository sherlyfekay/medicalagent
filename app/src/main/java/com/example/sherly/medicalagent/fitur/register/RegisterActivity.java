package com.example.sherly.medicalagent.fitur.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sherly.medicalagent.MainActivity;
import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.fitur.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    Button btnLanjut;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLanjut = (Button) findViewById(R.id.btnLanjut);
        tvLogin = (TextView) findViewById(R.id.tvLogin);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, RegisterLanjutActivity.class);
                startActivity(intent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent2);
            }
        });
    }
}
