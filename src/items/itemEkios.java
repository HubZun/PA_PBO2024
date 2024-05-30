package items;

public class itemEkios {
    protected String idItem, kategori, namaItem;
    protected int jumlahItem, hargaItem;

    public itemEkios(String idItem, String kategori, String namaItem,  int jumlahItem ,int hargaItem ) {
        this.idItem = idItem;
        this.kategori = kategori;
        this.namaItem = namaItem;
        this.jumlahItem = jumlahItem;
        this.hargaItem = hargaItem;
    }

    public String getIdItem() {
        return idItem;
    }

    public String getKategori() {
        return kategori;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public int getJumlahItem() {
        return jumlahItem;
    }

    public int getHargaItem() {
        return hargaItem;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public void setJumlahItem(int jumlahItem) {
        this.jumlahItem = jumlahItem;
    }

    public void setHargaItem(int hargaItem) {
        this.hargaItem = hargaItem;
    }

}