package user;

interface iUser{
    public default void login(){};
    public int getId();
    public String getPassword();
    public String getUsername();
}


public abstract class user implements iUser{
    protected int id;
    protected String username,password;

    public user(int id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password){
        return true;
    }
    
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
}