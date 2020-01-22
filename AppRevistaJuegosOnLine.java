

import java.util.Arrays;

/**
 * Punto de entrada a la aplicación
 * 
 * @author - 
 */
public class AppRevistaJuegosOnLine 
{
    public static void main(String[] args)
    {
        if(args.length != 2){
            System.out.println("Error en argumentos" + 
                "\nSintaxis: java AppRevistaJuegosOnLine <nombre> <n>");
        }else{
            String nombre = args[0];
            int total = Integer.parseInt(args[1]);
            RevistaOnLineJuegos revista = new RevistaOnLineJuegos(nombre,total);

            revista.leerDeFichero();
            System.out.println(revista.toString());
            System.out.println("***************************************");
            System.out.println("Puntuando...");
            revista.puntuar("Planet Zoo", 8);
            revista.puntuar("Steep", 7);
            revista.puntuar("Catastronauts", 9);
            revista.puntuar("Wattam", 9);
            System.out.println("Tras puntuar la revista queda\n");
            System.out.println(revista.toString());
            System.out.println("***************************************");
            System.out.println("Los juegos con valoracion superior a 8.2 son: ");

            String[] valoracionSuperiorA = revista.valoracionMayorQue(8.2);
            System.out.println(Arrays.toString(valoracionSuperiorA));

            System.out.println("***************************************");
            System.out.println("Borrando juegos de genero ROL... ");
            System.out.println("Borrados " + revista.borrarDeGenero(Genero.ROL) + "juegos.");

            System.out.println(revista.toString());

            
        }
    }
}