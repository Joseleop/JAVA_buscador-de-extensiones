package es.estudium.practicatema01;

import java.io.File;
import java.util.ArrayList;

public class Modelo {
    public Modelo() {}

    public ArrayList<String> buscarEnDiscos(String extension){
        ArrayList<String> resultados = new ArrayList<>();
        File[] discos = File.listRoots();
        for (File disco : discos) {
            buscarFicheros(disco, extension, resultados);
        }
        return resultados;
    }
    private void buscarFicheros(File directorio, String extension, ArrayList<String> resultados) {
        File[] contenido = directorio.listFiles();
        if (contenido != null) {
            for (File fichero : contenido) {
                if (fichero.isFile() && fichero.getName().toLowerCase().endsWith(extension)){
                    resultados.add(fichero.getAbsolutePath());
                }
                else if (fichero.isDirectory()) {
                    buscarFicheros(fichero, extension, resultados);
                }
            }
        }
    }
}

