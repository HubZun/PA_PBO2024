package user;
import java.util.ArrayList;

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
    private int saldo;
    
    public customer(int Id, String Username, String Password, int Saldo) {
        super(Id, Username, Password);
        this.saldo = Saldo;
    }
    
     @Override
     public boolean login(String username, String password){
         String db_username = "", db_password = "", db_role="";
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
                }
            
            con.close();

           if (username.equals(db_username) && password.equals(db_password) && db_role.equals("customer")){
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
        
    }

    public void main(String[] args)throws Exception{
        while(true) {
            System.out.println("=================================");
            System.out.println("|          Menu Customer        |");
            System.out.println("=================================");
            System.out.println("| 1. Akun                       |");
            System.out.println("| 2. Top up Saldo Akun          |");
            System.out.println("| 3. Top up Game                |");
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
                    break;
                case 0:
                    return;
                default:
                    break;
            }

        }
    }
}


