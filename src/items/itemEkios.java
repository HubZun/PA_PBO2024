package items;

public class itemEkios {
    private int idItem;
    private int hargaItem;
    private String namaItem;
    private String jenisItem;

    public int getIdItem() {
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
