package items;

public class itemEkios {
    private String idItem;
    private int hargaItem;
    private String namaItem;
    private String jenisItem;

    public itemEkios(String idItem,String namaItem,  String jenisItem ,int hargaItem ) {
        this.idItem = idItem;
        this.hargaItem = hargaItem;
        this.namaItem = namaItem;
        this.jenisItem = jenisItem;
    }

    public String getIdItem() {
        return idItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public int getHargaItem() {
        return hargaItem;
    }

    public String getJenisItem() {
        return jenisItem;
    }

    public void setHargaItem(int hargaItem) {
        this.hargaItem = hargaItem;
    }

    public void setJenisItem(String jenisItem) {
        this.jenisItem = jenisItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }
}
