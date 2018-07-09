package com.example.sherly.medicalagent.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.fitur.login.LoginActivity;
import com.example.sherly.medicalagent.fragment.beranda.MapsActivity;
import com.example.sherly.medicalagent.model.UpdateModel;
import com.example.sherly.medicalagent.model.agent.AgentModel;
import com.example.sherly.medicalagent.model.agent.DataAgentModel;
import com.example.sherly.medicalagent.service.ApiService;
import com.example.sherly.medicalagent.service.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    TextView tvNamaLengkap, tvTglLahir, tvJk, tvTelepon, tvJudul, tvAlamat, tvTambahan, tvLogout;
    ImageView ivFoto;
    Session session;
    SharedPreferences pref;
    String id_agent, token;
    String nama_lengkap, jk, tgl_lahir, telepon, judul, alamat, tambahan;
    LinearLayout llNama, llTglLahir, llJk, llTelepon, llAlamat;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profil, container, false);

        pref = getActivity().getSharedPreferences("medigent2", MODE_PRIVATE);
        id_agent = pref.getString("id_agent", "null");
        token = pref.getString("token", "null");

        ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
        tvNamaLengkap = (TextView) view.findViewById(R.id.tvNamaLengkap);
        tvTglLahir = (TextView) view.findViewById(R.id.tvTglLahir);
        tvJk = (TextView) view.findViewById(R.id.tvJk);
        tvTelepon = (TextView) view.findViewById(R.id.tvTelepon);
        tvJudul = (TextView) view.findViewById(R.id.tvJudul);
        tvAlamat = (TextView) view.findViewById(R.id.tvAlamat);
        tvTambahan = (TextView) view.findViewById(R.id.tvTambahan);
        tvLogout = (TextView) view.findViewById(R.id.tvLogout);
        llNama = (LinearLayout) view.findViewById(R.id.llNama);
        llTglLahir = (LinearLayout) view.findViewById(R.id.llTglLahir);
        llJk = (LinearLayout) view.findViewById(R.id.llJk);
        llTelepon = (LinearLayout) view.findViewById(R.id.llTelepon);
        llAlamat = (LinearLayout) view.findViewById(R.id.llAlamat);

        //Toast.makeText(getActivity(), ""+token, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(), ""+id_agent, Toast.LENGTH_SHORT).show();

        llNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_custom_view, null);
                final EditText etEditNama = (EditText) dialogView.findViewById(R.id.etEdit);

                etEditNama.setText(nama_lengkap);

                builder.setCancelable(false);
                builder.setView(dialogView);
                builder.setMessage("Nama Lengkap");

                builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        final String nama_lengkap = etEditNama.getText().toString();

                        UpdateModel newUpdate = new UpdateModel("nama_lengkap", nama_lengkap);
                        ApiService.service_patch.patchAgent("Bearer "+token, id_agent, newUpdate).enqueue(new Callback<AgentModel>() {
                            @Override
                            public void onResponse(Call<AgentModel> call, Response<AgentModel> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Nama lengkap Anda berhasil diperbarui", Toast.LENGTH_SHORT).show();
                                    tvNamaLengkap.setText(nama_lengkap);
                                    dialogInterface.dismiss();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<AgentModel> call, Throwable t) {

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
            }
        });

        llTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_custom_view, null);
                final EditText etEditTglLahir = (EditText) dialogView.findViewById(R.id.etEdit);

                etEditTglLahir.setText(tgl_lahir);

                builder.setCancelable(false);
                builder.setView(dialogView);
                builder.setMessage("Tanggal Lahir");

                builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        final String tgl_lahir = etEditTglLahir.getText().toString();

                        UpdateModel newUpdate = new UpdateModel("tgl_lahir", tgl_lahir);
                        ApiService.service_patch.patchAgent("Bearer "+token, id_agent, newUpdate).enqueue(new Callback<AgentModel>() {
                            @Override
                            public void onResponse(Call<AgentModel> call, Response<AgentModel> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Tanggal lahir Anda berhasil diperbarui", Toast.LENGTH_SHORT).show();
                                    tvTglLahir.setText(tgl_lahir);
                                    dialogInterface.dismiss();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<AgentModel> call, Throwable t) {

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
            }
        });

        llJk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_custom_view, null);
                final EditText etEditJk = (EditText) dialogView.findViewById(R.id.etEdit);

                etEditJk.setText(jk);

                builder.setCancelable(false);
                builder.setView(dialogView);
                builder.setMessage("Jenis Kelamin");

                builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        final String jk = etEditJk.getText().toString();

                        UpdateModel newUpdate = new UpdateModel("jk", jk);
                        ApiService.service_patch.patchAgent("Bearer "+token, id_agent, newUpdate).enqueue(new Callback<AgentModel>() {
                            @Override
                            public void onResponse(Call<AgentModel> call, Response<AgentModel> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Jenis kelamin Anda berhasil diperbarui", Toast.LENGTH_SHORT).show();
                                    tvJk.setText(jk);
                                    dialogInterface.dismiss();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<AgentModel> call, Throwable t) {

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
            }
        });

        llTelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_custom_view, null);
                final EditText etEditTelepon = (EditText) dialogView.findViewById(R.id.etEdit);

                etEditTelepon.setText(telepon);

                builder.setCancelable(false);
                builder.setView(dialogView);
                builder.setMessage("Telepon");

                builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        final String telepon = etEditTelepon.getText().toString();

                        UpdateModel newUpdate = new UpdateModel("telepon", telepon);
                        ApiService.service_patch.patchAgent("Bearer "+token, id_agent, newUpdate).enqueue(new Callback<AgentModel>() {
                            @Override
                            public void onResponse(Call<AgentModel> call, Response<AgentModel> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Telepon Anda berhasil diperbarui", Toast.LENGTH_SHORT).show();
                                    tvTelepon.setText(telepon);
                                    dialogInterface.dismiss();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<AgentModel> call, Throwable t) {

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
            }
        });

        llAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        session = new Session(getActivity());
        if(!session.loggedin()){
            logout();
        }

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        ApiService.service_get.getDetailAgent("Bearer "+token, id_agent).enqueue(new Callback<DataAgentModel>() {
            @Override
            public void onResponse(Call<DataAgentModel> call, Response<DataAgentModel> response) {
                if(response.isSuccessful()) {
                    nama_lengkap = response.body().getNama_lengkap();
                    tgl_lahir = response.body().getTgl_lahir();
                    jk = response.body().getJk();
                    telepon = response.body().getTelepon();
                    judul = response.body().getJudul();
                    alamat = response.body().getAlamat_lengkap();
                    tambahan = response.body().getTambahan();

                    Glide.with(view).load(response.body().getFoto()).into(ivFoto);
                    tvNamaLengkap.setText(nama_lengkap);
                    tvTglLahir.setText(tgl_lahir);
                    tvJk.setText(jk);
                    tvTelepon.setText(telepon);
                    tvJudul.setText(judul);
                    tvAlamat.setText(alamat);
                    tvTambahan.setText(tambahan);
                }
                else {
                    Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataAgentModel> call, Throwable t) {

            }
        });

        return view;
    }

    private void logout(){
        session.setLoggedin(false, "null", "null");
        getActivity().finish();
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }

    private void kosong() {
        tvNamaLengkap.setText(null);
        tvTglLahir.setText(null);
        tvTelepon.setText(null);
        tvJk.setText(null);
        tvTelepon.setText(null);
    }

}
