package user;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import items.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class customer extends user {
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    
    private static final String url = "jdbc:MySQL://localhost:3306/ekios_db";
    private static final String user = "root";
    private static final String pass = "";
    private static ArrayList <itemEkios> items = new ArrayList<>(); // array sementara yg menampung data dari db
    private static ArrayList <riwayatPembelian> riwayat = new ArrayList<>(); // array sementara yg menampung data dari db
    private static List<String> list = new ArrayList<>();
    private int saldo;
    
    public customer(int Id, String Username, String Password, int Saldo) {
        super(Id, Username, Password);
        this.saldo = Saldo;
    }
    
     @Override
     public boolean login(String username, String password){
         String db_username = "", db_password = "", db_role="";
         int db_id = 0;
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,user,pass);
            
            String query = "select * from tbuser where username = '"+getUsername()+"'";

            Statement st = con.createStatement();
            
           
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                db_username = rs.getString("username");
                db_password = rs.getString("password");
                db_role = rs.getString("role");
                db_id = rs.getInt("id_user");
                }
            
            con.close();

           if (username.equals(db_username) && password.equals(db_password) && db_role.equals("customer")){
                this.id = db_id;
                return true;
           }


        } catch (Exception e) {
            
            System.out.println(e);
        }
         return false;
    }

     public boolean signUp(String username, String password){
         String db_username = "", db_password = "";
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,user,pass);
            
            String query = "select * from tbuser where username = '"+username+"'";

            Statement st = con.createStatement();
            
           
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                db_username = rs.getString("username");
                }

           if (username.equals(db_username)){
               System.out.println("username telah digunakan, silahkan gunakan username yang lain");
           }else{
               try {
                   
                   Class.forName("com.mysql.cj.jdbc.Driver");

                   con = DriverManager.getConnection(url,user,pass);
                   
                   query = "insert into tbuser (username, password,saldo, role) values('"+username+"','"+password+"', 0, 'customer')";
                   
                   st.executeUpdate(query);
                   
                   System.out.println("akun berhasil dibuat");
                   
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

    public int getSaldo(){
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void akun() throws IOException {
        System.out.println("===========================");
        System.out.println("|      Akun Customer      |");
        System.out.println("===========================");
        System.out.println("| Username : " + this.username);
        System.out.println("| Id : " + this.id);
        System.out.println("| Password : " + this.password);
        System.out.println("| Saldo    : " + this.saldo);
        System.out.println("===========================");
        
    }

    public void saldo() throws Exception {
        System.out.println("===========================");
        System.out.println("|      Saldo Customer     |");
        System.out.println("===========================");
        System.out.println("| Saldo    : " + this.saldo);
        System.out.println("===========================");
        while (true) {
            System.out.print("Masukkan jumlah saldo yang ingin di topup : ");
            int topup = Integer.parseInt(br.readLine());
            System.out.print("Yakin ingin topup (y/n):");
            String cek = br.readLine();
            if(cek.equals("y")){
                setSaldo(topup);
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(url,user,pass);
                
                String query = "UPDATE tbuser SET saldo = '"+ getSaldo() +"' WHERE id_user = '"+ getId() +"'";

                Statement st = con.createStatement();
                st.executeUpdate(query);

                System.out.println("berhasil di update");
                con.close();
                break;
            }
            else{
                break;
            }
        }
    }

    public void topup() throws IOException {
        System.out.println("===========================");
        System.out.println("|      Saldo Customer     |");
        System.out.println("===========================");
        System.out.println("| Saldo    : " + this.saldo);
        System.out.println("===========================");
    }





    // ambil data dari table produk
    private static void insertToList()
    {
        try {
            items.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * FROM tbproduk";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                itemEkios itm = new itemEkios(rs.getString("id_produk"), rs.getString("kategori"), rs.getString("nama"), rs.getInt("jumlah"), rs.getInt("harga"));
                items.add(itm);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //lihatProduk
    private static void lihatProduk()
    {
        try {
            insertToList();
            if (items.size() == 0) {
                System.out.println("data kosong");
                return;
            }else{
                System.out.println("------------------------------------------");
                System.out.printf("%3s  %-5s  %-12s  %-10s %-6s %3s  %n", "No", "Id", "Nama produk", "Kategori", "Jumlah" ,"Harga");
                System.out.println("------------------------------------------");
                for(int i = 0; i < items.size(); i++){
                    itemEkios itm = items.get(i);
                    String id = itm.getIdItem();
                    String nama = itm.getNamaItem();
                    String kategori = itm.getKategori();
                    int harga = itm.getHargaItem();
                    int jumlah = itm.getJumlahItem();
                    System.out.printf("%3d  %-2s  %-8s %-10s %-6d %3d %n", (i+1), id, nama, kategori,jumlah,harga);
                }  
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    private static void insertKategoriToList() throws Exception
    {
        try {
            list.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT DISTINCT kategori FROM tbproduk";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);


            while(rs.next()){
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



    private static void insertTransaksi()
    {
        try {
            riwayat.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * FROM transaksi INNER JOIN tbuser ON transaksi.id_user = tbuser.id_user INNER JOIN tbproduk ON transaksi.id_produk = tbproduk.id_produk;";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                riwayatPembelian rbl = new riwayatPembelian(rs.getInt("id_transaksi"), rs.getString("username"), rs.getString("nama"), rs.getString("tanggal"), rs.getInt("total_harga"));
                riwayat.add(rbl);
            }
            
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
            for(int i = 0; i < list.size(); i++){
                String jen = list.get(i);
                System.out.println("| " +(i+1) + ". " + jen);
            }
            System.out.println("|99. Kembali");
            System.out.println("====================================");
            System.out.print("Masukkan pilihan kategori : ");
            int topup = Integer.parseInt(br.readLine());
            try {
                if (topup == 99) {
                    break;
                }
                String kategori;
                String kat = list.get(topup-1);
                System.out.println(kat);
                kategori = kat;
                Class.forName("com.mysql.cj.jdbc.Driver");
            
                Connection con = DriverManager.getConnection(url,user,pass);
                String query = "SELECT * FROM tbproduk where kategori = '" + kategori +"'";

                Statement st = con.createStatement();
                ResultSet rs =  st.executeQuery(query);
                int id = 0;
                while(rs.next()){
                    String jumlah = rs.getString("jumlah");
                    Integer harga = rs.getInt("harga");
                    System.out.print(id += 1);
                    System.out.print(" " + jumlah);
                    System.out.println(" "+harga);
                }
                rs.close();

                ResultSet rs2 =  st.executeQuery(query);
                int ids = 0;
                System.out.print("Pilih Produk Yang Ingin Dibeli "); int produk = Integer.parseInt(br.readLine());
                while(rs2.next()){
                    ids += 1;
                    if (produk == ids){
                        Integer harga = rs2.getInt("harga");
                        int total = this.saldo - harga;
                        LocalDate myLocalDate = LocalDate.now();
                        String queryy = "INSERT INTO transaksi (id_user, id_produk, tanggal, total_harga) values ('"+ this.id +"', '"+ rs2.getString("id_produk") +"', '"+ myLocalDate +"', '"+ total +"' )";
                        // riwayatPembelian rbl = new riwayatPembelian(rs.getInt("id_transaksi"), rs.getString("username"), rs.getString("nama"), rs.getString("tanggal"), rs.getInt("total_harga"));
                        Statement stt = con.createStatement();
                        stt.executeUpdate(queryy);
                        
                        System.out.print("berhasil ditambahkan ke db");
                        br.readLine();
                        break;
                    }
                }
                
                con.close();
                
            } catch (Exception e) {
                
                System.out.println(e);
            }
        }
    }


    public void main(String[] args)throws Exception{
        while(true) {
            System.out.println("=================================");
            System.out.println("|          Menu Customer        |");
            System.out.println("=================================");
            System.out.println("| 1. Akun                       |");
            System.out.println("| 2. Top up Saldo Akun          |");
            System.out.println("| 3. Transaksi                  |");
            System.out.println("| 4. Riwayat Transaksi          |");
            System.out.println("| 0. Log Out                    |");
            System.out.println("=================================");
            System.out.print("Masukkan Menu Pilihan Anda : ");
            int menuC = Integer.parseInt(br.readLine());
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
                        System.out.println("| 9. Kembali                    |");
                        System.out.println("=================================");
                        String pilih = br.readLine();
                        if (pilih.equals("1")){
                            lihatProduk();
                            System.out.print("Tekan Apa Saja Untuk Melanjutkan...."); br.readLine();
                        }
                        else if (pilih.equals("2")){
                            // transaksi();
                            insertKategoriToList();
                            transaksi();
                            System.out.print("Tekan Apa Saja Untuk Melanjutkan...."); br.readLine();
                        }
                        else if (pilih.equals("9")){
                            break;
                        }
                    }
                case 4:
                    insertTransaksi();
                    for(int i = 0; i < riwayat.size(); i++){
                        riwayatPembelian rwy = riwayat.get(i);
                        String user = rwy.getIdUser();
                        String produk = rwy.getIdProduk();
                        String tanggal = rwy.getTanggal();
                        int total = rwy.getTotal();
                        if (username.equals(user)){
                            System.out.println(user);
                            System.out.println(produk);
                            System.out.println(tanggal);
                            System.out.println(total);
                        } 
                    }  
                case 0:
                    return;
                default:
                    break;
            }

        }
    }
}


