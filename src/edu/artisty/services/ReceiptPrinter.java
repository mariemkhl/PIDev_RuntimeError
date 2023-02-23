package edu.artisty.services;

import edu.artisty.entities.Commande;
import edu.artisty.services.ServiceCommande;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

public class ReceiptPrinter implements Printable {

    private String receiptText;

    public ReceiptPrinter(String receiptText) {
        this.receiptText = receiptText;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        Font font = new Font("Monospaced", Font.PLAIN, 10);
        g2d.setFont(font);

        String[] lines = receiptText.split("\n");
        int y = 15;
        for (String line : lines) {
            g2d.drawString(line, 5, y);
            y += 15;
        }

        return Printable.PAGE_EXISTS;
    }

    public void printReceipt() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        try {
            job.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String receiptText = "Receipt\n\nItem 1\t$10.00\nItem 2\t$20.00\n\nTotal\t$30.00";
        ReceiptPrinter printer = new ReceiptPrinter(receiptText);
        printer.printReceipt();
    }

}
