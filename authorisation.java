import java.util.HashMap;

public class authorisation {
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    authorisation() {
        loginInfo.put("admin", "Wb6co8S5yom@");
        loginInfo.put("robert smoooth man","human very smooth");
    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
