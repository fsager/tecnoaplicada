package ar.com.trimix.common.codegen;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteDataSource implements javax.activation.DataSource {
	protected String contentType;
	protected String name;
	protected ByteArrayInputStream bais;
		
	public ByteDataSource (byte byts[])
	{
		bais=new ByteArrayInputStream(byts);
	}
	
	public InputStream getInputStream() throws IOException {
		System.out.println ("getInputStream");
		return bais;
	}

	public OutputStream getOutputStream() throws IOException {
		System.out.println ("getOutputStream");
		return null;
	}

	public String getContentType() {
		System.out.println ("getContentType");
		return contentType;
	}

	public void setContentType(String contentType) {
		System.out.println ("setContentType: "+contentType);
		this.contentType = contentType;
	}

	public String getName() {
		System.out.println ("getName");
		return name;
	}

	public void setName(String name) {
		System.out.println ("setName: "+name);
		this.name = name;
	}

}
