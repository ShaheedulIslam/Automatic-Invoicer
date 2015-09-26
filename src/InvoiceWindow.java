import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InvoiceWindow extends JFrame{

    JLabel jlCompanyName;
    JLabel jlCompanyAddress;

    JLabel jlDate;

    JLabel jlClientName;
    JLabel jlClientAddress;

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

    JPanel jpMain;
    JPanel jpButtons;

    String[] columnNames;
    Object[][] data;

    JButton jbSubmit;
    JButton jbAddItem;

    ArrayList<JTextField> alQTYTextField;
    ArrayList<JTextField> alDescriptionTextField;
    ArrayList<JTextField> alUnitPriceTextField;
    ArrayList<JLabel> alLabels;

    public InvoiceWindow(){
        super("Invoice Program");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponents();
        addActions();
        setVisible(true);

    }

    public void addComponents(){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
        }

        String[] columnNames = {"Item Description", "Quantity", "Price per Item", "Total"};
        Object[][] data = new Object[50][50];

        jlCompanyName = new JLabel("Company Name: ");
        jlCompanyAddress = new JLabel("Company Address: ");

        jlDate = new JLabel("Date: ");

        jlClientName = new JLabel("Client Name: ");
        jlClientAddress = new JLabel("Client Address: ");

        jtCompanyName = new JTextField();
        jtCompanyAddress = new JTextField();

        jtDate = new JTextField();

        jtClientName = new JTextField();
        jtClientAddress = new JTextField();

        jlCompanyName.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlCompanyAddress.setBorder(new EmptyBorder(10, 10, 10, 10));

        jlDate.setBorder(new EmptyBorder(10, 10, 10, 10));

        jlClientAddress.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlClientName.setBorder(new EmptyBorder(10, 10, 10, 10));

        jpMain = new JPanel(new GridLayout(0, 2));
        jpButtons = new JPanel(new GridLayout(1, 1));

        jbSubmit = new JButton("Submit");
        jbAddItem = new JButton("Add Item");

        jpMain.add(jlCompanyName);
        jpMain.add(jtCompanyName);

        jpMain.add(jlCompanyAddress);
        jpMain.add(jtCompanyAddress);

        jpMain.add(jlClientName);
        jpMain.add(jtClientName);

        jpMain.add(jlClientAddress);
        jpMain.add(jtClientAddress);

        jpMain.add(jlDate);
        jpMain.add(jtDate);

        jpMain.add(jbAddItem);
        jpButtons.add(jbSubmit);

        add(jpMain, BorderLayout.NORTH);
        add(jpButtons, BorderLayout.SOUTH);
    }

    public void addActions(){


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
                jpMain.remove(jbAddItem);
                revalidate();

                alQTYTextField = new ArrayList<JTextField>();
                alDescriptionTextField = new ArrayList<JTextField>();
                alUnitPriceTextField = new ArrayList<JTextField>();
                alLabels = new ArrayList<JLabel>();

                JLabel jlQty = new JLabel("Quantity: ");
                JLabel jlDescription = new JLabel("Description: ");
                JLabel jlUnitPrice = new JLabel("Unit Price: ");

                JTextField jtQty = new JTextField();
                JTextField jtDescription = new JTextField();
                JTextField jtUnitPrice = new JTextField();

                alQTYTextField.add(jtQty);
                alDescriptionTextField.add(jtDescription);
                alUnitPriceTextField.add(jtUnitPrice);

                alLabels.add(jlQty);
                alLabels.add(jlDescription);
                alLabels.add(jlUnitPrice);

                jpMain.add(jlQty);
                jpMain.add(jtQty);

                jpMain.add(jlDescription);
                jpMain.add(jtDescription);

                jpMain.add(jlUnitPrice);
                jpMain.add(jtUnitPrice);

                jpMain.add(jbAddItem);

                jlQty.setBorder(new EmptyBorder(10, 10, 10, 10));
                jlDescription.setBorder(new EmptyBorder(10, 10, 10, 10));

                jlUnitPrice.setBorder(new EmptyBorder(10, 10, 10, 10));
                revalidate();
            }
        });
    }


}