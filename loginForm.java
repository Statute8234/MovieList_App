import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        frame.setIconImage(new ImageIcon(LoginForm.class.getResource("assets\\film.png")).getImage());
        frame.setLayout(new BorderLayout());
        // elements
        form_panel = new JPanel();
        forgotPasswordForm = new ForgotPasswordForm(form_panel, this, authorization);
        signupForm = new SignupForm(form_panel, this, authorization);
        appScreen(frame);
        // close
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void appScreen(JFrame frame) {
        JPanel welcome_panel = new JPanel();
        welcome_panel.setBackground(Color.WHITE);
        welcome_panel.add(new JLabel("Login", SwingConstants.CENTER) {{
            setFont(new Font("Arial", Font.BOLD, 25));
        }});
        frame.add(welcome_panel, BorderLayout.NORTH);
        // Add form panel to the container
        JPanel container = new JPanel(new GridBagLayout());
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.setBackground(Color.WHITE);
        form_panel.setBackground(Color.LIGHT_GRAY);
        form_panel.setLayout(new GridBagLayout());
        form_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        container.add(form_panel);
        frame.add(container, BorderLayout.CENTER);
        // Initialize login screen
        loginScreen();
    }

    public void loginScreen() {
        form_panel.removeAll();
        form_panel.revalidate();
        form_panel.repaint();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel username_label = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        form_panel.add(username_label, gbc);

        username_TextField = new JTextField(15);
        gbc.gridx = 1;
        form_panel.add(username_TextField, gbc);

        JLabel password_label = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        form_panel.add(password_label, gbc);

        password_PasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        form_panel.add(password_PasswordField, gbc);

        JButton forgot_password_Button = new JButton("Forgot Password");
        forgot_password_Button.setFocusable(false);
        forgot_password_Button.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        form_panel.add(forgot_password_Button, gbc);

        JPanel submit_panel = new JPanel();
        submit_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        submit_panel.setBackground(Color.LIGHT_GRAY);

        JButton submit_Button = new JButton("Submit");
        submit_Button.setFocusable(false);
        submit_Button.addActionListener(this);
        submit_panel.add(submit_Button);

        JButton signUp_Button = new JButton("Sign up");
        signUp_Button.setFocusable(false);
        signUp_Button.addActionListener(this);
        submit_panel.add(signUp_Button);

        message = new JLabel("");
        message.setForeground(Color.YELLOW);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        form_panel.add(submit_panel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        form_panel.add(message, gbc);
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

        message.setText(authorization.verifyUser(username, password) ? "Successful" : "Sorry, something has gone wrong");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm(new Authorization()));
    }
}
