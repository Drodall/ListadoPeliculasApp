package servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas {

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo(){
         var archivo = new File(NOMBRE_ARCHIVO);
         try {
             // Si el archivo existe, NO se vuelve a crear
             if (archivo.exists()){
                 System.out.println("El archivo si existe!");
             }
             else{
                 //Si no existe se crea vacio
                 var salida = new PrintWriter(new FileWriter(archivo));
                 salida.close();
                 System.out.println("Se ha creado el archivo");
             }
         }catch (Exception e){
             System.out.println("Error al abrir el archivo: " + e.getMessage());
         }

    }

    @Override
    public void listarPeliculas() {
       // Se vuelve abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("Listado de Peliculas");
            // Se abre archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            // Leemos linea a linea el archivo
            String linea;
            linea = entrada.readLine();
            // Leemos todas las lineas
            while (linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                // Se lee la siguiente linea antes de terminar el ciclo
                linea = entrada.readLine();
            }//fin de while
            entrada.close();
        } catch (Exception e){
            System.out.println("Error a leer el archivo: " + e.getMessage());
        }



    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            // Revisar si existe el archivo
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo , anexar));
            // Se agrega la pelicula (ToString)
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego al archivo la pelicula: " + pelicula);
        } catch(Exception e){
            System.out.println("Error al agregar la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void buscarPeliculas(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            // Se abre el archivo para la lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            int indice = 1;
            boolean encontrada = false;
            var peliculaBuscar = pelicula.getNombre();
            while (lineaTexto != null){
                // Se busca la pelicula sin importar mayusculas/minusculas
                if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                   encontrada = true;
                   break;
                }
                // Leemos la siguiente linea antes de la siguiente iteracion
                lineaTexto = entrada.readLine();
                indice++;
            } //fin while
            //Se imprime los resultados de la busqueda
            if (encontrada)
                System.out.println("Pelicula " + lineaTexto
                        + " encontrada - linea " + indice);
            else
                System.out.println("No se encontro la pelicula: "
                        + pelicula.getNombre());
            entrada.close();
        } catch (Exception e){
            System.out.println("Error al buscar la pelicula: "
                    + e.getMessage());
        }

    }
}
