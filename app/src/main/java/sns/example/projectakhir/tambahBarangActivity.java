package sns.example.projectakhir;

import static android.text.TextUtils.isEmpty;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import sns.example.projectakhir.R;

public class tambahBarangActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nama, harga;
    ImageButton upGambar;
    Button simpan;

    ImageView btnBack;
    ActivityResultLauncher<Intent> filePickerLauncher;
    Uri fileUri;
    String Linked;
    StorageReference storageReference;
    FirebaseStorage storage;
    FirebaseFirestore DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_barang);
        nama =findViewById(R.id.etNama);
        harga = findViewById(R.id.etHarga);
        upGambar = findViewById(R.id.imageButton5);
        simpan = findViewById(R.id.button3);
        simpan.setOnClickListener(this);
        upGambar.setOnClickListener(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        DB = FirebaseFirestore.getInstance();

        btnBack = findViewById(R.id.btnKembali);
        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(this,DashboardPenjual.class);
            startActivity(i);
        });



        filePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    fileUri = data.getData();
                    if (fileUri != null) {
                        uploadFile(fileUri);
                    } else {
                        Log.e("Detail", "Failed to retrieve file URI");
                    }
                }
            }
        });
    }

    private void uploadFile(Uri fileUri) {
        File file = new File(fileUri.getPath());
        StorageReference fileRef = storageReference.child("img/"+ file.getName());
        // Upload file to Firebase Storage
        UploadTask uploadTask = fileRef.putFile(fileUri);
        // Register observers to listen for the upload progress or errors
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // File uploaded successfully
                Log.d("DashboardPenjual", "File uploaded successfully");
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Linked = uri.toString();
                        // Use the imageUrl as needed
                        addLink(Linked);
                    }

                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle unsuccessful uploads
                Log.e("DashboardPenjual", "Failed to upload file: " + e.getMessage());
            }
        });
    }
    Map<Object, String> leh = new HashMap<>();
    public void addLink(String link){
        leh.put("imgUrl",link);
    }
    public void addFIre(){
        leh.put("nama",nama.getText().toString());
        leh.put("harga",harga.getText().toString());
        DB.collection("Library")
                .add(leh);
    }

    @Override
    public void onClick(View view) {
        if (upGambar.getId()==view.getId()){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            filePickerLauncher.launch(intent);
        } else if (simpan.getId()==view.getId()) {
            if(!cekGeming()){
                return;
            }else
                addFIre();
            finish();
        }

    }
    public boolean cekGeming(){
        boolean cek = true;
        if(nama.getText().toString().isEmpty()){
            cek = false;
            Toast.makeText(this, "Harus diisi", Toast.LENGTH_SHORT).show();
        }if(harga.getText().toString().isEmpty()){
            cek = false;
            Toast.makeText(this, "Harus diisi", Toast.LENGTH_SHORT).show();

        }
        return cek;
    }
}