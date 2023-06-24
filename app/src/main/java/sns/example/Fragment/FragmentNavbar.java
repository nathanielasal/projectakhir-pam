package sns.example.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import sns.example.projectakhir.ActivityReviewInput2;
import sns.example.projectakhir.ActivityReviewPage;
import sns.example.projectakhir.daftarPesananActivity;
import sns.example.projectakhir.DashboardPembeli;
import sns.example.projectakhir.DashboardPenjual;
import sns.example.projectakhir.DialogForm;
import sns.example.projectakhir.DialogFormReview;
import sns.example.projectakhir.R;
import sns.example.projectakhir.tambahBarangActivity;

public class FragmentNavbar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navbar, container, false);

        // Find the button
        ImageButton ibBarang = view.findViewById(R.id.imageButton);
        ImageButton ibPesanan = view.findViewById(R.id.imageButton2);
        ImageButton ibReview = view.findViewById(R.id.imageButton4);

        // Set a click listener for the button
        ibBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the TargetActivity
                Intent intent = new Intent(getActivity(), DashboardPenjual.class);
                startActivity(intent);
            }
        });
        ibPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the TargetActivity
                Intent intent = new Intent(getActivity(), DashboardPembeli.class);
                startActivity(intent);
            }
        });
        ibReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the TargetActivity
                Intent intent = new Intent(getActivity(), ActivityReviewPage.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
