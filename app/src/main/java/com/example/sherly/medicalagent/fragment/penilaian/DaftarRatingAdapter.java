package com.example.sherly.medicalagent.fragment.penilaian;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.model.penilaian.DataRatingModel;

import java.util.ArrayList;

public class DaftarRatingAdapter extends RecyclerView.Adapter<DaftarRatingAdapter.ItemRowHolder>{

    ArrayList<DataRatingModel> dataRating;
    private Activity activity;

    public DaftarRatingAdapter(Activity activity, ArrayList<DataRatingModel> dataRating) {
        this.dataRating = dataRating;
        this.activity = activity;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_daftar_penilaian, viewGroup, false);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int i) {

        holder.tvTanggalRat.setText(dataRating.get(i).getTgl());
        holder.tvJenis.setText(dataRating.get(i).getJenis());
        holder.tvKomentar.setText(dataRating.get(i).getKomentar());
        holder.rb.setRating(dataRating.get(i).getRating());
    }

    @Override
    public int getItemCount() {
        return (null != dataRating ? dataRating.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTanggalRat, tvJenis, tvKomentar;
        protected RatingBar rb;

        public ItemRowHolder(View view) {
            super(view);
            this.tvTanggalRat = (TextView) view.findViewById(R.id.tvTanggalRat);
            this.tvJenis = (TextView)view.findViewById(R.id.tvJenis);
            this.tvKomentar = (TextView) view.findViewById(R.id.tvKomentar);
            this.rb = (RatingBar) view.findViewById(R.id.rb);
        }
    }

}