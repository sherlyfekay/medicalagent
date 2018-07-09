package com.example.sherly.medicalagent.fragment.beranda;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.model.agent.DataAgentModel;
import com.example.sherly.medicalagent.model.order.DataOrderModel;

import java.util.ArrayList;

public class DaftarOrderAdapter extends RecyclerView.Adapter<DaftarOrderAdapter.ItemRowHolder>{

    ArrayList<DataOrderModel> dataOrder;
    private Activity activity;

    public DaftarOrderAdapter(Activity activity, ArrayList<DataOrderModel> dataOrder) {
        this.dataOrder = dataOrder;
        this.activity = activity;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_daftar_order, viewGroup, false);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int i) {
        final DataOrderModel orderModel = dataOrder.get(i);

        holder.tvNamaPasien.setText(dataOrder.get(i).getNama_pasien());
        holder.tvJkPasien.setText(dataOrder.get(i).getJk());
        holder.tvDiagnosaPasien.setText(dataOrder.get(i).getDiagnosa());
        holder.tvAlamatPasien.setText(dataOrder.get(i).getAlamat());

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
        return (null != dataOrder ? dataOrder.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvNamaPasien, tvJkPasien, tvDiagnosaPasien, tvAlamatPasien;
        //protected CardView cvDaftarHis;

        public ItemRowHolder(View view) {
            super(view);
            this.tvNamaPasien = (TextView)view.findViewById(R.id.tvNamaPasien);
            this.tvJkPasien = (TextView) view.findViewById(R.id.tvJkPasien);
            this.tvDiagnosaPasien = (TextView)view.findViewById(R.id.tvDiagnosaPasien);
            this.tvAlamatPasien = (TextView) view.findViewById(R.id.tvAlamatPasien);
            //this.cvDaftarHis = (CardView) view.findViewById(R.id.cvDaftarHis);
        }
    }

}
