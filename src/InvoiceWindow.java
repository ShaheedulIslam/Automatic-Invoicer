import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceWindow extends JFrame{

    JLabel jlbCompanyDescription;
    JLabel jlbClientDescription;

    JTextArea jtxtCompanyDescription;
    JTextArea jtxtClientDescription;

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
        jlbCompanyDescription = new JLabel("From: ");
        jlbClientDescription = new JLabel("Bill To: ");

        jtxtCompanyDescription = new JTextArea();
        jtxtClientDescription = new JTextArea();

        jtxtCompanyDescription.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        jtxtClientDescription.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        jpMain = new JPanel(new GridLayout(2, 2));

        jpMain.add(jlbCompanyDescription);
        jpMain.add(jtxtCompanyDescription);

        jpMain.add(jlbClientDescription);
        jpMain.add(jtxtClientDescription);

        add(jpMain, BorderLayout.CENTER);


    }

    public void addActions(){

    }


}