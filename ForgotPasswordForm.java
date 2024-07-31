import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ForgotPasswordForm implements ActionListener {
    private JPanel panel;
    private JLabel message;
    private JTextField username_TextField, email_TextField;
    private LoginForm loginForm;
    private Authorization authorization;

    public ForgotPasswordForm(JPanel panel, LoginForm loginForm, Authorization authorization) {
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

        JLabel title = new JLabel("Forgot Password");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 1;
        formPanel.add(title, gbc);

        JLabel[] labels = {new JLabel("Username:"), new JLabel("Email:")};
        JTextField[] fields = {username_TextField = new JTextField(15), email_TextField = new JTextField(15)};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            formPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitPanel.setBackground(Color.WHITE);
        String[] buttonLabels = {"Submit", "Back"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.addActionListener(this);
            submitPanel.add(button);
        }

        gbc.gridy++;
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
        if (command.equals("Submit")) {
            handleForgotPassword();
        } else if (command.equals("Back")) {
            loginForm.loginScreen();
        }
    }

    private void handleForgotPassword() {
        String username = username_TextField.getText().trim();
        String email = email_TextField.getText().trim();

        if (username.isEmpty() || email.isEmpty()) {
            message.setText("All fields are required.");
        } else if (!email.contains("@")) {
            message.setText("Please enter a valid email address.");
        } else if (!authorization.userExists(username)) {
            message.setText("Username does not exist.");
        } else {
            // Handle forgot password logic
            message.setText("Password reset link sent to " + email + ".");
        }
    }
}
