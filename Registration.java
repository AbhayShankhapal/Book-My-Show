import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Registration extends JFrame implements ActionListener{
    long random;
    JButton signinButton, signupButton;
    JTextField nameTextField, surnameTextField, emailTextField, phoneTextField, ageTextField,passwordTextfield;
    JRadioButton male, female, other;
    ButtonGroup gendergroup;

    Registration(){
        setTitle("Book My Show/Registration");
        setLayout(null);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel text = new JLabel("Application Form");
        text.setBounds(260, 10, 350, 40);
        text.setFont(new Font("System", Font.BOLD, 38));
        add(text);

        JLabel formno = new JLabel("Form No. " + random);
        formno.setBounds(350, 70, 300, 30);
        formno.setFont(new Font("System", Font.BOLD, 20));
        add(formno);

        JLabel name = new JLabel("Name : ");
        name.setBounds(150,120,100,30);
        name.setFont(new Font("System", Font.BOLD, 20));
        add(name);

        nameTextField = new JTextField();
        nameTextField.setBounds(300,120,400,30);
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        add(nameTextField);

        JLabel surname = new JLabel("Surname : ");
        surname.setBounds(150,160,150,30);
        surname.setFont(new Font("System", Font.BOLD, 20));
        add(surname);

        surnameTextField = new JTextField();
        surnameTextField.setBounds(300,160,400,30);
        surnameTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        add(surnameTextField);

        JLabel Emailid = new JLabel("Email : ");
        Emailid.setBounds(150,200,100,30);
        Emailid.setFont(new Font("System", Font.BOLD, 20));
        add(Emailid);

        emailTextField = new JTextField();
        emailTextField.setBounds(300,200,400,30);
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        add(emailTextField);

        JLabel phonenumber = new JLabel("Phone : ");
        phonenumber.setBounds(150,240,200,30);
        phonenumber.setFont(new Font("System", Font.BOLD, 20));
        add(phonenumber);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(300,240,400,30);
        phoneTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        add(phoneTextField);

        JLabel Gender = new JLabel("Gender : ");
        Gender.setBounds(150,280,200,30);
        Gender.setFont(new Font("System", Font.BOLD, 20));
        add(Gender);

        male = new JRadioButton("Male");
        male.setBounds(300,280,80,30);
        male.setFont(new Font("System", Font.BOLD, 20));
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(390,280,100,30);
        female.setFont(new Font("System", Font.BOLD, 20));
        female.setBackground(Color.WHITE);
        add(female);

        other = new JRadioButton("Other");
        other.setBounds(500,280,100,30);
        other.setFont(new Font("System", Font.BOLD, 20));
        other.setBackground(Color.WHITE);
        add(other);

        gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(other);
        gendergroup.add(female);

        JLabel age = new JLabel("Age : ");
        age.setBounds(150,320,100,30);
        age.setFont(new Font("System", Font.BOLD, 20));
        add(age);

        ageTextField = new JTextField();
        ageTextField.setBounds(300,320,400,30);
        ageTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        add(ageTextField);

        JLabel password = new JLabel("Password : ");
        password.setBounds(150,360,150,30);
        password.setFont(new Font("System", Font.BOLD, 20));
        add(password);

        passwordTextfield = new JTextField();
        passwordTextfield.setBounds(300,360,400,30);
        passwordTextfield.setFont(new Font("Raleway", Font.BOLD, 20));
        add(passwordTextfield);

        signupButton = new JButton("SIGNUP");
        signupButton.setBounds(250,400,150,30);
        signupButton.setFont(new Font("System",Font.BOLD,20));
        signupButton.addActionListener(this);
        add(signupButton);

        signinButton = new JButton("LOGIN");
        signinButton.setBounds(490,400,150,30);
        signinButton.setFont(new Font("System",Font.BOLD,20));
        signinButton.addActionListener(this);
        add(signinButton);




        setSize(900,500);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setLocation(350, 100);

    }

    public void actionPerformed(ActionEvent ae){
            String form_no = ""+random;
            String name = nameTextField.getText();
            String surname = surnameTextField.getText();
            String emailid = emailTextField.getText();
            
            String phoneno = phoneTextField.getText();
            String Gender = null;
            if(male.isSelected()){
                Gender = "Male";
            }else if(female.isSelected()){
                Gender = "Female";
            }else if(other.isSelected()){
                Gender = "Other";
            }
            String age = ageTextField.getText();
            String Password = passwordTextfield.getText();
        if(ae.getSource() == signinButton){
            setVisible(false);
            new Login("").setVisible(true);
        }
        if(ae.getSource() == signupButton){
            if(name != null && surname != null && emailid != null && phoneno != null && Gender != null && age!= null && Password != null){
                try{
                    Conn conn = new Conn();
                    String query = "INSERT INTO registration VALUES('"+form_no+"','"+name+"','"+surname+"','"+emailid+"','"+phoneno+"','"+Gender+"','"+age+"', '"+Password+"')";
                    String query1 = "INSERT INTO login VALUES('"+form_no+"','"+emailid+"','"+Password+"')";
                    conn.s.executeUpdate(query);
                    conn.s.executeUpdate(query1);
                    JOptionPane.showMessageDialog(null, "Data Input done");
                    setVisible(false);
                    new Login(form_no).setVisible(true);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        new Registration();
    }
}
