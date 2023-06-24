package sns.example.Adapter;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import sns.example.Model.Proyek;
import sns.example.projectakhir.R;
import sns.example.projectakhir.detailAct;
import sns.example.projectakhir.tambahBarangActivity;

public class adapter_pembeli extends RecyclerView.Adapter<adapter_pembeli.viewHolder>{
    List<Proyek> ProyekList;

    public adapter_pembeli(List<Proyek> proyek) {
        this.ProyekList = proyek;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.item_dashboard_pembeli,parent,false);
        viewHolder isi = new viewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.namaBarang.setText(ProyekList.get(position).getNama());
        holder.hargaBarang.setText(ProyekList.get(position).getHarga());
        Glide.with(holder.itemView.getContext())
                .load(ProyekList.get(position).getImgUrl())
                .override(50,50)
                .into(holder.gamb);
    }

    @Override
    public int getItemCount() {
        if(ProyekList == null){
            return 0;
        }
        return ProyekList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView namaBarang, hargaBarang;
        ImageView gamb;
        ConstraintLayout card;
        String key;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.tvDashboardPembeli_NamaBrg);
            hargaBarang = itemView.findViewById(R.id.tvHarga);
            card = itemView.findViewById(R.id.card);
            card.setOnClickListener(new  View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent detilIntent = new Intent(v.getContext(), detailAct.class);
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Proyek proyek = ProyekList.get(position);
                        detilIntent.putExtra("nama", proyek.getNama());
                        detilIntent.putExtra("harga", proyek.getHarga());
                        detilIntent.putExtra("imgUrl", proyek.getImgUrl());
                        detilIntent.putExtra("key", proyek.getKey());
                        v.getContext().startActivity(detilIntent);
                    }
                }
            });



            gamb = itemView.findViewById(R.id.ivGambarBrg);
        }

        @Override
        public void onClick(View view) {
            Intent peh = new Intent(view.getContext(), tambahBarangActivity.class);
            int a = getAdapterPosition();
            peh.putExtra("nama", ProyekList.get(a).getNama());
            peh.putExtra("harga",ProyekList.get(a).getHarga());
            peh.putExtra("imgUrl",ProyekList.get(a).getImgUrl());
            peh.putExtra("key",ProyekList.get(a).getKey());

            view.getContext().startActivity(peh);
        }
    }
}
