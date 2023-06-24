package sns.example.Model;


public class Barang {
    public String id;
    public String harga;
    public String nama;

    public Barang(String harga, String nama){
        this.nama = nama;
        this.harga = harga;
    }

    public Barang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }



}



