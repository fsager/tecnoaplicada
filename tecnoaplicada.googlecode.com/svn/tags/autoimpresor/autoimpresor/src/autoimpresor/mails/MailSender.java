package autoimpresor.mails;


import java.util.List;

import autoimpresor.business.ContextManager;


public class MailSender {


	public static void sendMail (UeMail mail,List<UeMailAttach> attachs)
	{
		try {

				SteMailDefinition steMailService=(SteMailDefinition)ContextManager.getBizObject("steRealMailService");
				
	        	System.out.println ("mail.getUserTo(): "+mail.getUserTo());
	        	System.out.println ("mail.getUserFrom(): "+mail.getUserFrom());
	        	System.out.println ("mail.getCcc(): "+mail.getCcc());
	        	System.out.println ("mail.getBcc(): "+mail.getBcc());
	        	System.out.println ("mail.getSubject(): "+mail.getSubject());
	        	System.out.println ("mail.getTexto(): "+mail.getTexto());
	
	            if (mail.getReplyTo() == null)
	            	mail.setReplyTo(mail.getUserTo());
	            
	            if (attachs.size()>0)
	            {
	            	UeMailAttach[] attachsArray=new UeMailAttach[attachs.size()];
	            	
	            	for (int i=0;i<attachs.size();i++)
	            	{
	            		attachsArray[i] = (UeMailAttach)attachs.get(i);
	            	}
	            	
	            	steMailService.sendMail(mail.getUserTo(), mail.getUserFrom(), mail.getReplyTo(), mail.getCcc(), mail.getBcc(), mail.getSubject(), mail.getTexto(), attachsArray, mail.getPrioridad().intValue());
	            }
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
