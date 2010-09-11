package testerGeneral.domain;

/**
 * BinaryTable entity. @author MyEclipse Persistence Tools
 */

public class BinaryTable implements java.io.Serializable {

	// Fields

	private BinaryTableId id;

	// Constructors

	/** default constructor */
	public BinaryTable() {
	}

	/** full constructor */
	public BinaryTable(BinaryTableId id) {
		this.id = id;
	}

	// Property accessors

	public BinaryTableId getId() {
		return this.id;
	}

	public void setId(BinaryTableId id) {
		this.id = id;
	}

}