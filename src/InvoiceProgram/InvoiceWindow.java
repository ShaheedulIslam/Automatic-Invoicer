package InvoiceProgram;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.io.*;
import java.util.*;

public class InvoiceWindow extends JFrame {

    JLabel jlCompanyName;
    JLabel jlCompanyAddress;
    JLabel jlDate;
    JLabel jlItemName;
    JLabel jlClientName;
    JLabel jlClientAddress;
    JLabel jlQty;
    JLabel jlDescription;
    JLabel jlUnitPrice;
    JLabel jlItemLabel;
    JLabel jlSellerName;

    JTextField jtItemName;
    JTextField jtCompanyName;
    JTextField jtCompanyAddress;
    JTextField jtDate;
    JTextField jtClientName;
    JTextField jtQty;
    JTextField jtDescription;
    JTextField jtUnitPrice;
    JTextField jtClientAddress;
    JTextField jtSellerName;

    String sCompanyName;
    String sCompanyAddress;
    String sClientName;
    String sClientAddress;
    String sDate;
    String sSellerName;

    JPanel jpMain1;
    JPanel jpMainInfo;
    JPanel jpTabTitle;
    JPanel jpTabContent;
    JPanel jpButtons;

    JButton jbSubmit;
    JButton jbAddItem;
    JButton jbDeleteItem;
    JButton jbCloseButton;

    JTabbedPane jtpMainTabbedPane;

    int iNumOfItems;

    ArrayList<JTextField[]> alJPanel;
    JTextField[] jTextFieldArray;
    String[] itemInfo;
    Document document;
    EmptyBorder bLabelBorder;

    public InvoiceWindow() {
        super("Invoice Program");
        setSize(500, 300);
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
        jlSellerName = new JLabel("Seller Name");

        //----------------------------------------
        //Initialize al JTextFields
        //----------------------------------------
        jtCompanyName = new JTextField();
        jtCompanyAddress = new JTextField();
        jtDate = new JTextField();
        jtClientName = new JTextField();
        jtClientAddress = new JTextField();
        jtSellerName = new JTextField();

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
        jlSellerName.setBorder(bLabelBorder);


        //-------------------------------------------
        //Add everything to the JPanels
        //-------------------------------------------

        //Add jpMain1 to the center of the JFrame
        add(jpMain1, BorderLayout.CENTER);

        //Add components to jpMainInfo
        jpMainInfo.add(jlCompanyName);
        jpMainInfo.add(jtCompanyName);

        jpMainInfo.add(jlSellerName);
        jpMainInfo.add(jtSellerName);

        jpMainInfo.add(jlCompanyAddress);
        jpMainInfo.add(jtCompanyAddress);

        jpMainInfo.add(jlClientName);
        jpMainInfo.add(jtClientName);

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
                        sSellerName = jtSellerName.getText();

                        createPDF();
                        createItemTable();

                    }
                });

        jbAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iNumOfItems++;
                createItemPanel();
            }
        });

    }

    public void createItemPanel(){
        iNumOfItems++;

        JPanel jpTabContent = new JPanel(new GridLayout(0, 2));
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

        jpTabContent.add(jlItemName);
        jpTabContent.add(jtfItemName);
        jpTabContent.add(jlItemQuantity);
        jpTabContent.add(jtfItemQuantity);
        jpTabContent.add(jlItemPrice);
        jpTabContent.add(jtfItemPrice);
        jpTabTitle.add(jlTabTitle);
        jpTabTitle.add(jbCloseButton);

        jtpMainTabbedPane.addTab("",jpTabContent);
        jtpMainTabbedPane.setTabComponentAt(jtpMainTabbedPane.getTabCount() - 1, jpTabTitle);

        jTextFieldArray = new JTextField[3];

        jTextFieldArray[0] = jtfItemName;
        jTextFieldArray[1] = jtfItemQuantity;
        jTextFieldArray[2] = jtfItemPrice;

        alJPanel.add(jTextFieldArray);

        jbCloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iNumOfItems--;
                int i = jtpMainTabbedPane.indexOfTabComponent(jpTabTitle);
                if(i != -1){
                    jtpMainTabbedPane.remove(i);
                    alJPanel.remove(alJPanel.size() - 1);
                }
            }
        });

    }

    public void createPDF(){
        try {
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Invoice.pdf"));
            document.open();

            Font titleFont = FontFactory.getFont("Century-Gothic", 30);
            Font normalFont = FontFactory.getFont("Century-Gothic", 14);

            Paragraph pCompanyName = new Paragraph(sCompanyName, titleFont);
            Paragraph pCompanyAddress = new Paragraph(sCompanyAddress, normalFont);
            Paragraph pSellerName = new Paragraph("From " + sSellerName, normalFont);
            Paragraph pClientName = new Paragraph("Invoice For " + sClientName, normalFont);
            Paragraph pClientAddress = new Paragraph(sClientAddress, normalFont);
            Paragraph pDate = new Paragraph(sDate, normalFont);
            Paragraph pInvoiceForMessage = new Paragraph("Invoice For", normalFont);

            pCompanyName.setAlignment(Element.ALIGN_LEFT);
            pSellerName.setAlignment(Element.ALIGN_LEFT);
            pCompanyAddress.setAlignment(Element.ALIGN_LEFT);
            pClientName.setAlignment(Element.ALIGN_RIGHT);
            pClientAddress.setAlignment(Element.ALIGN_RIGHT);
            pDate.setAlignment(Element.ALIGN_RIGHT);
            pInvoiceForMessage.setAlignment(Element.ALIGN_RIGHT);

            document.add(pCompanyName);
            document.add(Chunk.NEWLINE);
            document.add(pClientName);
            document.add(pClientAddress);

            document.add(pSellerName);
            document.add(pCompanyAddress);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createItemTable(){
        try {
            PdfPTable itemTable = new PdfPTable(4);

            PdfPCell nameHeader = new PdfPCell(new Phrase("Description"));
            PdfPCell quantityHeader = new PdfPCell(new Phrase("Quantity"));
            PdfPCell unitPriceHeader = new PdfPCell(new Phrase("Unit Price"));
            PdfPCell amountHeader = new PdfPCell(new Phrase("Amount"));

            itemTable.addCell(nameHeader);
            itemTable.addCell(quantityHeader);
            itemTable.addCell(unitPriceHeader);
            itemTable.addCell(amountHeader);

            System.out.println(Arrays.toString(itemInfo));

            for (int i = 0; i < alJPanel.size(); i++){
                String itemName = alJPanel.get(i)[0].getText();
                Double quantity = Double.parseDouble(alJPanel.get(i)[1].getText());
                Double price = Double.parseDouble(alJPanel.get(i)[2].getText());
                Double amount = price * quantity;

                itemTable.addCell(new PdfPCell(new Phrase(itemName)));  //Name
                itemTable.addCell(new PdfPCell(new Phrase(quantity + "")));  //Quantity
                itemTable.addCell(new PdfPCell(new Phrase(price + " ")));  //Price
                itemTable.addCell(new PdfPCell(new Phrase(amount + " ")));

            }

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.add(itemTable);

            document.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error has occurred");
            e.printStackTrace();
        }
    }


}