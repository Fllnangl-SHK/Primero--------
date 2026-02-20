
class User {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private boolean isAdmin;

    public User(String username, String password, String fullName, String email, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String pw) {
        return password.equals(pw);
    }
    
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
