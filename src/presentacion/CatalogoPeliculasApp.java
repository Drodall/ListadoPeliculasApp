package presentacion;

import dominio.Pelicula;
import servicio.IServicioPeliculas;
import servicio.ServicioPeliculasArchivo;
import servicio.ServicioPeliculasLista;

import java.util.Scanner;

public class CatalogoPeliculasApp {
    public static void main(String[] args) {
        boolean salir = false;
        var input = new Scanner(System.in);
        // Agregamos la implementacion del servicio
        //IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasArchivo();
        while(!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(input , servicioPeliculas);
            } catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
            System.out.println();
        }//fin while
    }

    private static void mostrarMenu(){
        System.out.print("""
                *** Catalogo de Peliculas ***
                Seleccione la opcion
                1. Agregar pelicula
                2. Listar pelicula
                3. Buscar pelicula
                4. Salir
                """);
    }
    private static boolean ejecutarOpciones(Scanner input,
                                            IServicioPeliculas servicioPeliculas){
        int opcion = Integer.parseInt(input.nextLine());
        boolean salir = false;
        switch (opcion){
            case 1:
                System.out.println("Ingrese por favor el nombre de la pelicula: ");
                var nombrePelicula = input.nextLine();
                servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
                break;
            case 2:
                servicioPeliculas.listarPeliculas();
                break;
            case 3:
                System.out.println("Ingrese por favor la pelicula a buscar: ");
                var buscar = input.nextLine();
                servicioPeliculas.buscarPeliculas(new Pelicula(buscar));
                break;
            case 4:
                System.out.println("Adios!...");
                salir = true;
                break;

            default:
                System.out.println("Opcion incorrecta: " + opcion);

        }
        return salir;
    }
}
