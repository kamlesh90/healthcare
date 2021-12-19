package in.nit.hc.view;

import java.awt.Color;
import java.util.Date;
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
import com.lowagie.text.pdf.PdfWriter;

import in.nit.hc.entity.SlotRequest;

public class InvoicePdfView extends AbstractPdfView{

	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		
		HeaderFooter header = new HeaderFooter(new Phrase("INVOICE"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		
		document.setHeader(header); 
		
		HeaderFooter footer = new HeaderFooter(new Phrase(new Date() +"1ST Floor A Wing, Bhagwati Ashish Appt. Murbad, Road, Syndicate, "
																	 + "Kalyan West · 093227 69864"), true);
		footer.setAlignment(Element.ALIGN_RIGHT);
		document.setFooter(footer); 
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unused")
		SlotRequest sr = (SlotRequest) model.get("slotReq");
		
		// //download PDF with a given filename
		response.addHeader("Content-Disposition", "attachment;filename=INVOICE.pdf");
		
		Font tittleFont = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, Color.RED);
		Paragraph paragraph = new Paragraph("INVOICE", tittleFont);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingBefore(20.0f);
		paragraph.setSpacingAfter(20.0f);
		
		document.add(paragraph);
	}
	
	
}






































