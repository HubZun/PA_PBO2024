package user;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import items.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class customer extends user {
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static ArrayList<customer> cus = new ArrayList<>();
    private static final String url = "jdbc:MySQL://localhost:3306/ekios_db";
    private static final String user = "root";
    private static final String pass = "";
    // private static ArrayList<itemEkios> items = new ArrayList<>();


    private int saldo;
    
    public customer(int Id, String Username, String Password, int Saldo) {
        super(Id, Username, Password);
        this.saldo = Saldo;
    }
    
    // @Override
    // public void login(){}

    // @Override
    // public void logout(){}

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

    public static void connectDb()throws IOException {
        String username,password,passwordDb="";
        boolean ketemu = false;
        
        System.out.print("Masukkan Username : ");
        username = br.readLine();
        System.out.print("Masukkan Password : ");
        password = br.readLine();
        System.out.println(username + password);

        if(username.isEmpty() || password.isEmpty()){
            System.out.println("Isi semua form!!!");
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:MySQL://localhost:3306/ekios_db";
            String user = "root";
            String pass = "";

            Connection con = DriverManager.getConnection(url,user,pass);
            Statement st = con.createStatement();
            String query = "select * from tbuser where username = '"+username+"'";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                passwordDb = rs.getString("password");
                customer ctm = new customer(rs.getInt("id_user"), rs.getString("username"), passwordDb, rs.getInt("saldo"));
                cus.add(ctm);
                ketemu = true;
            }
            con.close();
            
            if (ketemu = true && password.equals(passwordDb)){
                System.out.println("Akun Ditemukan");
            }else{
                System.out.println("Akun Tidak Ditemukan");
            }
        } catch (Exception e) {
            System.out.println("Error = "+e);
        }
    }

    public static void akun() throws IOException {
        String username, password;
        int saldo;
        for (int i = 0; i < cus.size(); i++) {
            customer ctm = cus.get(i);
            username = ctm.getUsername();
            password = ctm.getPassword();
            saldo = ctm.getSaldo();
            System.out.println("===========================");
            System.out.println("|      Akun Customer      |");
            System.out.println("===========================");
            System.out.println("| Username : " + username);
            System.out.println("| Password : " + password);
            System.out.println("| Saldo    : " + saldo);
            System.out.println("===========================");
        } 
        
    }

    public static void saldo() throws Exception {
        int saldo;
        for (int i = 0; i < cus.size(); i++) {
            customer ctm = cus.get(i);
            saldo = ctm.getSaldo();
            System.out.println("===========================");
            System.out.println("|      Saldo Customer     |");
            System.out.println("===========================");
            System.out.println("| Saldo    : " + saldo);
            System.out.println("===========================");
            while (true) {
                System.out.print("Masukkan jumlah saldo yang ingin di topup : ");
                int topup = Integer.parseInt(br.readLine());
                System.out.print("Yakin ingin topup (y/n):");
                String cek = br.readLine();
                if(cek.equals("y")){
                    ctm.setSaldo(topup);
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection(url,user,pass);
                    
                    String query = "UPDATE tbuser SET saldo = '"+ ctm.getSaldo() +"' WHERE id_user = '"+ ctm.getId() +"'";

                    Statement st = con.createStatement();
                    st.executeUpdate(query);

                    System.out.println("berhasil di update");
                    con.close();
                    break;
                }
                else{
                    System.out.println(cek);
                    topup = 0;
                    break;
                }
            }
        }
        

    }

    public static void main(String[] args)throws Exception{
        connectDb(); 
        while(true) {
            System.out.println("=================================");
            System.out.println("|          Menu Customer        |");
            System.out.println("=================================");
            System.out.println("| 1. Akun                       |");
            System.out.println("| 2. Top up Saldo Akun          |");
            System.out.println("| 3. Top up Game                |");
            System.out.println("| 4. Top up Pulsa               |");
            System.out.println("| 5. Log Out                    |");
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
                case 5:
                    return;
                default:
                    break;
            }

        }
    }
}


