package InvoiceProgram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InvoiceWindow extends JFrame {

    JLabel jlCompanyName;
    JLabel jlCompanyAddress;

    JLabel jlDate;

    JLabel jlItemName;
    JLabel jlClientName;
    JLabel jlClientAddress;
    JLabel jlItemLabel;

    JTextField jtItemName;
    JTextField jtCompanyName;
    JTextField jtCompanyAddress;
    JTextField jtDate;

    JTextField jtClientName;
    JTextField jtClientAddress;

    String sCompanyName;
    String sCompanyAddress;

    String sClientName;
    String sClientAddress;

    String sDate;

    JPanel jpMain1;
    JPanel jpMainInfo;
    JPanel jpButtons;

    JButton jbSubmit;
    JButton jbAddItem;
    JButton jbDeleteItem;
    JButton jbCloseButton;

    public JTabbedPane jtpMainTabbedPane;

    int iCurrentJPanel;
    int iNumOfItems;

    ArrayList<JPanel> alJPanel;

    JLabel jlQty;
    JLabel jlDescription;
    JLabel jlUnitPrice;

    JTextField jtQty;
    JTextField jtDescription;
    JTextField jtUnitPrice;

    JPanel jpTabTitle;

    EmptyBorder bLabelBorder;



    public InvoiceWindow() {
        super("Invoice Program");
        setSize(500, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponents();
        addActions();
        setVisible(true);

    }

    public void addComponents() {

        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){

        }

        //----------------------------------------
        //Initialize all the arrays, ints, borders
        //----------------------------------------

        alJPanel = new ArrayList<>();
        iCurrentJPanel = -1;
        iNumOfItems = 0;
        bLabelBorder = new EmptyBorder(10, 10, 10, 10);

        //----------------------------------------
        //Initialize all the JLabels
        //----------------------------------------

        jlCompanyName = new JLabel("Company Name: ");
        jlCompanyAddress = new JLabel("Company Address: ");
        jlDate = new JLabel("Date: ");
        jlItemLabel = new JLabel("Item");
        jlClientName = new JLabel("Client Name: ");
        jlClientAddress = new JLabel("Client Address: ");

        //----------------------------------------
        //Initialize al JTextFields
        //----------------------------------------
        jtCompanyName = new JTextField();
        jtCompanyAddress = new JTextField();
        jtDate = new JTextField();
        jtClientName = new JTextField();
        jtClientAddress = new JTextField();

        //----------------------------------------
        //Initialize al JButtons
        //----------------------------------------
        jbSubmit = new JButton("Submit");
        jbAddItem = new JButton("Add Item");
        jbDeleteItem = new JButton("Delete Item");
        jbCloseButton = new JButton("X");

        //----------------------------------------
        //Initialize al JPanels
        //----------------------------------------
        jpMain1 = new JPanel(new GridLayout()); //Just a place holder for the JPanels so it is in the centre
        jpMainInfo = new JPanel(new GridLayout(0, 2));  //Will have JLabels and JTextFields for Company and Client info
        jpButtons = new JPanel(new GridLayout(1, 2));   //Hold the submit button

        jtpMainTabbedPane = new JTabbedPane();

        //----------------------------------------
        //Add all the borders
        //----------------------------------------
        jlCompanyName.setBorder(bLabelBorder);
        jlCompanyAddress.setBorder(bLabelBorder);
        jlDate.setBorder(bLabelBorder);
        jlClientAddress.setBorder(bLabelBorder);
        jlClientName.setBorder(bLabelBorder);


        //-------------------------------------------
        //Add everything to the JPanels
        //-------------------------------------------

        //Add jpMain1 to the center of the JFrame
        add(jpMain1, BorderLayout.CENTER);

        //Add components to jpMainInfo
        jpMainInfo.add(jlClientName);
        jpMainInfo.add(jtClientName);

        jpMainInfo.add(jlClientAddress);
        jpMainInfo.add(jtClientAddress);

        jpMainInfo.add(jlDate);
        jpMainInfo.add(jtDate);

        //Add jpMainInfo to jpMain1
        jpMain1.add(jpMainInfo);

        //Add submit button to jpButtons
        jpButtons.add(jbSubmit);
        jpButtons.add(jbAddItem);

        //Add jpButtons to jpMain1
        //Set Minimum size of the button panel
        add(jpButtons, BorderLayout.SOUTH);

        //-------------------------------------------
        //Customize the Components
        //-------------------------------------------

        jtpMainTabbedPane.add("Client and Company Information", jpMainInfo);
        jpMain1.add(jtpMainTabbedPane);

    }

    public void addActions() {


        jbSubmit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sCompanyName = jtCompanyName.getText();
                        sCompanyAddress = jtCompanyAddress.getText();

                        sClientName = jtClientName.getText();
                        sClientAddress = jtClientAddress.getText();

                        System.out.println("Company Name: " + sCompanyName);
                        System.out.println("Company Address: " + sCompanyAddress);
                        System.out.println("Client Name: " + sClientName);
                        System.out.println("Client Name: " + sClientAddress);
                        System.out.println();
                    }
                });

        jbAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iCurrentJPanel++;
                iNumOfItems++;
                createItemPanel();
            }
        });

    }

    public void createItemPanel(){
        JPanel jpItemPanel = new JPanel(new GridLayout(0, 2));
        JPanel jpTabTitle = new JPanel();

        JLabel jlItemName = new JLabel("Item Name: ");
        JLabel jlItemQuantity = new JLabel("Item Quantity: ");
        JLabel jlItemPrice = new JLabel("Item Price: ");
        JLabel jlTabTitle = new JLabel("Item ");
        JTextField jtfItemName = new JTextField();
        JTextField jtfItemQuantity = new JTextField();
        JTextField jtfItemPrice = new JTextField();
        JButton jbCloseButton = new JButton("X");

        jbCloseButton.setForeground(Color.RED);
        jbCloseButton.setBorderPainted(false);
        jbCloseButton.setContentAreaFilled(false);
        jbCloseButton.setFocusPainted(false);
        jbCloseButton.setOpaque(false);
        jbCloseButton.setForeground(Color.RED);


        jlItemName.setBorder(bLabelBorder);
        jlItemQuantity.setBorder(bLabelBorder);
        jlItemPrice.setBorder(bLabelBorder);

        jpItemPanel.add(jlItemName);
        jpItemPanel.add(jtfItemName);
        jpItemPanel.add(jlItemQuantity);
        jpItemPanel.add(jtfItemQuantity);
        jpItemPanel.add(jlItemPrice);
        jpItemPanel.add(jtfItemPrice);
        jpTabTitle.add(jlTabTitle);
        jpTabTitle.add(jbCloseButton);

        jtpMainTabbedPane.addTab("",jpItemPanel);
        jtpMainTabbedPane.setTabComponentAt(jtpMainTabbedPane.getTabCount() - 1, jpTabTitle);

        alJPanel.add(jpItemPanel);

        jbCloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jtpMainTabbedPane.indexOfTabComponent(jpTabTitle);
                if(i != -1){
                    jtpMainTabbedPane.remove(i);
                    alJPanel.remove(alJPanel.size()-1);
                }
            }
        });

    }

}