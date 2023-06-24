package sns.example.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import sns.example.Model.Review;
import sns.example.projectakhir.DialogForm;
import sns.example.projectakhir.DialogFormReview;
import sns.example.projectakhir.R;
import sns.example.Model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Review> dataList;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public ReviewAdapter(Context context, ArrayList<Review> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Review data = dataList.get(position);
        holder.textViewRateCount.setText(data.getRateCount());
        holder.textViewReviewInput.setText(data.getReviewInput());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String reviewId = data.getKey();
                        if (reviewId != null) {
                            databaseReference.child("review").child(mAuth.getUid()).child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setMessage("Apakah Anda yakin ingin menghapus review '" + data.getReviewInput() + "'?");
                builder.show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                DialogFormReview dialogForm = new DialogFormReview(
                        data.getKey(),
                        data.getRateCount(),
                        data.getReviewInput()
                );
                dialogForm.show(fragmentManager, "form");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewRateCount;
        private TextView textViewReviewInput;
        private ImageButton btnDelete, btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRateCount = itemView.findViewById(R.id.item_tvRateCount);
            textViewReviewInput = itemView.findViewById(R.id.item_tvReviewInput);
            btnDelete = itemView.findViewById(R.id.btnDeleteReview);
            btnEdit = itemView.findViewById(R.id.btnEditReview);


        }
    }
}