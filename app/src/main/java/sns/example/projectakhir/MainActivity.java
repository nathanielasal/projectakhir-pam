package sns.example.projectakhir;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText etemail, etpassword;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mAuth = FirebaseAuth.getInstance();
            etemail = findViewById(R.id.ptUsername);
            etpassword = findViewById(R.id.etPassword);
            Button login = findViewById(R.id.btnLogin);
            login.setOnClickListener(this);
            Button register = findViewById(R.id.btnSignUp);

//            login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MainActivity.this, DashboardPembeli.class);
//                    startActivity(intent);
//                }
//            });
//
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
            String email = etemail.getText().toString();
            String password = etpassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Login successful
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, DashboardPenjual.class);
                                startActivity(i);
                                // Proceed with your desired actions, such as navigating to the main activity
                            } else {
                                // Login failed
                                Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
