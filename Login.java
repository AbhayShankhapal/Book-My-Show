import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
public class Login extends JFrame implements ActionListener{
    JTextField emailidtTextField,passwordTextfield;
    JButton loginButton, registrationButton;
    String form_no;
    Login(String form_no){
        this.form_no = form_no;
        setLayout(null);
        setTitle("Book My Show/Login");
        setSize(800,480);
        setVisible(true);
        setLocation(350,200);
        

        JLabel text = new JLabel("Welcome to Book My Show");
        text.setBounds(200,10,600,50);
        text.setFont(new Font("Raleway", Font.BOLD, 30));
        add(text);

        JLabel login = new JLabel("Login");
        login.setBounds(350,100,200,50);
        login.setFont(new Font("Raleway", Font.BOLD, 40));
        add(login);

        JLabel email = new JLabel("Email-id : ");
        email.setBounds(250,170,100,30);
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        add(email);

        emailidtTextField = new JTextField();
        emailidtTextField.setBounds(400, 170,200,30);
        emailidtTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(emailidtTextField);

        JLabel password = new JLabel("Password :");
        password.setBounds(250,230,150,30);
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        add(password);

        passwordTextfield = new JTextField();
        passwordTextfield.setBounds(400, 230,200,30);
        passwordTextfield.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordTextfield);

        loginButton = new JButton("Login");
        loginButton.setBounds(250,290,100,30);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        add(loginButton);

        registrationButton = new JButton("Registration");
        registrationButton.setBounds(480,290,120,30);
        registrationButton.setBackground(Color.BLACK);
        registrationButton.setForeground(Color.WHITE);
        registrationButton.addActionListener(this);
        add(registrationButton);

        getContentPane().setBackground(Color.WHITE);

    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            Conn conn = new Conn();
            String email = emailidtTextField.getText();
            String password = passwordTextfield.getText();
            String form_no = "";
            String query1 = "SELECT * FROM login WHERE Email_id = ? AND password = ?";
            try (Connection connection = conn.c;
                 PreparedStatement pstmt = connection.prepareStatement(query1)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    form_no = rs.getString("form_no");
                    JOptionPane.showMessageDialog(null, "Login successful");
                    setVisible(false);
                    new TheatreSelector(form_no).setVisible(true);
                    // Depending on your application flow, you might want to open a new window or perform other actions here
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please check your email and password.");
                }
            } catch (SQLException e) {
                // Provide feedback to the user or log the error
                JOptionPane.showMessageDialog(null, "An error occurred while attempting to login.");
                System.out.println(e); // Log the exception for debugging purposes
            }
        } else if (ae.getSource() == registrationButton) {
            setVisible(false);
            new Registration().setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        new Login("");
    }
}