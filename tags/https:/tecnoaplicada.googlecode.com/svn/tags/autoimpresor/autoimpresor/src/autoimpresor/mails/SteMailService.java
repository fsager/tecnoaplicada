package autoimpresor.mails;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import autoimpresor.business.ContextManager;



public class SteMailService implements SteMailDefinition {
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}


	public boolean sendMail(final String to, final String from,
			final String cc, final String bcc, final String subject,
			final String message) {
		return sendMail (to, from, from, cc, bcc, subject, message, null, 0);
	}

	protected Address[] getListFromAddress(String p_address)
			throws AddressException {
		StringTokenizer st = new StringTokenizer(p_address, ";");
		Address add[] = new Address[st.countTokens()];
		int i = 0;
		while (st.hasMoreElements()) {
			add[i] = new InternetAddress(st.nextToken());
			i = i + 1;
		}

		return add;
	}
	
	public static void main (String args[])
	{
		try {
			SteMailDefinition mailService=(SteMailDefinition)ContextManager.getBizObject ("steMailService");
			
			mailService.sendMail("jpnicotra@gmail.com", "uesiglo21@uesiglo.edu.ar", null, null, "Sujeto", "Mensaje");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean sendMail(final String to, final String from, final String cc, final String bcc,
			final String subject, final String message, final UeMailAttach[] attachs) {
		return sendMail (to, from, from, cc, bcc, subject, message, attachs, 0);
	}


	public boolean sendMail(final String to, final String from, final String replyTo, final String cc,
			final String bcc, final String subject, final String message,
			final UeMailAttach[] attachs, int prioridad) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipients(Message.RecipientType.TO,
						getListFromAddress(to));
				if (cc != null)
					mimeMessage.setRecipient(Message.RecipientType.CC,
							new InternetAddress(cc));
				if (bcc != null)
					mimeMessage.setRecipient(Message.RecipientType.BCC,
							new InternetAddress(bcc));
				mimeMessage.setFrom(new InternetAddress(from));
				mimeMessage.setSubject(subject);
				mimeMessage.setReplyTo(getListFromAddress(replyTo));

				MimeMultipart multipart = new MimeMultipart("related");
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(message, "text/html");
				multipart.addBodyPart(messageBodyPart);
				for (int i=0;attachs!=null && i<attachs.length;i++)
				{
					messageBodyPart = new MimeBodyPart();
					DataSource source = new ByteArrayDataSource(attachs[i].getFileInfo(), attachs[i].getMimeType());
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(attachs[i].getFileName());
					messageBodyPart.setHeader("Content-ID","<"+attachs[i].getFileName()+">");
					multipart.addBodyPart(messageBodyPart);
				}

				mimeMessage.setContent(multipart);
			}
		};

			this.mailSender.send(preparator);

		return true;
	}


	public boolean sendMailError(Exception e, Class clase, String method)
			throws Exception {
		String subject="Error inesperado en Clase: "+clase.getName();
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);
		ps.close();
		baos.close();
		this.sendMail(ContextManager.getProperty("batch.errores.mail.to"), ContextManager.getProperty("portal.mail.origen"), ContextManager.getProperty("portal.mail.origen")
					, ContextManager.getProperty("batch.errores.mail.cc"), null, subject, "Error no contemplado:<br/>"+baos.toString(), null, 0);
		return false;
	}

}
