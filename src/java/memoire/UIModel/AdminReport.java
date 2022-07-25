/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.UIModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import memoire.Dao.CooperativeDao;
import memoire.Dao.SectorDao;
import memoire.Domain.Cooperative;
import memoire.Domain.Sector;

/**
 *
 * @author Niyigena Clemence
 */
@ManagedBean(name="adminreport")
@SessionScoped
public class AdminReport  implements Serializable{
     private String test;
    public List<Cooperative> list = new ArrayList<>();
    private List<Sector> sectors = new SectorDao().getAll("from Sector");

    public void createPDF(List<Cooperative>list) {
        list = new CooperativeDao().getAllBy(test);
        // evaluationlist = new ArrayList<>();
        try { //catch better your exceptions, this is just an example
            FacesContext context = FacesContext.getCurrentInstance();
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            if (!document.isOpen()) {
                document.open();
            }
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\Images");
            path = path.substring(0, path.indexOf("\\build"));
            path = path + "\\web\\Images\\logo.PNG";
            Image image = Image.getInstance(path);

            image.scaleAbsolute(520, 50);
            image.setAlignment(Element.ALIGN_LEFT);
            Paragraph title = new Paragraph();
            //BEGIN page

            title.add(image);
            document.add(title);
            Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(8, 20, 0));
            Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 11,Font.BOLD);
            Paragraph pa = new Paragraph(new Chunk("RWANDA COOPERATIVE AGENCY\n", font0));
            pa.setAlignment(Element.ALIGN_CENTER);
            Paragraph pa2 = new Paragraph(new Chunk("ALL COOPERATIVE \n", font2));
            pa2.setAlignment(Element.ALIGN_CENTER);
            Paragraph pa3 = new Paragraph(new Chunk("REPORT OF ALL COOPERATIVE \n", font5));
            pa3.setAlignment(Element.ALIGN_CENTER);
            document.add(pa);
            document.add(pa2);
            document.add(pa3);
            document.add(new Paragraph("\n"));
            
            document.add(new Paragraph("\n"));
            
            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(110);
            table.addCell(new Phrase("tinno", font0));
            table.addCell(new Phrase("name", font0));
            table.addCell(new Phrase("activity", font0));
            table.addCell(new Phrase("membernumber", font0));
            table.addCell(new Phrase("regdate", font0));
            table.addCell(new Phrase("mission", font0));
            table.addCell(new Phrase("vision", font0));
            
            table.addCell(new Phrase("investement", font0));
            table.addCell(new Phrase("type", font0));
            
            for (Cooperative req : new CooperativeDao().findAllCooperatives()) {
                table.addCell(new Phrase(req.getTinno()));
                table.addCell(new Phrase(req.getName()));
                table.addCell(new Phrase(req.getActivity()));
               
                table.addCell(new Phrase(req.getMembernumber()));
                 table.addCell(new Phrase(req.getRegdate().toString()));
                table.addCell(new Phrase(req.getMission()));
                table.addCell(new Phrase(req.getVision()));
                table.addCell(new Phrase(req.getInvestement()));
                table.addCell(new Phrase(req.getType()));
                 
            }
            document.add(table);
            document.close();
            
            String fileName = "Cooperative";

            writePDFToResponse(context.getExternalContext(), baos, fileName);

            context.responseComplete();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "" + e, ""));
        }

    }

    private void exportPDFTable() throws DocumentException {
        int numberOfColumns = 9;

        PdfPTable pdfTable = new PdfPTable(numberOfColumns);
        float[] width = new float[]{50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f};
        pdfTable.setWidths(width);
        //pdfTable.setWidthPercentage(100);
        BaseFont helvetica = null;
        try {
            helvetica = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);
        } catch (Exception e) {
            //font exception
        }
        DecimalFormat myFormatter = new DecimalFormat(
                "#,##0.0");
        Font font = new Font(helvetica, 8, Font.NORMAL);
        pdfTable.addCell(new Paragraph("tinno", font));
        pdfTable.addCell(new Paragraph("name", font));
        pdfTable.addCell(new Paragraph("activity", font));
        pdfTable.addCell(new Paragraph("regdate", font));
        pdfTable.addCell(new Paragraph("memernumber", font));
        pdfTable.addCell(new Paragraph("mission", font));
        pdfTable.addCell(new Paragraph("vision", font));
        pdfTable.addCell(new Paragraph("investement", font));
        pdfTable.addCell(new Paragraph("type", font));

        try {
            
            
        } catch (Exception e) {
        }
            
    }

    private void writePDFToResponse(ExternalContext externalContext, ByteArrayOutputStream baos, String fileName) {
        try {
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Expires", "0");
            externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            externalContext.setResponseHeader("Pragma", "public");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
            externalContext.setResponseContentLength(baos.size());
            OutputStream out = externalContext.getResponseOutputStream();
            baos.writeTo(out);
            externalContext.responseFlushBuffer();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }
}
