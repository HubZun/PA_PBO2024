package items;

public final class pembelian {
    private int idPelanggan;
    private int idItemKios;
    private int idPembelian;
    private int saldoPelanggan;
    private int subTotalHarga;

    private String usernamePelanggan;
    private String namaItem;
    private String jenisItem;

    public int getIdPembelian() {
        return idPembelian;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public int getIdItemKios() {
        return idItemKios;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public String getUsernamePelanggan() {
        return usernamePelanggan;
    }

    public int getSaldoPelanggan() {
        return saldoPelanggan;
    }

    public String getJenisItem() {
        return jenisItem;
    }

    public int getSubTotalHarga() {
        return subTotalHarga;
    }
}
