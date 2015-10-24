package InvoiceProgram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

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

    public JTabbedPane jtbMainTabbedPane;

    int iCurrentJPanel;
    int iNumOfItems;

    ArrayList<JPanel[]> alJPanel;
    JPanel[] aryPanelArray;

    JLabel jlQty;
    JLabel jlDescription;
    JLabel jlUnitPrice;

    JTextField jtQty;
    JTextField jtDescription;
    JTextField jtUnitPrice;

    EmptyBorder bLabelBorder;

    JPanel x;


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
        aryPanelArray = new JPanel[4];
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
        x = new JPanel();

        jtbMainTabbedPane = new JTabbedPane();

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

        x.add(jlItemLabel);
        x.add(jbCloseButton);
        x.add(jlItemLabel);
        x.add(jbCloseButton);

        //Add jpButtons to jpMain1
        //Set Minimum size of the button panel
        add(jpButtons, BorderLayout.SOUTH);

        //-------------------------------------------
        //Customize the Components
        //-------------------------------------------

        jtbMainTabbedPane.add("Client and Company Information", jpMainInfo);
        jpMain1.add(jtbMainTabbedPane);
        jbCloseButton.setBorderPainted(false);
        jbCloseButton.setContentAreaFilled(false);
        jbCloseButton.setFocusPainted(false);
        jbCloseButton.setOpaque(false);
        jbCloseButton.setForeground(Color.RED);

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
                createItemPanel();
            }
        });

    }

    /**
     * Creates an item panel
     * It also adds it to the arraylist holding
     * All textfields and stuff
     */
    public void createItemPanel() {
        iNumOfItems++;
        JPanel jpTemporary = createItemComponents();
        aryPanelArray = new JPanel[100000];

        alJPanel.add(aryPanelArray);

        aryPanelArray[iCurrentJPanel] = jpTemporary;
        alJPanel.add(aryPanelArray);

        createCloseableTabbedPane(jpTemporary);

        revalidate();
    }


    /**
     * Creates all JTextFields and JLabels
     * For the item
     * Then adds a border with the item number on it
     * Also adds to array
     * Returns a JPanel
     */
    public JPanel createItemComponents() {

        System.out.println("Creating Components for Item");

        JLabel jlItemName = new JLabel("Item Name: ");
        JLabel jlItemQuantity = new JLabel("Item Quantity: ");
        JLabel jlItemPrice = new JLabel("Item Price: ");

        JTextField jtfItemName = new JTextField();
        JTextField jtfItemQuantity = new JTextField();
        JTextField jtfItemPrice = new JTextField();

        System.out.println("Adding Borders");

        jlItemName.setBorder(bLabelBorder);
        jlItemQuantity.setBorder(bLabelBorder);

        jlItemPrice.setBorder(bLabelBorder);

        System.out.println("Creating temporary JPanel");
        JPanel jpTemporary = new JPanel(new GridLayout(0, 2));

        jpTemporary.add(jlItemName);
        jpTemporary.add(jtfItemName);

        jpTemporary.add(jlItemQuantity);
        jpTemporary.add(jtfItemQuantity);

        jpTemporary.add(jlItemPrice);
        jpTemporary.add(jtfItemPrice);

        System.out.println("Adding Temporary JPanel to array aryPanelArray");
        aryPanelArray[iCurrentJPanel] = jpTemporary;


        return jpTemporary;
    }

    /**
     * Removes the latest item panel
     * Also removes from the array
     */
    public void removeItemPanel(JPanel x) {
        int index = jtbMainTabbedPane.indexOfTabComponent(x);
        jtbMainTabbedPane.remove(index);
    }

    public void createCloseableTabbedPane(JPanel jpTemporary){

        jtbMainTabbedPane.addTab("Item", jpTemporary);
        jtbMainTabbedPane.setTabComponentAt(iCurrentJPanel+1, x);

        jpMain1.add(jtbMainTabbedPane);
        jbCloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iCurrentJPanel--;
                int index = jtbMainTabbedPane.indexOfTabComponent(x);
                jtbMainTabbedPane.remove(index);
            }
        });

    }


}