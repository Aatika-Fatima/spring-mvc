package com.accenture.mvc.views;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import com.accenture.mvc.formatter.DateFormatter;
import com.accenture.mvc.model.Customer;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfView extends AbstractPdfView {
 	Locale locale =LocaleContextHolder.getLocale();

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Customer> customers = (List<Customer>) model.get("customers");

		document.add(new Chunk("Customers: "));
		// Create table with 3 columns.
		PdfPTable table = new PdfPTable(3);
		table.addCell(new Paragraph("First Name"));
		table.addCell(new Paragraph("Last Number"));
		table.addCell(new Paragraph("Date Of Birth"));
		for (Customer customer : customers) {
			table.addCell(customer.getFirstName());
			table.addCell(customer.getLastName());
			System.out.println(locale);
			table.addCell(new DateFormatter("YYYY-MM-DD").print(customer.getDob(),locale));
			document.add(Chunk.NEWLINE);
		}
		document.add(table);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

	}

}
