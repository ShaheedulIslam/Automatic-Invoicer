import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InvoiceWindow extends JFrame{

    JLabel jlCompanyName;
    JLabel jlCompanyAddress;

    JLabel jlDate;

    JLabel jlItemName;
    JLabel jlClientName;
    JLabel jlClientAddress;

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

    JPanel jpMain;
    JPanel jpButtons;

    String[] columnNames;
    Object[][] data;

    JButton jbSubmit;
    JButton jbAddItem;
    JButton jbDeleteItem;

    int iNumOfItems;

    ArrayList<JTextField[]> aljtxtFields;
    ArrayList<JLabel[]> alLabels;

    JLabel jlArray[];
    JTextField jtfArray[];

    JLabel jlQty;
    JLabel jlDescription;
    JLabel jlUnitPrice;

    JTextField jtQty;
    JTextField jtDescription;
    JTextField jtUnitPrice;


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

        int iNumOfItems = 0;

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
        jbDeleteItem = new JButton("Delete Item");

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
        jpMain.add(jbDeleteItem);

        jpButtons.add(jbSubmit);

        add(jpMain, BorderLayout.NORTH);
        add(jpButtons, BorderLayout.SOUTH);
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

                System.out.println("Adding Item");

                jpMain.remove(jbAddItem);
                jpMain.remove(jbDeleteItem);
                revalidate();

                iNumOfItems++;
                System.out.println();
                System.out.println("Number of Items: " + iNumOfItems);

                jlItemName = new JLabel("Name: ");
                jlQty = new JLabel("Quantity: ");
                jlDescription = new JLabel("Description: ");
                jlUnitPrice = new JLabel("Unit Price: ");

                jtItemName = new JTextField();
                jtQty = new JTextField();
                jtDescription = new JTextField();
                jtUnitPrice = new JTextField();

                JTextField jtfArray[] = new JTextField[4];
                jtfArray[0] = jtItemName;
                jtfArray[1] = jtQty;
                jtfArray[2] = jtDescription;
                jtfArray[3] = jtUnitPrice;

                JLabel jlArray[] = new JLabel[4];
                jlArray[0] = jlItemName;
                jlArray[1] = jlQty;
                jlArray[2] = jlDescription;
                jlArray[3] = jlUnitPrice;

                aljtxtFields = new ArrayList<JTextField[]>();
                alLabels = new ArrayList<JLabel[]>();

                aljtxtFields.add(jtfArray);
                alLabels.add(jlArray);

                jpMain.add(jlItemName);
                jpMain.add(jtItemName);

                jpMain.add(jlQty);
                jpMain.add(jtQty);


                jpMain.add(jlDescription);
                jpMain.add(jtDescription);

                jpMain.add(jlUnitPrice);
                jpMain.add(jtUnitPrice);

                jpMain.add(jbAddItem);
                jpMain.add(jbDeleteItem);

                jlItemName.setBorder(new EmptyBorder(10, 10, 10, 10));
                jlQty.setBorder(new EmptyBorder(10, 10, 10, 10));
                jlDescription.setBorder(new EmptyBorder(10, 10, 10, 10));

                jlUnitPrice.setBorder(new EmptyBorder(10, 10, 10, 10));

                jpMain.add(jbAddItem);
                jpMain.add(jbDeleteItem);

                revalidate();

            }
        });

        jbDeleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(iNumOfItems <= 0)){
                    System.out.println("Deleting Item");
                    iNumOfItems--;

                    System.out.println();
                    System.out.println("Number of Items: " + iNumOfItems);

                    jpMain.remove(jbAddItem);
                    jpMain.remove(jbDeleteItem);

                    jpMain.remove(jlItemName);
                    jpMain.remove(jlUnitPrice);
                    jpMain.remove(jlDescription);
                    jpMain.remove(jlQty);

                    jpMain.remove(jtItemName);
                    jpMain.remove(jtUnitPrice);
                    jpMain.remove(jtDescription);
                    jpMain.remove(jtQty);

                    jpMain.add(jbAddItem);
                    jpMain.add(jbDeleteItem);
                    revalidate();

                    try {
                        aljtxtFields.remove(aljtxtFields.size() - 1);
                        alLabels.remove(alLabels.size() - 1);
                    } catch (Exception e1) {
                        /*JTextField jtfArray[] = new JTextField[4];
                        JLabel jlArray[] = new JLabel[4];

                        jlArray[0] = jlItemName;
                        jlArray[1] = jlQty;
                        jlArray[2] = jlDescription;
                        jlArray[3] = jlUnitPrice;

                        jtfArray[0] = jtItemName;
                        jtfArray[1] = jtQty;
                        jtfArray[2] = jtDescription;
                        jtfArray[3] = jtUnitPrice;

                        aljtxtFields.add(jtfArray);
                        alLabels.add(jlArray);

                        aljtxtFields.remove(aljtxtFields.size() - 1);
                        alLabels.remove(alLabels.size() - 1);*/
                    }

                    System.out.println();

                    System.out.println();
                    System.out.println("Text Field Array Size: " + aljtxtFields.size());
                    System.out.println("Labels Array Size: " + aljtxtFields.size());
                }else{
                    System.out.println("NO ALLOWED TO DELETE ITEM.");
                }

            }
        });


    }
}