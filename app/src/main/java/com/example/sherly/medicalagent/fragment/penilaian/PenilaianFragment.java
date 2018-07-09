package com.example.sherly.medicalagent.fragment.penilaian;


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
import com.example.sherly.medicalagent.model.penilaian.RatingModel;
import com.example.sherly.medicalagent.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenilaianFragment extends Fragment {

    private RecyclerView rvDaftarRating;
    private DaftarRatingAdapter ratingAdapter;

    public PenilaianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_penilaian, container, false);
        final SharedPreferences pref = getActivity().getSharedPreferences("medigent2", MODE_PRIVATE);
        final String id_agent = pref.getString("id_agent", "null");
        final String token = pref.getString("token", "null");

        rvDaftarRating = (RecyclerView) view.findViewById(R.id.rvDaftarRating);

        ApiService.service_get.getRatingByAgent("Bearer "+token, id_agent).enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {
                if(response.isSuccessful()) {

                    ratingAdapter = new DaftarRatingAdapter(getActivity(), response.body().getRatings());
                    rvDaftarRating.setLayoutManager(new LinearLayoutManager(getActivity()));
                    //rvDaftarHistori.setFocusable(false);
                    //rvDaftarHistori.setNestedScrollingEnabled(false);
                    rvDaftarRating.setAdapter(ratingAdapter);
                    ratingAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
