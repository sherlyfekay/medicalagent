package com.example.sherly.medicalagent.fragment.histori;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.model.histori.HistoriModel;
import com.example.sherly.medicalagent.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoriFragment extends Fragment {

    private RecyclerView rvDaftarHistori;
    private DaftarHistoriAdapter historiAdapter;

    public HistoriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_histori, container, false);

        final SharedPreferences pref = getActivity().getSharedPreferences("medigent2", MODE_PRIVATE);
        final String id_agent = pref.getString("id_agent", "null");
        final String token = pref.getString("token", "null");
        rvDaftarHistori = (RecyclerView) view.findViewById(R.id.rvDaftarHistori);

        ApiService.service_get.getHistoryByAgent("Bearer "+token, id_agent).enqueue(new Callback<HistoriModel>() {
            @Override
            public void onResponse(Call<HistoriModel> call, Response<HistoriModel> response) {
                if(response.isSuccessful()) {

                    historiAdapter = new DaftarHistoriAdapter(getActivity(), response.body().getHistories());
                    rvDaftarHistori.setLayoutManager(new LinearLayoutManager(getActivity()));
                    //rvDaftarHistori.setFocusable(false);
                    //rvDaftarHistori.setNestedScrollingEnabled(false);
                    rvDaftarHistori.setAdapter(historiAdapter);
                    historiAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HistoriModel> call, Throwable t) {

            }
        });
        return view;
    }

}
