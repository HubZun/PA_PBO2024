package user;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import items.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class customer extends user {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);

    private static final String url = "jdbc:MySQL://localhost:3306/ekios_db";
    private static final String user = "root";
    private static final String pass = "";
    private static ArrayList<itemEkios> items = new ArrayList<>(); // array sementara yg menampung data dari db
    private static ArrayList<riwayatPembelian> riwayat = new ArrayList<>(); // array sementara yg menampung data dari db
    private static List<String> list = new ArrayList<>();
    private int saldo;

    public customer(int Id, String Username, String Password, int Saldo) {
        super(Id, Username, Password);
        this.saldo = Saldo;
    }

    @Override
    public boolean login(String username, String password) {
        String db_username = "", db_password = "", db_role = "", sha_password = "";
        int db_id = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

            String query = "select * from tbuser where username = '" + username + "'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                db_username = rs.getString("username");
                db_password = rs.getString("password");
                db_role = rs.getString("role");
                saldo = rs.getInt("saldo");
                db_id = rs.getInt("id_user");
            }


            query = "select password from tbuser where password = SHA1('"+password+"')";

            st = con.createStatement();

            rs = st.executeQuery(query);

            while (rs.next()) {
                sha_password = rs.getString("password");
            }

            con.close();

            if (username.equals(db_username) && sha_password.equals(db_password) && db_role.equals("customer")) {
                this.id = db_id;
                return true;
            }

        } catch (Exception e) {

            System.out.println(e);
        }
        return false;
    }

    public boolean signUp(String username, String password) {
        String db_username = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

            String query = "select * from tbuser where username = '" + username + "'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                db_username = rs.getString("username");
            }

            if (username.equals(db_username)) {
                System.out.println("\nusername telah digunakan, silahkan gunakan username yang lain");
            } else {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    con = DriverManager.getConnection(url, user, pass);
                    
                    query = "insert into tbuser (username, password,saldo, role) values('" + username + "',SHA('" + password + "'), 0, 'customer')";

                    st.executeUpdate(query);
                    
                    con.close();


                    return true;

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (Exception e) {

            System.out.println(e);
        }
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void akun() throws IOException {
        System.out.print("\033\143");
        System.out.println("===========================");
        System.out.println("|      Akun Customer      |");
        System.out.println("===========================");
        System.out.println("| Username : " + this.username);
        System.out.println("| Password : " + this.password);
        System.out.println("| Saldo    : " + this.saldo);
        System.out.println("===========================");
        System.out.print("Tekan Enter Untuk Melanjutkan...");
        br.readLine();
    }

    public void saldo() throws Exception {
        System.out.print("\033\143");
        System.out.println("===========================");
        System.out.println("|      Saldo Customer     |");
        System.out.println("===========================");
        System.out.println("| Saldo    : " + this.saldo);
        System.out.println("===========================");
        int topup;
        while (true) {
            while (true) {
                try {
                    System.out.print("Masukkan jumlah saldo yang ingin di topup : ");
                    topup = Integer.parseInt(br.readLine());
                    if (topup <= 0) {
                        System.out.println("Masukkan jumlah saldo lebih dari 0");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Inputan Harus Berupa Integer!");
                }
            }

            System.out.print("Yakin ingin topup (y/n):");
            String cek = br.readLine();
            if (cek.equals("y")) {
                int saldoS = getSaldo();
                setSaldo(saldoS + topup);
                updateSaldo();

                System.out.println("Topup Saldo Berhasil...");
                break;
            } else {
                break;
            }
        }
        System.out.print("Tekan Enter Untuk Melanjutkan...");
        br.readLine();
    }

    private void updateSaldo() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(url, user, pass);

        String query = "UPDATE tbuser SET saldo = '" + getSaldo() + "' WHERE id_user = '" + getId() + "'";

        Statement st = con.createStatement();
        st.executeUpdate(query);

        con.close();
    }

    // ambil data dari table produk
    private static void insertToList() {
        try {
            items.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);
            String query = "SELECT * FROM tbproduk";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                itemEkios itm = new itemEkios(rs.getString("id_produk"), rs.getString("kategori"), rs.getString("nama"), rs.getInt("jumlah"), rs.getInt("harga"));
                items.add(itm);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //lihatProduk
    private static void lihatProduk() {
        try {
            insertToList();
            if (items.size() == 0) {
                System.out.println("data kosong");
                return;
            } else {
                System.out.println("===========================================================================");
                System.out.printf("| %3s  %-7s  %-15s  %-17s %-7s %10s  %n", "No", "Id", "Nama produk", "Kategori", "Jumlah" ,"Harga");
                System.out.println("===========================================================================");

                for(int i = 0; i < items.size(); i++){
                    itemEkios itm = items.get(i);
                    String id = itm.getIdItem();
                    String nama = itm.getNamaItem();
                    String kategori = itm.getKategori();
                    int harga = itm.getHargaItem();
                    int jumlah = itm.getJumlahItem();
                    
                    System.out.printf("|%3d  %-7s  %-15s %-17s %-7d %10d %n", (i+1), id, nama, kategori,jumlah,harga);
                }  
                System.out.println("===========================================================================");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void insertKategoriToList() throws Exception {
        try {
            list.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);
            String query = "SELECT DISTINCT kategori FROM tbproduk";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String kategori;
                kategori = rs.getString("kategori");
                list.add(kategori);
            }
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

    private void insertTransaksi() {
        try {
            riwayat.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");
            int x = getId();
            Connection con = DriverManager.getConnection(url, user, pass);
            String query = "SELECT * FROM transaksi INNER JOIN tbuser ON transaksi.id_user = tbuser.id_user INNER JOIN tbproduk ON transaksi.id_produk = tbproduk.id_produk WHERE transaksi.id_user = '" + x + "'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                riwayatPembelian rbl = new riwayatPembelian(rs.getInt("id_transaksi"), rs.getString("username"), rs.getString("nama"), rs.getString("tanggal"), rs.getInt("total_harga"));
                riwayat.add(rbl);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // transaksi
    private void transaksi() throws Exception {
        while (true) {
            System.out.println("====================================");
            System.out.println("|           Pilih Kategori         |");
            System.out.println("====================================");
            for (int i = 0; i < list.size(); i++) {
                String jen = list.get(i);
                System.out.println("| " + (i + 1) + ". " + jen);
            }
            System.out.println("|99. Kembali");
            System.out.println("====================================");
            int topup;
            while (true) {
                try {
                    System.out.print("Masukkan pilihan kategori : ");
                    topup = Integer.parseInt(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Inputan Harus Berupa Integer!");
                }
            }
            try {
                if (topup == 99) {
                    break;
                }
                String kategori, namaBarang = "", jumlah="";
                String kat = list.get(topup - 1);
                kategori = kat;
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, user, pass);
                String query = "SELECT * FROM tbproduk where kategori = '" + kategori + "'";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                int id = 0;
                System.out.println("============================");
                System.out.println("       " + kategori);
                System.out.println("============================");
                while (rs.next()) {
                    jumlah = rs.getString("jumlah");
                    Integer harga = rs.getInt("harga");
                    namaBarang = rs.getString("nama");
                    System.out.print("| " + (id += 1) + ".");
                    System.out.print(" " + jumlah);
                    System.out.println("    " + harga);
                }
                System.out.println("============================");
                rs.close();
                
               
                ResultSet rs2 = st.executeQuery(query);
                int ids = 0;
                int produk;
                while (true) {
                    try {
                        System.out.print("Pilih Produk Yang Ingin Dibeli >> ");
                        produk = Integer.parseInt(br.readLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Inputan Harus Berupa Integer!");
                    }
                }

                while (rs2.next()) {
                    ids += 1;
                    if (produk == ids) {
                        Integer harga = rs2.getInt("harga");
                        System.out.println("Harga Produk >> Rp." + harga);
                        System.out.println("Jumlah Saldo Anda : " + getSaldo());
                        System.out.println("===============================");
                        System.out.print("Ingin Melakukan Pembayaran (y/n) >> ");
                        String bayar = br.readLine();
                        if (bayar.equals("y")) {
                            int total = this.saldo - harga;
                            if (total < 0) {
                                System.out.println("Saldo Anda Tidak Mencukupi Silahkan Isi Kembali Saldo");
                            } else {
                                System.out.print("\033\143");
                                strukForm sf = new strukForm();
                                sf.setVisible(true);
                                int id_user = this.id;
                                
                                LocalDate myLocalDate = LocalDate.now();

//                                sf.lblIdTransaksi.setText(String.valueOf(idTransaksi));
                                sf.lblTanggal.setText(String.valueOf(myLocalDate));
                                sf.lblIdUser.setText(String.valueOf(getId()));
                                sf.lblNamaProduk.setText(jumlah +" "+ namaBarang);
                                sf.lblHargaItem.setText(String.valueOf(harga));
                                sf.lblNamaUser.setText(getUsername());
                                sf.lblPpn.setText(String.valueOf(harga * 10 / 100));
                                sf.lblHargaTotal.setText(String.valueOf(harga + (harga * 10 / 100)));
                                
                                
                                sf.btnConfirm.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            String queryy = "INSERT INTO transaksi (id_user, id_produk, tanggal, total_harga) values ('" + id_user + "', '" + rs2.getString("id_produk") + "', '" + myLocalDate + "', '" + total + "' )";
                                            Statement stt = con.createStatement();
                                            stt.executeUpdate(queryy);
                                            setSaldo(total);
                                            updateSaldo();
                                            sf.dispose();
                                            System.out.println("berhasil Melakukan Pembelian...");
                                            System.out.println("Sisa Saldo Anda : " + getSaldo());
                                        } catch (Exception e1) {
                                            System.out.println(e1);
                                        }
                                    }
                                });

                                sf.btnBatal.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            sf.dispose();
                                            System.out.print("Tekan Enter Untuk Kembali...");
                                        } catch (Exception ex) {
                                            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                
                                
                                br.readLine();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }

                con.close();

            } catch (Exception e) {

                System.out.println(e);
            }
        }
    }

    public void menu() throws Exception {
        while (true) {
            System.out.print("\033\143");
            System.out.println("=================================");
            System.out.println("|          Menu Customer        |");
            System.out.println("=================================");
            System.out.println("| 1. Akun                       |");
            System.out.println("| 2. Top up Saldo Akun          |");
            System.out.println("| 3. Transaksi                  |");
            System.out.println("| 4. Riwayat Transaksi          |");
            System.out.println("| 0. Log Out                    |");
            System.out.println("=================================");
            int menuC;
            while (true) {
                try {
                    System.out.print("Masukkan Menu Pilihan Anda : ");
                    menuC = Integer.parseInt(br.readLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Inputan Harus Berupa Integer!");
                }
            }
            switch (menuC) {
                case 1:
                    akun();
                    break;
                case 2:
                    saldo();
                    break;
                case 3:
                    while (true) {
                        System.out.print("\033\143");
                        System.out.println("=================================");
                        System.out.println("|          Top Up Produk        |");
                        System.out.println("=================================");
                        System.out.println("| 1. Lihat Produk               |");
                        System.out.println("| 2. Beli Produk                |");
                        System.out.println("| 0. Kembali                    |");
                        System.out.println("=================================");
                        System.out.print("| Masukkan Menu Pilihan Anda : ");
                        String pilih = br.readLine();
                        if (pilih.equals("1")) {
                            lihatProduk();
                            System.out.print("Tekan Apa Saja Untuk Melanjutkan....");
                            br.readLine();
                            
                        } else if (pilih.equals("2")) {
                            insertKategoriToList();
                            transaksi();
                            System.out.print("Tekan Apa Saja Untuk Melanjutkan....");
                            br.readLine();
                            
                        } else if (pilih.equals("0")) {
                            break;
                            
                        }
                    }
                    break;
                case 4:
                    insertTransaksi();
                    for (int i = 0; i < riwayat.size(); i++) {
                        riwayatPembelian rwy = riwayat.get(i);
                        String user = rwy.getIdUser();
                        String produk = rwy.getIdProduk();
                        String tanggal = rwy.getTanggal();
                        int total = rwy.getTotal();
                        System.out.println("=======================================");
                        System.out.println("|          Riwayat Pembelian          |");
                        if (username.equals(user)) {
                            System.out.println("=======================================");
                            System.out.println("| NO                : " + (i + 1));
                            System.out.println("| Nama User         : " + user);
                            System.out.println("| Nama Produk       : " + produk);
                            System.out.println("| Tanggal Pembelian : " + tanggal);
                            System.out.println("| Total Harga       : " + total);
                            System.out.println("=======================================");
                        }
                    }
                    System.out.print("Tekan Apa Saja Untuk Melanjutkan....");
                    br.readLine();
                    
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
    }
}
