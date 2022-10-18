package com.example.sl_utilities_provider.utility;

import com.example.sl_utilities_provider.entities.Worker;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import com.lowagie.text.Font;
import java.io.IOException;
import java.util.List;

public class PdfUtilityWorker {

    private List<Worker> workers;

    public PdfUtilityWorker(List<Worker> workers) {
        this.workers = workers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Worker name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Contact No.", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Service", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for(Worker worker : workers){
            table.addCell(worker.getName());
            table.addCell(worker.getAddress());
            table.addCell(worker.getContact());
            table.addCell(worker.getServiceName());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Current Worker List", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {0.5f, 0.5f, 0.5f, 0.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
