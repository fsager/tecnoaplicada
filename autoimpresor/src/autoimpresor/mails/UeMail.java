package autoimpresor.mails;

import java.util.Date;


/**
 * UeMailPool generated by MyEclipse - Hibernate Tools
 */

public class UeMail  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Date fechaCarga;
     private String replyTo;
     private String userFrom;
     private String userTo;
     private String ccc;
     private String bcc;
     private String subject;
     private String texto;
     private Long estado;
     private Long prioridad;
     private String errorMsg;


    // Constructors

    public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/** default constructor */
    public UeMail() {
    }

	/** minimal constructor */
    public UeMail(Date fechaCarga, String userFrom, String userTo, String subject, String texto, Long estado) {
        this.fechaCarga = fechaCarga;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.subject = subject;
        this.texto = texto;
        this.estado = estado;
    }
    
    /** full constructor */
    public UeMail(Date fechaCarga, String userFrom, String userTo, String ccc, String bcc, String subject, String texto, Long estado) {
        this.fechaCarga = fechaCarga;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.ccc = ccc;
        this.bcc = bcc;
        this.subject = subject;
        this.texto = texto;
        this.estado = estado;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCarga() {
        return this.fechaCarga;
    }
    
    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getUserFrom() {
        return this.userFrom;
    }
    
    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return this.userTo;
    }
    
    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getCcc() {
        return this.ccc;
    }
    
    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getBcc() {
        return this.bcc;
    }
    
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getEstado() {
        return this.estado;
    }
    
    public void setEstado(Long estado) {
        this.estado = estado;
    }

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public Long getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}
   








}