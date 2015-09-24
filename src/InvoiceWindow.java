import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceWindow extends JFrame{

    JLabel jlbCompanyDescription;
    JLabel jlbClientDescription;
    JLabel jlbInvoiceNumber;
    JLabel jlbInvoiceDate;

    JTextArea jtxtCompanyDescription;
    JTextArea jtxtClientDescription;

    JTextField jtfInvoiceNumber;
    JTextField jtfInvoiceDate;

    String sCompanyDescription;
    String sClientDescription;

    JPanel jpMain;
    JPanel jpButtons;

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
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        jlbCompanyDescription = new JLabel("From: ");
        jlbClientDescription = new JLabel("Bill To: ");
        jlbInvoiceNumber = new JLabel("Invoice Number: ");
        jlbInvoiceDate = new JLabel("Invoice Date: ");

        jtxtCompanyDescription = new JTextArea();
        jtxtClientDescription = new JTextArea();

        jtfInvoiceNumber = new JTextField();
        jtfInvoiceDate = new JTextField();

        jpMain = new JPanel(new GridLayout(0, 2));

        jtfInvoiceNumber.setBorder(blackBorder);
        jtxtCompanyDescription.setBorder(blackBorder);
        jtxtClientDescription.setBorder(blackBorder);
        jtfInvoiceDate.setBorder(blackBorder);

        jpMain.add(jlbCompanyDescription);
        jpMain.add(jtxtCompanyDescription);

        jpMain.add(jlbClientDescription);
        jpMain.add(jtxtClientDescription);

        jpMain.add(jlbInvoiceNumber);
        jpMain.add(jtfInvoiceNumber);

        jpMain.add(jlbInvoiceDate);
        jpMain.add(jtfInvoiceDate);

        add(jpMain, BorderLayout.CENTER);


    }

    public void addActions(){

    }


}