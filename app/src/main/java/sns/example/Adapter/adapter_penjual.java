package sns.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import sns.example.Model.Proyek;
import sns.example.projectakhir.EditAct;
import sns.example.projectakhir.R;
import sns.example.projectakhir.tambahBarangActivity;

public class adapter_penjual extends RecyclerView.Adapter<adapter_penjual.viewHolder> {
    List<Proyek> ProyekList;

    public adapter_penjual(List<Proyek> proyek) {
        this.ProyekList = proyek;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.item_dashboard, parent, false);
        viewHolder isi = new viewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.namaBarang.setText(ProyekList.get(position).getNama());
        holder.hargaBarang.setText(ProyekList.get(position).getHarga());
        Glide.with(holder.itemView.getContext())
                .load(ProyekList.get(position).getImgUrl())
                .override(50, 50)
                .into(holder.gamb);
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proyek proyek = ProyekList.get(holder.getAdapterPosition());
                String proyekId = proyek.getKey();

                // Get a reference to the Firestore collection "Library" and delete the document with the corresponding ID
                FirebaseFirestore.getInstance().collection("Library").document(proyekId)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Remove the item from the list and update the RecyclerView
                                ProyekList.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                Toast.makeText(v.getContext(), "Objek " + proyek.getNama() + " terhapus", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to delete data from Firestore
                                Toast.makeText(v.getContext(), "Gagal menghapus objek: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        if (ProyekList == null) {
            return 0;
        }
        return ProyekList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView namaBarang, hargaBarang;
        ImageView gamb;
        Button hapus, edit;

        String key;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.tvDashboard_NamaBrg);
            hargaBarang = itemView.findViewById(R.id.tvHarga);
            hapus = itemView.findViewById(R.id.btnDelete);
            edit = itemView.findViewById(R.id.btnEdit);
            gamb = itemView.findViewById(R.id.ivGambarBrg);

            hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Proyek proyek = ProyekList.get(getAdapterPosition());
                    String proyekId = proyek.getKey();

                    // Get a reference to the Firestore collection "Library" and delete the document with the corresponding ID
                    FirebaseFirestore.getInstance().collection("Library").document(proyekId)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Remove the item from the list and update the RecyclerView
                                    ProyekList.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    Toast.makeText(v.getContext(), "Objek " + proyek.getNama() + " terhapus", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Failed to delete data from Firestore
                                    Toast.makeText(v.getContext(), "Gagal menghapus objek: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editIntent = new Intent(v.getContext(), EditAct.class);
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Proyek proyek = ProyekList.get(position);
                        editIntent.putExtra("nama", proyek.getNama());
                        editIntent.putExtra("harga", proyek.getHarga());
                        editIntent.putExtra("imgUrl", proyek.getImgUrl());
                        editIntent.putExtra("key", proyek.getKey());
                        v.getContext().startActivity(editIntent);
                    }
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Proyek proyek = ProyekList.get(position);
                Intent peh = new Intent(view.getContext(), tambahBarangActivity.class);
                peh.putExtra("nama", proyek.getNama());
                peh.putExtra("harga", proyek.getHarga());
                peh.putExtra("imgUrl", proyek.getImgUrl());
                peh.putExtra("key", proyek.getKey());
                view.getContext().startActivity(peh);
            }
        }
    }
}
