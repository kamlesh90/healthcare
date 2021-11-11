package in.nit.hc.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nit.hc.entity.Specialization;

public class SpecializationPdfViews extends AbstractPdfView{
	
	@Override
	protected void buildPdfMetadata(
			Map<String, Object> model, 
			Document document, 
			HttpServletRequest request
			) {
		
		HeaderFooter header = new HeaderFooter(new Phrase("SPECIALIZATION PDF VIEW"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		
		document.setHeader(header);
		
		HeaderFooter footer = new HeaderFooter(new Phrase(new Date()+" (C) Naresh IT Page # "), true);
		footer.setAlignment(Element.ALIGN_RIGHT); 
		
		document.setFooter(footer); 
	}

	@Override
	protected void buildPdfDocument(
			Map<String, Object> model,
			Document document,
			PdfWriter writer,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception {
		
		// for downloading PDF with given name
		response.addHeader("Content-Disposition", "attachment;filename=SPEC.pdf");
		
		 @SuppressWarnings("unchecked")
		List<Specialization> list = (List<Specialization>) model.get("list"); 
		
		// create com.lowagie.text.Element
		Font tittleFont = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, Color.RED);
		Paragraph tittle = new Paragraph("SPECIALIZATION DATA", tittleFont); 
 		tittle.setAlignment(Element.ALIGN_CENTER); 
 		tittle.setSpacingBefore(20.0f);
 		tittle.setSpacingAfter(25.0f); 
 		
		
		// add to Document 
		document.add(tittle);
		
		PdfPTable table = new PdfPTable(4);
		table.addCell(new Phrase("ID"));
		table.addCell(new Phrase("CODE")); 
		table.addCell(new Phrase("NAME"));
		table.addCell(new Phrase("NOTE")); 
		
		for(Specialization spec : list) {
			table.addCell(spec.getId().toString());
			table.addCell(spec.getSpecCode()); 
			table.addCell(spec.getSpecName());
			table.addCell(spec.getSpecNote()); 
		}
		
		// add to Document
		document.add(table);
	}

}


































