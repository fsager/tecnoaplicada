package autoimpresor.domain.access;

/**
 * Clases entity. @author MyEclipse Persistence Tools
 */

public class Clases implements java.io.Serializable {

	// Fields

	private Integer codigoClase;
	private String descripcionClase;
	private Short duracion;
	private Short minima;
	private Short maxima;
	private String concepto;
	private Integer i6;
	private Integer i12;
	private Integer i24;
	private Integer i36;
	private Integer i48;
	private Integer i60;

	// Constructors

	/** default constructor */
	public Clases() {
	}

	/** full constructor */
	public Clases(String descripcionClase, Short duracion, Short minima,
			Short maxima, String concepto, Integer i6, Integer i12,
			Integer i24, Integer i36, Integer i48, Integer i60) {
		this.descripcionClase = descripcionClase;
		this.duracion = duracion;
		this.minima = minima;
		this.maxima = maxima;
		this.concepto = concepto;
		this.i6 = i6;
		this.i12 = i12;
		this.i24 = i24;
		this.i36 = i36;
		this.i48 = i48;
		this.i60 = i60;
	}

	// Property accessors

	public Integer getCodigoClase() {
		return this.codigoClase;
	}

	public void setCodigoClase(Integer codigoClase) {
		this.codigoClase = codigoClase;
	}

	public String getDescripcionClase() {
		return this.descripcionClase;
	}

	public void setDescripcionClase(String descripcionClase) {
		this.descripcionClase = descripcionClase;
	}

	public Short getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Short duracion) {
		this.duracion = duracion;
	}

	public Short getMinima() {
		return this.minima;
	}

	public void setMinima(Short minima) {
		this.minima = minima;
	}

	public Short getMaxima() {
		return this.maxima;
	}

	public void setMaxima(Short maxima) {
		this.maxima = maxima;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Integer getI6() {
		return this.i6;
	}

	public void setI6(Integer i6) {
		this.i6 = i6;
	}

	public Integer getI12() {
		return this.i12;
	}

	public void setI12(Integer i12) {
		this.i12 = i12;
	}

	public Integer getI24() {
		return this.i24;
	}

	public void setI24(Integer i24) {
		this.i24 = i24;
	}

	public Integer getI36() {
		return this.i36;
	}

	public void setI36(Integer i36) {
		this.i36 = i36;
	}

	public Integer getI48() {
		return this.i48;
	}

	public void setI48(Integer i48) {
		this.i48 = i48;
	}

	public Integer getI60() {
		return this.i60;
	}

	public void setI60(Integer i60) {
		this.i60 = i60;
	}

}