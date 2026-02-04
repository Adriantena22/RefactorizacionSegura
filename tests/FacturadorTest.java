import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FacturadorTest {

   @Test
   @DisplayName("Test constructor valido")
   public void testConstructorValido() {
      Facturador facturador = new Facturador();
      assertNotNull(facturador, "El constructor devuelve null.");
   }

   @Test
   @DisplayName("Test cabecera correcta")
   public void testCabeceraCorrecta() {
      PrintStream salidaConsola = System.out;
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream salidaTest = new PrintStream(baos);
      System.setOut(salidaTest);
      try {
         Facturador.main(null);
      } catch(Exception e) {
         fail("Excepcion inesperada.");
      }
      System.setOut(salidaConsola);
      assertTrue(baos.toString().contains("FACTURA DE ACTUACIONES"), "La salida no contiene la cabecera esperada.");
   }

 @Test
@DisplayName("Test de regresión de salida del Facturador")
public void testRegresionSalidaFacturador() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));
    
    try {
        Facturador.main(new String[]{});
        String salida = outputStream.toString();
        
        // Verificar con diferentes formatos decimales
        boolean contieneBase = salida.contains("72800.0") || salida.contains("72800,0");
        boolean contieneIVA = salida.contains("15288.00") || salida.contains("15288,00");
        boolean contieneTotal = salida.contains("88088.00") || salida.contains("88088,00");
        boolean contieneCreditos = salida.contains("4108");
        
        assertTrue(contieneBase, 
            "La salida debería contener '72800.0' o '72800,0': \n" + salida);
        assertTrue(contieneIVA, 
            "La salida debería contener '15288.00' o '15288,00': \n" + salida);
        assertTrue(contieneTotal, 
            "La salida debería contener '88088.00' o '88088,00': \n" + salida);
        assertTrue(contieneCreditos, 
            "La salida debería contener '4108': \n" + salida);
            
    } catch(Exception e) {
        fail("Excepción inesperada: " + e.getMessage());
    } finally {
        System.setOut(originalOut);
    }
}
}