package com.example.sherly.medicalagent.fitur.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherly.medicalagent.MainActivity;
import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.fitur.register.RegisterActivity;
import com.example.sherly.medicalagent.model.login.LoginModel;
import com.example.sherly.medicalagent.model.login.PostLoginModel;
import com.example.sherly.medicalagent.service.ApiService;
import com.example.sherly.medicalagent.service.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnLogin;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);

        if(session.loggedin()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        tvRegister = (TextView) findViewById(R.id.tvRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data_email = etEmail.getText().toString();
                String data_password = etPassword.getText().toString();

                if(data_email.isEmpty() || data_password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Mohon lengkapi form login Anda. Terima kasih", Toast.LENGTH_SHORT).show();
                }
                else {

                    PostLoginModel postLoginModel = new PostLoginModel(data_email, data_password);

                    ApiService.service_post.postLogin(postLoginModel).enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                            if(response.isSuccessful()) {
                                String status = response.body().getStatus();

                                if(status.equals("100")) {
                                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();

                                    String id_agent = response.body().getId_agent();
                                    String token = response.body().getToken();

                                    session.setLoggedin(true, token, id_agent);

                                    Intent masuk = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(masuk);
                                    finish();
                                }
                                else if (status.equals("101")) {
                                    Toast.makeText(LoginActivity.this, ""+ response.body().getMessage(), Toast.LENGTH_LONG).show();
                                }

                                else if (status.equals("102")) {
                                    Toast.makeText(LoginActivity.this, ""+ response.body().getMessage(), Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, ""+ response.body().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Data tidak ditemukan", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Cek sambungan internet Anda", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
