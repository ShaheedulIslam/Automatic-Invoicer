import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceWindow extends JFrame{

    JLabel jlCompanyName;
    JLabel jlCompanyAddress;
    JLabel jlCompanyPhoneNumber;

    JLabel jlClientName;
    JLabel jlClientAddress;

    JTextField jtCompanyName;
    JTextField jtCompanyAddress;
    JTextField jtCompanyPhoneNumber;

    JTextField jtClientName;
    JTextField jtClientAddress;

    String sCompanyName;
    String sCompanyAddress;
    String sCompanyPhoneNumber;

    String sClientName;
    String sClientAddress;

    JPanel jpMain;
    JPanel jpButtons;

    String[] columnNames;
    Object[][] data;

    JButton jbClear;
    JButton jbSubmit;

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

        String[] columnNames = {"Item Description", "Quantity", "Price per Item", "Total"};
        Object[][] data = new Object[50][50];

        jlCompanyName = new JLabel("Company Name: ");

        jlCompanyAddress = new JLabel("Company Address: ");
        jlCompanyPhoneNumber = new JLabel("Company Phone Number: ");

        jlClientName = new JLabel("Client Name: ");
        jlClientAddress = new JLabel("Client Address: ");

        jtCompanyName = new JTextField();

        jtCompanyAddress = new JTextField();
        jtCompanyPhoneNumber = new JTextField();

        jtClientName = new JTextField();
        jtClientAddress = new JTextField();

        jlCompanyName.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlCompanyPhoneNumber.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlCompanyAddress.setBorder(new EmptyBorder(10, 10, 10, 10));

        jlClientAddress.setBorder(new EmptyBorder(10, 10, 10, 10));
        jlClientName.setBorder(new EmptyBorder(10, 10, 10, 10));

        jpMain = new JPanel(new GridLayout(6, 2));
        jpButtons = new JPanel(new GridLayout(1, 2));

        jbClear = new JButton("Clear");
        jbSubmit = new JButton("Submit");

        jpMain.add(jlCompanyName);
        jpMain.add(jtCompanyName);

        jpMain.add(jlCompanyAddress);
        jpMain.add(jtCompanyAddress);

        jpMain.add(jlCompanyPhoneNumber);
        jpMain.add(jtCompanyPhoneNumber);

        jpMain.add(jlClientName);
        jpMain.add(jtClientName);

        jpMain.add(jlClientAddress);
        jpMain.add(jtClientAddress);

        jpButtons.add(jbClear);
        jpButtons.add(jbSubmit);

        add(jpMain, BorderLayout.NORTH);
        add(jpButtons, BorderLayout.SOUTH);
    }

    public void addActions(){
        jbClear.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        jtCompanyName.setText("");
                        jtCompanyAddress.setText("");
                        jtCompanyPhoneNumber.setText("");

                        jtClientName.setText("");
                        jtClientAddress.setText("");
                    }
                }
        );

        jbSubmit.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sCompanyName = jtCompanyName.getText();
                sCompanyAddress = jtCompanyAddress.getText();
                sCompanyPhoneNumber = jtCompanyPhoneNumber.getText();

                sClientName = jtClientName.getText();
                sClientAddress = jtClientAddress.getText();
            }
    });
    }


}