import java.util.Locale;

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

	private static final double BASE_HEAVY = 4000d;
	private static final double BASE_ROCK = 3000d;
	private static final int UMBRAL_HEAVY = 500;
	private static final int UMBRAL_ROCK = 1000;
	private static final double EXTRA_HEAVY = 20d;
	private static final double EXTRA_ROCK = 30d;
	private static final double IVA = 0.21;

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
				importeActuacion = BASE_HEAVY;
				if (asistentes > UMBRAL_HEAVY)
					importeActuacion += EXTRA_HEAVY * (asistentes - UMBRAL_HEAVY);
				break;
			case "rock":
				importeActuacion = BASE_ROCK;
				if (asistentes > UMBRAL_ROCK)
					importeActuacion += EXTRA_ROCK * (asistentes - UMBRAL_ROCK);
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
		System.out.printf(Locale.US, "IVA (21%%): %.2f euros\n", totalFactura * IVA);
		System.out.printf(Locale.US, "TOTAL: %.2f euros\n", totalFactura * (1 + IVA));
		System.out.println("Créditos obtenidos: " + creditos);
	}
}