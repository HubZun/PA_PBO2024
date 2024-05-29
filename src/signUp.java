
import user.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class signUp {
    static String username, password;
    
    public static boolean main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        customer cs;
        
        System.out.print("Masukkan username baru > ");
        username = br.readLine();
        
        System.out.print("Masukkan password baru > ");
        password = br.readLine();
        
        cs = new customer(0, "", "", 0);
        
        if (cs.signUp(username,password)){
            System.out.println("registrasi berhasil");
            return true;
        }else{
            System.out.println("gagal");
            return false;
        }
        
    }
    
}
