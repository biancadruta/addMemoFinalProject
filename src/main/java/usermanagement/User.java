package usermanagement;

public class User {
    private String email;
    private String pwd;
    private String confirmpwd;
    private int id;


    public User(String email, String pwd, String confirmpwd) {
        this.email = email;
        this.pwd = pwd;
        this.confirmpwd = confirmpwd ;}



    public User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmpwd() {
        return confirmpwd;
    }

    public void setConfirmpwd(String confirmpwd) {
        this.confirmpwd = confirmpwd;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", confirmpwd='" + confirmpwd + '\'' +
                '}';
    }
}


