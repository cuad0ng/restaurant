//package com.hcmute.utils;
//
//import com.hcmute.model.AccountModel;
//import com.hcmute.model.CartModel;
//import com.hcmute.model.InvoiceModel;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//
//import java.io.IOException;
//import java.util.List;
//
//public class InvoicePDFGenerator {
//    private PDDocument invoicePDF;
//    private PDPage page;
//
//    // list of item in invoice
//    private List<CartModel> listItem;
//    private String invoiceTitle;
//    private AccountModel account;
//    private InvoiceModel invoice;
//
//    /**
//     * Initialize
//     * @param list list of product
//     * @param account user account
//     * @param inc invoice
//     */
//    public InvoicePDFGenerator(List<CartModel> list, AccountModel account, InvoiceModel inc){
//        invoicePDF = new PDDocument();
//        page = new PDPage();
//        this.listItem = list;
//        this.account =  account;
//        this.invoice = inc;
//        invoiceTitle = new String("Restaurant Final Project");
//        invoicePDF.addPage(page);
//    }
//
//    public void writeData(){
//        PDPage myPage = this.invoicePDF.getPage(0);
//        try{
//            PDPageContentStream cs = new PDPageContentStream(this.invoicePDF, myPage);
//
//            // first line
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 20);
//            cs.newLineAtOffset(140, 750);
//            cs.showText(invoiceTitle);
//            cs.endText();
//
//            // second line
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 18);
//            cs.newLineAtOffset(270, 690);
//            cs.showText("Invoice");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(60, 610);
//            cs.showText("ID Invoice: ");
//            cs.newLine();
//            cs.showText("Customer Name: ");
//            cs.newLine();
//            cs.showText("Phone Number: ");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(170, 610);
//            cs.showText(String.valueOf(invoice.getId()));
//            cs.newLine();
//            cs.showText(account.getUsername());
//            cs.newLine();
//            cs.showText("");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(80, 540);
//            cs.showText("Product Name");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(200, 540);
//            cs.showText("Unit Price");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(310, 540);
//            cs.showText("Quantity");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(410, 540);
//            cs.showText("Price");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(80, 520);
//            for(int i =0; i<listItem.size(); i++) {
//              cs.showText(listItem.get(i).getProduct_name());
//              cs.newLine();
//            }
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(200, 520);
//            for(int i =0; i<listItem.size(); i++) {
//              cs.showText(String.valueOf(listItem.get(i).getProduct_price()));
//              cs.newLine();
//            }
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(310, 520);
//            for(int i =0; i<listItem.size(); i++) {
//              cs.showText(String.valueOf(listItem.get(i).getProductAmount()));
//              cs.newLine();
//            }
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(410, 520);
//            for(int i =0; i<listItem.size(); i++) {
//              int price = listItem.get(i).getProduct_price()*listItem.get(i).getProductAmount();
//              cs.showText(String.valueOf(price));
//              cs.newLine();
//            }
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(310, (500-(20*listItem.size())));
//            cs.showText("Total: ");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            //Calculating where total is to be written using number of products
//            cs.newLineAtOffset(410, (500-(20*listItem.size())));
//            cs.showText(String.valueOf(invoice.getTotalCost()));
//            cs.endText();
//
//            //Close the content stream
//            cs.close();
//            //Save the PDF
//            this.invoicePDF.save("./invoice_" + String.valueOf(invoice.getId()));
//
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//}
