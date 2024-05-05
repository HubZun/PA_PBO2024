package user;

public class customer extends user {
    private String username,password;
    private int id,saldo;
    
    public customer(int id, String username, String password) {
        super(id, username, password);
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public void login(){}

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

    public void managemenAtributEkios(){}

    public void melihatRiwayatTransaksi(){}

    public int getSaldo(){
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
