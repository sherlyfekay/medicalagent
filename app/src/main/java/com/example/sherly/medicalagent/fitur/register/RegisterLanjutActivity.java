package com.example.sherly.medicalagent.fitur.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.fitur.login.LoginActivity;
import com.example.sherly.medicalagent.model.role.DataRole;
import com.example.sherly.medicalagent.model.role.RoleModel;
import com.example.sherly.medicalagent.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterLanjutActivity extends AppCompatActivity {

    TextView tvKembali;
    Button btnRegister;
    Spinner spRole;
    ArrayList<DataRole> dataRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lanjut);

        tvKembali = (TextView) findViewById(R.id.tvKembali);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        spRole = (Spinner) findViewById(R.id.spRole);

        ApiService.service_get.getRole().enqueue(new Callback<RoleModel>() {
            @Override
            public void onResponse(Call<RoleModel> call, Response<RoleModel> response) {
                if(response.isSuccessful()) {
                    dataRoles = response.body().getRoles();
                    ArrayList<String> nama_role = new ArrayList<>();
                    ArrayList<String> id_role = new ArrayList<>();

                    for (int i=0; i < response.body().getCount(); i++) {
                        nama_role.add(dataRoles.get(i).getNama_role());
                        id_role.add(dataRoles.get(i).get_id());
                    }

                    ArrayAdapter<String> adapterRole = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nama_role);
                    spRole.setAdapter(adapterRole);
                    adapterRole.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(RegisterLanjutActivity.this, "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RoleModel> call, Throwable t) {

            }
        });

        tvKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterLanjutActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RegisterLanjutActivity.this, LoginActivity.class);
                startActivity(intent2);
            }
        });
    }
}
