package pl.coderslab.model;

import pl.coderslab.util.PasswordUtil;

public class User {

    private int id;
    private String userName;
    private String email;
    private String password;
    private int goupId;

    //Konstruktor do pobierania użytkowników z bazydanych
    public User(){}

    //Konstruktor dla tworzenia nowego użytkownika
    public User(String userName, String email, String password, int goupId) {
        this.userName = userName;
        this.email = email;
        this.password = pl.coderslab.util.PasswordUtil.createHash(password); //
        this.goupId = goupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGoupId() {
        return goupId;
    }

    public void setGoupId(int goupId) {
        this.goupId = goupId;
    }

    @Override
    public String toString() {
        return "\n" + "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", goupId=" + goupId +
                '}';
    }
}
