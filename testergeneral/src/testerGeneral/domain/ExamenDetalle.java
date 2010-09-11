
package testerGeneral.domain;

/**
 * ExamenDetalle entity. @author MyEclipse Persistence Tools
 */

public class ExamenDetalle implements java.io.Serializable {

	// Fields

	public static final String EXAD_CODIGO_TEST_COOR_BIMANUAL="TEST_COOR_BIMANUAL";
	public static final String EXAD_CODIGO_TEST_COOR_VISOMOTORA="TEST_COOR_VISOMOTORA";
	public static final String EXAD_CODIGO_TEST_REAC_SIMPLE="TEST_REAC_SIMPLE";
	public static final String EXAD_CODIGO_TEST_CTR_TEMPORO="TEST_CTR_TEMPORO";
	public static final String EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA="TEST_COOR_BIMANUAL_FINA";
	public static final String EXAD_CODIGO_TEST_REAC_MULTIPLES_COND="TEST_REAC_MULTIPLES_COND";
	public static final String EXAD_CODIGO_TEST_REAC_MONOTONIA="TEST_REAC_MONOTONIA";
	public static final String EXAD_CODIGO_TEST_REAC_MULT_NO_COND="TEST_REAC_MULT_NO_COND";
	public static final String EXAD_CODIGO_TEST_PERC_REAC="TEST_PERC_REAC";
	
	private Long exadId;
	private Examen examen;
	private String exadDetalle;
	private String exadCodigo;

	// Constructors

	/** default constructor */
	public ExamenDetalle() {
	}

	/** full constructor */
	public ExamenDetalle(Examen examen, String exadDetalle, String exadCodigo) {
		this.examen = examen;
		this.exadDetalle = exadDetalle;
		this.exadCodigo = exadCodigo;
	}

	// Property accessors

	public Long getExadId() {
		return this.exadId;
	}

	public void setExadId(Long exadId) {
		this.exadId = exadId;
	}

	public Examen getExamen() {
		return this.examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public String getExadDetalle() {
		return this.exadDetalle;
	}

	public void setExadDetalle(String exadDetalle) {
		this.exadDetalle = exadDetalle;
	}

	public String getExadCodigo() {
		return this.exadCodigo;
	}

	public void setExadCodigo(String exadCodigo) {
		this.exadCodigo = exadCodigo;
	}

}