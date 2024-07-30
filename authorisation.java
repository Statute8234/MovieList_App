import java.util.HashMap;

public class Authorization {
    private HashMap<String, User> userInfo;
    
    Authorization() {
        userInfo = new HashMap<>();
        userInfo.put("Username", new User("Username", "Password", "email", "Full Name"));
    }

    protected HashMap getLoginInfo() {
        return userInfo;
    }

    public boolean verifyUser(String username, String password) {
        User user = userInfo.get(username);
        return user != null && user.getPassword().equals(password);
    }


    public void addUser(String username, String password, String email, String fullName) {
        userInfo.put(username, new User(username, password, email, fullName));
    }

    public boolean userExists(String username) {
        return userInfo.containsKey(username);
    }

    public boolean emailExists(String email) {
        for (User user : userInfo.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public class User {
        private String username;
        private String password;
        private String email;
        private String fullName;
    
        public User(String username, String password, String email, String fullName) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.fullName = fullName;
        }
    
        public String getPassword() {
            return password;
        }
    
        public String getEmail() {
            return email;
        }
    
        public String getFullName() {
            return fullName;
        }
    }
}
