import java.util.*;
public class Facturador{

	//Repertorio de conciertos del grupo
	static String[][] repertorio = {
		 {"Tributo Robe", "heavy"}
		,{"Homaneje Queen", "rock"}
		,{"Magia Knoppler", "rock"}
		,{"Demonios Rojos", "heavy"}
	};

	//Actuaciones realizadas indicando el concierto ofrecido y audiencias obtenidas.
	static Integer[][] actuaciones = {{0, 2000}, {2, 1200}, {0, 950}, {3, 1140}};

	static String cliente = "Ayuntamiento de Badajoz";

		public static void main(String[] args) {
		Double totalFactura = 0d;
		Integer creditos = 0;

		System.out.println("FACTURA DE ACTUACIONES");
		System.out.println("Cliente: " + cliente);

		for(int i = 0; i < actuaciones.length; i++){
			Integer iConcierto = actuaciones[i][0];
			Integer asistentes = actuaciones[i][1];  
			String tipoConcierto = repertorio[iConcierto][1];  
			
			Double importeActuacion = calcularImporteActuacion(tipoConcierto, asistentes);
			totalFactura += importeActuacion;

			creditos += calcularCreditos(tipoConcierto, asistentes);

			System.out.println("\tConcierto: " + repertorio[iConcierto][0]);
			System.out.println("\t\tAsistentes: " + asistentes);
		}
		
		imprimirFactura(totalFactura, creditos);
	}
		private static Double calcularImporteActuacion(String tipoConcierto, Integer asistentes) {
		Double importeActuacion = 0d;
		
		switch (tipoConcierto){
			case "heavy":
				importeActuacion = 4000d;
				if (asistentes > 500)
					importeActuacion += 20 * (asistentes - 500);
				break;
			case "rock":
				importeActuacion = 3000d;
				if (asistentes > 1000)
					importeActuacion += 30 * (asistentes - 1000);
				break;
			default:
				throw new RuntimeException("Tipo de concierto desconocido.");
		}
		
		return importeActuacion;
	}
		private static Integer calcularCreditos(String tipoConcierto, Integer asistentes) {
		Integer creditos = Math.max(asistentes - 500, 0);
		if (tipoConcierto.equals("heavy"))
			creditos += asistentes / 5;
		return creditos;
	}
		private static void imprimirFactura(Double totalFactura, Integer creditos) {
		System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
		System.out.printf(Locale.US, "IVA (21%%): %.2f euros\n", totalFactura * 0.21);
		System.out.printf(Locale.US, "TOTAL: %.2f euros\n", totalFactura * 1.21);
		System.out.println("Créditos obtenidos: " + creditos);
	}
}
