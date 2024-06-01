
import user.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.IOException;

public class signUp {
    static String username, password, confirmPassword;

    public static boolean main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        customer cs;
        Console c = System.console();

        System.out.print("Masukkan username baru > ");
        username = br.readLine();

        char[] pass = c.readPassword("Masukkan password > ");
        password = new String(pass);

        char[] pass1 = c.readPassword("Masukkan confirm password > ");
        confirmPassword = new String(pass1);

        cs = new customer(0, "", "", 0);

        if (password.equals(confirmPassword)) {
            if (cs.signUp(username, password)) {
                System.out.println("Sign Up Berhasil");
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("password dan confirm password harus sama");
            return false;
        }

    }

}
