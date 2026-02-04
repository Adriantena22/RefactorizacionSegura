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

	public static void main(String[] args) throws Exception{
		Double totalFactura = 0d;
		Integer creditos = 0;

		System.out.println("FACTURA DE ACTUACIONES");
		System.out.println("Cliente: " + cliente);

		for(int i = 0; i < actuaciones.length; i++){
			Integer iConcierto = actuaciones[i][0];
			Double importeActuacion = 0d;

			switch (repertorio[iConcierto][1]){
				case "heavy":
					importeActuacion = 4000d;
					if (actuaciones[i][1] > 500)
						importeActuacion += 20 * (actuaciones[i][1] - 500);
					break;
				case "rock":
					importeActuacion = 3000d;
					if (actuaciones[i][1] > 1000)
						importeActuacion += 30 * (actuaciones[i][1] - 1000);
					break;
				default:
					throw new Exception("Tipo de concierto desconocido.");
			}
			
			totalFactura += importeActuacion;

			creditos += Math.max(actuaciones[i][1] - 500, 0);
			if (repertorio[iConcierto][1].equals("heavy"))
				creditos += actuaciones[i][1] / 5;

			System.out.println("\tConcierto: " + repertorio[iConcierto][0]);
			System.out.println("\t\tAsistentes: " + actuaciones[i][1]);
		}
		System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
		System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * 0.21);
		System.out.printf("TOTAL: %.2f euros\n", totalFactura * 1.21);
		System.out.println("Créditos obtenidos: " + creditos);

	}
}
