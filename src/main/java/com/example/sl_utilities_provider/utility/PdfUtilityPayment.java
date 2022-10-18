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

public class PdfUtilityPayment {

    private Service service;

    public PdfUtilityPayment(Service service) {
        this.service = service;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph(service.getName(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        for(Worker worker: service.getWorkers()){
            document.add(new Paragraph(worker.getName()));
        }
        document.close();

    }
}
