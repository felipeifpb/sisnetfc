package util;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnviarEmail {

	private static InternetAddress emailUser;
	
	public static void enviarEmail(String email, int codigo) throws EmailException{
		
		List<InternetAddress> lista = new ArrayList<InternetAddress>();
		
		Email msg = new SimpleEmail();
		
		/* autenticando no servidor de email */
		msg.setHostName("smtps.bol.com.br");
		msg.setSmtpPort(587);
		msg.setAuthentication("sisnetsocial@bol.com.br", "sisnet123");
		msg.setStartTLSEnabled(true);
		
		/* adicionando os emails a lista de emails */
		emailUser = new InternetAddress();
		emailUser.setAddress(email);
		lista.add(emailUser);
		
		emailUser = new InternetAddress();
		emailUser.setAddress("sisnetsocial@bol.com.br");
		lista.add(emailUser);
		
		/* dados da mensagem */
		msg.setFrom("sisnetsocial@bol.com.br"); //remetente
		msg.setSubject("Redefinição de senha"); //assunto
		
		String mensagem = "Seu código para redefinição de senha é: " + codigo; //mensagem
		
		msg.setMsg( mensagem ); //adicionando a mensagem ao email
		msg.setTo(lista);		//adicionando a lista de emails ao email
		msg.send();	
		
	}	
}
