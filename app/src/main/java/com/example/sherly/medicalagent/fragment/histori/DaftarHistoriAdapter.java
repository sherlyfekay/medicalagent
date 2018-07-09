package com.example.sherly.medicalagent.fragment.histori;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.model.histori.DataHistoriModel;

import java.util.ArrayList;

public class DaftarHistoriAdapter extends RecyclerView.Adapter<DaftarHistoriAdapter.ItemRowHolder>{

    ArrayList<DataHistoriModel> dataHistori;
    private Activity activity;

    public DaftarHistoriAdapter(Activity activity, ArrayList<DataHistoriModel> dataHistori) {
        this.dataHistori = dataHistori;
        this.activity = activity;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_daftar_histori, viewGroup, false);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int i) {
        final DataHistoriModel detailHistoryModel = dataHistori.get(i);

        holder.tvTanggalHis.setText(dataHistori.get(i).getCreated_at());
        holder.tvNamaPasien.setText(dataHistori.get(i).getNama_pasien());
        holder.tvJenis.setText(dataHistori.get(i).getJenis());
        holder.tvAlamatPasien.setText(dataHistori.get(i).getAlamat_pasien());

//        holder.cvDaftarHis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(activity, DetailHistoryActivity.class);
//                intent.putExtra("id_orderoffer", detailHistoryModel.get_id());
//                activity.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (null != dataHistori ? dataHistori.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTanggalHis, tvJenis, tvNamaPasien, tvAlamatPasien;
        //protected CardView cvDaftarHis;

        public ItemRowHolder(View view) {
            super(view);
            this.tvTanggalHis = (TextView) view.findViewById(R.id.tvTanggalHis);
            this.tvJenis = (TextView)view.findViewById(R.id.tvJenis);
            this.tvNamaPasien = (TextView) view.findViewById(R.id.tvNamaPasien);
            this.tvAlamatPasien = (TextView)view.findViewById(R.id.tvAlamatPasien);
            //this.cvDaftarHis = (CardView) view.findViewById(R.id.cvDaftarHis);
        }
    }

}