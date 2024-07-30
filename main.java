import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        Authorization authorization_data = new Authorization();
        LoginForm loginPage = new LoginForm(authorization_data.getLoginInfo());
    }
}
