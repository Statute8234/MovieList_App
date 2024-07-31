import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm implements ActionListener {
    private JTextField username_TextField;
    private JPasswordField password_PasswordField;
    private JLabel message;
    public JPanel form_panel;
    public JFrame frame;
    private ForgotPasswordForm forgotPasswordForm;
    private SignupForm signupForm;
    private Authorization authorization;
    private HomePage homePage;

    public LoginForm(Authorization authorization) {
        this.authorization = authorization;

        frame = new JFrame("Movie list App");
        String iconPath = "assets\\film.png";
        Image icon = new ImageIcon(LoginForm.class.getResource(iconPath)).getImage();
        frame.setIconImage(icon);
        frame.setLayout(new BorderLayout());

        form_panel = new JPanel();
        forgotPasswordForm = new ForgotPasswordForm(form_panel, this, authorization);
        signupForm = new SignupForm(form_panel, this, authorization);
        homePage = new HomePage(form_panel);

        appScreen(frame);
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

        JPanel container = new JPanel(new GridBagLayout());
        container.add(form_panel);
        frame.add(container, BorderLayout.CENTER);

        loginScreen();
    }

    public void loginScreen() {
        form_panel.removeAll();
        form_panel.revalidate();
        form_panel.repaint();

        form_panel.setBackground(Color.LIGHT_GRAY);
        form_panel.setLayout(new GridBagLayout());
        form_panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 1;
        formPanel.add(title, gbc);

        JLabel[] labels = {new JLabel("Username:"), new JLabel("Password:")};
        JTextField[] fields = {username_TextField = new JTextField(15), password_PasswordField = new JPasswordField(15)};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            formPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        JButton forgot_password_Button = new JButton("Forgot Password");
        forgot_password_Button.setFocusable(false);
        forgot_password_Button.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = labels.length + 1;
        gbc.gridwidth = 2;
        formPanel.add(forgot_password_Button, gbc);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitPanel.setBackground(Color.WHITE);
        String[] buttonLabels = {"Submit", "Sign up"};
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

        form_panel.add(formPanel);
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
            homePage.addContent();
        } else {
            message.setText("Invalid username or password");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm(new Authorization()));
    }
}
