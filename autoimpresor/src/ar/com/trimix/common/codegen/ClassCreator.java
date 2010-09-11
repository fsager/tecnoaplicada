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
			
			String domain="autoimpresor";
			if (doPersistence)
			{
				HibernateGenerator.makeClass(domain, "CarnetLicencias");
				/*HibernateGenerator.makeClass(domain, "Caja");
				HibernateGenerator.makeClass(domain, "ClaseLicencia");				
				HibernateGenerator.makeClass(domain, "Licencia");
				HibernateGenerator.makeClass(domain, "Persona");
				HibernateGenerator.makeClass(domain, "Usuario");*/
			}
			if (doService)
			{
				BizAndServiceGenerator.makeClass(domain, "CarnetLicencias");
				/*BizAndServiceGenerator.makeClass(domain, "Caja");
				BizAndServiceGenerator.makeClass(domain, "ClaseLicencia");
				BizAndServiceGenerator.makeClass(domain, "Licencia");
				BizAndServiceGenerator.makeClass(domain, "Persona");
				BizAndServiceGenerator.makeClass(domain, "Usuario");*/
			}

			if (showSpring)
			{
				SpringXmlGenerator sxg=new SpringXmlGenerator();
				
				sxg.makeClass(domain, "CarnetLicencias");
				/*sxg.makeClass(domain, "Caja");
				sxg.makeClass(domain, "ClaseLicencia");
				sxg.makeClass(domain, "Licencia");
				sxg.makeClass(domain, "Persona");
				sxg.makeClass(domain, "Usuario");*/
				
				sxg.showClass(System.out);
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
