package sns.example.projectakhir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import sns.example.Adapter.adapter_penjual;
import sns.example.Fragment.FragmentNavbar;
import sns.example.Model.Proyek;

public class DashboardPenjual extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore DB;
    List<Proyek> proyek = new ArrayList<>();
    adapter_penjual Penjual;
    RecyclerView recycler;
    Button tambah;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_penjual);
        DB = FirebaseFirestore.getInstance();
        recycler =findViewById(R.id.recyclerView);
        tambah = findViewById(R.id.btnAdd);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.navbar, new FragmentNavbar())
                .commit();
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambah = new Intent(getApplicationContext(), tambahBarangActivity.class);
                tambah.putExtra("key",key);
                startActivity(tambah);
            }
        });
        load();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        Penjual = new adapter_penjual(proyek);
        recycler.setAdapter(Penjual);

    }
    public void load(){
        DB.collection("Library")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange documentChange : value.getDocumentChanges()){
                            if(documentChange.getType() == DocumentChange.Type.ADDED){
                                Proyek wii = documentChange.getDocument().toObject(Proyek.class);
                                wii.setKey(documentChange.getDocument().getId());

                                proyek.add(wii);
//                                DocumentSnapshot doc = documentChange.getDocument();
//                                String lo = (String) doc.get("Judul");
//                                Courses X = new Courses(lo);
//                                X.setKey(documentChange.getDocument().getId());
//                                courses.add(X);
                            }
                            Penjual.notifyDataSetChanged();
                        }
                    }
                });

    }
    @Override
    public void onClick(View view) {
    }
}

