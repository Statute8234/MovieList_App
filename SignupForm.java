import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupForm implements ActionListener{
    private JPanel panel;
    private JLabel message;
    private LoginForm loginForm;

    public SignupForm (JPanel panel, LoginForm loginForm) {
        this.panel = panel;
        this.loginForm = loginForm;
    }

    public void addContent() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel fullName_Label = new JLabel("Full Name: ");
        JTextField fullName_TextField = new JTextField();
        JLabel username_label = new JLabel("Username: ");
        JTextField username_TextField = new JTextField();
        JLabel email_label = new JLabel("Email: ");
        JTextField email_TextField = new JTextField();
        JLabel password_label = new JLabel("Password: ");
        JPasswordField password_TextField = new JPasswordField();

        JButton signupPassword = new JButton("Sign up");
        signupPassword.setPreferredSize(new Dimension(200, 30));
        signupPassword.setFocusable(false);
        signupPassword.addActionListener(this);

        JPanel submit_panel = new JPanel();
        submit_panel.setAlignmentX(0);
        submit_panel.setBackground(Color.LIGHT_GRAY);
        JButton back_Button = new JButton("Back");
        back_Button.setFocusable(false);
        back_Button.addActionListener(this);

        message = new JLabel("");
        message.setForeground(Color.YELLOW);

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
        panel.add(signupPassword);
        panel.add(submit_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Sign up")) {
            message.setText("Welcome");
        } else if (command.equals("Back")) {
            loginForm.loginScreen();
        }
    }
}
