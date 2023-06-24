package sns.example.projectakhir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.remote.WatchChange;

import java.util.ArrayList;
import java.util.List;

public class detailAct extends AppCompatActivity implements View.OnClickListener {
    String nama,harga,imgUrl,key;
    TextView name,price;
    ImageView img;
    Button review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_barang);
        nama = getIntent().getStringExtra("nama");
        harga = getIntent().getStringExtra("harga");
        imgUrl = getIntent().getStringExtra("imgUrl");
        key = getIntent().getStringExtra("key");

        name = findViewById(R.id.tvDetail_NamaBrg);
        img = findViewById(R.id.ivDetailBrg);
        price = findViewById(R.id.tvHargaBrg);


        name.setText(nama);
        price.setText(harga);
        Glide.with(detailAct.this)
                .load(imgUrl)
                .override(500,500)
                .into(img);

        review = findViewById(R.id.btnReview);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailAct.this, ActivityReviewPage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
    }
}