package joao.io.projetoFoco.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

public class testeReport {

	public static void main(String[] args) throws JRException
	{
		
		Date d = new Date();
        
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("en", "US"));
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
        
        System.out.println("en: " + df1.format(d));
        System.out.println("pt: " + df2.format(d));
        
		/*
		 * //Obtem o valor atual do sistema long inicioContagem =
		 * System.currentTimeMillis();
		 * 
		 * //Compilacao no formato jasper para o jrprint
		 * JasperFillManager.fillReportToFile("reports/relatorioTeste.jasper", null, new
		 * JREmptyDataSource(1));
		 * System.err.println("Tempo de compilacao jasper -> jrprint: " +
		 * (System.currentTimeMillis() - inicioContagem));
		 * 
		 * //Reinicia o contador inicioContagem = System.currentTimeMillis();
		 * 
		 * //Geracao do PDF
		 * JasperExportManager.exportReportToPdfFile("reports/relatorioTeste.jrprint");
		 * System.err.println("Tempo de geracao do PDF: " + (System.currentTimeMillis()
		 * - inicioContagem));
		 */
	}
}
