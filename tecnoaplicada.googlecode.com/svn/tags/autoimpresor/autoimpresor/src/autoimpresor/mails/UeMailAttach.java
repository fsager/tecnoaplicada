package autoimpresor.mails;

/**
 * UeMailPoolAttach entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UeMailAttach implements java.io.Serializable {

	// Fields

	private Long id;
	private UeMail ueMailPool;
	private String mimeType;
	private String fileName;
	private byte[] fileInfo;

	// Constructors

	/** default constructor */
	public UeMailAttach() {
	}

	/** minimal constructor */
	public UeMailAttach(UeMail ueMailPool, String mimeType,
			String fileName) {
		this.ueMailPool = ueMailPool;
		this.mimeType = mimeType;
		this.fileName = fileName;
	}

	/** full constructor */
	public UeMailAttach(UeMail ueMailPool, String mimeType,
			String fileName, byte[] fileInfo) {
		this.ueMailPool = ueMailPool;
		this.mimeType = mimeType;
		this.fileName = fileName;
		this.fileInfo = fileInfo;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UeMail getUeMailPool() {
		return this.ueMailPool;
	}

	public void setUeMailPool(UeMail ueMailPool) {
		this.ueMailPool = ueMailPool;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte []getFileInfo() {
		return this.fileInfo;
	}

	public void setFileInfo(byte []fileInfo) {
		this.fileInfo = fileInfo;
	}

}