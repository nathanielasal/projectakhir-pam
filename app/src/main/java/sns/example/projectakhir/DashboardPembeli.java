package sns.example.projectakhir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import sns.example.Adapter.adapter_pembeli;
import sns.example.Model.Proyek;
import sns.example.projectakhir.R;
import sns.example.Adapter.adapter_penjual;

public class DashboardPembeli extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore DB;
    List<Proyek> proyek = new ArrayList<>();
    adapter_pembeli Pembeli;
    RecyclerView recycler;

    ImageView btnBack;


    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_pembeli);
        DB = FirebaseFirestore.getInstance();
        recycler =findViewById(R.id.recyclerView);

        load();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        Pembeli = new adapter_pembeli(proyek);
        recycler.setAdapter(Pembeli);

        btnBack = findViewById(R.id.btnKembali);
        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(this,DashboardPenjual.class);
            startActivity(i);
        });

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
                            Pembeli.notifyDataSetChanged();
                        }
                    }
                });

    }
    @Override
    public void onClick(View view) {
    }
}

