import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginForm implements ActionListener{
    private JTextField username_TextField;
    private JPasswordField password_PasswordField;
    private JLabel message;
    public JPanel form_panel;
    public JFrame frame;
    private ForgotPasswordForm forgotPasswordForm;
    private SignupForm signupForm;
    private Authorization authorization;

    public LoginForm(Authorization authorisation) {
        this.authorization = new Authorization();

        frame = new JFrame("Movie list App");
        String iconPath = "assets\\film.png";
        Image icon = new ImageIcon(LoginForm.class.getResource(iconPath)).getImage();
        frame.setIconImage(icon);
        frame.setLayout(new BorderLayout());
        // elements
        form_panel = new JPanel();
        forgotPasswordForm = new ForgotPasswordForm(form_panel, this, authorization);
        signupForm = new SignupForm(form_panel, this, authorization);
        appScreen(frame);
        // close
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void appScreen(JFrame frame) {
        JPanel welcome_panel = new JPanel();
        JLabel welcome_message = new JLabel("Welcome");
        welcome_message.setFont(new Font("Arial", Font.BOLD, 25));
        welcome_panel.add(welcome_message);
        frame.add(welcome_panel, BorderLayout.NORTH);
        // Add form panel to the container
        JPanel container = new JPanel(new GridBagLayout());
        container.add(form_panel);
        frame.add(container, BorderLayout.CENTER);
        // Initialize login screen
        loginScreen();
    }

    public void loginScreen() {
        form_panel.removeAll();
        form_panel.revalidate();
        form_panel.repaint();

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Submit")) {
            handleLogin();
        } else if (command.equals("Forgot Password")) {
            forgotPasswordForm.addContent();
        } else if (command.equals("Sign up")) {
            signupForm.addContent();
        }
    }

    private void handleLogin() {
        String username = username_TextField.getText().trim();
        String password = String.valueOf(password_PasswordField.getPassword()).trim();

        if (authorization.verifyUser(username, password)) {
            message.setText("Login successful!");
        } else {
            message.setText("Invalid username or password");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm(new Authorization()));
    }
}
