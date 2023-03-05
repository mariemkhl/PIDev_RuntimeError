/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author ASUS
 */
public class BackgroundImageEvent extends PdfPageEventHelper {
    Image image;
    
    @Override 
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            if (image == null) {
                image = Image.getInstance("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\back.png");
            }

            PdfContentByte canvas = writer.getDirectContentUnder();
            image.scaleAbsolute(document.getPageSize());
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
