package testerGeneral.domain.reportes;

public class CategoriaValor {
	private String categoria;//01,2,3,etc para errores. 1-3, 3-5, etc para tiempo o metros
	private int value;//Cantidad de personas
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
