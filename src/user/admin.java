package user;

import items.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class admin extends user {
   
    private static InputStreamReader sr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(sr);
    private static final String url = "jdbc:MySQL://localhost:3306/ekios_db";
    private static final String user = "root";
    private static final String pass = "";
    private static ArrayList <itemEkios> items = new ArrayList<>(); // array sementara yg menampung data dari db
    
    private static List<String> list = new ArrayList<>();
   
    public admin(int id, String username, String password) {
        super(id, username, password);
    }
    
    public void cls()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private static String randomUID() throws Exception  {
        
            final String uuid = "PRD" + UUID.randomUUID().toString().split("-")[3].toUpperCase();
                
            Class.forName("com.mysql.cj.jdbc.Driver");
                
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * FROM tbproduk where id_produk = '"+ uuid +"'";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);

            
            if (rs.next() == false) { // jika UID belum digunakan di DB
                con.close();
                return uuid;
               
              } else {
                randomUID(); // recursive statement
              }


            con.close();

            return uuid;

        
    }

    @Override
    public boolean login(String username, String password){
        String db_username = "", db_password = "", db_role="", sha_password = "";
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(url,user,pass);
        
        String query = "select * from tbuser where username = '"+username+"'";

        Statement st = con.createStatement();
        ResultSet rs =  st.executeQuery(query);

        while(rs.next()){
            db_username = rs.getString("username");
            db_password = rs.getString("password");
            db_role = rs.getString("role");
            }
        

        query = "select password from tbuser where password = SHA1('"+password+"')";

        st = con.createStatement();

        rs = st.executeQuery(query);

        while (rs.next()) {
            sha_password = rs.getString("password");
        }

        con.close();

        if (username.equals(db_username) && sha_password.equals(db_password) && db_role.equals("admin")){
            return true;
        }


    } catch (Exception e) {
        
        System.out.println(e);
    }
        return false;
    }

    @Override
    public void logout(){}

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

    // ========== crud methods PRODUK ==========
    

    // private static void addProduk()
    // {
    //     try {
    //         String uid = randomUID();
    //         System.out.print("nama produk : ");
    //         String nama = br.readLine();
    //         System.out.print("Jenis produk :");
    //         String jenis = br.readLine();
    //         System.out.print("harga : ");
    //         int harga = Integer.parseInt(br.readLine());

    //         if (harga < 0 || harga == 0){
    //             System.out.println("harga tidak boleh kurang atau kosong");
    //             return;
    //         }

    //         itemEkios items = new itemEkios(uid,nama,jenis,harga);

    //         Class.forName("com.mysql.cj.jdbc.Driver");

    //         Connection con = DriverManager.getConnection(url,user,pass);
            
    //         String query = "INSERT INTO tbproduk values('"+items.getIdItem()+"', '"+items.getNamaItem() +"', '"+items.getJenisItem()+"', '"+items.getHargaItem()+"' )";

    //         Statement st = con.createStatement();
    //         st.executeUpdate(query);

    //         System.out.println("berhasil ditambahkan ke db");
    //         con.close();



    //     } catch (Exception e) {
            
    //         System.out.println(e);
    //     }
    // }

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

    private static void addProduk() throws Exception {
        try {
            
            
            System.out.println("====================================");
            System.out.println("|           Pilih Kategori         |");
            System.out.println("===================================="); 
            for(int i = 0; i < list.size(); i++){
                String jen = list.get(i);
                System.out.println("| " +(i+1) + ". " + jen);
            }
            System.out.println("| 99. Tambah Kategori Baru");
            System.out.println("| 0. Exit");
            System.out.println("====================================");
            int pilih;
            while(true){
                try{
                    System.out.print("Masukkan Pilihan Kategori : ");
                    pilih = Integer.parseInt(br.readLine());
                    break;
                }
                catch(NumberFormatException e){
                    System.out.println("Inputan Harus Angka!");
                }
            }
            if(pilih == 0){
                return;
            }
            try {
                String kategori;
                if(pilih != 99){
                    String kat = list.get(pilih-1);
                    System.out.println(kat);
                    kategori = kat;
                }
                else{
                    System.out.print("Masukkan Kategori : ");
                    kategori = br.readLine();
                }
                String uid = randomUID();
                System.out.print("nama produk : ");
                String nama = br.readLine();
                if (nama.equals("")){
                    throw new IOException();

                }
                int jumlah;
                while(true){
                    try{
                        System.out.print("jumlah item : ");
                        jumlah = Integer.parseInt(br.readLine());
                        if(jumlah <= 0){
                            System.out.println("jumlah tidak valid");
                            continue;
                        }
                        break;
                    }
                    catch(NumberFormatException e){
                        System.out.println("Inputan Harus Angka!");
                    }
                }
                int harga;
                while(true){
                    try{
                        System.out.print("harga : ");
                        harga = Integer.parseInt(br.readLine());
                        if(harga <= 0){
                            System.out.println("harga tidak valid");
                            continue;
                        }
                        break;
                    }
                    catch(NumberFormatException e){
                        System.out.println("Inputan Harus Angka!");
                    }
                }
                itemEkios items = new itemEkios(uid, kategori, nama,jumlah,harga);
    
                Class.forName("com.mysql.cj.jdbc.Driver");
    
                Connection conn = DriverManager.getConnection(url,user,pass);
                
                String queryy = "INSERT INTO tbproduk values('"+items.getIdItem()+"', '"+items.getKategori() +"', '"+items.getNamaItem() +"', '"+items.getJumlahItem()+"', '"+items.getHargaItem()+"' )";
    
                Statement stt = conn.createStatement();
                stt.executeUpdate(queryy);
    
                System.out.println("berhasil ditambahkan ke db");
                conn.close();
                System.out.println("tekan enter untuk melanjutkan...");br.readLine();
    
            } catch (Exception e) {
                
                System.out.println(e);
            }

        }catch ( IOException e  ) {
            System.out.println("error input!");
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
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

                
                // System.out.println(rs.getString("id_produk"));
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void lihatProduk()
    {
        try {
            insertToList();

            if (items.size() == 0) {
                System.out.println("data kosong");
                return;

            }else{
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

    private static void ubahProduk()
    {
        try {
            lihatProduk();
            if (items.size() == 0){
                System.out.println("data kosong!");
                return;
            
            }else{
                System.out.print("ubah nomor (0 = kembali): ");
                int ubah = Integer.parseInt(br.readLine());
                if (ubah == 0)return;

                for (int i= 0; i < items.size(); i++){
                    if((ubah-1) == i){
                        itemEkios itm = items.get(i);

                        System.out.print("nama produk : ");
                        String nama = br.readLine();
                        if (nama.equals("")){
                            throw new IOException();

                        }

                        itm.setNamaItem(nama);
                        // System.out.println(itm.getNamaItem());

                        System.out.println("kategori lama : " + itm.getKategori());
                        
                        System.out.println("====================================");
                        System.out.println("|           Pilih Kategori         |");
                        System.out.println("===================================="); 
                        for(int j = 0; j < list.size(); j++){
                            String jen = list.get(j);
                            System.out.println("| " +(j+1) + ". " + jen);
                        }
                        System.out.println("| 99. Input kategori manual");
                        System.out.println("| 100. Exit");
                        System.out.println("====================================");

                        int pilih;
                        while(true){
                            try{
                                System.out.print("Masukkan Pilihan Kategori : ");
                                pilih = Integer.parseInt(br.readLine());
                                break;
                            }
                            catch(NumberFormatException e){
                                System.out.println("Inputan Harus Angka!");
                            }
                        }
                        if(pilih == 100){
                            return;
                        }
                        String kategori;
                        if(pilih != 99){

                            String kat = list.get(pilih-1);
                            System.out.println(kat);
                            kategori = kat;

                                               
                           
                        }else{
                            System.out.print("Masukkan Kategori : ");
                            kategori = br.readLine();
                        }

                        // System.out.print("Kategori :");
                        // String kategori = br.readLine();
                        // if (kategori.equals("")){
                        //     throw new IOException();
                            
                        // }
                        itm.setKategori(kategori);



                        int harga;
                        while(true){
                            try{
                                System.out.print("harga : ");
                                harga = Integer.parseInt(br.readLine());
                                if(harga <= 0){
                                    System.out.println("harga tidak valid");
                                    continue;
                                }
                                break;
                            }
                            catch(NumberFormatException e){
                                System.out.println("Inputan Harus Angka!");
                            }
                        }
                        // System.out.print("harga : ");
                        // int harga = Integer.parseInt(br.readLine());
                       
                        itm.setHargaItem(harga);
                   
                        

                        Class.forName("com.mysql.cj.jdbc.Driver");

                        Connection con = DriverManager.getConnection(url,user,pass);
                        
                        String query = "UPDATE tbproduk SET nama = '"+itm.getNamaItem() +"', kategori =  '"+itm.getKategori()+"', jumlah = '"+ itm.getJumlahItem() +"', harga = '"+itm.getHargaItem()+"' WHERE id_produk = '"+itm.getIdItem()+"'";

                        Statement st = con.createStatement();
                        st.executeUpdate(query);

                        System.out.println("berhasil di update");
                        con.close();
                        System.out.println("tekan enter untuk melanjutkan...");br.readLine();
                        
                    }
                }

            }
        }catch( NumberFormatException e){
            System.out.println("error input!");
        }catch ( IOException e  ) {
            System.out.println("error input!");
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private static void hapusProduk()
    {
        try {
            lihatProduk();
            if(items.size() == 0){
                System.out.println("data kosong!");
                return;
            }else{
                System.out.print("Hapus nomor (0 = kembali): ");
                int hapus = Integer.parseInt(br.readLine());
                if (hapus == 0){
                    return;
                }else if(hapus < 0){
                    System.out.println("oops!!!");
                    return;
                }

                for(int i = 0; i < items.size(); i++){
                    if((hapus - 1) == i){
                        itemEkios itm = items.get(i);

                        Class.forName("com.mysql.cj.jdbc.Driver");

                        Connection con = DriverManager.getConnection(url,user,pass);
                        
                        String query = "DELETE FROM tbproduk WHERE id_produk = '"+itm.getIdItem()+"'";

                        Statement st = con.createStatement();
                        st.executeUpdate(query);

                        System.out.println("berhasil dihapus");
                        con.close();
                        System.out.println("tekan enter untuk melanjutkan...");br.readLine();

                        return;
                    }
                }
                // jika "hapus" tidak ada di data
                System.out.println("data tidak ada!");
                System.out.println("tekan enter untuk melanjutkan...");br.readLine();

            }

        } catch (Exception e) {
            
            System.out.println(e);
        }

    }
    // ========== EOF - crud methods PRODUK ==========




    private void managemenAtributEkios() throws Exception
    {
        try {
            
                
            cls();

            insertKategoriToList();
            System.out.println("================================");
            System.out.println("|           Produk             |");
            System.out.println("================================");
            System.out.println("| 1. lihat produk              |");
            System.out.println("| 2. tambah produk             |");
            System.out.println("| 3. ubah produk               |");
            System.out.println("| 4. hapus produk              |");
            System.out.println("| 0. kembali                   |");
            System.out.println("================================");
            System.out.print("| pilih : ");
            int pilih = Integer.parseInt(br.readLine());
            switch (pilih) {
                case 1:
                    lihatProduk();
                    System.out.println("tekan enter untuk melanjutkan...");br.readLine();

                    break;
                case 2:
                    addProduk();
                    break;

                case 3 :
                    ubahProduk();
                    break;
            
                case 4 :
                    hapusProduk();
                    break;
                case 0 :
                    return;

                default:
                    System.out.println("pilihan tidak ada");

                    break;
                }
            

            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }



    // RIWAYAT TRANSAKSI
    public static void melihatRiwayatTransaksi()
    {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
//            String query = "SELECT * FROM transaksi";
            String query = "SELECT * FROM transaksi INNER JOIN tbuser ON transaksi.id_user = tbuser.id_user INNER JOIN tbproduk ON transaksi.id_produk = tbproduk.id_produk ";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);

            if (rs.next() == false) {
                System.out.println("tidak ada data!");
                System.out.println("tekan enter untuk melanjutkan...");br.readLine();
                return;
                           
            }else{
                System.out.println("=============================================================");
                System.out.printf("%-6s  %-10s  %-10s  %-15s %-10s  %n", "Id trx", "Username", "id produk", "tanggal", "total harga");
                System.out.println("=============================================================");

                do{
                    int id_trx = rs.getInt("id_transaksi");
                    String username = rs.getString("username");
                    String id_produk = rs.getString("id_produk");
                    String tanggal = rs.getDate("tanggal").toString();
                    int harga = rs.getInt("total_harga");
                    System.out.printf(" %-6d  %-10s  %-10s %-15s %-10d %n", id_trx, username, id_produk, tanggal, harga);


                }while(rs.next());
                System.out.println("=============================================================");

                System.out.println("tekan enter untuk melanjutkan...");br.readLine();

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // LIHAT LIST MEMBER
    public static void melihatMember()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * FROM tbuser";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);
            System.out.println("===================================");
            System.out.printf("%-6s  %-10s %6s %n",  "ID", "Username", "role");
            System.out.println("===================================");

            while(rs.next()){
                // data user dimasukkan ke dalam list (nanti)
           

                int id = rs.getInt("id_user");
                String username = rs.getString("username");
                String role = rs.getString("role");

                System.out.printf("%-6s  %-10s  %6s %n",  id, username, role);
 
                
            }
            System.out.println("===================================");

            System.out.println("tekan enter untuk melanjutkan...");br.readLine();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void main(String[] args) throws IOException // hapus static untuk run dari file login.java
    {
        try {
            
            boolean flag = true;

            while (true) {
                
                // cls();

                System.out.println(randomUID());
                
                if (flag){ // cek program berjalan pertama kali
                    System.out.println("Welcome Admin");

                }

                flag = false;
                System.out.println("==================================");
                System.out.println("|           Menu Admin           |");
                System.out.println("==================================");
                System.out.println("| 1. Produk                      |"); // crud produk
                System.out.println("| 2. Member                      |"); // lihat data member/customer
                System.out.println("| 3. Transaksi                   |"); // lihat data transaksi
                System.out.println("| 0. logout                      |"); // exit to login
                System.out.println("==================================");
                System.out.print("| Pilih : ");
                int menu = Integer.parseInt(br.readLine());

                switch (menu) {
                    case 1:
                        managemenAtributEkios();  
                        break;
                    case 2 :
                        melihatMember();

                        break;
                    case 3 :
                        melihatRiwayatTransaksi();

                        break;

                    case 4 :
                        // managemenkategori();
                        break;
                    case 0 :
                        System.out.println("good by admin :)");
                        return;
                    default:
                        System.out.println("pilihan tidak ada");

                        break;
                }

            }

        } catch (Exception e) {
            System.err.println(e);

        }


    }


}
