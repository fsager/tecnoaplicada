package testerGeneral;
import java.io.File;
import java.util.List;

import javax.swing.filechooser.FileFilter;

import testerGeneral.domain.Dominio;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class ImageFilter extends FileFilter {

	public ImageFilter()
	{
		dominios=frontend.utils.Util.getDominios("EXTENCION_IMAGENES");	
	}
	
	private List <Dominio> dominios;
    public List<Dominio> getDominios() {
		return dominios;
	}

	public void setDominios(List<Dominio> dominios) {
		this.dominios = dominios;
	}

	public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        
        for(int i=0;i<dominios.size();i++)
        {
        	if(f.getName().toLowerCase().endsWith(dominios.get(i).getDomCodigo().toLowerCase()))
        		return true;
        }
        
       return false;
    }
    
    public String getDescription() {
        return "Solo imágenes";
    }
}