package com.example.sl_utilities_provider.utility;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.entities.Worker;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfUtility {

    private List<Service> services;

    public PdfUtility(List<Service> services) {
        this.services = services;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Service name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Workers", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for(Service service : services){
            table.addCell(service.getName());
            StringBuilder sb = new StringBuilder();
            for(Worker worker:service.getWorkers()){
                sb.append(worker.getName());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            table.addCell(sb.toString());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Service Worker List", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
