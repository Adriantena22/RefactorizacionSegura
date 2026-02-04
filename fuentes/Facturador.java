import java.util.Locale;

public class Facturador{
	
	enum TipoConcierto {
		HEAVY("heavy"),
		ROCK("rock");
		
		private final String codigo;
		
		TipoConcierto(String codigo) {
			this.codigo = codigo;
		}
		
		public String getCodigo() {
			return codigo;
		}
		
		public static TipoConcierto fromString(String codigo) {
			for (TipoConcierto tipo : values()) {
				if (tipo.codigo.equals(codigo)) {
					return tipo;
				}
			}
			throw new IllegalArgumentException("Tipo de concierto desconocido: " + codigo);
		}
	}

	// Catálogo de conciertos disponibles
	static String[][] conciertos = {
		 {"Tributo Robe", TipoConcierto.HEAVY.getCodigo()}
		,{"Homaneje Queen", TipoConcierto.ROCK.getCodigo()}
		,{"Magia Knoppler", TipoConcierto.ROCK.getCodigo()}
		,{"Demonios Rojos", TipoConcierto.HEAVY.getCodigo()}
	};

	// Actuaciones realizadas en el período facturado
	static Integer[][] actuacionesRealizadas = {{0, 2000}, {2, 1200}, {0, 950}, {3, 1140}};

	static String cliente = "Ayuntamiento de Badajoz";

	// CONSTANTES - REFACTOR 2
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

		for(int indiceActuacion = 0; indiceActuacion < actuacionesRealizadas.length; indiceActuacion++){
			Integer indiceConcierto = actuacionesRealizadas[indiceActuacion][0];
			Integer asistentes = actuacionesRealizadas[indiceActuacion][1];  
			String codigoTipoConcierto = conciertos[indiceConcierto][1];  
			TipoConcierto tipoConcierto = TipoConcierto.fromString(codigoTipoConcierto);
			
			Double importeActuacion = calcularImporteActuacion(tipoConcierto, asistentes);
			totalFactura += importeActuacion;

			creditos += calcularCreditos(tipoConcierto, asistentes);

			System.out.println("\tConcierto: " + conciertos[indiceConcierto][0]);
			System.out.println("\t\tAsistentes: " + asistentes);
		}
		
		imprimirFactura(totalFactura, creditos);
	}

	private static Double calcularImporteActuacion(TipoConcierto tipoConcierto, Integer asistentes) {
		Double importeActuacion = 0d;
		
		switch (tipoConcierto){
			case HEAVY:
				importeActuacion = BASE_HEAVY;
				if (asistentes > UMBRAL_HEAVY)
					importeActuacion += EXTRA_HEAVY * (asistentes - UMBRAL_HEAVY);
				break;
			case ROCK:
				importeActuacion = BASE_ROCK;
				if (asistentes > UMBRAL_ROCK)
					importeActuacion += EXTRA_ROCK * (asistentes - UMBRAL_ROCK);
				break;
		}
		
		return importeActuacion;
	}

	private static Integer calcularCreditos(TipoConcierto tipoConcierto, Integer asistentes) {
		Integer creditos = Math.max(asistentes - 500, 0);
		if (tipoConcierto == TipoConcierto.HEAVY)
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