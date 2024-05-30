package items;

// public final class riwayatPembelian {
//     private int idRiwayat;
//     private int idPembelian;
//     private String tanggalRiwayat;

//     public int getIdPembelian() {
//         return idPembelian;
//     }

//     public int getIdRiwayat() {
//         return idRiwayat;
//     }

//     public String getTanggalRiwayat() {
//         return tanggalRiwayat;
//     }

//     public void tampilkanRiwayat(){}
// }

public final class riwayatPembelian {
    private final int idRiwayat;
    private final String idUser;
    private final String idProduk;
    private final String tanggal;
    private final int total;

    public riwayatPembelian(int idRiwayat, String idUser, String idProduk, String tanggal, int total) {
        this.idRiwayat = idRiwayat;
        this.idUser = idUser;
        this.idProduk = idProduk;
        this.tanggal = tanggal;
        this.total = total;
    }

    public int getIdRiwayat() {
        return idRiwayat;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getTotal() {
        return total;
    }
}
