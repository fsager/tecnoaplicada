package ar.com.trimix.common.codegen;

public class ClassCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			boolean doPersistence=true;
			boolean doService=true;
			boolean showSpring=true;
			
			String domain="testerGeneral";
			if (doPersistence)
			{
				HibernateGenerator.makeClass(domain, "PersonaExamen");
				HibernateGenerator.makeClass(domain, "ResultadoDetalleExamen");
				HibernateGenerator.makeClass(domain, "Resultado");
			}
			if (doService)
			{
				BizAndServiceGenerator.makeClass(domain, "PersonaExamen");
				BizAndServiceGenerator.makeClass(domain, "ResultadoDetalleExamen");
				BizAndServiceGenerator.makeClass(domain, "Resultado");
			}

			if (showSpring)
			{
				SpringXmlGenerator sxg=new SpringXmlGenerator();
				
				sxg.makeClass(domain, "PersonaExamen");
				sxg.makeClass(domain, "ResultadoDetalleExamen");
				sxg.makeClass(domain, "Resultado");
				
				sxg.showClass(System.out);
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
