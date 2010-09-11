package testerGeneral.domain;

public enum OrigenFotosEnum {
	DISCO("DISCO"),
	WEBCAM("WEBCAM");
	

	private final String value;
	OrigenFotosEnum(String value) {
        this.value = value;
    }
	
	public String getValue() {
		return value;
	}
}
