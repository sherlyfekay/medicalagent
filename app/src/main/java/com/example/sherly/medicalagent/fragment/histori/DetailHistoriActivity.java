package com.example.sherly.medicalagent.fragment.histori;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.model.CreateModel;
import com.example.sherly.medicalagent.model.UpdateModel;
import com.example.sherly.medicalagent.model.histori.DetailHistoryModel;
import com.example.sherly.medicalagent.model.shift.ShiftModel;
import com.example.sherly.medicalagent.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoriActivity extends AppCompatActivity {

    private RecyclerView rvDetailHistori;
    private DetailHistoriAdapter historiAdapter;
    private TextView tvCreatedAt, tvNamaPasien, tvDiagnosa, tvRole, tvNamaAgent, tvJumlahShift;
    private FloatingActionButton fab, fabDone;
    private String id_agent, token, id_orderoffer;
    private Integer status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_histori);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Detail Histori");

        final SharedPreferences pref = getSharedPreferences("medigent2", MODE_PRIVATE);
        id_agent = pref.getString("id_agent", "null");
        token = pref.getString("token", "null");

        rvDetailHistori = (RecyclerView) findViewById(R.id.rvDetailHistori);
        tvCreatedAt = (TextView) findViewById(R.id.tvCreatedAt);
        tvNamaPasien = (TextView) findViewById(R.id.tvNamaPasien);
        tvDiagnosa = (TextView) findViewById(R.id.tvDiagnosa);
        tvRole = (TextView) findViewById(R.id.tvRole);
        tvNamaAgent = (TextView) findViewById(R.id.tvNamaAgent);
        tvJumlahShift = (TextView) findViewById(R.id.tvJumlahShift);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabDone = (FloatingActionButton) findViewById(R.id.fabDone);

        Intent intent = getIntent();
        id_orderoffer = intent.getStringExtra("id_orderoffer");
        //Integer status = intent.getIntExtra("status", 0);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailHistoriActivity.this, TambahTindakanActivity.class);
                intent1.putExtra("id_orderoffer", id_orderoffer);
                startActivity(intent1);
            }
        });

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DetailHistoriActivity.this);

                builder.setMessage("Apakah Anda yakin penanganan untuk pasien ini telah selesai ?");

                builder.setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {

                        ArrayList<UpdateModel> arrayList = new ArrayList<>();
                        arrayList.add(new UpdateModel("status", 3));

                        ApiService.service_patch.patchOO("Bearer "+token, id_orderoffer, arrayList).enqueue(new Callback<CreateModel>() {
                            @Override
                            public void onResponse(Call<CreateModel> call, Response<CreateModel> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(DetailHistoriActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                    finish();
//                                    overridePendingTransition(0, 0);
//                                    startActivity(getIntent());
//                                    overridePendingTransition(0, 0);
                                }
                                else {
                                    Toast.makeText(DetailHistoriActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<CreateModel> call, Throwable t) {
                                Toast.makeText(DetailHistoriActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
//            //return false;
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Toast.makeText(DetailHistoriActivity.this, "Status : "+status, Toast.LENGTH_SHORT).show();
        *//*if(status.equals(null)) {
            menu.getItem(0).setVisible(false);
        }
        else if(status == 3) {
            menu.getItem(0).setVisible(false);
        }
        else {
            menu.getItem(0).setVisible(true);
        }*//*
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            Toast.makeText(DetailHistoriActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            *//*final SharedPreferences pref = getSharedPreferences("medigent2", MODE_PRIVATE);
            final String token = pref.getString("token", "null");

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Apakah Anda yakin penanganan untuk histori ini telah selesai ?");

            builder.setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {

                    UpdateModel updateModel = new UpdateModel("status", 3);

                    ApiService.service_patch.patchOO("Bearer "+token, id_orderoffer,updateModel).enqueue(new Callback<CreateModel>() {
                        @Override
                        public void onResponse(Call<CreateModel> call, Response<CreateModel> response) {
                            Toast.makeText(DetailHistoriActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        }

                        @Override
                        public void onFailure(Call<CreateModel> call, Throwable t) {

                        }
                    });
                }
            });

            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            final AlertDialog alertDialog = builder.create();
            alertDialog.show();*//*
//            //return false;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onResume() {
        super.onResume();

        ApiService.service_get.getHistoryByOO("Bearer "+token, id_orderoffer).enqueue(new Callback<DetailHistoryModel>() {
            @Override
            public void onResponse(Call<DetailHistoryModel> call, Response<DetailHistoryModel> response) {
                if(response.isSuccessful()) {
                    tvCreatedAt.setText(response.body().getCreated_at());
                    tvNamaPasien.setText(response.body().getNama_pasien());
                    tvDiagnosa.setText(response.body().getDiagnosa());
                    tvRole.setText(response.body().getRole());
                    tvNamaAgent.setText(response.body().getNama_agent());
                    tvJumlahShift.setText(""+response.body().getJml_shift());

                    status = response.body().getStatus();
                    if(status == 3) {
                        fab.setVisibility(View.GONE);
                        fabDone.setVisibility(View.GONE);
                    }
                }
                else {
                    Toast.makeText(DetailHistoriActivity.this, "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DetailHistoryModel> call, Throwable t) {

            }
        });

        ApiService.service_get.getShiftByOO("Bearer "+token, id_orderoffer).enqueue(new Callback<ShiftModel>() {
            @Override
            public void onResponse(Call<ShiftModel> call, Response<ShiftModel> response) {
                if(response.isSuccessful()) {
                    historiAdapter = new DetailHistoriAdapter(DetailHistoriActivity.this, response.body().getShifts());
                    rvDetailHistori.setLayoutManager(new LinearLayoutManager(DetailHistoriActivity.this));
                    rvDetailHistori.setFocusable(false);
                    rvDetailHistori.setNestedScrollingEnabled(false);
                    rvDetailHistori.setAdapter(historiAdapter);
                }
                else {
                    Toast.makeText(DetailHistoriActivity.this, "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShiftModel> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
