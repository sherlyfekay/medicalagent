package com.example.sherly.medicalagent;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sherly.medicalagent.fragment.BerandaFragment;
import com.example.sherly.medicalagent.fragment.beranda.MapsFragment;
import com.example.sherly.medicalagent.fragment.histori.HistoriFragment;
import com.example.sherly.medicalagent.fragment.penilaian.PenilaianFragment;
import com.example.sherly.medicalagent.fragment.ProfilFragment;

public class MainActivity extends AppCompatActivity {

//    private Button btnLogout;
//    Session session;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.tabLayout);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    //.replace(R.id.tabContent, new BerandaFragment())
                    .replace(R.id.tabContent, new MapsFragment())
                    .commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.action_beranda:
                        //fragment = new BerandaFragment();
                        fragment = new MapsFragment();
                        break;
                    case R.id.action_histori:
                        fragment = new HistoriFragment();
                        break;
                    case R.id.action_penilaian:
                        fragment = new PenilaianFragment();
                        break;
                    case R.id.action_profil:
                        fragment = new ProfilFragment();
                        break;
//                    case R.id.nav_profil:
//                        Bundle bundle = new Bundle();
//                        bundle.putString("id_pasien", id_pasien);
//                        fragment = new ProfilFragment();
//                        fragment.setArguments(bundle);
//                        break;
                    default:
                        fragment = new BerandaFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.tabContent, fragment)
                        .commit();

                return true;
            }
        });
//        btnLogout = (Button) findViewById(R.id.btnLogout);
//
//        session = new Session(this);
//        if(!session.loggedin()){
//            logout();
//        }
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logout();
//            }
//        });

        //getSupportFragmentManager().beginTransaction().replace(R.id.dashboard, new DashboardFragment()).commit();
    }

//    private void logout(){
//        session.setLoggedin(false, "null", "null");
//        finish();
//        startActivity(new Intent(MainActivity.this,LoginActivity.class));
//    }
}
