import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class loginForm implements ActionListener{
    private HashMap<String, String> loginInfo = new HashMap<String, String>();
    private JTextField username_TextField;
    private JPasswordField password_PasswordField;
    private JLabel message;
    private JPanel form_panel;

    public loginForm(HashMap<String, String> loginInfo) {
        this.loginInfo = loginInfo;

        JFrame frame = new JFrame("Movie list App");
        String iconPath = "assets\\film.png";
        Image icon = new ImageIcon(loginForm.class.getResource(iconPath)).getImage();
        frame.setIconImage(icon);
        frame.setLayout(new BorderLayout());
        // elements
        loginScreen(frame);
        // close
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void loginScreen(JFrame frame) {
        JPanel welcome_panel = new JPanel();
        JLabel welcome_message = new JLabel("Welcome");
        welcome_message.setFont(new Font(null, Font.BOLD, 25));
        welcome_panel.add(welcome_message);
        frame.add(welcome_panel, BorderLayout.NORTH);

        form_panel = new JPanel();
        form_panel.setBackground(Color.lightGray);
        form_panel.setLayout(new BoxLayout(form_panel, BoxLayout.Y_AXIS));
        form_panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel username_label = new JLabel("Username:");
        username_TextField = new JTextField();
        username_TextField.setPreferredSize(new Dimension(50, 30));

        JLabel password_label = new JLabel("Password:");
        password_PasswordField = new JPasswordField();
        password_PasswordField.setPreferredSize(new Dimension(200, 30));

        JButton forgot_password_Button = new JButton("Forgot Password");
        forgot_password_Button.setPreferredSize(new Dimension(200, 30));
        forgot_password_Button.setFocusable(false);
        forgot_password_Button.addActionListener(this);

        JPanel submit_panel = new JPanel();
        submit_panel.setAlignmentX(0);
        submit_panel.setBackground(Color.LIGHT_GRAY);
        JButton submit_Button = new JButton("Submit");
        submit_Button.setFocusable(false);
        submit_Button.addActionListener(this);
        JButton signUp_Button = new JButton("Sign up");
        signUp_Button.setFocusable(false);
        signUp_Button.addActionListener(this);
        
        message = new JLabel("");
        message.setForeground(Color.YELLOW);

        submit_panel.add(submit_Button);
        submit_panel.add(signUp_Button);
        submit_panel.add(message);

        form_panel.add(username_label);
        form_panel.add(username_TextField);
        form_panel.add(password_label);
        form_panel.add(password_PasswordField);
        form_panel.add(forgot_password_Button);
        form_panel.add(submit_panel);

        JPanel container = new JPanel(new GridBagLayout());
        container.add(form_panel);

        frame.add(container, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Submit")) {
            String username = username_TextField.getText();
            String password = new String(password_PasswordField.getPassword());

            if (loginInfo.containsKey(username) && loginInfo.get(username).equals(password)) {
                message.setText("Login successful!");
            } else {
                message.setText("Sorry, something is wrong");
            }
        } else if (command.equals("Forgot Password")) {
            form_panel.removeAll();
            form_panel.revalidate();
            form_panel.repaint();
        } else if (command.equals("Sign up")) {
            form_panel.removeAll();
            form_panel.revalidate();
            form_panel.repaint();
        }
    }
}
