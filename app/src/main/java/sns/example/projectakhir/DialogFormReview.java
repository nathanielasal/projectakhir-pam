package sns.example.projectakhir;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sns.example.Model.Review;

public class DialogFormReview extends DialogFragment {

    TextView etReviewInput, etRateCount;
    Button btnPostReview;
    RatingBar ratingBar;
    float rateValue;
    String reviewId, rateCount, reviewInput;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public DialogFormReview(String reviewId, String rateCount, String reviewInput) {
        this.reviewId = reviewId;
        this.rateCount = rateCount;
        this.reviewInput = reviewInput;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_form_review, container, false);
        etRateCount = view.findViewById(R.id.tvRateCount);
        etReviewInput = view.findViewById(R.id.etReviewInput);
        ratingBar = view.findViewById(R.id.ratingBar);
        btnPostReview = view.findViewById(R.id.btnPostReview);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        etRateCount.setText(rateCount);
        etReviewInput.setText(reviewInput);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();

                if(rateValue<=1 && rateValue>=0)
                    etRateCount.setText(rateValue + " / 5  Bad");
                else if(rateValue<=2 && rateValue>1)
                    etRateCount.setText(rateValue + " /5  Okay");
                else if(rateValue<=3 && rateValue>2)
                    etRateCount.setText(rateValue + " / 5  Good");
                else if(rateValue<=4 && rateValue>3)
                    etRateCount.setText(rateValue + " / 5  Very good");
                else if(rateValue<=5 && rateValue>4)
                    etRateCount.setText(rateValue + " / 5  Best!");
            }
        });

        btnPostReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rateCount = etRateCount.getText().toString();
                String reviewInput = etReviewInput.getText().toString();
                database.child("review").child(mAuth.getUid()).child(reviewId).setValue(new Review(rateCount, reviewInput)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(view.getContext(), "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view.getContext(), "Data gagal diupdate", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
