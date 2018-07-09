package com.example.sherly.medicalagent.fragment.beranda;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherly.medicalagent.R;
import com.example.sherly.medicalagent.fragment.beranda.DaftarOrderAdapter;
import com.example.sherly.medicalagent.model.agent.AgentModel;
import com.example.sherly.medicalagent.model.order.DataOrderModel;
import com.example.sherly.medicalagent.model.order.OrderModel;
import com.example.sherly.medicalagent.service.ApiService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mGoogleMap;

    LinearLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;
    TextView tvBottomSheet;
    ImageView ivPanah1, ivPanah2;
    DaftarOrderAdapter orderAdapter;
    RecyclerView rvDaftarOrder;
    ArrayList<DataOrderModel> dataOrder;

    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        final SharedPreferences pref = getActivity().getSharedPreferences("medigent2", MODE_PRIVATE);
        final String token = pref.getString("token", "null");

        layoutBottomSheet = (LinearLayout) view.findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        tvBottomSheet = (TextView) view.findViewById(R.id.tvBottomSheet);
        ivPanah1 = (ImageView) view.findViewById(R.id.ivPanah1);
        ivPanah2 = (ImageView) view.findViewById(R.id.ivPanah2);
        rvDaftarOrder = (RecyclerView) view.findViewById(R.id.rvDaftarOrder);
        ivPanah1.setBackgroundResource(R.drawable.ic_up);
        ivPanah2.setBackgroundResource(R.drawable.ic_up);

        Toast.makeText(getActivity(), "Bisa", Toast.LENGTH_LONG).show();

        ApiService.service_get.getOrders("Bearer "+token).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                if(response.isSuccessful()) {

                    orderAdapter = new DaftarOrderAdapter(getActivity(), response.body().getOrders());
                    rvDaftarOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
                    //rvDaftarHistori.setFocusable(false);
                    //rvDaftarHistori.setNestedScrollingEnabled(false);
                    rvDaftarOrder.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();

                    dataOrder = response.body().getOrders();
                    Toast.makeText(getActivity(), ""+dataOrder, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), ""+response.body().getCount(), Toast.LENGTH_SHORT).show();

                    createMarker(dataOrder, response.body().getCount());
                }
                else {
                    Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Error : "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        tvBottomSheet.setText("Tutup Daftar");
                        ivPanah1.setBackgroundResource(R.drawable.ic_down);
                        ivPanah2.setBackgroundResource(R.drawable.ic_down);
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        tvBottomSheet.setText("Lihat Daftar");
                        ivPanah1.setBackgroundResource(R.drawable.ic_up);
                        ivPanah2.setBackgroundResource(R.drawable.ic_up);
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                    break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    public void createMarker(ArrayList<DataOrderModel> dataOrder, int count) {
        mGoogleMap.clear();

        ArrayList<LatLng> latLngs = new ArrayList<>();
        Double jarak;
        for(int i=0; i<count; i++) {
            latLngs.add(new LatLng(dataOrder.get(i).getLat(), dataOrder.get(i).getLng()));

//            jarak = distance(dataOrder.get(i).getLat(), dataOrder.get(i).getLng(), dataOrder.get(i+1).getLat(), dataOrder.get(i+1).getLng());
//            Toast.makeText(getActivity(), ""+jarak, Toast.LENGTH_SHORT).show();
            mGoogleMap.addMarker(new MarkerOptions().position(latLngs.get(i)).title(dataOrder.get(i).getNama_pasien()).snippet(dataOrder.get(i).getAlamat()));
        }
    }

    public static double distance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        //double dist = earthRadius * c *1000;
        //dist = Math.pow(dist,2);
        double dist = earthRadius * c;
        return dist;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Sydney, Australia, and move the camera
        LatLng pens = new LatLng(-7.276421, 112.793866);
        mGoogleMap.addMarker(new MarkerOptions().position(pens).title("Politeknik Elektronika Negeri Surabaya").snippet("Jl. Raya ITS, Keputih, Sukolilo"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(pens));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(pens)
                .zoom(15)
                .build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
