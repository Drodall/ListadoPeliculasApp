package servicio;

import dominio.Pelicula;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas{

    private final List<Pelicula> peliculas;
    public ServicioPeliculasLista(){
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Listado de peliculas...");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se agrego la pelicula: " + pelicula);
    }

    @Override
    public void buscarPeliculas(Pelicula pelicula) {
        // Regresa el indice de la pelicula encontrada en la lista
        int indice = peliculas.indexOf(pelicula);
        if(indice == -1)
            System.out.println("No se encontro la pelicula buscada: " + pelicula);
        else
        System.out.println("Pelicula encontrada en el indice: " + indice);
    }

    public static void main(String[] args) {
        var pelicula1 = new Pelicula("Akira");
        var pelicula2 = new Pelicula("Saint Seiya La batalla de los dioses");
        //Creando el servicio
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        //Agregar peliculas a la lista
        servicioPeliculas.agregarPelicula(pelicula1);
        servicioPeliculas.agregarPelicula(pelicula2);
        //Listar peliculas
        servicioPeliculas.listarPeliculas();
        //Buscar una pelicula
        servicioPeliculas.buscarPeliculas(new Pelicula("Saint Seiya La batalla de los dioses"));

    }
}
