package in.nit.hc.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean send(
						String [] to, 
						String [] cc, 
						String [] bcc, 
						String subject, 
						String text, 
						Resource [] files
						) {
		
		boolean sent = false;
		
		try {
			//1. create empty MimeMessage
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMultipart content = new MimeMultipart("alternative");
			javax.mail.internet.MimeBodyPart pdf = new javax.mail.internet.MimeBodyPart();
			pdf.setText("TEXT SET BY ME");
			content.addBodyPart(pdf); 
			
			message.setContent(content,"alternative"); 
			
			//2. fill the details
			MimeMessageHelper helper = new MimeMessageHelper(message, files!=null && files.length>0);
			
			helper.setTo(to);
			
			if(cc!=null)
				helper.setCc(cc); 
			if(bcc!=null)
				helper.setBcc(bcc); 
			if(files!=null) {
				for(Resource rob : files) {
					helper.addAttachment(rob.getFilename(), rob); 
				}
			}
			
			// 3. send message
			mailSender.send(message);   //error
			
			sent = true;
				
		} catch (Exception e) {
			e.printStackTrace();
			sent=false;
		}
		
		return sent;
	}
	
	/** send overloaded method */
	public boolean send(
					String to, 
					String subject, 
					String text, 
					Resource file) {
		
		return send(
					new String[] {to}, 
					null, 
					null, 
					subject, 
					text, 
					file!=null ? new Resource[] {file}:null);   //error
	}
	
   public boolean send(
		   			String to, 
		   			String subject, 
		   			String text) {
	   
	   return send(to, subject, text, null); 
   }
	
}
