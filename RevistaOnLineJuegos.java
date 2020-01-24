import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * La clase representa a una tienda on-line en la
 * que se publican los juegos que se van lanzando al mercado
 * 
 * Un objeto de esta clase guarda en un array los juegos 
 *
 * @author Oskar Contreras Junguitu
 */
public class RevistaOnLineJuegos 
{
    private String nombre;
    private Juego[] juegos;
    private int total;

    /**
     * Constructor  
     * Crea el array de juegos al tamaño máximo indicado por la constante
     * e inicializa el resto de atributos
     */
    public RevistaOnLineJuegos(String nombre, int n) {
        this.nombre = nombre;
        juegos = new Juego[n];
        int total = 0;
    }

    /**
     * Devuelve true si el array está completo, false en otro caso
     */
    public boolean estaCompleta() {
        return juegos.length == total;
    }

    /**
     *    Añade un nuevo juego solo si el array no está completo y no existe otro juego
     *    ya con el mismo nombre.  Si no se puede añadir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El juego se añade de tal forma que queda insertado en orden alfabético de título
     *    (de menor a mayor)
     *     !!OJO!! No hay que ordenar ni utilizar ningún algoritmo de ordenación
     *    Hay que insertar en orden 
     *    
     */
    public void add(Juego juego) {
        if(estaCompleta()){
            System.out.println("No se a podido añadir el juego, la lista esta llena.");
        }
        else if(existeJuego(juego.getTitulo()) <= total){
            System.out.println("El juego ya esta añadido a la lista");
        }
        else{
             juegos[total] = juego;
            total++;
        }
    }


    /**
     * Efectúa una búsqueda en el array del juego cuyo titulo se
     * recibe como parámetro. Es ndiferente mayúsculas y minúsculas
     * Si existe el juego devuelve su posición, si no existe devuelve -1
     */
    public int existeJuego(String titulo) {
        for(int i = 0; i < total; i++){
            if(titulo.equalsIgnoreCase(juegos[i].getTitulo())){
                return i;
            }

        }
        return -1;
    }

    // /**
     // * Representación textual de la revista
     // * Utiliza StringBuilder como clase de apoyo.
     // * Se incluye el nombre de la  revista on-line.
     // * (Ver resultados de ejecución)
     // */
    // public String toString() {

        // return "";
    // }

    /**
     *  Se puntúa el juego de título indicado con 
     *  la puntuación recibida como parámetro. 
     *  La puntuación es un valor entre 1 y 10 (asumimos esto como correcto)
     *  Si el juego no existe se muestra un mensaje en pantalla
     */
    public void puntuar(String titulo, int puntuacion) {

        int posicion = existeJuego(titulo);
        if(existeJuego(titulo) == -1){
            System.out.println("El juego no existe.");
        }
        else{
            juegos[posicion].puntuar(puntuacion);
        }
    }

    /**
     * Devuelve un array con los nombres de los juegos 
     * con una valoración media mayor a la indicada  
     * 
     * El array se devuelve todo en mayúsculas y ordenado ascendentemente
     */
    public String[] valoracionMayorQue(double valoracion) {
        int media = 0;
        for (int i = 0; i < total; i++) {
            if (juegos[i].getValoracionMedia() > valoracion) {
                media++;
            }
        }
        String[] array = new String[media];
        int pos = 0;
        for (int i = 0; i < total; i++) {
            if (juegos[i].getValoracionMedia() > valoracion) {
                array[pos] = juegos[i].getTitulo();
                pos++;
            }
        }
        return array;
    }

    /**
     * Borrar los juegos del género indicado devolviendo
     * el nº de juegos borradas
     */
    public int borrarDeGenero(Genero genero) {
        int borrados = 0;
        for(int i = 0; i < total; i++){
            if(genero == juegos[i].getGenero()){
                juegos[i - 1] = juegos[i];
                borrados++;
            }

        }
        return borrados;
    }

    /**
     * Lee de un fichero de texto los datos de los juegos
     * con ayuda de un objeto de la  clase Scanner
     * y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("juegos.txt"));
            while (sc.hasNextLine()) {
                Juego juego = new Juego(sc.nextLine());
                this.add(juego);

            }

        } catch (IOException e) {
            System.out.println("Error al leer del fichero");
        } finally {
            sc.close();
        }

    }

}
