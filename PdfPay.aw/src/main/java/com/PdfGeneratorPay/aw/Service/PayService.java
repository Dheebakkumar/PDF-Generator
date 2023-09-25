package com.PdfGeneratorPay.aw.Service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import org.springframework.stereotype.Service;
import org.w3c.dom.css.RGBColor;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PayService {

	public String paySlip(OutputStream outputstream) throws Exception {
				
		try {
			
			Rectangle pageSize = new Rectangle(700,1100);
			Document document = new Document(pageSize);
			PdfWriter writer = PdfWriter.getInstance(document, outputstream);
			
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		    writer.setPageEvent(event);
		    document.open();
		    
		    Image image1 = Image.getInstance("images\\qr1.jpg");
			image1.scalePercent(80);
			image1.setAlignment(Element.ALIGN_MIDDLE);
			
			Image cal = Image.getInstance("images\\cal.png");
			cal.scalePercent(50);
			cal.setAlignment(Element.ALIGN_LEFT);
			
			Image time = Image.getInstance("images\\time.png");
			time.scalePercent(50);
			time.setAlignment(Element.ALIGN_LEFT);
			
			Image location = Image.getInstance("images\\location.png");
			location.scalePercent(50);
			location.setAlignment(Element.ALIGN_LEFT);
			
			Image ticket = Image.getInstance("images/ticket2.jpg");
			ticket.scalePercent(80);
			ticket.setAlignment(Element.ALIGN_MIDDLE);
			
			
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("\tyyyy-MM-dd");
	        String currentDateTime = dateFormatter.format(new Date());
	        
			Font normal = FontFactory.getFont(FontFactory.HELVETICA,14);
			Font bold = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16);
			
			
		    Paragraph para2 = new Paragraph(" ");
		   // Paragraph para1 = new Paragraph(currentDateTime,normal);
		    
		    PdfPTable table1 = new PdfPTable(1);               // Second Table
		   
		    PdfPCell dcell = new PdfPCell();
		    dcell.setBackgroundColor(new BaseColor(128, 229, 255));
		    dcell.setBorder(Rectangle.LEFT|Rectangle.RIGHT);
		    table1.setWidthPercentage(100);
		    
		    Chunk c1 = new Chunk("Booking Date :",bold);
		    Chunk c2 = new Chunk(" "+currentDateTime,normal);
		    
		    Phrase p = new Phrase();
		    p.add(c1);
		    p.add(c2);
		    
		    dcell.addElement(p);
		    dcell.setPadding(10f);
		    dcell.setPaddingLeft(15f);
		    table1.addCell(dcell);
		    
		    
		    
		    
		    PdfPTable table2 = new PdfPTable(2);           // Third Table 
		    table2.setWidthPercentage(100);
		   
		    PdfPTable nestedTable = new PdfPTable(1);
		    PdfPCell nestedcell = new PdfPCell();

		    nestedcell.addElement(new Phrase("Order ID" ,bold));
		    nestedcell.addElement(new Phrase("  0412 \n \n" ,normal));
		    nestedcell.addElement(new Phrase("Ticket ID" ,bold));
		    nestedcell.addElement(new Phrase("  0011 \n \n" ,normal));
		    nestedcell.addElement(new Phrase("Ticket Class" ,bold));
		    nestedcell.addElement(new Phrase("  SEH - Ultra Early Bird \n" ,normal));
		    nestedcell.setBorder(Rectangle.LEFT);
		    nestedcell.setFixedHeight(220);
		    nestedcell.setPadding(15f);
		    nestedcell.addElement(nestedTable);
		    table2.addCell(nestedcell);
		    
		    PdfPCell imgCell = new PdfPCell();
		    imgCell.setPaddingTop(22f);
		    imgCell.addElement(image1);
		    imgCell.setBorder(Rectangle.RIGHT);
		    table2.addCell(imgCell);

		    
		    float columnWidth[] = {20,100};
		    PdfPTable nestedTable2 = new PdfPTable(columnWidth);
		    PdfPCell nestedcell2 = new PdfPCell();
		    nestedcell2.setBorder(0);
		    nestedcell2.addElement(cal);
		    nestedTable2.addCell(nestedcell2);
		    PdfPCell nestedcell3 = new PdfPCell();
		    nestedcell3.setBorder(0);
		    nestedcell3.addElement(new Phrase("\t \t \t May 07 ,2024",normal));
		    nestedTable2.addCell(nestedcell3);

		    PdfPTable nestedTable3 = new PdfPTable(columnWidth);
		    PdfPCell nestedcell4 = new PdfPCell();
		    nestedcell4.setBorder(0);
		    nestedcell4.addElement(time);
		    nestedTable3.addCell(nestedcell4);
		    PdfPCell nestedcell5 = new PdfPCell();
		    nestedcell5.setBorder(0);
		    nestedcell5.addElement(new Phrase("\t \t \t 10:00 PM",normal));
		    nestedTable3.addCell(nestedcell5);
		    
		    PdfPTable nestedTable4 = new PdfPTable(columnWidth);
		    PdfPCell nestedcell6 = new PdfPCell();
		    nestedcell6.setBorder(0);
		    nestedcell6.addElement(location); 
		    nestedTable4.addCell(nestedcell6);
		    PdfPCell nestedcell7 = new PdfPCell();
		    nestedcell7.setBorder(0);
		    nestedcell7.addElement(new Phrase("\t \t \t The Venue Arupa, \n \t \t weststraat UK",normal));
		    nestedTable4.addCell(nestedcell7);
		    
		    PdfPTable table3 = new PdfPTable(2);                 // fourth Table
		    table3.setWidthPercentage(100);    
		    
		    PdfPTable nestedTable1 = new PdfPTable(1);
		    PdfPCell nestedcell1 = new PdfPCell();
		    nestedcell1.setBackgroundColor(BaseColor.CYAN);
		    nestedcell1.addElement(new Phrase("\t Event Details :" ,bold));
		    nestedcell1.addElement(new Phrase("\t \t \t \t SEH - Buleria x Icons\n \n" ,normal));
		    nestedcell1.addElement(nestedTable1);
		    nestedcell1.addElement(nestedTable2);
		    nestedcell1.addElement(nestedTable3);
		    nestedcell1.addElement(nestedTable4);
		    nestedcell1.setPadding(15f);
		    nestedcell1.setBorder(Rectangle.LEFT);
		 
		    table3.addCell(nestedcell1);
		    PdfPCell imgCell1 = new PdfPCell();
		    imgCell1.setBackgroundColor(BaseColor.CYAN);
		    imgCell1.setPaddingTop(45f);
		    imgCell1.addElement(ticket);
		    imgCell1.setBorder(Rectangle.RIGHT);
		    table3.addCell(imgCell1);
		   
		    
		    
		    PdfPTable table4 = new PdfPTable(1);                 // fivth Table
		    table4.setWidthPercentage(100);
		    PdfPCell fourcell = new PdfPCell();
		    fourcell.setBorder(Rectangle.LEFT|Rectangle.RIGHT);
		    
		    PdfPTable nestedTable5 = new PdfPTable(1);
		    nestedTable5.setWidthPercentage(100);
		    PdfPCell nestedcell8 = new PdfPCell();
		    nestedcell8.setBorder(0);
		    nestedcell8.addElement(new Phrase("Payment Summary :" ,bold));
		    nestedcell8.setPaddingLeft(15f);
		    nestedcell8.addElement(new Phrase("\t \t \t \t Ticket Subtotal\n" ,normal));
		    nestedTable5.addCell(nestedcell8);
		 
		    fourcell.addElement(nestedTable5);
		  
		    table4.addCell(fourcell);
		    
		    PdfPTable table5 = new PdfPTable(1);                 // fivth Table
		    table5.setWidthPercentage(100); 
		    PdfPCell fiveCell = new PdfPCell();
		    fiveCell.setBorder(Rectangle.LEFT|Rectangle.RIGHT);
		    
		    float colWidth[] = {150,50};
		    PdfPTable paytable = new PdfPTable(colWidth);
		    paytable.setWidthPercentage(100);
		    PdfPCell paycell = new PdfPCell();
		    
		    paycell.addElement(new Phrase("SEH - Ultra Early Bird @ Af1. 55.00",normal));
		    paycell.addElement(new Phrase("SEH - Buleria x Icons @ Af1. 100.00",normal));
		    paycell.setPaddingLeft(30f);
		    paycell.setBorder(0);		   
		    paytable.addCell(paycell);
		    
		    PdfPCell paycell2 = new PdfPCell();
		    
		    paycell2.addElement(new Phrase("Af1. 110.00",normal));
		    paycell2.addElement(new Phrase("Af1. 100.00",normal));
		    paycell2.setBorder(0);
		    paytable.addCell(paycell2);
		    
		    fiveCell.addElement(paytable);
		    table5.addCell(fiveCell);
		    
		    
		    PdfPTable table6 = new PdfPTable(1);                 // sixth Table
		    table6.setWidthPercentage(100); 
		    PdfPCell sixCell = new PdfPCell();
		    
		    sixCell.setBorder(Rectangle.LEFT|Rectangle.RIGHT|Rectangle.BOTTOM);
		    sixCell.setPadding(15f);
		    sixCell.setPaddingBottom(20f);
		    float colWidth1[] = {150,50};
		    PdfPTable paytable1 = new PdfPTable(colWidth1);
		    paytable1.setWidthPercentage(93); 
		    
		    PdfPCell paycell1 = new PdfPCell();
		    paycell1.addElement(new Phrase("Total Amount",bold));
		    paycell1.setBorder(Rectangle.TOP);
		    paycell1.setPaddingLeft(0);

		    paytable1.addCell(paycell1);
		  
		    PdfPCell paycell3 = new PdfPCell();
		    paycell3.addElement(new Phrase("Af1. 220.00",bold));
		    paycell3.setBorder(Rectangle.TOP);
		    paycell3.setPaddingLeft(10);
		    paytable1.addCell(paycell3);

		    sixCell.addElement(paytable1);
		    table6.addCell(sixCell);
		    
		   
		   
		    
		    
		    
		    
		    
		    
		    
		    
		    document.add(table1);
		    document.add(table2);
		    document.add(table3);
		    document.add(table4);
		    document.add(table5);
		    document.add(table6);
		   

		    document.close();
		    
		    
			return "File is Created";
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("File not Created");
			return "File is Not Created ";
		}
	}
}

 class HeaderFooterPageEvent extends PdfPageEventHelper {
	 
    public void onStartPage(PdfWriter writer,Document document) {
    
    	Image image;
		try {   
		    PdfPTable table = new PdfPTable(1);
		    table.setWidthPercentage(100);
		    PdfPCell headcell = new PdfPCell();
		    headcell.setPadding(15f);
		    headcell.setBorder(Rectangle.LEFT|Rectangle.RIGHT |Rectangle.TOP);
		    
		    PdfPTable table1 = new PdfPTable(1); 
		    PdfPCell headcell1 = new PdfPCell();
		    headcell1.setBorder(0);
			image = Image.getInstance("images/logo-pay-aw.png");
			image.scalePercent((float) 100.00);
			image.setAlignment(Element.ALIGN_CENTER);
			image.scaleAbsolute(300, 35);
			image.setSpacingBefore(20f);
			table1.setWidthPercentage(75);
			headcell1.addElement(image);
			table1.addCell(headcell1);
			
			headcell.addElement(table1);
			
			table.addCell(headcell);
			document.add(table);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    	
    public void onEndPage(PdfWriter writer,Document document) {
    	
    	try {
    		
    		Font normal1 = FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.WHITE);
			Font bold1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16,BaseColor.WHITE);
		    
			Image phone = Image.getInstance("images/phone1.png");
			phone.scalePercent(60);
			phone.setAlignment(Element.ALIGN_MIDDLE);
			
			Image world = Image.getInstance("images/internet1.png");
			world.scalePercent(55);
			world.setAlignment(Element.ALIGN_MIDDLE);
			
			Image mail = Image.getInstance("images/mail1.png");
			mail.scalePercent(70);
			mail.setAlignment(Element.ALIGN_MIDDLE);
			
			Image whatsapp = Image.getInstance("images/whatsapp1.png");
			whatsapp.scalePercent(70);
			whatsapp.setAlignment(Element.ALIGN_MIDDLE);
			
			Image face = Image.getInstance("images/face1.png");
			face.scalePercent(70);
			face.setAlignment(Element.ALIGN_MIDDLE);
			
			Image insta = Image.getInstance("images/insta1.png");
			insta.scalePercent(70);
			insta.setAlignment(Element.ALIGN_MIDDLE);
			
			Image you = Image.getInstance("images/you2.png");
			you.scalePercent(70);
			you.setAlignment(Element.ALIGN_MIDDLE);
			
    		
    		PdfPTable table7 = new PdfPTable(1);                 // seventh Table
		    table7.setWidthPercentage(100); 
		    PdfPCell sevenCell = new PdfPCell();
		    sevenCell.setBackgroundColor(BaseColor.DARK_GRAY);
		    sevenCell.setPadding(15);
		    sevenCell.setBorder(Rectangle.LEFT|Rectangle.RIGHT|Rectangle.BOTTOM);
		    
		    float colWidth[] = {150,50};
		    PdfPTable ftable = new PdfPTable(colWidth);
		    ftable.setWidthPercentage(100);
		    PdfPCell fcell = new PdfPCell();
		    fcell.setPaddingLeft(100);
		    fcell.addElement(new Phrase("Information",bold1));
		    fcell.setBorder(0);		   
		    ftable.addCell(fcell);
		    PdfPCell fcell1 = new PdfPCell();
		    fcell1.setPaddingLeft(0);
		    fcell1.addElement(new Phrase("Social",bold1));
		    fcell1.setBorder(0);
		    ftable.addCell(fcell1);

		    sevenCell.addElement(ftable);
		    
		    float columnWidth2[] = {7,50,8,8,8};
		    PdfPTable fnestedTable = new PdfPTable(columnWidth2);
		    fnestedTable.getDefaultCell().setBorder(0);
		    fnestedTable.getDefaultCell().setPaddingLeft(10);
		    fnestedTable.addCell(phone);
		    fnestedTable.setSpacingBefore(20);
		   
		    PdfPCell fnestedcell1 = new PdfPCell();
		    fnestedcell1.setBorder(0);
		    fnestedcell1.setPaddingLeft(7);
		    fnestedcell1.addElement(new Phrase("149",normal1));
		    fnestedTable.addCell(fnestedcell1);
		    
		    fnestedTable.addCell(face);
		    fnestedTable.addCell(you);
		    fnestedTable.addCell(insta);
		    
		    sevenCell.addElement(fnestedTable);
		    
		    float columnWidth3[] = {6,50};
		    PdfPTable fnestedTable1 = new PdfPTable(columnWidth3);
		    fnestedTable1.getDefaultCell().setBorder(0);
		    PdfPCell fnestedcell4 = new PdfPCell();
		    fnestedcell4.setBorder(0);
		    fnestedcell4.addElement(whatsapp);
		    fnestedTable1.addCell(fnestedcell4);
		    PdfPCell fnestedcell3 = new PdfPCell();
		    fnestedcell3.addElement(new Phrase("1234567890",normal1));
		    fnestedcell3.setBorder(0);
		    fnestedTable1.addCell(fnestedcell3);
		    
		    sevenCell.addElement(fnestedTable1);
		    
		   
		    PdfPTable fnestedTable2 = new PdfPTable(columnWidth3);
		    fnestedTable2.getDefaultCell().setBorder(0);
		    PdfPCell fnestedcell5 = new PdfPCell();
		    fnestedcell5.setBorder(0);
		    fnestedcell5.addElement(mail);
		    fnestedTable2.addCell(fnestedcell5);
		    PdfPCell fnestedcell6 = new PdfPCell();
		    fnestedcell6.addElement(new Phrase("pay.aw@gmail.com",normal1));
		    fnestedcell6.setBorder(0);
		    fnestedTable2.addCell(fnestedcell6);
		    
		    sevenCell.addElement(fnestedTable2);
		    
		    PdfPTable fnestedTable3 = new PdfPTable(columnWidth3);
		    fnestedTable3.getDefaultCell().setBorder(0);
		    PdfPCell fnestedcell7 = new PdfPCell();
		    fnestedcell7.setBorder(0);
		    fnestedcell7.addElement(world);
		    fnestedTable3.addCell(fnestedcell7);
		    PdfPCell fnestedcell8 = new PdfPCell();
		    fnestedcell8.setBorder(0);
		    fnestedcell8.addElement(new Phrase("pay.aw",normal1));
		    fnestedTable3.addCell(fnestedcell8);
		    
		    sevenCell.addElement(fnestedTable3);
		    
		    
		    
		    table7.addCell(sevenCell);
		    
		    document.add(table7);
    	}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
} 

