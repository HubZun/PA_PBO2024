import user.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class login {
    public static void main(String[] args) throws IOException, Exception {
        
    InputStreamReader ir = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(ir);
        
        String pilih, username, password;
        admin ad;
        customer cs;
        System.out.print("\033\143");
        while (true){
            System.out.println("\n\n======= Login Page ========");
            System.out.println("| 1. login                |");
            System.out.println("| 2. sign up              |");
            System.out.println("| 0. exit                 |");
            System.out.println("===========================");
            System.out.print("| silahkan pilih menu >> ");
            pilih = br.readLine();
            
            if (pilih.equals("1")){
                Console c = System.console();
                
                System.out.print("| masukkan username >> ");
                username = br.readLine();
                
                char[] pass = c.readPassword("| masukkan password >> ");
                password = new String(pass);
                
                ad = new admin(0,username,password);
                cs = new customer(0, username, password, 0);
                
                if (ad.login(username, password)){
                    ad.main(args);
                }else{
                    if (cs.login(username,password)) {
                        cs.menu();
                    }else{
                        System.out.println("\nusername dan password tidak ditemukan, coba lagi");
                    }
                }
            }else if(pilih.equals("2")){
                
                if (signUp.main(args)){
                    continue;
                }else{
                    continue;
                }
                
            
            }
            else if(pilih.equals("0")){
                System.exit(0);
            }
        
            
        }
        
        
        
        
    }
}