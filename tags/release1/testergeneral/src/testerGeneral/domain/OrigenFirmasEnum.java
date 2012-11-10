package testerGeneral.domain;

public enum OrigenFirmasEnum {	
	DISCO("DISCO"),
	PAD("PAD");
	
	private final String value;
	
	OrigenFirmasEnum(String value) {
        this.value = value;
    }
	
	public String getValue() {
		return value;
	}
}
