package sns.example.Model;


public class Proyek {
    private String nama;
    private String harga;

    private String imgUrl, key;

    public Proyek (String nama, String harga, String deskripsi) {
        this.nama = nama;
        this.harga = harga;
    }

    public Proyek(){}


    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}

