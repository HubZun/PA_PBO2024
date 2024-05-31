import user.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class login {
    public static void main(String[] args) throws IOException, Exception {
        
    InputStreamReader ir = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(ir);
        
        String pilih, username, password;
        admin ad;
        customer cs;
        
        while (true){
            System.out.println("======= Login Page ========");
            System.out.println("| 1. login                |");
            System.out.println("| 2. sign up              |");
            System.out.println("| 3. exit                 |");
            System.out.println("===========================");
            System.out.print("| silahkan pilih menu >> ");
            pilih = br.readLine();
            
            if (pilih.equals("1")){
                System.out.print("| masukkan username >> ");
                username = br.readLine();
                System.out.print("| masukkan password >> ");
                password = br.readLine();
                
                ad = new admin(0,username,password);
                cs = new customer(0, username, password, 0);
                
                if (ad.login(username, password)){
                    ad.main(args);
                }else{
                    if (cs.login(username,password)) {
                        cs.menu();
                    }else{
                        System.out.println("gagal");
                    }
                }
            }else if(pilih.equals("2")){
                signUp su = new signUp();
                
                if (su.main(args)){
                    continue;
                }else{
                    System.out.println("gagal masuk ke menu customer");
                }
                
            
            }
            else if(pilih.equals("3")){
                return;
            }
        
            
        }
        
        
        
        
    }
}