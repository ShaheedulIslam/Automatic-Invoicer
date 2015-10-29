package InvoiceProgram;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import com.itextpdf.text.Document;
import java.awt.Font;


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
    JButton jbFileChooserButton;

    JTabbedPane jtpMainTabbedPane;

    int iNumOfItems;

    ArrayList<JTextField[]> alJPanel;
    JTextField[] jTextFieldArray;
    String[] itemInfo;
    Document document;
    EmptyBorder bLabelBorder;
    Double totalPrice;
    PdfWriter writer;
    JFileChooser fileChooser;
    Image pdfImage;
    com.itextpdf.text.Font normalFont;
    Font fontForJTF;

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){}

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
        jbFileChooserButton = new JButton("Logo not selected");

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
        jpMainInfo.add(jbFileChooserButton);
        jpMainInfo.add(new JLabel());

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

        fontForJTF = new Font("Arial", Font.PLAIN, 20);

        jtCompanyName.setFont(fontForJTF);
        jtCompanyAddress.setFont(fontForJTF);
        jtDate.setFont(fontForJTF);
        jtClientName.setFont(fontForJTF);
        jtClientAddress.setFont(fontForJTF);
        jtSellerName.setFont(fontForJTF);

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
                        sDate = jtDate.getText();

                        createPDF();
                        createItemTable();

                        JOptionPane.showMessageDialog(null, "Your Invoice PDF has been created successfully");

                    }
                });

        jbAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iNumOfItems++;
                createItemPanel();
            }
        });

        jbFileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files",  ImageIO.getReaderFileSuffixes());
                    fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(filter);
                    int returnValue = fileChooser.showOpenDialog(null);
                    File file = null;

                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                    }

                    pdfImage = Image.getInstance(fileChooser.getSelectedFile().getAbsolutePath());
                    jbFileChooserButton.setText("Logo Selected");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

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

        jtfItemName.setFont(fontForJTF);
        jtfItemQuantity.setFont(fontForJTF);
        jtfItemPrice.setFont(fontForJTF);

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
            writer = PdfWriter.getInstance(document, new FileOutputStream("Invoice.pdf"));
            document.open();

            com.itextpdf.text.Font titleFont = FontFactory.getFont("Arial-Narrow", 30);
            normalFont = FontFactory.getFont("Arial-Narrow", 11);
            titleFont.setColor(BaseColor.BLUE);

            com.itextpdf.text.Font dateFont = FontFactory.getFont("Arial-Narrow", 18);
            dateFont.setColor(BaseColor.DARK_GRAY);

            Paragraph pCompanyName = new Paragraph(sCompanyName, titleFont);
            Paragraph pCompanyAddress = new Paragraph(sCompanyAddress, normalFont);
            Paragraph pSellerName = new Paragraph("From " + sSellerName, normalFont);
            Paragraph pClientName = new Paragraph("Invoice For " + sClientName, normalFont);
            Paragraph pClientAddress = new Paragraph(sClientAddress, normalFont);
            Paragraph pDate = new Paragraph("INVOICE  | " + sDate, dateFont);
            Paragraph pInvoiceForMessage = new Paragraph("Invoice For", normalFont);


            pCompanyName.setAlignment(Element.ALIGN_LEFT);
            pSellerName.setAlignment(Element.ALIGN_LEFT);
            pCompanyAddress.setAlignment(Element.ALIGN_LEFT);
            pClientName.setAlignment(Element.ALIGN_RIGHT);
            pClientAddress.setAlignment(Element.ALIGN_RIGHT);
            pDate.setAlignment(Element.ALIGN_LEFT);
            pInvoiceForMessage.setAlignment(Element.ALIGN_RIGHT);

            if (jbFileChooserButton.getText().equals("Logo not selected")){
                document.add(pCompanyName);
            }else{
                pdfImage.scaleAbsolute(150, 150);
                document.add(pdfImage);
            }

            document.add(pDate);

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
            float[] columnWidths = new float[] {100f, 60f, 60f, 60f};

            PdfPTable itemTable = new PdfPTable(columnWidths);

            itemTable.setWidths(columnWidths);

            Phrase nameHeaderPhrase = new Phrase("Description");
            Phrase quantityHeaderPhrase = new Phrase("Quantity");
            Phrase unitPriceHeaderPhrase = new Phrase("Unit Price");
            Phrase amountHeaderPhrase = new Phrase("Amount");

            nameHeaderPhrase.setFont(normalFont);
            quantityHeaderPhrase.setFont(normalFont);
            unitPriceHeaderPhrase.setFont(normalFont);
            amountHeaderPhrase.setFont(normalFont);

            PdfPCell nameHeader = new PdfPCell(nameHeaderPhrase);
            PdfPCell quantityHeader = new PdfPCell(quantityHeaderPhrase);
            PdfPCell unitPriceHeader = new PdfPCell(unitPriceHeaderPhrase);
            PdfPCell amountHeader = new PdfPCell(amountHeaderPhrase);

            nameHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
            quantityHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
            unitPriceHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
            amountHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);

            nameHeader.setFixedHeight(20F);
            quantityHeader.setFixedHeight(20F);
            unitPriceHeader.setFixedHeight(20F);
            amountHeader.setFixedHeight(20F);

            itemTable.addCell(nameHeader);
            itemTable.addCell(quantityHeader);
            itemTable.addCell(unitPriceHeader);
            itemTable.addCell(amountHeader);

            nameHeader.setFixedHeight(30f);
            quantityHeader.setFixedHeight(30f);
            unitPriceHeader.setFixedHeight(30f);
            amountHeader.setFixedHeight(30f);

            totalPrice = 0.0;

            for (int i = 0; i < alJPanel.size(); i++){
                String itemName = alJPanel.get(i)[0].getText();
                int quantity = Integer.parseInt(alJPanel.get(i)[1].getText());
                Double price = Double.parseDouble(alJPanel.get(i)[2].getText());
                Double amount = price * quantity;

                Phrase itemNamePhrase = new Phrase(itemName, normalFont);
                Phrase quantityPhrase = new Phrase(quantity + "", normalFont);
                Phrase pricePhrase = new Phrase("" + price, normalFont);
                Phrase amountPhrase = new Phrase("" + amount, normalFont);

                DecimalFormat df = new DecimalFormat("0.00");
                amount = Double.parseDouble(df.format(amount));

                PdfPCell itemNameCell = new PdfPCell(itemNamePhrase);
                PdfPCell quantityCell = new PdfPCell(quantityPhrase);
                PdfPCell priceCell = new PdfPCell(pricePhrase);
                PdfPCell amountCell = new PdfPCell(amountPhrase);

                itemNameCell.setFixedHeight(40f);
                quantityCell.setFixedHeight(40f);
                priceCell.setFixedHeight(40f);
                amountCell.setFixedHeight(40f);

                itemTable.addCell(itemNameCell);  //Name
                itemTable.addCell(quantityCell);  //Quantity
                itemTable.addCell(priceCell);  //Price
                itemTable.addCell(amountCell);

                totalPrice += amount;
            }

            for (int i = 0; i <= 26; i++){
                PdfPCell cell = new PdfPCell(new Phrase(" "));
                cell.setFixedHeight(40f);
                itemTable.addCell(cell);

            }

            DecimalFormat df = new DecimalFormat("0.00");
            totalPrice = Double.parseDouble(df.format(totalPrice));

            com.itextpdf.text.Font totalPriceFont = FontFactory.getFont("Arial-Narrow", 30);
            totalPriceFont.setColor(BaseColor.RED);

            Paragraph pTotalPrice = new Paragraph("TOTAL: "+ totalPrice, totalPriceFont);
            pTotalPrice.setAlignment(Element.ALIGN_RIGHT);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.add(itemTable);
            document.add(Chunk.NEWLINE);
            document.add(pTotalPrice);

            Paragraph pGoodBye = new Paragraph("Thank you for your business." +
                    " Please leave feedback once you are happy and enjoy your purchase!");

            pGoodBye.setFont(normalFont);

            pGoodBye.setAlignment(Element.ALIGN_CENTER);

            document.add(Chunk.NEWLINE);
            document.add(pGoodBye);

            document.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter the correct information");
            e.printStackTrace();
        }
    }



}