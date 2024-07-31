import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class SignupForm implements ActionListener {
    private JPanel panel;
    private JLabel message;
    private JTextField fullName_TextField, username_TextField, email_TextField;
    private JPasswordField password_PasswordField;
    private LoginForm loginForm;
    private Authorization authorization;

    public SignupForm(JPanel panel, LoginForm loginForm, Authorization authorization) {
        this.panel = panel;
        this.loginForm = loginForm;
        this.authorization = authorization;
    }

    public void addContent() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Sign up");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 1;
        formPanel.add(title, gbc);

        String[] labels = {"Full Name:", "Username:", "Email:", "Password:"};
        JTextField[] fields = {fullName_TextField = new JTextField(15), username_TextField = new JTextField(15), 
                               email_TextField = new JTextField(15), password_PasswordField = new JPasswordField(15)};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        JButton generatePassword_button = new JButton("Generate Password");
        generatePassword_button.setFocusable(false);
        generatePassword_button.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = labels.length + 1;
        gbc.gridwidth = 2;
        formPanel.add(generatePassword_button, gbc);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitPanel.setBackground(Color.WHITE);
        String[] buttonLabels = {"Sign up", "Back"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.addActionListener(this);
            submitPanel.add(button);
        }

        gbc.gridy++;
        gbc.gridx = 1;
        formPanel.add(submitPanel, gbc);

        message = new JLabel("");
        message.setForeground(Color.RED);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(message, gbc);

        panel.add(formPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Sign up")) {
            handleSignup();
        } else if (command.equals("Generate Password")) {
            password_PasswordField.setText(PasswordGenerator.generateRandomPassword(12));
            message.setText(password_PasswordField.getText());
        } else if (command.equals("Back")) {
            loginForm.loginScreen();
        }
    }

    private void handleSignup() {
        String fullName = fullName_TextField.getText().trim();
        String username = username_TextField.getText().trim();
        String email = email_TextField.getText().trim();
        String password = new String(password_PasswordField.getPassword()).trim();

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            message.setText("All fields are required.");
        } else if (!email.contains("@")) {
            message.setText("Please enter a valid email address.");
        } else if (password.length() < 12) {
            message.setText("Password must be at least 12 characters long.");
        } else if (authorization.userExists(username)) {
            message.setText("Username already exists.");
        } else {
            authorization.addUser(username, password, email, fullName);
            message.setText("Welcome, " + fullName + "!");
        }
    }

    private static class PasswordGenerator {
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        private static final SecureRandom random = new SecureRandom();

        public static String generateRandomPassword(int length) {
            StringBuilder password = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return password.toString();
        }
    }
}
