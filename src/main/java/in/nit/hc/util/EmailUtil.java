package in.nit.hc.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	boolean sent = false;
	public boolean send(String [] to, String [] cc, String [] bcc, String subject, String text, Resource [] files) {
		
		try {
			//1. create empty MimeMessage
			MimeMessage message = mailSender.createMimeMessage();
			
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
			mailSender.send(message);
			
			sent = true;
				
		} catch (Exception e) {
			e.printStackTrace();
			sent=false;
		}
		
		return sent;
	}
	
	/** send overloaded method */
	public boolean send(String to, String subject, String text, Resource file) {
		
		return send(new String[] {to}, null, null, subject, text, file!=null ? new Resource[] {file}:null); 
	}
	
   public boolean send(String to, String subject, String text) {
	   
	   return send(to, subject, text, null); 
   }
	
}
