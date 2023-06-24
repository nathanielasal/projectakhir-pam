package sns.example.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import sns.example.Model.Proyek;

public class EditAct extends AppCompatActivity{
    private EditText edtNama, edtHarga;
    private Button btnSv;
    private String cekNama, cekHarga, key;
    private DatabaseReference DB;
    private FirebaseFirestore FS;

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_barang);

        edtNama = findViewById(R.id.etNamaEdit);
        edtHarga = findViewById(R.id.etHargaEdit);
        btnSv = findViewById(R.id.button3);
        key = getIntent().getStringExtra("key");

        btnBack = findViewById(R.id.btnKembali);
        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(this,DashboardPenjual.class);
            startActivity(i);
        });

        DB = FirebaseDatabase.getInstance().getReference();
        FS = FirebaseFirestore.getInstance();
        getData();
        btnSv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cekNama = edtNama.getText().toString();
                cekHarga = edtHarga.getText().toString();

                if(isEmpty(cekNama) || isEmpty(cekHarga)){
                    Toast.makeText(EditAct.this, "Data harus diisi", Toast.LENGTH_SHORT).show();
                }else {
                    Proyek setCatatan = new Proyek();
                    setCatatan.setNama(edtNama.getText().toString());
                    setCatatan.setHarga(edtHarga.getText().toString());
                    updateData();
                    Intent pe = new Intent(EditAct.this, DashboardPenjual.class);
                    startActivity(pe);
                }
            }
        });
    }

    private  boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void  getData(){
        Bundle extras = getIntent().getExtras(); //tambahan
        if (extras != null){ //if - tambahan
            final String getNama = getIntent().getExtras().getString("dataJudul");
            final String getHarga = getIntent().getExtras().getString("dataIsi");
            edtNama.setText(getNama);
            edtHarga.setText(getHarga);
        }
    }

    private void updateData(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Map cnt = new HashMap<>();
            cnt.put("nama",edtNama.getText().toString());
            cnt.put("harga",edtHarga.getText().toString());
            FS.collection("Library").document(key).update(cnt);
//            String getKey = getIntent().getExtras().getString("getPrimaryKey");
//            DB.child("Catatan")
//                    .child("Notes")
//                    .child(getKey)
//                    .setValue(cnt)
//                    .addOnSuccessListener(new OnSuccessListener<Void>(){
//                        @Override
//                        public void onSuccess(Void aVoid){
//                            edtNama.setText("");
//                            edtHarga.setText("");
//                            Toast.makeText(EditAct.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    });
        }

    }
}
