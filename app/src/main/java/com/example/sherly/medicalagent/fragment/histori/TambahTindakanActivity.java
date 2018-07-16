package com.example.sherly.medicalagent.fragment.histori;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.model.CreateModel;
import com.example.sherly.medicalagent.model.UpdateModel;
import com.example.sherly.medicalagent.model.shift.DetailShiftModel;
import com.example.sherly.medicalagent.service.ApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahTindakanActivity extends AppCompatActivity {

    EditText etTglShift, etTindakan, etKondisi;
    Button btnSimpanShift;
    Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tindakan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Tambah Laporan");

        final SharedPreferences pref = getSharedPreferences("medigent2", MODE_PRIVATE);
        final String token = pref.getString("token", "null");


        etTglShift = (EditText) findViewById(R.id.etTglShift);
        etTindakan = (EditText) findViewById(R.id.etTindakan);
        etKondisi = (EditText) findViewById(R.id.etKondisi);
        btnSimpanShift = (Button) findViewById(R.id.btnSimpanShift);
        mCalendar = Calendar.getInstance();

        Intent intent = getIntent();
        final String id_orderoffer = intent.getStringExtra("id_orderoffer");

        etTglShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(TambahTindakanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //datePicker.setMinDate(mCalendar.getTime()-(mCalendar.getTime()%(24*60*60*1000)));
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, day);

                        String dateFormat = "dd-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                        etTglShift.setText(simpleDateFormat.format(mCalendar.getTime()));
                        //today.setTimeInMillis();
                        /*if(mCalendar.after(Calendar.getInstance()) || DateUtils.isToday(mCalendar.getTimeInMillis())) {

                        }
                        else {
                            Toast.makeText(PemesananActivity.this, "Tolong pilih tanggal hari ini atau setelah hari ini", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSimpanShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTglShift.getText().toString().isEmpty() || etTindakan.getText().toString().isEmpty() || etKondisi.getText().toString().isEmpty()) {
                    Toast.makeText(TambahTindakanActivity.this, "Mohon lengkapi data laporan Anda", Toast.LENGTH_SHORT).show();
                }
                else {
                    String tgl_shift = etTglShift.getText().toString();
                    String tindakan = etTindakan.getText().toString();
                    String kondisi = etKondisi.getText().toString();

                    DetailShiftModel shiftModel = new DetailShiftModel(tgl_shift, tindakan, kondisi, id_orderoffer);

                    ApiService.service_post.postShift("Bearer "+token, shiftModel).enqueue(new Callback<CreateModel>() {
                        @Override
                        public void onResponse(Call<CreateModel> call, Response<CreateModel> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(TambahTindakanActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(TambahTindakanActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateModel> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
