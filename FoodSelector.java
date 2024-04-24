import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodSelector extends JFrame implements ActionListener{
    private JPanel foodPanel;
    private Map<String, ImageIcon> foodImages;
    private Map<String, ImageIcon> nonvegfoodImages;
    String formno;
    String category;
    StringBuilder food = new StringBuilder();
    JButton vegButton, nonvegButton, NextButton;
    String date;
    public FoodSelector(String formno,String date){
        this.formno = formno;
        this.date = date;
        setLayout(null);
        setTitle("Food Selection");
        setSize(1960, 900);
        setBounds(0, 0, 1960, 900);

        JLabel text = new JLabel("Select the Food");
        text.setFont(new Font("System", Font.BOLD, 30));
        text.setBounds(650, 10, 300, 50);
        add(text);

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font("System", Font.BOLD, 25));
        categoryLabel.setBounds(700, 80, 300, 30);
        add(categoryLabel);

        vegButton = new JButton("VEG");
        vegButton.setFont(new Font("System", Font.BOLD, 25));
        vegButton.setBounds(500, 130, 120, 40);
        vegButton.setBackground(Color.GREEN);
        vegButton.addActionListener(this);
        add(vegButton);

        nonvegButton = new JButton("NON-VEG");
        nonvegButton.setFont(new Font("System", Font.BOLD, 25));
        nonvegButton.setBackground(Color.RED);
        nonvegButton.setBounds(690, 130, 150, 40);
        nonvegButton.addActionListener(this);
        add(nonvegButton);

        NextButton = new JButton("NEXT");
        NextButton.setFont(new Font("System", Font.BOLD, 25));
        NextButton.setBackground(new Color(173, 216, 230));
        NextButton.setBounds(900, 130, 120, 40);
        NextButton.addActionListener(this);
        add(NextButton);

        initializeFoodImages();

        // Create grid panel for food items
        foodPanel = new JPanel(new GridLayout(0, 5));
        foodPanel.setBounds(0, 180, 1960, 600); // Adjusted bounds for grid panel
        add(foodPanel);

        setVisible(true);
        getContentPane().setBackground(new Color(255, 255, 240));
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == vegButton){
            foodPanel.removeAll();
            category = "Veg";
            // Display food items and images
            for (Map.Entry<String, ImageIcon> entry : foodImages.entrySet()) {
                JLabel foodLabel = new JLabel(entry.getKey(), entry.getValue(), SwingConstants.CENTER);
                foodLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                foodLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                foodLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        actionPerformed(new ActionEvent(foodLabel, ActionEvent.ACTION_PERFORMED, ""));
                    }
                });
                foodPanel.add(foodLabel);
            }

            // Refresh the panel to reflect changes
            foodPanel.revalidate();
            foodPanel.repaint();
        }
        else if(ae.getSource() == nonvegButton){
            foodPanel.removeAll();
            category = "Non-Veg";
            for(Map.Entry<String, ImageIcon> entry : nonvegfoodImages.entrySet()){
                JLabel foodLabel = new JLabel(entry.getKey(), entry.getValue(), SwingConstants.CENTER);
                foodLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                foodLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                foodLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        actionPerformed(new ActionEvent(foodLabel, ActionEvent.ACTION_PERFORMED, ""));
                    }
                });
                foodPanel.add(foodLabel);
            }
            foodPanel.revalidate();
            foodPanel.repaint();
        }
        else if(ae.getSource() == NextButton){
            savetoDatabase(food.toString());
        }else if (ae.getSource() instanceof JLabel) {
            JLabel clickedLabel = (JLabel) ae.getSource();
            String foodName = clickedLabel.getText();
            food = food.append(foodName+", ");
            System.out.println(foodName);
        }
    }

    private void initializeFoodImages() {
        foodImages = new HashMap<>();
        nonvegfoodImages = new HashMap<>();
        ImageIcon pizzaIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/pizza.jpg"));
        ImageIcon burgerIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/pasta.jpg"));
        ImageIcon saladIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/salad.jpg"));
        ImageIcon pastaIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/burger.jpg"));
        ImageIcon sushiIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/sushi.jpg"));
        ImageIcon cheesecakeIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/cheesecake.jpg"));
        ImageIcon chocolatebrownieIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/chocolatebrownie.jpg"));
        ImageIcon icecreamIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Veg/icecream.jpg"));

        Image pizzaImage = pizzaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image burgerImage = burgerIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image saladImage = saladIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image pastaImage = pastaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image sushiImage = sushiIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image cheesecakeImage = cheesecakeIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image chocolatebrownImage = chocolatebrownieIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image icecreamImage = icecreamIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

    // Create ImageIcon objects with scaled images
        ImageIcon scaledPizzaIcon = new ImageIcon(pizzaImage);
        ImageIcon scaledBurgerIcon = new ImageIcon(burgerImage);
        ImageIcon scaledSaladIcon = new ImageIcon(saladImage);
        ImageIcon scaledPastaIcon = new ImageIcon(pastaImage);
        ImageIcon scaledSushiIcon = new ImageIcon(sushiImage);
        ImageIcon scaledcheesecakeIcon = new ImageIcon(cheesecakeImage);
        ImageIcon scaledchocolatebrownieIcon = new ImageIcon(chocolatebrownImage);
        ImageIcon scaledicecreamIcon = new ImageIcon(icecreamImage);

    // Put scaled ImageIcon objects into the foodImages map
        foodImages.put("Pizza", scaledPizzaIcon);
        foodImages.put("Burger", scaledBurgerIcon);
        foodImages.put("Salad", scaledSaladIcon);
        foodImages.put("Pasta", scaledPastaIcon);
        foodImages.put("Sushi", scaledSushiIcon);
        foodImages.put("Cheese Cake", scaledcheesecakeIcon);
        foodImages.put("Chocolate Brownie", scaledchocolatebrownieIcon);
        foodImages.put("Icrecream", scaledicecreamIcon);

        
        ImageIcon chickencaesarwrapIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Nonveg/chickencaesarwrap.jpg"));
        ImageIcon chickennuggetsIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Nonveg/chickennuggets.jpg"));
        ImageIcon ChickenQuesadillaIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Nonveg/ChickenQuesadilla.jpg"));
        ImageIcon chickenwingsIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Nonveg/chickenwings.jpg"));
        ImageIcon fishandchipsIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Nonveg/fishandchips.jpg"));
        ImageIcon pepperonipizzaIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Nonveg/pepperonipizza.jpg"));
        ImageIcon shrimptacosIcon = new ImageIcon(ClassLoader.getSystemResource("Image/Nonveg/shrimptacos.jpg"));
        

        Image chickencaesarwrapImage = chickencaesarwrapIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image chickennuggetsImage = chickennuggetsIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image ChickenQuesadillaImage = ChickenQuesadillaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image chickenwingsImage = chickenwingsIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image fishandchipsImage = fishandchipsIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image pepperonipizzaImage = pepperonipizzaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image shrimptacosImage = shrimptacosIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

    // Create ImageIcon objects with scaled images
        ImageIcon scaledchickencaesarwrapIcon = new ImageIcon(chickencaesarwrapImage);
        ImageIcon scaledchickennuggetsrgerIcon = new ImageIcon(chickennuggetsImage);
        ImageIcon scaledChickenQuesadillaIcon = new ImageIcon(ChickenQuesadillaImage);
        ImageIcon scaledchickenwingsIcon = new ImageIcon(chickenwingsImage);
        ImageIcon scaledfishandchipsIcon = new ImageIcon(fishandchipsImage);
        ImageIcon scaledpepperonipizzaeIcon = new ImageIcon(pepperonipizzaImage);
        ImageIcon scaledshrimptacosIcon = new ImageIcon(shrimptacosImage);

    // Put scaled ImageIcon objects into the foodImages map
        nonvegfoodImages.put("chicken caesar wrap", scaledchickencaesarwrapIcon);
        nonvegfoodImages.put("Chicken Nuggets", scaledchickennuggetsrgerIcon);
        nonvegfoodImages.put("Chicken Quesadilla", scaledChickenQuesadillaIcon);
        nonvegfoodImages.put("Chicken Wings", scaledchickenwingsIcon);
        nonvegfoodImages.put("Fish and Chips", scaledfishandchipsIcon);
        nonvegfoodImages.put("Pepperoni Pizza", scaledpepperonipizzaeIcon);
        nonvegfoodImages.put("Shrimp Tacos", scaledshrimptacosIcon);
        
    }

    private void savetoDatabase(String foods){
        if(category == null || foods == null){
            try{
                Conn conn = new Conn();
                String query = "insert into foodselector values('"+formno+"','"+date+"','0','0')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "No food selected");
                setVisible(false);
                new Payment(formno,date).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            try{
                Conn conn = new Conn();
                String query = "insert into foodselector values('"+formno+"','"+date+"','"+category+"','"+foods+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Data Saved");
                setVisible(false);
                new Payment(formno,date).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
        }
       
    }

    public static void main(String[] args) {
        new FoodSelector("","");
    }
}
