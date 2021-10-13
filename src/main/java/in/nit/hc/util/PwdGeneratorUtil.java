package in.nit.hc.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class PwdGeneratorUtil {
	
	public static String genratePassword() {
		String pwd = UUID
						.randomUUID()
						.toString()
						.replace('-', '!')
						.substring(0, 15);
		
		return pwd;
	}
}
