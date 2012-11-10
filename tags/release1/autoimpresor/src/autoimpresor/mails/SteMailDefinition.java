package autoimpresor.mails;



public interface SteMailDefinition {
	
	public boolean sendMail(final String to, final String from,
			final String cc, final String bcc, final String subject,
			final String message) throws Exception;
	public boolean sendMailError(final Exception e, final Class clase, String method) throws Exception;
	public boolean sendMail(final String to, final String from,
			final String cc, final String bcc, final String subject,
			final String message, final UeMailAttach []attachs);
	public boolean sendMail(final String to, final String from, final String replyTo, 
			final String cc, final String bcc, final String subject,
			final String message, final UeMailAttach []attachs, int prioridad);
}
