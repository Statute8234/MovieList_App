import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class SignupForm implements ActionListener{
    private JPanel panel;
    private JLabel message;
    private JTextField fullName_TextField;
    private JTextField username_TextField;
    private JTextField email_TextField;
    private JPasswordField password_TextField;
    private LoginForm loginForm;
    private Authorization authorization;

    public SignupForm (JPanel panel, LoginForm loginForm, Authorization authorization) {
        this.panel = panel;
        this.loginForm = loginForm;
        this.authorization = authorization;
    }

    public void addContent() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel fullName_Label = new JLabel("Full Name: ");
        fullName_TextField = new JTextField();
        JLabel username_label = new JLabel("Username: ");
        username_TextField = new JTextField();
        JLabel email_label = new JLabel("Email: ");
        email_TextField = new JTextField();
        JLabel password_label = new JLabel("Password: ");
        password_TextField = new JPasswordField();

        JButton generatePassword_button = new JButton("Generate Password");
        generatePassword_button.setPreferredSize(new Dimension(200, 30));
        generatePassword_button.setFocusable(false);
        generatePassword_button.addActionListener(this);

        JPanel submit_panel = new JPanel();
        submit_panel.setAlignmentX(0);
        submit_panel.setBackground(Color.LIGHT_GRAY);
        
        JButton signUp_Button = new JButton("Sign up");
        signUp_Button.setFocusable(false);
        signUp_Button.addActionListener(this);

        JButton back_Button = new JButton("Back");
        back_Button.setFocusable(false);
        back_Button.addActionListener(this);

        message = new JLabel("");
        message.setForeground(Color.YELLOW);

        submit_panel.add(signUp_Button);
        submit_panel.add(back_Button);
        submit_panel.add(message);

        panel.add(fullName_Label);
        panel.add(fullName_TextField);
        panel.add(username_label);
        panel.add(username_TextField);
        panel.add(email_label);
        panel.add(email_TextField);
        panel.add(password_label);
        panel.add(password_TextField);
        panel.add(generatePassword_button);
        panel.add(submit_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Sign up")) {
            handleSignup();
        } else if (command.equals("Generate Password")) {
            int passwordLength = 12;
            PasswordGenerator randomPassword = new PasswordGenerator();
            String rnd = randomPassword.generateRandomPassword(12);
            password_TextField.setText(rnd);
            message.setText(rnd);
        } else if (command.equals("Back")) {
            loginForm.loginScreen();
        }
    }

    private void handleSignup() {
        String fullName = fullName_TextField.getText().trim();
        String username = username_TextField.getText().trim();
        String email = email_TextField.getText().trim();
        String password = new String(password_TextField.getPassword()).trim();

        // Simple validation checks
        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            message.setText("All fields are required.");
        } else if (!email.contains("@")) {
            message.setText("Please enter a valid email address.");
        } else if (password.length() < 12) {
            message.setText("Password must be at least 12s characters long.");
        } else if (authorization.userExists(username)) {
            message.setText("Invalid username");
        } else {
            // successful signup
            authorization.addUser(username, password, email, fullName);
            message.setText("Welcome, " + fullName + "!");
        }
    }

    private class PasswordGenerator {
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        private static final SecureRandom random = new SecureRandom();

        public static String generateRandomPassword(int length) {
            if (length <= 0) {
                throw new IllegalArgumentException("Password length must be greater than zero");
            }

            StringBuilder password = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(CHARACTERS.length());
                password.append(CHARACTERS.charAt(index));
            }

            return password.toString();
        }
    }
}
