package sns.example.projectakhir;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPage extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private EditText etUsername, etEmail, etPassword;
    private Button btnSignUp;

    private TextView tvHaveAcc, tvSignIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etUsername = findViewById(R.id.ptUsername);
        etEmail = findViewById(R.id.ptEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        mAuth = FirebaseAuth.getInstance();
        tvHaveAcc = findViewById(R.id.tvHaveAcc);
        tvSignIn = findViewById(R.id.tvSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                registerUser(username, email, password);
            }
        });

        tvHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser(String username, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            // Simpan data pengguna ke Firebase Database
                            // Misalnya, Anda dapat membuat koleksi "users" dan menyimpan data pengguna ke dalamnya.
                            // Jangan lupa untuk mengambil ID pengguna saat mendaftar dan gunakan sebagai dokumen baru di koleksi "users".
                            // Contoh:
                            // String userId = mAuth.getCurrentUser().getUid();
                            // FirebaseFirestore.getInstance().collection("users").document(userId).set(user);

                            Toast.makeText(RegisterPage.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            finish(); // Selesaikan aktivitas pendaftaran dan kembali ke halaman login
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterPage.this, "Registration failed. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
