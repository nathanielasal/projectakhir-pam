package sns.example.Adapter;


import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import sns.example.Model.Proyek;
import sns.example.projectakhir.R;

public class adapter_proyek extends RecyclerView.Adapter<adapter_proyek.viewHolder>{
    List<Proyek> ProyekList;
    Context c;

    public adapter_proyek(List<Proyek> kont,Context context) {
        this.ProyekList = kont;
        this.c=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.detail_barang,parent,false);
        viewHolder isi = new viewHolder(daftar);
        return isi;
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.nama.setText(ProyekList.get(position).getNama());
        holder.harga.setText(ProyekList.get(position).getHarga());
        Glide.with(holder.itemView.getContext())
                .load(ProyekList.get(position).getImgUrl())
                .override(500,500)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        if(ProyekList == null){
            return 0;
        }
        return ProyekList.size();
    }
    FirebaseStorage storage;
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama,harga;
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvDetail_NamaBrg);
            img = itemView.findViewById(R.id.ivDetailBrg);
            harga = itemView.findViewById(R.id.tvHargaBrg);
            storage = FirebaseStorage.getInstance();
        }

        @Override
        public void onClick(View view) {

        }
    }
}
