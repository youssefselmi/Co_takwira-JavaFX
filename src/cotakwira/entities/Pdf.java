/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;

//import pidev.DataBase.DataBase;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cotakwira.tools.ConnexionBD;

/**
 *
 * @author esprit
 */
public class Pdf {

    private Connection con;
    private Statement ste;

    public Pdf() {
        //      con = DataBase.getInstance().getConnection();
        con = ConnexionBD.getInstance().getCnx();

    }

    public void add(String file, String N, String N1, String N2, String N3, String N4) throws FileNotFoundException, SQLException, DocumentException {

        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
        Document my_pdf_report = new Document();
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
        my_pdf_report.open();
        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(2);
        //create a cell object
        PdfPCell table_cell;

        table_cell = new PdfPCell(new Phrase("Nom Equipe1"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase(N));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Nom Equipe 2"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase(N1));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Heure Début"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase(N2));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Heure Fin"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase(N3));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase("Nom Stade"));
        my_report_table.addCell(table_cell);
        table_cell = new PdfPCell(new Phrase(N4));
        my_report_table.addCell(table_cell);
       
      

        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();

        /* Close all DB related objects */
    }

}
