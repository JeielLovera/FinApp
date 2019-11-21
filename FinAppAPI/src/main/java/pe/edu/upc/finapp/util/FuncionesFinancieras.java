package pe.edu.upc.finapp.util;

import pe.edu.upc.finapp.model.entity.Factoring;

public class FuncionesFinancieras {

	public static double Conversion_Efectiva_A_Efectiva(int numtd, int numfrecuenciatasaoriginal, double ptasaoriginal) {
		double nueva_tasa;
		nueva_tasa=(Math.pow((1+(ptasaoriginal/100)), (numtd/numfrecuenciatasaoriginal))-1)*100;
		return nueva_tasa;
	}
	
	public static double Conversion_Nominal_A_Efectiva(int numtd, int numfrecuenciatasaoriginal, double ptasaoriginal, int numfrecuenciacapitalizacion) {
		double nueva_tasa;
		int m,n;
		m=numfrecuenciatasaoriginal/numfrecuenciacapitalizacion;
		n=numtd/numfrecuenciacapitalizacion;
		nueva_tasa=(Math.pow((1+(ptasaoriginal/(m*100))), n)-1)*100;
		return nueva_tasa;
	}
	
	public static double Calcular_Porcentaje_De_Descuento(double ptetd) {
		double pdescuento;
		return 0;//pdescuento=
	}
	
	public static void Calculo_Factoring(Factoring fct) {
		
		int numtd;
		double ptetd;
		double pdescuento;
		numtd=(int)((fct.getCfactura().getDvencimiento().getTime()-fct.getCfactura().getDfirma().getTime())/86400000);
		
		//CALCULOS INTERMEDIOS
		if(fct.getCtipointeres().getCtipointeres()==1) {
			ptetd=Conversion_Efectiva_A_Efectiva(numtd, fct.getNumfrecuenciatasaoriginal(), fct.getPtasaoriginal());
		}
		else {
			ptetd=Conversion_Nominal_A_Efectiva(numtd, fct.getNumfrecuenciatasaoriginal(), fct.getPtasaoriginal(), fct.getNumfrecuenciacapitalizacion());
		}
		
		
	}

}
