import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Payment extends JFrame{
    private Connection c;
    String form_no;
    JLabel PaymentLabel,Date,Date1,Address,Seats,foodcategory,foodcategory1,Seats1,Address1,formno,formlabel,username,name,moviename,moviename1,categorylabel,categorylabel1,Seatslabel,Seatslabel1,foodLabel,foodlabel1;
    String date;
    public Payment(String form_no,String date){

        System.out.println(form_no + " " + date);
        this.date = date;
        this.form_no = form_no;
        setLayout(null);
        setSize(1960,900);
        setBounds(0,0,1960,900);


        PaymentLabel = new JLabel("Payment");
        PaymentLabel.setFont(new Font("System", Font.BOLD, 30));
        PaymentLabel.setBounds(690,10,150,40);
        add(PaymentLabel);

        formlabel = new JLabel("Form : ");
        formlabel.setFont(new Font("System", Font.BOLD, 20));
        formlabel.setBounds(690,80,70,30);
        add(formlabel);

        formno = new JLabel(form_no);
        formno.setFont(new Font("System", Font.BOLD, 20));
        formno.setBounds(770, 80, 100, 30);
        add(formno);

        username = new JLabel("Name : ");
        username.setFont(new Font("System", Font.BOLD, 20));
        username.setBounds(600,120,80,30);
        add(username);

        name = new JLabel();
        name.setFont(new Font("System",Font.BOLD,20));
        name.setBounds(690, 120, 450, 30);
        add(name);

        moviename = new JLabel("Movie : ");
        moviename.setFont(new Font("System", Font.BOLD, 20));
        moviename.setBounds(500,180,75,30);
        add(moviename);

        moviename1 = new JLabel();
        moviename1.setFont(new Font("System",Font.BOLD,20));
        moviename1.setBounds(575,180,100,30);
        add(moviename1);

        Date = new JLabel("Date : ");
        Date.setFont(new Font("System", Font.BOLD, 20));
        Date.setBounds(500,240,75,30);
        add(Date);

        Date1 = new JLabel(date);
        Date1.setFont(new Font("System",Font.BOLD,20));
        Date1.setBounds(575,240,300,30);
        add(Date1);

        Address = new JLabel("Address : ");
        Address.setFont(new Font("System", Font.BOLD, 20));
        Address.setBounds(500,300,100,30);
        add(Address);

        Address1 = new JLabel();
        Address1.setFont(new Font("System",Font.BOLD,20));
        Address1.setBounds(600,300,300,30);
        add(Address1);

        categorylabel = new JLabel("Category : ");
        categorylabel.setFont(new Font("System", Font.BOLD, 20));
        categorylabel.setBounds(500,360,110,30);
        add(categorylabel);

        categorylabel1 = new JLabel();
        categorylabel1.setFont(new Font("System",Font.BOLD,20));
        categorylabel1.setBounds(610,360,100,30);
        add(categorylabel1);

        Seats = new JLabel("Seats : ");
        Seats.setFont(new Font("System", Font.BOLD, 20));
        Seats.setBounds(500,420,70,30);
        add(Seats);

        Seats1 = new JLabel();
        Seats1.setFont(new Font("System",Font.BOLD,20));
        Seats1.setBounds(570,420,100,30);
        add(Seats1);

        Seatslabel = new JLabel("No. of Seats : ");
        Seatslabel.setFont(new Font("System", Font.BOLD, 20));
        Seatslabel.setBounds(500,480,150,30);
        add(Seatslabel);

        Seatslabel1 = new JLabel();
        Seatslabel1.setFont(new Font("System", Font.BOLD, 20));
        Seatslabel1.setBounds(650,480,50,30);
        add(Seatslabel1);

        foodcategory = new JLabel("Food category : ");
        foodcategory.setFont(new Font("System", Font.BOLD, 20));
        foodcategory.setBounds(500,540,160,30);
        add(foodcategory);

        foodcategory1 = new JLabel();
        foodcategory1.setFont(new Font("System", Font.BOLD, 20));
        foodcategory1.setBounds(660,540,200,30);
        add(foodcategory1);

        foodLabel = new JLabel("Food : ");
        foodLabel.setFont(new Font("System", Font.BOLD, 20));
        foodLabel.setBounds(500,600,70,30);
        add(foodLabel);

        foodlabel1 = new JLabel();
        foodlabel1.setFont(new Font("System", Font.BOLD, 20));
        foodlabel1.setBounds(570,600,300,30);
        add(foodlabel1);

        paymentdetails();

        setVisible(true);
    }

    private void paymentdetails() {
        
        //String query1 = "SELECT * FROM movieselection WHERE form_no = '" + form_no + "' AND Date = '" + date + "'";
        //String query2 = "SELECT * FROM foodselector WHERE formno = '" + form_no + "' AND Date = '" + date + "'";
        
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management", "root", "Nashik@123");
            // Statement s = c.createStatement();
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM movieselection WHERE form_no = ? AND Date = ?");
            pstmt.setString(1, form_no);
            pstmt.setString(2, date);
            
            PreparedStatement pstmt1 = c.prepareStatement("SELECT * FROM foodselector WHERE formno = ? AND Date = ?");
            pstmt1.setString(1, form_no);
            pstmt1.setString(2, date);
            ResultSet r1 = pstmt1.executeQuery();
            ResultSet r = pstmt.executeQuery();

            PreparedStatement pstmt2 = c.prepareStatement("SELECT * FROM registration WHERE form_no = ?");
            pstmt2.setString(1, form_no);
            ResultSet r2 = pstmt2.executeQuery();


            
            // Check if both result sets have data
            if(r.next()) {
                // Access data from the result sets
                String movieName = r.getString("Movie");
                String state = r.getString("State");
                String city = r.getString("City");
                String theatre = r.getString("Theatre");
                String address = state + ", " + city + ", " + theatre;
                String cat = r.getString("movie_category");
                String seat = r.getString("Seats");
                String noseat = r.getString("no_of_seats");
                
                
                // Set the retrieved data to the JLabels
                moviename1.setText(movieName);
                Address1.setText(address);
                Date1.setText(date);
                categorylabel1.setText(cat);
                Seats1.setText(seat);
                Seatslabel1.setText(noseat);
                
            }
            if(r1.next()){
                String fcat = r1.getString("Category");
                String food = r1.getString("Food_item");
                foodcategory1.setText(fcat);
                foodlabel1.setText(food);
            }
            if(r2.next()){
                String name1 = (r2.getString("Name"));
                String surname = (r2.getString("Surname"));
                StringBuilder fullname = new StringBuilder();
                fullname = fullname.append(name1+" ");
                fullname = fullname.append(surname);
                String fullname1 = fullname.toString();
                name.setText(fullname1);

            }
        } catch (Exception e) {
            // Handle SQL exceptions
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving payment details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void main(String[] args) {
        new Payment("","");
    }
}
