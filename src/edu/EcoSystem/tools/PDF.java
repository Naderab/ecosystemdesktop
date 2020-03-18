/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.tools;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.EcoSystem.entities.Participation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Nader
 */
public class PDF {

    public void pdf(Participation p) throws SQLException, FileNotFoundException, DocumentException {
        try {
            // System.out.println("Haouet------------------------------------->"+nom);

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);

            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Nader\\Desktop/Tickets" + randomNum + ".pdf"));
            document.open();
            document.add(new Paragraph("  EcoSystem ", FontFactory.getFont(FontFactory.TIMES)));
            //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //String date=new Date().toString();
            //document.add(new Paragraph(" Date : " + new Date().toString()));
            //  document.add(new Paragraph("-----------------------------------------------------------------"));
            // document.add(new Paragraph("-----------------------------------------------------------------"));
            com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
            com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Ticket"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.PINK);
            table.addCell(cell);

//                table.addCell("nom");
//              table.addCell(nom1);
//              table.addCell("prenom");
//              table.addCell(prenom1);
            table.addCell("Evenement");
            table.addCell(p.getNomEvent());
            table.addCell("Email Utilisateur");
            table.addCell(p.getEmail());
            table.addCell("***************************");
            table.addCell("***************************");

            table.addCell("Date Debut");
            table.addCell(String.valueOf(p.getDateDebut()));
            table.addCell("Date Fin");
            table.addCell(String.valueOf(p.getDateFin()));
            table.addCell("***************************");
            table.addCell("***************************");
            table.addCell("Nombre de Place");
            table.addCell(Integer.toString(p.getNbPlace()));

            document.add(table);
            document.close();
            TrayNotification tray = new TrayNotification("Ticket", "Ticket de participation créée avec succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(3));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
