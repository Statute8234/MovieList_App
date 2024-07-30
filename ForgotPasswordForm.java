import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordForm implements ActionListener{
    private JPanel panel;
    private JLabel message;
    private LoginForm loginForm;
    private JTextField email_TextField;
    private Authorization authorization;

    public ForgotPasswordForm (JPanel panel, LoginForm loginForm, Authorization authorization) {
        this.panel = panel;
        this.loginForm = loginForm;
        this.authorization = authorization;
        email_TextField = new JTextField();
    }

    public void addContent() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JLabel email_label = new JLabel("Email: ");
        email_TextField.setPreferredSize(new Dimension(200, 30));

        JButton restPassword = new JButton("Rest Password");
        restPassword.setPreferredSize(new Dimension(200, 30));
        restPassword.setFocusable(false);
        restPassword.addActionListener(this);

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

        panel.add(email_label);
        panel.add(email_TextField);
        panel.add(restPassword);
        panel.add(submit_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Rest Password")) {
            String email = email_TextField.getText().trim();
            if (email.isEmpty()) {
                message.setText("Please enter yuor email address");
            } else if (authorization.emailExists(email)) {
                message.setText("Password reset instructions sent to your email.");
            } else {
                message.setText("Invalid email");
            }
        } else if (command.equals("Back")) {
            loginForm.loginScreen();
        }
    }
}   
