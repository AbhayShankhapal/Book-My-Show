import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class TheatreSelector extends JFrame implements ActionListener {
    private Connection conn;
    private JComboBox<String> stateComboBox, cityComboBox, theatreComboBox;
    private JButton saveButton,nextButton, silverButton, goldButton, platinumButton;
    private StringBuilder seat = new StringBuilder();
    String formno;
    int rows = 6, cols = 10;
    int numSeats =0;
    private JButton[][] seats;
    String movieName;
    String seatNumber;
    String category;
    public TheatreSelector(String form_no) {
        this.formno = form_no;
        setLayout(null);
        setBounds(0,0,1960,900);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Image/et00304730-anyygmypht-portrait.png"));
        Image i2 = i1.getImage().getScaledInstance(200,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(120,150,i3.getIconWidth(), i3.getIconHeight());
        movieName = "Fighter";
        label.putClientProperty("movieName", movieName);
        label.addMouseListener(new MovieSelection());
        add(label);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Image/et00357020-kkxcbbuqmk-portrait.png"));
        Image i5 = i4.getImage().getScaledInstance(200,400,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel label1 = new JLabel(i6);
        label1.setBounds(340,150,i6.getIconWidth(), i6.getIconHeight());
        movieName = "Valliban";
        label1.putClientProperty("movieName", movieName);
        label1.addMouseListener(new MovieSelection());
        add(label1);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("Image/et00359069-pgbblujnce-portrait.png"));
        Image i8 = i7.getImage().getScaledInstance(200,400,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel label2 = new JLabel(i9);
        label2.setBounds(560,150,i3.getIconWidth(), i3.getIconHeight());
        movieName = "Lal Salam";
        label2.putClientProperty("movieName", movieName);
        label2.addMouseListener(new MovieSelection());
        add(label2);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("Image/et00376031-blcwrdjbah-portrait.png"));
        Image i11 = i10.getImage().getScaledInstance(200,400,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel label3 = new JLabel(i12);
        label3.setBounds(780,150,i3.getIconWidth(), i3.getIconHeight());
        movieName = "Police";
        label3.putClientProperty("movieName", movieName);
        label3.addMouseListener(new MovieSelection());
        add(label3);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("Image/et00379479-dzdubkdgum-portrait.png"));
        Image i14 = i13.getImage().getScaledInstance(200,400,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel label4 = new JLabel(i15);
        label4.setBounds(1000,150,i3.getIconWidth(), i3.getIconHeight());
        movieName = "Premalu";
        label4.putClientProperty("movieName", movieName);
        label4.addMouseListener(new MovieSelection());
        add(label4);

        ImageIcon i16 = new ImageIcon(ClassLoader.getSystemResource("Image/et00382570-mqvbaumqqf-portrait.png"));
        Image i17 = i16.getImage().getScaledInstance(200,400,Image.SCALE_DEFAULT);
        ImageIcon i18 = new ImageIcon(i17);
        JLabel label6 = new JLabel(i18);
        label6.setBounds(1220,150,i3.getIconWidth(), i3.getIconHeight());
        movieName = "Ozler";
        label6.putClientProperty("movieName", movieName);
        label6.addMouseListener(new MovieSelection());
        add(label6);

        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management", "root", "Nashik@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stateComboBox = new JComboBox<>();
        cityComboBox = new JComboBox<>();
        theatreComboBox = new JComboBox<>();
        saveButton = new JButton("Save");
        Color lightBlue = new Color(173, 216, 230); // RGB values for light blue
        saveButton.setBackground(lightBlue);

        stateComboBox.addActionListener(this);
        cityComboBox.addActionListener(this);
        saveButton.addActionListener(this);

        setLayout(null);

        JLabel text = new JLabel("Select the THEATRE");

        text.setBounds(550,10,500,40);
        text.setFont(new Font("System", Font.BOLD, 38));
        add(text);

        JLabel state = new JLabel("State : ");
        state.setBounds(440,60,50,30);
        add(state);
        
        stateComboBox.setBounds(500, 60, 150, 30);
        
        JLabel city = new JLabel("City : ");
        city.setBounds(660,60,50,25);
        add(city);
        
        cityComboBox.setBounds(720, 60, 150, 30);
        
        JLabel theatre = new JLabel("Theatre : ");
        theatre.setBounds(880,60,80,30);
        add(theatre);
        
        theatreComboBox.setBounds(970, 60, 150, 30);
        
        saveButton.setBounds(710, 100, 80, 30);

        add(stateComboBox);
        add(cityComboBox);
        add(theatreComboBox);
        add(saveButton);
        JLabel seatLabel = new JLabel("Select the Seat");
        seatLabel.setFont(new Font("System", Font.BOLD, 20));
        seatLabel.setBounds(650,570,300,20);
        add(seatLabel);
        JPanel seatPanel = new JPanel(new GridLayout(rows, cols));
        seats = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = new JButton(""+(i * cols + j + 1));
                seats[i][j].addActionListener(new SeatClickListener());
                seatPanel.add(seats[i][j]);
            }
        }

        seatPanel.setBounds(450,600,500,150);
        
        add(seatPanel);

        nextButton = new JButton("NEXT");
        nextButton.setForeground(Color.BLACK);
        nextButton.setBackground(lightBlue);
        nextButton.setFont(new Font("System", Font.BOLD, 30));
        nextButton.addActionListener(this);
        nextButton.setBounds(1000,650,150,50);
        add(nextButton);

        JLabel category = new JLabel("Select the Category");
        category.setFont(new Font("System",Font.BOLD,20));
        category.setBounds(100,570,250,30);
        add(category);

        silverButton = new JButton("Silver");
        silverButton.setFont(new Font("System", Font.BOLD, 20));
        silverButton.setBackground(Color.LIGHT_GRAY);
        silverButton.addActionListener(new categoryselection());
        silverButton.setBounds(120,610,150,30);
        add(silverButton);

        goldButton = new JButton("Gold");
        goldButton.setFont(new Font("System", Font.BOLD, 20));
        goldButton.setBackground(Color.ORANGE);
        goldButton.addActionListener(new categoryselection());
        goldButton.setBounds(120,650,150,30);
        add(goldButton);

        platinumButton = new JButton("Platinum");
        platinumButton.setFont(new Font("System", Font.BOLD, 20));
        platinumButton.setBackground(Color.WHITE);
        platinumButton.addActionListener(new categoryselection());
        platinumButton.setBounds(120,690,150,30);
        add(platinumButton);



        loadStates();
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private void loadStates() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT state FROM theatres");
            while (rs.next()) {
                stateComboBox.addItem(rs.getString("state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCities(String state) {
        cityComboBox.removeAllItems();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT city FROM theatres WHERE state = ?");
            pstmt.setString(1, state);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cityComboBox.addItem(rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTheatres(String city) {
        theatreComboBox.removeAllItems();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT theatre_name FROM theatres WHERE city = ?");
            pstmt.setString(1, city);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                theatreComboBox.addItem(rs.getString("theatre_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    class categoryselection implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == silverButton){
                category = "Silver";
            }
            else if(ae.getSource() == goldButton){
                category = "Gold";
            }
            else if(ae.getSource() == platinumButton){
                category = "Platinum";
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedState="",selectedCity="",selectedTheatre="",selectedMovie="",selectedSeat="",numberofSeats="";
        
        if (e.getSource() == stateComboBox) {
            selectedState = (String) stateComboBox.getSelectedItem();
            loadCities(selectedState);
        } else if (e.getSource() == cityComboBox) {
            selectedCity = (String) cityComboBox.getSelectedItem();
            loadTheatres(selectedCity);
        } else if(e.getSource() == nextButton){
            selectedState = (String) stateComboBox.getSelectedItem();
            selectedCity = (String) cityComboBox.getSelectedItem();
            selectedTheatre = (String) theatreComboBox.getSelectedItem();
            selectedMovie = movieName; // Get the selected movie name
            selectedSeat = seat.toString(); // Get the selected seat
            numberofSeats = ""+numSeats; // Set the number of seats (1 for now, adjust as needed)
            saveToNewTable(selectedState, selectedCity, selectedTheatre, selectedMovie, selectedSeat, numberofSeats);
        }
    }
    public void saveToNewTable(String selectedState, String selectedCity, String selectedTheatre, String selectedMovie, String selectedSeat, String numberofSeats){
        Date date = new Date();
        String date1 = date.toString();
        if(selectedState != null && selectedCity != null && selectedTheatre != null && selectedMovie != null && selectedSeat != null && numberofSeats != null){
            try{
                Conn conn = new Conn();
                String query = "insert into MovieSelection values('"+formno+"','"+date+"','"+selectedState+"','"+selectedCity+"','"+selectedTheatre+"','"+selectedMovie+"','"+category+"','"+selectedSeat+"','"+numberofSeats+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Data Saved");
                setVisible(false);
                new FoodSelector(formno, date1).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
    }
    private class SeatClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedSeat = (JButton) e.getSource();
            
            seatNumber = clickedSeat.getText();
            if(clickedSeat.getBackground().equals(Color.GREEN)){
                clickedSeat.setBackground(null); // Reset background color
                numSeats = numSeats-1;
            } else {
            // Seat is not selected, so select it
                clickedSeat.setBackground(Color.GREEN); // Change background color to green
                seat.append(seatNumber).append(" ");
                numSeats =  numSeats+1;
            }
            
        }
    }

    private class MovieSelection implements MouseListener{
        public void mouseClicked(MouseEvent ae){
            JLabel clickedLabel = (JLabel)ae.getSource();
            movieName = (String) clickedLabel.getClientProperty("movieName");
            System.out.println("Clicked on movie: " + movieName);
        }
        public void mousePressed(MouseEvent e) {
            // Handle mouse press event
        }
    
        public void mouseReleased(MouseEvent e) {
            // Handle mouse release event
        }
    
        public void mouseEntered(MouseEvent e) {
            // Handle mouse enter event
        }
    
        public void mouseExited(MouseEvent e) {
            // Handle mouse exit event
        }
    }

    public static void main(String[] args) {
        new TheatreSelector("");
    }
}
