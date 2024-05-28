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


public class admin extends user {
    // private String username,password;
    // private int id;
    private static InputStreamReader sr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(sr);
    private static final String url = "jdbc:MySQL://localhost:3306/ekios_db";
    private static final String user = "root";
    private static final String pass = "";
    private static ArrayList <itemEkios> items = new ArrayList<>();


    public admin(int id, String username, String password) {
        super(id, username, password);
        // this.id = id;
        // this.username = username;
        // this.password = password;
    }
    
    private static String randomUID() {
        final String uuid = "PRD" + UUID.randomUUID().toString().split("-")[3].toUpperCase();
        return uuid;
        
    }
    // @Override
    // public void login(){}

    // @Override
    // public void logout(){}

    // @Override
    // public int getId() {
    //     return id;
    // }

    // @Override
    // public String getPassword() {
    //     return password;
    // }
    
    // @Override
    // public String getUsername() {
    //     return username;
    // }
    

    static void addProduk()
    {
        try {
            String uid = randomUID();
            System.out.print("nama produk : ");
            String nama = br.readLine();
            System.out.print("Jenis produk :");
            String jenis = br.readLine();
            System.out.print("harga : ");
            int harga = Integer.parseInt(br.readLine());

            itemEkios items = new itemEkios(uid,nama,jenis,harga);

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,user,pass);
            
            String query = "INSERT INTO tbproduk values('"+items.getIdItem()+"', '"+items.getNamaItem() +"', '"+items.getJenisItem()+"', '"+items.getHargaItem()+"' )";

            Statement st = con.createStatement();
            st.executeUpdate(query);

            System.out.println("berhasil ditambahkan ke db");
            con.close();



        } catch (Exception e) {
            
            System.out.println(e);
        }
    }

    private static void insertToList()
    {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * FROM tbproduk";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){
                itemEkios itm = new itemEkios(rs.getString("id_produk"), rs.getString("nama_produk"), rs.getString("jenis_produk"), rs.getInt("harga_produk"));
                items.add(itm);

                
                // System.out.println(rs.getString("id_produk"));
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void lihatProduk()
    {
        try {
            insertToList();

            if (items.size() == 0) {
                System.out.println("data kosong");
                return;

            }else{
                System.out.println("------------------------------------------");
                System.out.printf("%3s  %-6s  %-6s  %5s %3s %n", "No", "Id", "Nama produk", "jenis produk", "harga");
                System.out.println("------------------------------------------");

                for(int i = 0; i < items.size(); i++){
                    itemEkios itm = items.get(i);
                    String id = itm.getIdItem();
                    String nama = itm.getNamaItem();
                    String jenis = itm.getJenisItem();
                    int harga = itm.getHargaItem();

                    
                    System.out.printf("%3d  %-1s  %-10s %-10s %3d %n", (i+1), id, nama, jenis,harga);
                    

                    

                }  

                System.out.println("------------------------------------------");

            
            }

        
        // System.out.println(rs);



        } catch (Exception e) {
        

            System.out.println(e);
        }
    
    }

    static void ubahProduk()
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
                        itm.setNamaItem(nama);
                        
                        System.out.print("Jenis produk :");
                        String jenis = br.readLine();
                        itm.setJenisItem(jenis);

                        System.out.print("harga : ");
                        int harga = Integer.parseInt(br.readLine());
                        itm.setHargaItem(harga);

                        Class.forName("com.mysql.cj.jdbc.Driver");

                        Connection con = DriverManager.getConnection(url,user,pass);
                        
                        String query = "UPDATE tbproduk SET nama_produk = '"+itm.getNamaItem() +"', jenis_produk =  '"+itm.getJenisItem()+"', harga_produk = '"+itm.getHargaItem()+"' WHERE id_produk = '"+itm.getIdItem()+"'";

                        Statement st = con.createStatement();
                        st.executeUpdate(query);

                        System.out.println("berhasil di update");
                        con.close();
                        
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    static void hapusProduk()
    {
        try {
            lihatProduk();
            if(items.size() == 0){
                System.out.println("data kosong!");
                return;
            }else{
                System.out.print("Hapus nomor (0 = kembali): ");
                int hapus = Integer.parseInt(br.readLine());
                if (hapus == 0)return;

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
                    }
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }


    public static void managemenAtributEkios() throws IOException
    {
        System.out.println("Produk");
        System.out.println("1. lihat produk");
        System.out.println("2. tambah produk");
        System.out.println("3. ubah produk");
        System.out.println("4. hapus produk");
        System.out.println("0. kembali");
        System.out.print("pilih :");
        int pilih = Integer.parseInt(br.readLine());
        switch (pilih) {
            case 1:
                lihatProduk();
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
                break;
        }

    }

    public void melihatRiwayatTransaksi(){}


    public static void melihatMember()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * FROM tbuser";

            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery(query);
            System.out.println("------------------------------------------");
            System.out.printf("%-6s  %-6s  %5s %3s %n",  "ID", "Username", "Password", "role");
            System.out.println("------------------------------------------");

            while(rs.next()){
                // data user dimasukkan ke dalam list (nanti)
                // itemEkios itm = new itemEkios(rs.getString("id_produk"), rs.getString("nama_produk"), rs.getString("jenis_produk"), rs.getInt("harga_produk"));
                // items.add(itm);

                int id = rs.getInt("id_user");
                String username = rs.getString("username");
                String password = rs.getString("password");

                System.out.printf("%-6s  %-6s %-10s %3s %n",  id, username, password, "...");
               
                // System.out.println(rs.getString("id_produk"));
                
            }
            System.out.println("------------------------------------------");


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws IOException
    {
        try {
            while (true) {
                    
                System.out.println(randomUID());
                System.out.println("Welcome Admin");
                System.out.println("Menu :");
                System.out.println("1. Produk");
                System.out.println("2. Member");
                System.out.println("3. Transaksi");
                System.out.println("0. logout");
                System.out.print("Pilih :");
                int menu = Integer.parseInt(br.readLine());

                switch (menu) {
                    case 1:
                        managemenAtributEkios();  
                        break;
                    case 2 :
                        melihatMember();

                        break;
                    case 3 :

                        break;
                    case 0 :
                        System.out.println("good by admin :)");
                        return;
                    default:
                        break;
                }

            }

        } catch (Exception e) {
            System.err.println(e);

        }


    }


}
