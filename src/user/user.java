package user;


public abstract class user{
    protected int id;
    protected String username,password;

    public user(int id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void login(){}
    public void logout(){}
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