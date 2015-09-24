import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InvoiceWindow extends JFrame{

    JLabel jlbCompanyDescription;
    JLabel jlbClientDescription;
    JLabel jlbInvoiceNumber;
    JLabel jlbInvoiceDate;
    JLabel jlbQuantity;
    JLabel jlbDescriptionItem;
    JLabel jlbPrice;

    JTextArea jtxtCompanyDescription;
    JTextArea jtxtClientDescription;

    JTextField jtfInvoiceNumber;
    JTextField jtfInvoiceDate;
    JTextField jtfQuantity;
    JTextField jtfDescriptionItem;
    JTextField jtfPrice;

    JButton jbSubmit;

    String sCompanyDescription;
    String sClientDescription;

    JPanel jpMain;
    JPanel jpMain1;

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
        Font font = new Font("Serif", Font.BOLD, 25);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        jlbCompanyDescription = new JLabel("From: ");
        jlbClientDescription = new JLabel("Bill To: ");
        jlbInvoiceNumber = new JLabel("Invoice Number: ");
        jlbInvoiceDate = new JLabel("Invoice Date: ");
        jlbQuantity = new JLabel("Quantity: ");
        jlbDescriptionItem = new JLabel("Description: ");
        jlbPrice = new JLabel("Price: ");

        jlbInvoiceDate.setFont(font);
        jlbCompanyDescription.setFont(font);
        jlbClientDescription.setFont(font);
        jlbInvoiceNumber.setFont(font);
        jlbInvoiceDate.setFont(font);
        jlbQuantity.setFont(font);
        jlbDescriptionItem.setFont(font);
        jlbPrice.setFont(font);

        jtfQuantity = new JTextField();
        jtfDescriptionItem = new JTextField();
        jtfPrice = new JTextField();

        jtxtCompanyDescription = new JTextArea();
        jtxtClientDescription = new JTextArea();

        jtfInvoiceNumber = new JTextField();
        jtfInvoiceDate = new JTextField();

        jbSubmit = new JButton("Submit");

        jpMain = new JPanel(new GridLayout(0, 2));
        jpMain1 = new JPanel(new GridLayout(0, 2));

        jtfInvoiceNumber.setBorder(blackBorder);
        jtxtCompanyDescription.setBorder(blackBorder);
        jtxtClientDescription.setBorder(blackBorder);
        jtfInvoiceDate.setBorder(blackBorder);
        jtfPrice.setBorder(blackBorder);
        jtfDescriptionItem.setBorder(blackBorder);
        jtfQuantity.setBorder(blackBorder);

        jpMain.add(jlbCompanyDescription);
        jpMain.add(jtxtCompanyDescription);

        jpMain.add(jlbClientDescription);
        jpMain.add(jtxtClientDescription);

        jpMain.add(jlbInvoiceNumber);
        jpMain.add(jtfInvoiceNumber);

        jpMain.add(jlbInvoiceDate);
        jpMain.add(jtfInvoiceDate);

        jpMain1.add(jlbQuantity);
        jpMain1.add(jtfQuantity);
        jpMain1.add(jlbDescriptionItem);
        jpMain1.add(jtfDescriptionItem);
        jpMain1.add(jlbPrice);
        jpMain1.add(jtfPrice);
        jpMain1.add(jbSubmit);

        add(jpMain, BorderLayout.CENTER);
        add(jpMain1, BorderLayout.SOUTH);
    }

    public void addActions(){

    }


}